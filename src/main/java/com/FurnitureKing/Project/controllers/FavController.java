package com.FurnitureKing.Project.controllers;

import com.FurnitureKing.Project.models.Basket;
import com.FurnitureKing.Project.models.Fav;
import com.FurnitureKing.Project.models.Product;
import com.FurnitureKing.Project.payload.response.MessageResponse;
import com.FurnitureKing.Project.repositories.FavRepository;
import com.FurnitureKing.Project.utils.CurrentDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class FavController {

    private final FavRepository favRepository;

    public FavController(FavRepository favRepository) {
        this.favRepository = favRepository;
    }

    /* Get all clients */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/favs")
    public ResponseEntity<List<Fav>> getFavs() {
        List<Fav> favList = favRepository.findAll();
        return ResponseEntity.ok(favList);
    }

    /* Search 1 fav by Id */
    @GetMapping("/favs/id/{favId}")
    public ResponseEntity<?> getFav(@PathVariable final String favId) {
        Optional<Fav> fav = favRepository.findById(favId);
        if (!fav.isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: fav doesn't exist!"));
        } else {
            return ResponseEntity.ok(fav);
        }
    }

    /* Search 1 fav by client */
    @GetMapping("/favs/client/{clientId}")
    public ResponseEntity<?> getFavByClientId(@PathVariable final String clientId) {
        Optional<Fav> fav = favRepository.getFavByClient_Id(clientId);
        if (!fav.isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: fav doesn't exist!"));
        } else {
            return ResponseEntity.ok(fav.get().getProducts());
        }
    }

    /* Delete 1 fav */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/favs/delete/product/{productId}/client/{clientId}")
    public ResponseEntity<?> deleteFav(@PathVariable final String clientId, @PathVariable final String productId) {

        Optional<Fav> fav = favRepository.getFavByClient_Id(clientId);
        if (fav.isPresent()) {
            List<Product> listProducts = new ArrayList<>();
            fav.get().getProducts().forEach(fP -> {
                if (!fP.getId().equals(productId)) {
                    listProducts.add(fP);
                }
            });

            fav.get().setProducts(listProducts);

            fav.ifPresent(favRepository::save);
            return ResponseEntity.ok().body(new MessageResponse("Product deleted on Fav"));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: fav doesn't exist!"));
        }

    }

    /* Update 1 fav */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/favs/put/client/{clientId}")
    public ResponseEntity<?> updateFav(@PathVariable final String clientId, @RequestBody Product product) {
        Optional<Fav> fav = favRepository.getFavByClient_Id(clientId);

        if (!fav.isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: fav doesn't exist!"));
        } else {
            List<Product> favProducts = fav.get().getProducts();
            if (favProducts == null) {
                List<Product> newFavProducts = new ArrayList<Product>();
                newFavProducts.add(product);
                fav.get().setProducts(newFavProducts);
            } else {
                final Boolean[] Present = {false};
                favProducts.forEach(fP -> {
                    if (fP.getId().equals(product.getId())) {
                        Present[0] = true;
                    }
                });
                if (!Present[0]) {
                    fav.get().getProducts().add(product);
                }
            }
            fav.get().setUpdatedAt(CurrentDateTime.getCurrentDateTime());

        }
        fav.ifPresent(favRepository::save);
        return ResponseEntity.ok(fav);
    }
}
