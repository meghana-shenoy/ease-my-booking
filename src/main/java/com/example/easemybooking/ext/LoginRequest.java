package com.example.easemybooking.ext;

public class LoginRequest {
    private int email;
    private String password;

    public LoginRequest(int email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters and setters
    public int getEmail() {
        return email;
    }

    public void setEmail(int email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}