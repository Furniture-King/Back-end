package com.FurnitureKing.Project.controllers;

import com.FurnitureKing.Project.models.ShoppingCart;
import com.FurnitureKing.Project.models.ScItems;
import com.FurnitureKing.Project.payload.response.MessageResponse;
import com.FurnitureKing.Project.repositories.ShoppingCartRepository;
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
public class ShoppingCartController {

    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartController(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    /* Get all shoppingCarts */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/shoppingCart")
    public ResponseEntity<List<ShoppingCart>> getShoppingCarts() {
        List<ShoppingCart> shoppingCartList = shoppingCartRepository.findAll();
        return ResponseEntity.ok(shoppingCartList);
    }

    /* Search 1 shoppingCart by Id */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/shoppingCart/id/{shoppingCartId}")
    public ResponseEntity<Optional<ShoppingCart>> getShoppingCart(@PathVariable final ObjectId shoppingCartId) {
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(shoppingCartId);
        if (shoppingCart.isPresent()) {
            return ResponseEntity.ok(shoppingCart);
        }
        return ResponseEntity.notFound().build();
    }

    /* Search 1 shoppingCart by client */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/shoppingCart/client/{clientId}")
    public ResponseEntity<Optional<ShoppingCart>> getShoppingCartByClientId(@PathVariable final ObjectId clientId) {
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.getShoppingCartByClient_Id(clientId);
        if (shoppingCart.isPresent()) {
            return ResponseEntity.ok(shoppingCart);
        }
        return ResponseEntity.notFound().build();
    }

    /* Create shoppingCart */
    @PostMapping(value = "/shoppingCart/post")
    public ResponseEntity<MessageResponse> addShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        shoppingCart.setCreatedAt(CurrentDateTime.getCurrentDateTime());
        shoppingCartRepository.insert(shoppingCart);

        return ResponseEntity.ok().body(new MessageResponse("The shoppingCart is created"));
    }

    /* Delete 1 product of shoppingCart*/
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/shoppingCart/delete/product/{productId}/client/{clientId}")
    public ResponseEntity<?> deleteShoppingCart(@PathVariable final ObjectId productId ,@PathVariable final ObjectId clientId ) {

        Optional<ShoppingCart> ShoppingCart = shoppingCartRepository.getShoppingCartByClient_Id(clientId);
        if (ShoppingCart.isPresent()) {
            List<ScItems> ScItems = ShoppingCart.get().getScItems();
            if(ScItems == null){
                return ResponseEntity.notFound().build();
            }else{
                // il y a des produits
                List<ScItems> newScItems = new ArrayList<ScItems>();

                ScItems.forEach(bT ->{
                    // pour chaque produits
                        Integer count = 0;
                    if (!bT.getProduct().getId().toString().equals(productId.toString())){
                        newScItems.add(bT);
                    }
                });
                ShoppingCart.get().setScItems(newScItems);
            }


            AtomicReference<Double> SCTotalPrice = new AtomicReference<>((double) 0);
            ShoppingCart.get().getScItems().forEach(bT -> {
                SCTotalPrice.updateAndGet(v -> v + bT.getPriceProduct());
            });
            ShoppingCart.get().setSCTotalPrice(SCTotalPrice.get());


            ShoppingCart.get().setUpdatedAt(CurrentDateTime.getCurrentDateTime());
            ShoppingCart.ifPresent(shoppingCartRepository::save);
            return ResponseEntity.ok().body(new MessageResponse("The ShoppingCart is deleted"));
        }
        return ResponseEntity.notFound().build();
    }

    /* update 1 ShoppingCart */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/shoppingCart/put/client/{clientId}")
    public ResponseEntity<String> updateShoppingCart(@PathVariable final ObjectId clientId, @RequestBody ScItems scItems) {

        Optional<ShoppingCart> ShoppingCart = shoppingCartRepository.getShoppingCartByClient_Id(clientId);
        if(ShoppingCart.isPresent()) {
            List<ScItems> ScItems = ShoppingCart.get().getScItems();
            if (ScItems == null) {
                List<ScItems> newScItems = new ArrayList<ScItems>();
                newScItems.add(scItems);
                newScItems.get(0).setPriceProduct(newScItems.get(0).getProduct().getPrice() * newScItems.get(0).getQté());
                ShoppingCart.get().setScItems(newScItems);
            } else {
                final Boolean[] Present = {false};
                ScItems.forEach(bT -> {
                    if (bT.getProduct().getId().toString().equals(scItems.getProduct().getId().toString())) {
                        Present[0] = true;
                        if (scItems.getQté() == null) {
                            bT.setQté(1);
                        } else {
                            bT.setQté(scItems.getQté());
                        }
                        bT.setPriceProduct(bT.getProduct().getPrice() * bT.getQté());
                    }
                });
                if (!Present[0]) {
                    ShoppingCart.get().getScItems().add(scItems);
                    ShoppingCart.get().getScItems().forEach(bT -> {
                        bT.setPriceProduct(bT.getProduct().getPrice() * bT.getQté());
                    });
                }
            }
            AtomicReference<Double> SCTotalPrice = new AtomicReference<>((double) 0);
            ShoppingCart.get().getScItems().forEach(bT -> {
                SCTotalPrice.updateAndGet(v -> v + bT.getPriceProduct());
            });
            ShoppingCart.get().setSCTotalPrice(SCTotalPrice.get());
        }


        ShoppingCart.ifPresent(shoppingCartRepository::save);
        return ResponseEntity.ok().body("Basket updated successfully!");
    }
}
