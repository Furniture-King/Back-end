package com.FurnitureKing.Project.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Document(collection = "Basket")
public class Basket {
    @Id
    private ObjectId id;

    @DBRef
    private Client client;

    @Field
    private List<String> products;

    @Field
    private long createdAt;

    @Field
    private long updatedAt;

    public Basket(){}

    public Basket(Client client, List<String> products, long createdAt, long updatedAt) {
        this.client = client;
        this.products = products;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Client getClient() {return client;}
    public void setClient(Client client) {this.client = client;}

    public List<String> getProducts() {return products;}
    public void setProducts(List<String> products) {this.products = products;}

    public long getCreatedAt() {return createdAt;}
    public void setCreatedAt(long createdAt) {this.createdAt = createdAt;}

    public long getUpdatedAt() {return updatedAt;}
    public void setUpdatedAt(long updatedAt) {this.updatedAt = updatedAt;}
}
