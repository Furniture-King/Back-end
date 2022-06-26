package com.FurnitureKing.Project.repositories;

import com.FurnitureKing.Project.models.Package;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends MongoRepository<Package, String> {
}
