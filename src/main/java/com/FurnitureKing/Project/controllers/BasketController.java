package com.FurnitureKing.Project.controllers;

import com.FurnitureKing.Project.models.Basket;
import com.FurnitureKing.Project.models.BasketTab;
import com.FurnitureKing.Project.payload.response.MessageResponse;
import com.FurnitureKing.Project.repositories.BasketRepository;
import com.FurnitureKing.Project.utils.CurrentDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class BasketController {

    private final BasketRepository basketRepository;

    public BasketController(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    /* Get all baskets */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/basket")
    public ResponseEntity<List<Basket>> getBaskets() {
        List<Basket> basketList = basketRepository.findAll();
        return ResponseEntity.ok(basketList);
    }

    /* Search 1 basket by Id */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/basket/id/{basketId}")
    public ResponseEntity<Optional<Basket>> getBasket(@PathVariable final String basketId) {
        Optional<Basket> basket = basketRepository.findById(basketId);
        if (basket.isPresent()) {
            return ResponseEntity.ok(basket);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    /* Search 1 basket by client */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/basket/client/{clientId}")
    public ResponseEntity<Optional<Basket>> getBasketByClientId(@PathVariable final String clientId) {
        Optional<Basket> basket = basketRepository.getBasketByClient_Id(clientId);
        if (basket.isPresent()) {
            return ResponseEntity.ok(basket);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    /* Search nb of items in basket */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/basket/count/client/{clientId}")
    public ResponseEntity<?> getBasketCountByClientId(@PathVariable final String clientId) {
        Optional<Basket> basket = basketRepository.getBasketByClient_Id(clientId);
        AtomicReference<Integer> count = new AtomicReference<>(0);
        if (basket.isPresent() && basket.get().getBasketTab().stream().count() > 0) {
            basket.get().getBasketTab().forEach(bT ->{
                count.updateAndGet(v -> v + bT.getQté());
            });
        }
        return ResponseEntity.ok(count);
    }


    /* Create basket */
    @PostMapping(value = "/basket/post")
    public ResponseEntity<MessageResponse> addBasket(@RequestBody Basket basket) {
        basket.setCreatedAt(CurrentDateTime.getCurrentDateTime());
        basketRepository.insert(basket);
        return ResponseEntity.ok().body(new MessageResponse("The basket is created"));
    }

    /* Delete 1 product of basket*/
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/baskets/delete/product/{productId}/client/{clientId}")
    public ResponseEntity<?> deleteBasket(@PathVariable final String productId ,@PathVariable final String clientId ) {

        Optional<Basket> Basket = basketRepository.getBasketByClient_Id(clientId);

        if (Basket.isPresent()) {
            List<BasketTab> BasketTab = Basket.get().getBasketTab();
            if(BasketTab == null){
                return ResponseEntity.notFound().build();
            }else{
                // il y a des produits
                List<BasketTab> newBasketTab = new ArrayList<>();

                BasketTab.forEach(bT ->{
                    // pour chaque produits
                    if (!bT.getProduct().getId().equals(productId)){
                        newBasketTab.add(bT);
                    }
                });
                Basket.get().setBasketTab(newBasketTab);
            }


            AtomicReference<Double> basketTotalPrice = new AtomicReference<>((double) 0);
            Basket.get().getBasketTab().forEach(bT -> {
                basketTotalPrice.updateAndGet(v -> v + bT.getPriceProduct());
            });
            Basket.get().setBasketTotalPrice(basketTotalPrice.get());

            Basket.get().setUpdatedAt(CurrentDateTime.getCurrentDateTime());
            Basket.ifPresent(basketRepository::save);
            return ResponseEntity.ok().body(new MessageResponse("The basket is deleted"));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    /* update 1 basket */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/baskets/put/client/{clientId}")
    public ResponseEntity<MessageResponse> updateBasket(@PathVariable final String clientId, @RequestBody BasketTab basketTab) {

        Optional<Basket> Basket = basketRepository.getBasketByClient_Id(clientId);
        if(Basket.isPresent()) {
            List<BasketTab> BasketTab = Basket.get().getBasketTab();
            if (BasketTab == null) {
                List<BasketTab> newBasketTab = new ArrayList<>();
                newBasketTab.add(basketTab);
                newBasketTab.get(0).setPriceProduct(newBasketTab.get(0).getProduct().getPrice() * newBasketTab.get(0).getQté());
                Basket.get().setBasketTab(newBasketTab);
            } else {
                final Boolean[] Present = {false};
                BasketTab.forEach(bT -> {
                    if (bT.getProduct().getId().equals(basketTab.getProduct().getId())) {
                        Present[0] = true;
                        if (basketTab.getQté() == null) {
                            bT.setQté(1);
                        } else {
                            bT.setQté(basketTab.getQté());
                        }
                        bT.setPriceProduct(bT.getProduct().getPrice() * bT.getQté());
                    }
                });
                if (!Present[0]) {
                    Basket.get().getBasketTab().add(basketTab);
                    Basket.get().getBasketTab().forEach(bT -> {
                        bT.setPriceProduct(bT.getProduct().getPrice() * bT.getQté());
                    });
                }
            }
            AtomicReference<Double> basketTotalPrice = new AtomicReference<>((double) 0);
            Basket.get().getBasketTab().forEach(bT -> {
                basketTotalPrice.updateAndGet(v -> v + bT.getPriceProduct());
            });
            Basket.get().setBasketTotalPrice(basketTotalPrice.get());
        }else{
            return ResponseEntity.ok().body(new MessageResponse("Error : Basket not found !"));
        }

        Basket.get().setUpdatedAt(CurrentDateTime.getCurrentDateTime());
        Basket.ifPresent(basketRepository::save);
        return ResponseEntity.ok().body(new MessageResponse("Basket updated successfully !"));
    }
}
