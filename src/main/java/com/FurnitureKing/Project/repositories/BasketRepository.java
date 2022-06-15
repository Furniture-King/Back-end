package com.FurnitureKing.Project.repositories;

import com.FurnitureKing.Project.models.BankCard;
import com.FurnitureKing.Project.models.Basket;
import com.FurnitureKing.Project.models.Client;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository
        extends MongoRepository<Basket, ObjectId> {

    Optional<Basket> getBasketByClientAndId(ObjectId clientId);

    Optional<Basket> findById(ObjectId basketId);

}
