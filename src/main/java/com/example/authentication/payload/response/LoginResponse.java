package com.example.authentication.payload.response;

public class LoginResponse {
    private String token;

    public LoginResponse() {
    }

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }
}
