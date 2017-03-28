package com.example.rent.zulicywiesciapp.model;

/**
 * Created by md on 3/26/17.
 */

public class AddNewsResponse {
    private Status status;
    private Long id;

    public AddNewsResponse(Status status, Long id) {
        this.status = status;
        this.id = id;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
