package com.FurnitureKing.Project.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

public class Category {
    @Id
    private ObjectId id;
    @Field
    private ObjectId title;
    @Field
    private Date createdAt;
    @Field
    private Date updatedAt;
}
