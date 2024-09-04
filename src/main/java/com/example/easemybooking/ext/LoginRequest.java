package com.example.easemybooking.ext;

public class LoginRequest {
    private String username;
    private String password;

    public LoginRequest(String email, String password) {
        this.username = email;
        this.password = password;
    }

    // Getters and setters
    public String getEmail() {
        return username;
    }

    public void setEmail(String email) {
        this.username = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}