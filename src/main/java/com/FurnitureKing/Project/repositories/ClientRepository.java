package com.FurnitureKing.Project.repositories;

import com.FurnitureKing.Project.models.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository
        extends MongoRepository<Client, String> {

    Optional<Client> findByUsername(String username);

    Boolean existsByUsername(String username);

    Optional<Client> findByEmail(String email);

    Optional<Client> findById(String clientId);
}
