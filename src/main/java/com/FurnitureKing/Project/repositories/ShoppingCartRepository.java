package com.FurnitureKing.Project.repositories;

import com.FurnitureKing.Project.models.ShoppingCart;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository
        extends MongoRepository<ShoppingCart, ObjectId> {

    Optional<ShoppingCart> getShoppingCartByClient_Id(ObjectId clientId);
    Optional<ShoppingCart> findById(ObjectId basketId);

}
