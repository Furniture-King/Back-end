package com.FurnitureKing.Project.repositories;

import com.FurnitureKing.Project.models.Basket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository
        extends MongoRepository<Basket, String> {

    Optional<Basket> getBasketByClient_Id(String clientId);
    Optional<Basket> findById(String basketId);

}
