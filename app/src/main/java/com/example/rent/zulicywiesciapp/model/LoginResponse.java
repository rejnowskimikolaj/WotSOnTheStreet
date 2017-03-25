package com.example.rent.zulicywiesciapp.model;

import com.google.gson.annotations.Expose;

/**
 * Created by md on 3/25/17.
 */

public class LoginResponse {


    private Status status;
    private User user;

    public LoginResponse(Status status, User user) {
        this.status = status;
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
