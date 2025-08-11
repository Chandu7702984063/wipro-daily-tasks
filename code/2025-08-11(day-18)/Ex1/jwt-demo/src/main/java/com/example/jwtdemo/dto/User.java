package com.example.jwtdemo.dto;

public class User {
    private String user;
    private String token;

    // Removed pwd to avoid returning it in API responses
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
