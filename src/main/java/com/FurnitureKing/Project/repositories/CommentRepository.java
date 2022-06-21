package com.FurnitureKing.Project.repositories;

import com.FurnitureKing.Project.models.Comment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository
        extends MongoRepository<Comment, ObjectId> {

    List<Comment> findCommentByClientAndId(ObjectId clientId);

    List<Comment> findCommentsByProductAndIdEquals(ObjectId productId);
}
