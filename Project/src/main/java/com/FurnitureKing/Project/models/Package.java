package com.FurnitureKing.Project.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

public class Package {
    @Id
    private ObjectId id;
    @Field
    private String title;
    @Field
    private Integer volume;
    @Field
    private Integer weight;
    @Field
    private Date createdAt;
    @Field
    private Date updatedAt;
}
