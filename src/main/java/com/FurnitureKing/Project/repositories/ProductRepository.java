package com.FurnitureKing.Project.repositories;

import com.FurnitureKing.Project.models.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository
        extends MongoRepository<Product,ObjectId> {


    @Query("{ 'category' : ?0 }")
    List<Product> findProductByCategory(String category);

    void deleteById(ObjectId productId);

    Optional<Product> findById(ObjectId productId);
}
