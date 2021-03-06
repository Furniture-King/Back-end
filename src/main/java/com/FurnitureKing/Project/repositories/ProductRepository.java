package com.FurnitureKing.Project.repositories;

import com.FurnitureKing.Project.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository
        extends MongoRepository<Product,String> {


    @Query("{ 'categoryName' : ?0 }")
    List<Product> findProductsByCategory(String category);

    List<String> getDistinctByCategoryName();

    void deleteById(String productId);

    Optional<Product> findById(String productId);
}
