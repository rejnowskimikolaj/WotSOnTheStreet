package com.example.rent.zulicywiesciapp.model;

/**
 * Created by md on 4/5/17.
 */

public class DeleteResponse {

    private Status status;

    public DeleteResponse(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

}
