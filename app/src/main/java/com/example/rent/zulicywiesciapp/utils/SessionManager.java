package com.example.rent.zulicywiesciapp.utils;

import com.example.rent.zulicywiesciapp.model.User;

/**
 * Created by User on 2017-03-27.
 */

public class SessionManager {

    private static SessionManager ourInstance = new SessionManager();
    private User user;

    public static SessionManager getInstance() {
        return ourInstance;
    }

    private SessionManager() {
    }

    public  User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
