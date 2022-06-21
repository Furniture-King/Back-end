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
        System.out.println("dans la requete");
        Optional<Basket> basket = basketRepository.getBasketByClient_Id(clientId);
        System.out.println(basket.get().getClient().getFirstName());

        List<BasketTab> bddBasket = basket.get().getBasketTab();

        if(basket.get().getBasketTab() == null){
            List<BasketTab> newBasketTab = new ArrayList<BasketTab>();
            newBasketTab.add(basketTab);
            basket.get().setBasketTab(newBasketTab);
        }else{
            Boolean flag = false;
            //voir si foreach fonctionne
            if (!bddBasket.stream().anyMatch(bt -> bt.getProductId() == basketTab.getProductId())) {
                basket.get().getBasketTab().add(basketTab);
            } else {
                List<BasketTab> listeBasket = new ArrayList<BasketTab>();
                basket.get().getBasketTab().forEach(bt -> {
                    if (bt.getProductId() == basketTab.getProductId()) {
                        bt.setQté(bt.getQté() + basketTab.getQté());
                    }
                    listeBasket.add(bt);
                });
                basket.get().setBasketTab(listeBasket);
            }
        }


        basket.ifPresent(basketRepository::save);
        return ResponseEntity.ok("ok");
    }

}
