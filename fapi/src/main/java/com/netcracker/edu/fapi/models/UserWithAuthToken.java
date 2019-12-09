package com.netcracker.edu.fapi.models;

public class UserWithAuthToken {
    private UserViewModel user;
    private String token;

    public UserWithAuthToken() {
    }

    public UserWithAuthToken(UserViewModel user, String token) {
        this.user = user;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserViewModel getUser() {
        return user;
    }

    public void setUser(UserViewModel user) {
        this.user = user;
    }
}
