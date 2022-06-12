package com.FurnitureKing.Project.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = "client")
public class Client {
    @Id
    private ObjectId id;

    @DBRef
    private Set<Role> roles = new HashSet<>();

    @NotBlank
    @Size(max = 50)
    @Email
    @Indexed(unique = true)
    @Field
    private String email;

    @NotBlank
    @Field
    private String passwordHash;

    @NotBlank
    @Size(max = 120)
    @Field
    private String passwordSalt;

    @Field
    private Integer civility;

    @NotBlank
    @Size(max = 20)
    private String username;

    @Field
    private String lastName;

    @Field
    private String firstName;

    @Field
    private String address;

    @Field
    private String postalCode;

    @Field
    private String city;

    @Field
    private String phone;

    @Field
    private Integer nbConnection;

    @Field
    private List<String> favProduct;

    @Field
    private long createdAt;

    @Field
    private long updatedAt;

    public Client(){}

    public Client(ObjectId id,
                  String email,
                  String passwordHash,
                  String passwordSalt,
                  Integer civility,
                  String lastName,
                  String firstName,
                  String address,
                  String postalCode,
                  String city,
                  String phone,
                  Integer nbConnection,
                  List<String> favProduct,
                  long createdAt,
                  long updatedAt) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
        this.civility = civility;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.phone = phone;
        this.nbConnection = nbConnection;
        this.favProduct = favProduct;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Client(String email, String password, Integer civility, String lastName, String firstName, String address, String postalCode, String city, String phone, Integer nbConnection, List<String> favProduct) {
        this.email = email;
        this.passwordHash = password;
        this.civility = civility;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.phone = phone;
        this.nbConnection = nbConnection;
        this.favProduct = favProduct;
    }

    public ObjectId getId() {return id;}
    public void setId(ObjectId id) {this.id = id;}

    public Set<Role> getRoles() {return roles;}
    public void setRoles(Set<Role> roles) {this.roles = roles;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPasswordHash() {return passwordHash;}
    public void setPasswordHash(String passwordHash) {this.passwordHash = passwordHash;}

    public String getPasswordSalt() {return passwordSalt;}
    public void setPasswordSalt(String passwordSalt) {this.passwordSalt = passwordSalt;}

    public Integer getCivility() {return civility;}
    public void setCivility(Integer civility) {this.civility = civility;}

    public String getUsername() {return username;}
    public void setUsername(String username) { this.username = username;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public String getPostalCode() {return postalCode;}
    public void setPostalCode(String postalCode) {this.postalCode = postalCode;}

    public String getCity() {return city;}
    public void setCity(String city) {this.city = city;}

    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}

    public Integer getNbConnection() {return nbConnection;}
    public void setNbConnection(Integer nbConnection) {this.nbConnection = nbConnection;}

    public List<String> getFavProduct() {return favProduct;}
    public void setFavProduct(List<String> favProduct) {this.favProduct = favProduct;}

    public long getCreatedAt() {return createdAt;}
    public void setCreatedAt(long createdAt) {this.createdAt = createdAt;}

    public long getUpdatedAt() {return updatedAt;}
    public void setUpdatedAt(long updatedAt) {this.updatedAt = updatedAt;}

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
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
