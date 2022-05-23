package com.FurnitureKing.Project.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

public class Order {
    @Id
    private ObjectId id;
    @Field
    private String number;
    @DBRef
    private Client client;
    @DBRef
    private Set<Product> products;
    @Field
    private String address;
    @Field
    private String city;
    @Field
    private Integer postalCode;
    @Field
    private Integer nb_package;
    @Field
    private long price;
    @Field
    private long createdAt;
    @Field
    private long updatedAt;

    public Client getClient() {return client;}
    public void setClient(Client client) {this.client = client;}

    public Set<Product> getProducts() {return products;}
    public void setProducts(Set<Product> products) {this.products = products;}

    public ObjectId getId() {return id;}
    public void setId(ObjectId id) {this.id = id;}

    public String getNumber() {return number;}
    public void setNumber(String number) {this.number = number;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public String getCity() {return city;}
    public void setCity(String city) {this.city = city;}

    public Integer getPostalCode() {return postalCode;}
    public void setPostalCode(Integer postalCode) {this.postalCode = postalCode;}

    public Integer getNb_package() {return nb_package;}
    public void setNb_package(Integer nb_package) {this.nb_package = nb_package;}

    public long getPrice() {return price;}
    public void setPrice(long price) {this.price = price;}

    public long getCreatedAt() {return createdAt;}
    public void setCreatedAt(long createdAt) {this.createdAt = createdAt;}

    public long getUpdatedAt() {return updatedAt;}
    public void setUpdatedAt(long updatedAt) {this.updatedAt = updatedAt;}

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", postalCode=" + postalCode +
                ", nb_package=" + nb_package +
                ", price=" + price +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
