package com.FurnitureKing.Project.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Document(collection = "bankcard")
public class BankCard {
    @Id
    private ObjectId id;

    @DBRef
    private Client client;

    @NotBlank
    @Size(max = 50)
    @Indexed(unique = true)
    @Field
    private String number;

    @NotBlank
    @Field
    private String expirationDate;

    @NotBlank
    @Field
    private String cvc;

    @Field
    private long createdAt;

    @Field
    private long updatedAt;

    public BankCard() {
    }

    public BankCard(String number, String cvc, String expirationDate, Client client) {
        this.number = number;
        this.cvc = cvc;
        this.expirationDate = expirationDate;
        this.client = client;
    }

    public String getNumber() {return number;}
    public void setNumber(String number) {this.number = number;}

    public String getExpirationDate() {return expirationDate;}
    public void setExpirationDate(String expirationDate) {this.expirationDate = expirationDate;}

    public String getCvc() {return cvc;}
    public void setCvc(String cvc) {this.cvc = cvc;}

    public Client getClient() {return client;}
    public void setClient(Client client) {this.client = client;}

    public long getCreatedAt() {return createdAt;}
    public void setCreatedAt(long createdAt) {this.createdAt = createdAt;}

    public long getUpdatedAt() {return updatedAt;}
    public void setUpdatedAt(long updatedAt) {this.updatedAt = updatedAt;}
}
