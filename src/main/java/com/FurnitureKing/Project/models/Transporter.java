package com.FurnitureKing.Project.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "transporter")
public class Transporter {
    @Id
    private String id;
    @Field
    private String undertaking;
    @Field
    private String address;
    @Field
    private String city;
    @Field
    private Integer postalCode;
    @Field
    private Integer deliveryPrice;
    @Field
    private long createdAt;
    @Field
    private long updatedAt;

    public Transporter() {}

    public Transporter(String id, String undertaking, String address, String city, Integer postalCode, Integer deliveryPrice, long createdAt, long updatedAt) {
        this.id = id;
        this.undertaking = undertaking;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.deliveryPrice = deliveryPrice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Transporter( String undertaking, String address, String city, Integer postalCode, Integer deliveryPrice, long createdAt) {
        this.undertaking = undertaking;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.deliveryPrice = deliveryPrice;
        this.createdAt = createdAt;
    }

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getUndertaking() {return undertaking;}
    public void setUndertaking(String undertaking) {this.undertaking = undertaking;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public String getCity() {return city;}
    public void setCity(String city) {this.city = city;}

    public Integer getPostalCode() {return postalCode;}
    public void setPostalCode(Integer postalCode) {this.postalCode = postalCode;}

    public Integer getDeliveryPrice() {return deliveryPrice;}
    public void setDeliveryPrice(Integer deliveryPrice) {this.deliveryPrice = deliveryPrice;}

    public long getCreatedAt() {return createdAt;}
    public void setCreatedAt(long createdAt) {this.createdAt = createdAt;}

    public long getUpdatedAt() {return updatedAt;}
    public void setUpdatedAt(long updatedAt) {this.updatedAt = updatedAt;}
}
