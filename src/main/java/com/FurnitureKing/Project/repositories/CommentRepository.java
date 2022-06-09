package com.FurnitureKing.Project.repositories;

import com.FurnitureKing.Project.models.Client;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository
        extends MongoRepository<Client, ObjectId> {

}
