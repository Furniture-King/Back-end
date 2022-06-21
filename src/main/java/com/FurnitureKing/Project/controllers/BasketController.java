package com.FurnitureKing.Project.controllers;

import com.FurnitureKing.Project.models.Basket;
import com.FurnitureKing.Project.models.BasketTab;
import com.FurnitureKing.Project.repositories.BasketRepository;
import com.FurnitureKing.Project.utils.CurrentDateTime;
import org.bson.types.ObjectId;
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
    public ResponseEntity<Optional<Basket>> getBasket(@PathVariable final ObjectId basketId) {
        Optional<Basket> basket = basketRepository.findById(basketId);
        if (basket.isPresent()) {
            return ResponseEntity.ok(basket);
        }
        return ResponseEntity.notFound().build();
    }

    /* Search 1 basket by client */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/basket/{clientId}")
    public ResponseEntity<Optional<Basket>> getBasketByClientId(@PathVariable final ObjectId clientId) {
        Optional<Basket> basket = basketRepository.getBasketByClientAndId(clientId);
        if (basket.isPresent()) {
            return ResponseEntity.ok(basket);
        }
        return ResponseEntity.notFound().build();
    }

    /* Create basket */
    @PostMapping(value = "/basket/post")
    public ResponseEntity<String> addBasket(@RequestBody Basket basket) {
        basket.setCreatedAt(CurrentDateTime.getCurrentDateTime());
        basketRepository.insert(basket);
        return ResponseEntity.ok().body("The basket is created");
    }

    /* Delete 1 basket */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/baskets/delete/{basketsId}")
    public ResponseEntity<String> deleteBasket(@PathVariable final ObjectId basketId) {
        Optional<Basket> basket = basketRepository.findById(basketId);
        if (basket.isPresent()) {
            basketRepository.deleteById(basketId);
            return ResponseEntity.ok().body("basket deleted");
        }
        return ResponseEntity.notFound().build();
    }

    /* update 1 basket */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/baskets/put/client/{clientId}")
    public ResponseEntity<String> updateBasket(@PathVariable final ObjectId clientId, @RequestBody BasketTab basketTab) {
        Optional<Basket> bddBasket = basketRepository.getBasketByClient_Id(clientId);
        List<BasketTab> bddBasketTab = bddBasket.get().getBasketTab();
        if (bddBasketTab == null) {
           List<BasketTab> newBasketTab = new ArrayList<BasketTab>();
           newBasketTab.add(basketTab);
           bddBasket.get().setBasketTab(newBasketTab);
        }else{
            final Boolean[] Present = {false};
           bddBasketTab.forEach(bT ->{
                   if(bT.getProductId().toString().equals(basketTab.getProductId().toString())){
                       Present[0] = true;
                       bT.setQté(bT.getQté() + basketTab.getQté());
                   }
           });
           if(!Present[0]){
               bddBasket.get().getBasketTab().add(basketTab);
           }
        }
        bddBasket.ifPresent(basketRepository::save);
        return ResponseEntity.ok("ok");
    }
}
