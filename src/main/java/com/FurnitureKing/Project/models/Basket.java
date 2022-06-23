package com.FurnitureKing.Project.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "basket")
public class Basket {
    @Id
    private String id;

    @DBRef
    private Client client;

    @Field
    private List<BasketTab> basketTab;

    @Field
    private Double basketTotalPrice;

    @Field
    private long createdAt;

    @Field
    private long updatedAt;


    public Basket(){}

    public Basket(Client client, List<BasketTab> basketTab) {
        this.client = client;
        this.basketTab = basketTab;
    }

    public Basket(Client client, long createdAt) {
        this.client = client;
        this.createdAt = createdAt;
    }

    public Client getClient() {return client;}
    public void setClient(Client client) {this.client = client;}

    public List<BasketTab> getBasketTab() {return basketTab;}
    public void setBasketTab(List<BasketTab> basketTab) {this.basketTab = basketTab;}

    public Double getBasketTotalPrice() {return basketTotalPrice;}
    public void setBasketTotalPrice(Double basketTotalPrice) {this.basketTotalPrice = basketTotalPrice;}

    public long getCreatedAt() {return createdAt;}
    public void setCreatedAt(long createdAt) {this.createdAt = createdAt;}

    public long getUpdatedAt() {return updatedAt;}
    public void setUpdatedAt(long updatedAt) {this.updatedAt = updatedAt;}
}
