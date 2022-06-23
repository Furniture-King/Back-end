package com.FurnitureKing.Project.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "basket")
public class Basket {
    @Id
    private ObjectId id;

    @DBRef
    private ObjectId clientId;

    @Field
    private List<BasketTab> basketTab;

    @Field
    private Double basketTotalPrice;

    @Field
    private long createdAt;

    @Field
    private long updatedAt;


    public Basket(){}

    public Basket(ObjectId clientId, List<BasketTab> basketTab) {
        this.clientId = clientId;
        this.basketTab = basketTab;
    }

    public Basket(ObjectId clientId, long createdAt) {
        this.clientId = clientId;
        this.createdAt = createdAt;
    }

    public ObjectId getClientId() {return clientId;}
    public void setClientId(ObjectId clientId) {this.clientId = clientId;}

    public List<BasketTab> getBasketTab() {return basketTab;}
    public void setBasketTab(List<BasketTab> basketTab) {this.basketTab = basketTab;}

    public Double getBasketTotalPrice() {return basketTotalPrice;}
    public void setBasketTotalPrice(Double basketTotalPrice) {this.basketTotalPrice = basketTotalPrice;}

    public long getCreatedAt() {return createdAt;}
    public void setCreatedAt(long createdAt) {this.createdAt = createdAt;}

    public long getUpdatedAt() {return updatedAt;}
    public void setUpdatedAt(long updatedAt) {this.updatedAt = updatedAt;}
}
