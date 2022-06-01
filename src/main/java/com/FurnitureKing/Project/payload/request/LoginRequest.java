package com.FurnitureKing.Project.payload.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String passwordHash;

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPasswordHash() {return passwordHash;}

    public void setPassword(String passwordHash) {this.passwordHash = passwordHash;}
}
