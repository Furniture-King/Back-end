package com.FurnitureKing.Project.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "package")
public class Package {
    @Id
    private String id;
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
