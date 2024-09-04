package com.example.easemybooking.dto;

public class SuccessToken
{
    String token = "token";
    String value;

    public SuccessToken() {
    }

    public SuccessToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SuccessToken{" +
                "token='" + token + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}