package com.FurnitureKing.Project.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.Date;

public class Order {
    @Id
    private ObjectId id;
    @Field
    private String address;
    @Field
    private String city;
    @Field
    private Integer postalCode;
    @Field
    private Integer nb_package;
    @Field
    private Date createdAt;
    @Field
    private Date updatedAt;
}
