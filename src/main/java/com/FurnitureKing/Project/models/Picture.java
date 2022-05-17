package com.FurnitureKing.Project.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class Picture {
    @Id
    private ObjectId id;
    @Field
    private ObjectId product_id;
    @Field
    private String img; /* >>>>>>>> voir pour azure */
    @Field
    private Integer resolution;
}
