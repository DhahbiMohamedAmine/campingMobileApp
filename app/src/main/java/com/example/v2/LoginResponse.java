package com.example.v2;

public class LoginResponse {
    private String message;
    private String token;
    private User user;
    // Getters and setters
    public String getMessage() {return message;}
    public void setMessage(String message) {this.message = message;}
    public String getToken() {return token;}
    public void setToken(String token) {this.token = token;}
    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}
}

