package com.FurnitureKing.Project.repositories;

import com.FurnitureKing.Project.models.Client;
import com.FurnitureKing.Project.models.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository
        extends MongoRepository<Client, ObjectId> {

    Optional<Client> findByEmail(String email);
    Optional<Client> findById(ObjectId clientId);
}
