package com.FurnitureKing.Project.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "comments")
public class Comment {
    @Id
    private ObjectId id;

    @DBRef
    private Client client;

    @DBRef
    private Product product;

    @NotBlank
    @Field
    private String note;

    @NotBlank
    @Size(max = 120)
    @Field
    private String comment;

    @Field
    private long createdAt;

    @Field
    private long updatedAt;
}
