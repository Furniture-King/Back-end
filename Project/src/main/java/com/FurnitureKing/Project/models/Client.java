package com.FurnitureKing.Project.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import java.util.Date;

@Document(collection = "client")
public class Client {
    @Id
    private ObjectId id;
    @Field
    private String email;
    @Field
    private String password;
    @Field
    private Integer civility;
    @Field
    private String lastName;
    @Field
    private String firstName;
    @Field
    private String phone;
    @Field
    private Integer nbConnection;
    @Field
    private String favProduct;
    @Field
    private Date createdAt;
    @Field
    private Date updatedAt;

    public Client(){
    }

    public Client(ObjectId id,
                  String email,
                  String password,
                  Integer civility,
                  String lastName,
                  String firstName,
                  String phone,
                  Integer nbConnection,
                  String favProduct,
                  Date createdAt,
                  Date updatedAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.civility = civility;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
        this.nbConnection = nbConnection;
        this.favProduct = favProduct;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Client(
                  String email,
                  String password,
                  Integer civility,
                  String lastName,
                  String firstName,
                  String phone,
                  Integer nbConnection,
                  String favProduct,
                  Date createdAt,
                  Date updatedAt) {
        this.email = email;
        this.password = password;
        this.civility = civility;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
        this.nbConnection = nbConnection;
        this.favProduct = favProduct;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ObjectId getId() {return id;}
    public void setId(ObjectId id) {this.id = id;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public Integer getCivility() {return civility;}
    public void setCivility(Integer civility) {this.civility = civility;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}

    public Integer getNbConnection() {return nbConnection;}
    public void setNbConnection(Integer nbConnection) {this.nbConnection = nbConnection;}

    public String getFavProduct() {return favProduct;}
    public void setFavProduct(String favProduct) {this.favProduct = favProduct;}

    public Date getCreatedAt() {return createdAt;}
    public void setCreatedAt(Date createdAt) {this.createdAt = createdAt;}

    public Date getUpdatedAt() {return updatedAt;}
    public void setUpdatedAt(Date updatedAt) {this.updatedAt = updatedAt;}

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", civility=" + civility +
                ", lastName=" + lastName +
                ", firstName=" + firstName +
                ", phone=" + phone +
                ", nbConnection=" + nbConnection +
                ", favProduct='" + favProduct + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
