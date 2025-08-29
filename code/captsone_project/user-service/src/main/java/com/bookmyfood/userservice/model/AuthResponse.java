package com.bookmyfood.userservice.model;

public class AuthResponse {
    private String token;

    // Constructor
    public AuthResponse(String token) {
        this.token = token;
    }

    // Getter
    public String getToken() {
        return token;
    }

    // Optional: Setter (if you need to modify the token after creation)
    public void setToken(String token) {
        this.token = token;
    }
}
