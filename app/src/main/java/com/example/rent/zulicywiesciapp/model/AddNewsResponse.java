package com.example.rent.zulicywiesciapp.model;

/**
 * Created by md on 3/26/17.
 */

public class AddNewsResponse {
    private Status status;
    private Integer id;

    public AddNewsResponse(Status status, Integer id) {
        this.status = status;
        this.id = id;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
