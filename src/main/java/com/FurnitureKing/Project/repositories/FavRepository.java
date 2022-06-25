package com.FurnitureKing.Project.repositories;

import com.FurnitureKing.Project.models.Basket;
import com.FurnitureKing.Project.models.Fav;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavRepository
        extends MongoRepository<Fav, String> {

    Optional<Fav> getFavByClient_Id(String clientId);
}
