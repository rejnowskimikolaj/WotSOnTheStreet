package com.example.rent.zulicywiesciapp.model;

/**
 * Created by md on 3/26/17.
 */

public class AddNewsResponse {
    private Status status;
    private Integer id;
    private String name;

    public AddNewsResponse(Status status, Integer id) {
        this.status = status;
        this.id = id;
    }

    public AddNewsResponse(Status status, String name) {
        this.status = status;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
