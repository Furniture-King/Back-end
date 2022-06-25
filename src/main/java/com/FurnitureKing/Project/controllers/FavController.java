package com.FurnitureKing.Project.controllers;

import com.FurnitureKing.Project.models.Fav;
import com.FurnitureKing.Project.models.Product;
import com.FurnitureKing.Project.payload.response.MessageResponse;
import com.FurnitureKing.Project.repositories.FavRepository;
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
            return ResponseEntity.ok(fav);
        }
    }

    /* Delete 1 fav */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/favs/delete/{favId}")
    public ResponseEntity<?> deleteFav(@PathVariable final String favId) {

        Optional<Fav> fav = favRepository.findById(favId);

        if (!fav.isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: fav doesn't exist!"));
        } else {
            favRepository.deleteById(favId);
            return ResponseEntity.ok().body(new MessageResponse("Fav deleted"));
        }
    }

    /* Update 1 fav */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/fav/put/{clientId}")
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
            return ResponseEntity.ok(fav);
        }
    }
}
