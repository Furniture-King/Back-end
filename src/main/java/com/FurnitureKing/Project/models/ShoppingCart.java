package com.FurnitureKing.Project.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "basket")
public class ShoppingCart {
    @Id
    private ObjectId id;

    @DBRef
    private Client client;

    @Field
    private List<ScItems> scItems;

    @Field
    private Double scTotalPrice;

    @Field
    private long createdAt;

    @Field
    private long updatedAt;


    public ShoppingCart(){}

    public ShoppingCart(Client client, List<ScItems> scItems) {this.client = client;}

    public ShoppingCart(Client client, long createdAt) {this.client = client;}

    public Client getClient() {return client;}
    public void setClient(Client client) {this.client = client;}

    public List<ScItems> getScItems() {return scItems;}
    public void setScItems(List<ScItems> basketTab) {this.scItems = scItems;}

    public Double getSCTotalPrice() {return scTotalPrice;}
    public void setSCTotalPrice(Double basketTotalPrice) {this.scTotalPrice = scTotalPrice;}

    public long getCreatedAt() {return createdAt;}
    public void setCreatedAt(long createdAt) {this.createdAt = createdAt;}

    public long getUpdatedAt() {return updatedAt;}
    public void setUpdatedAt(long updatedAt) {this.updatedAt = updatedAt;}
}
