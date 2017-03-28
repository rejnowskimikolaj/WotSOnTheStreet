package com.example.rent.zulicywiesciapp.model;

/**
 * Created by md on 3/28/17.
 */

public class AuthResponse {

    private boolean status;

    public AuthResponse(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
