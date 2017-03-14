package com.example.rent.zulicywiesciapp.model;

/**
 * Created by RENT on 2017-03-13.
 */

public enum Categories {

    POLITICS(1),
    SPORT(2),
    ECONOMICS(3),
    ART(4),
    TECHNOLOGY(5);

    private int id;
    private Categories(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
