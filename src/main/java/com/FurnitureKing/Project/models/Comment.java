package com.FurnitureKing.Project.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Document(collection = "comments")
public class Comment {
    @Id
    private String id;

    @DBRef
    private Set<Client> client;

    @DBRef
    private Set<Product> product;

    @NotBlank
    @Field
    private Integer note;

    @NotBlank
    @Size(max = 120)
    @Field
    private String comment;

    @Field
    private long createdAt;

    @Field
    private long updatedAt;

    public Comment() {}

    public Comment(Set<Client> client, Set<Product> product, Integer note, String comment) {
        this.client = client;
        this.product = product;
        this.note = note;
        this.comment = comment;
    }

    public Set<Client> getClient() {
        return client;
    }

    public void setClient(Set<Client> client) {
        this.client = client;
    }

    public Set<Product> getProduct() {
        return product;
    }

    public void setProduct(Set<Product> product) {
        this.product = product;
    }

    public Integer getNote() {return note;}
    public void setNote(Integer note) {this.note = note;}

    public String getComment() {return comment;}
    public void setComment(String comment) {this.comment = comment;}

    public long getCreatedAt() {return createdAt;}
    public void setCreatedAt(long createdAt) {this.createdAt = createdAt;}

    public long getUpdatedAt() {return updatedAt;}
    public void setUpdatedAt(long updatedAt) {this.updatedAt = updatedAt;}
}
