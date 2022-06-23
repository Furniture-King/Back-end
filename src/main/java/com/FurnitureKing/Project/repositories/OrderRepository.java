package com.FurnitureKing.Project.repositories;

import com.FurnitureKing.Project.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository
        extends MongoRepository<Order, String> {
    Optional<Order> findById(String orderId);

    void deleteById(String orderId);
}
