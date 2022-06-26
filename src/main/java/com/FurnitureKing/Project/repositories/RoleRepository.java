package com.FurnitureKing.Project.repositories;

import com.FurnitureKing.Project.models.Role;
import com.FurnitureKing.Project.models.ERole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository
        extends MongoRepository<Role, String> {

    Optional<Role> findByName(ERole name);
}
