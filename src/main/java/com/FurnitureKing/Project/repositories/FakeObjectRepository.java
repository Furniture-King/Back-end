package com.FurnitureKing.Project.repositories;
import com.FurnitureKing.Project.models.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public class FakeObjectRepository
        extends MongoRepository<FakeObject, ObjectId> {

}
