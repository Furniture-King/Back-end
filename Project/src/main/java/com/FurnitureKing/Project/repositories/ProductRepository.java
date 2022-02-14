package com.FurnitureKing.Project.repositories;

import com.FurnitureKing.Project.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository
        extends MongoRepository<Product,String> {

}
