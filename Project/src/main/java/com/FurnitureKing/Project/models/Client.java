package com.FurnitureKing.Project.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import java.util.Date;

@Document(collection = "client")
public class Client {
    @Id
    private ObjectId id;
    @Field
    private Integer status;
    @Indexed(unique = true)
    private String email;
    @Field
    private String passwordHash;
    @Field
    private String passwordSalt;
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
                  Integer status,
                  String email,
                  String passwordHash,
                  String passwordSalt,
                  Integer civility,
                  String lastName,
                  String firstName,
                  String phone,
                  Integer nbConnection,
                  String favProduct,
                  Date createdAt,
                  Date updatedAt) {
        this.id = id;
        this.status = status;
        this.email = email;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
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
                  Integer status,
                  String email,
                  String passwordHash,
                  String passwordSalt,
                  Integer civility,
                  String lastName,
                  String firstName,
                  String phone,
                  Integer nbConnection,
                  String favProduct,
                  Date createdAt,
                  Date updatedAt) {
        this.status = status;
        this.email = email;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
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

    public Integer getStatus() {return status;}
    public void setStatus(Integer status) {this.status = status;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPasswordHash() {return passwordHash;}
    public void setPasswordHash(String passwordHash) {this.passwordHash = passwordHash;}

    public String getPasswordSalt() {return passwordSalt;}
    public void setPasswordSalt(String passwordSalt) {this.passwordSalt = passwordSalt;}

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
                ", status=" + status +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", passwordSalt='" + passwordSalt + '\'' +
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
