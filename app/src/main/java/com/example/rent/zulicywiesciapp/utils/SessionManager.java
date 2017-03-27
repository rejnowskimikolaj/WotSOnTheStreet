package com.example.rent.zulicywiesciapp.utils;

/**
 * Created by User on 2017-03-27.
 */

public class SessionManager {

    private static SessionManager ourInstance = new SessionManager();
    private String token;

    public static SessionManager getInstance() {
        return ourInstance;
    }

    private SessionManager() {
    }

    public  String getToken() {
        return token;
    }

    public  void setToken(String token) {
        token = token;
    }


    public void register(String token) {
        this.token=token;
    }

    public void logIn(String token) {
        this.token=token;
    }

}
