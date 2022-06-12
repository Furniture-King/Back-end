package com.FurnitureKing.Project.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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

    public Comment(ObjectId id, Client client, Product product, String note, String comment, long createdAt, long updatedAt) {
        this.id = id;
        this.client = client;
        this.product = product;
        this.note = note;
        this.comment = comment;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Comment(Client client, Product product, String note, String comment, long createdAt, long updatedAt) {
        this.client = client;
        this.product = product;
        this.note = note;
        this.comment = comment;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Client getClient() {return client;}
    public void setClient(Client client) {this.client = client;}

    public Product getProduct() {return product;}
    public void setProduct(Product product) {this.product = product;}

    public String getNote() {return note;}
    public void setNote(String note) {this.note = note;}

    public String getComment() {return comment;}
    public void setComment(String comment) {this.comment = comment;}

    public long getCreatedAt() {return createdAt;}
    public void setCreatedAt(long createdAt) {this.createdAt = createdAt;}

    public long getUpdatedAt() {return updatedAt;}
    public void setUpdatedAt(long updatedAt) {this.updatedAt = updatedAt;}
}
