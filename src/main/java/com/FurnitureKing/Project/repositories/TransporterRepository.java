package com.FurnitureKing.Project.repositories;

import com.FurnitureKing.Project.models.Role;
import com.FurnitureKing.Project.models.Transporter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransporterRepository
        extends MongoRepository<Transporter, String> {

}
