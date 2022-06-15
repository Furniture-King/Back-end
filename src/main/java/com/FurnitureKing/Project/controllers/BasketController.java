package com.FurnitureKing.Project.controllers;

import com.FurnitureKing.Project.models.BankCard;
import com.FurnitureKing.Project.models.Basket;
import com.FurnitureKing.Project.models.Client;
import com.FurnitureKing.Project.repositories.BasketRepository;
import com.FurnitureKing.Project.utils.CurrentDateTime;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BasketController {

    private final BasketRepository basketRepository;

    public BasketController(BasketRepository basketRepository) {this.basketRepository = basketRepository;}

    /* Get all baskets */
    @GetMapping("/basket")
    public ResponseEntity<List<Basket>> getBaskets() {
        List<Basket> basketList = basketRepository.findAll();
        return ResponseEntity.ok(basketList);
    }

    /* Search 1 basket by Id */
    @GetMapping("/basket/id/{basketId}")
    public ResponseEntity<Optional<Basket>> getBasket(@PathVariable final ObjectId basketId) {
        Optional<Basket> basket = basketRepository.findById(basketId);
        if(basket.isPresent()){
            return ResponseEntity.ok(basket);
        }
        return ResponseEntity.notFound().build();
    }

    /* Search 1 basket by client */
    @GetMapping("/basket/{clientId}")
    public ResponseEntity<Optional<Basket>> getBasketByClientId(@PathVariable final ObjectId clientId) {
        Optional<Basket> basket = basketRepository.getBasketByClientAndId(clientId);
        if(basket.isPresent()){
            return ResponseEntity.ok(basket);
        }
        return ResponseEntity.notFound().build();
    }

    /* Create basket */
    @PostMapping(value = "/basket/post")
    public ResponseEntity<String> addBasket(@RequestBody Basket basket){
        basket.setCreatedAt(CurrentDateTime.getCurrentDateTime());
        basketRepository.insert(basket);
        return ResponseEntity.ok().body("The basket is created");
    }

    /* Delete 1 basket */
    @DeleteMapping("/baskets/delete/{basketsId}")
    public ResponseEntity<String> deleteClient(@PathVariable final ObjectId basketId) {
        Optional<Basket> basket = basketRepository.findById(basketId);
        if(basket.isPresent()){
            basketRepository.deleteById(basketId);
            return ResponseEntity.ok().body("basket deleted");
        }
        return ResponseEntity.notFound().build();
    }

    @DBRef
    private Client client;

    @Field
    private List<String> products;

    /* Update 1 bankCard */
    @PutMapping("/bankCard/put/{bankCardId}")
    public ResponseEntity<Optional<BankCard>> updateProduct(@PathVariable final ObjectId basketId, @RequestBody Basket basketUpdate) {
        Optional<Basket> basket = basketRepository.findById(basketId);
        if(!basket.isPresent()){
            addBasket(basketUpdate);
        }
        else{
            basket.ifPresent(b -> {
                b.setNumber(bankCardUpdate.getNumber());
                b.setExpirationDate(bankCardUpdate.getExpirationDate());
                b.setCvc(bankCardUpdate.getCvc());
                b.setUpdatedAt(CurrentDateTime.getCurrentDateTime());
                basketRepository.save(bc);
            });
        }
        return ResponseEntity.ok(bankCard);
    }
}
