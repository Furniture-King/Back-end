package com.FurnitureKing.Project.payload.request;

import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

public class SignupRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @Size(max = 50)
    @Email
    private String email;

    private Set<String> roles;


    private Integer civility;

    private String lastName;


    private String firstName;


    private String address;


    private String postalCode;


    private String city;


    private String phone;


    private Integer nbConnection;


    private List<String> favProduct;


    private long createdAt;

    private String password;

    public Integer getCivility() {
        return civility;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getNbConnection() {
        return nbConnection;
    }

    public List<String> getFavProduct() {
        return favProduct;
    }

    public long getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(long createdAt) {this.createdAt = createdAt;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public Set<String> getRoles() {return this.roles;}
    public void setRole(Set<String> roles) {this.roles = roles;}
}
