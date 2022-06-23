package com.FurnitureKing.Project.repositories;

import com.FurnitureKing.Project.models.Basket;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository
        extends MongoRepository<Basket, ObjectId> {

    Optional<Basket> getBasketByClient_Id(ObjectId clientId);
    Optional<Basket> findById(ObjectId basketId);

}
