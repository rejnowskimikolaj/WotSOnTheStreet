package com.example.rent.zulicywiesciapp.utils;

import com.example.rent.zulicywiesciapp.model.User;
import com.example.rent.zulicywiesciapp.retrofit.ApiManager;

/**
 * Created by User on 2017-03-27.
 */

public class SessionManager {

    private static SessionManager ourInstance = new SessionManager();
    private static User user;

    public static SessionManager getInstance() {
        return ourInstance;
    }

    private SessionManager() {
    }

    public static void checkIfLoggedIn(ApiManager.OnAuthCheckListener listener){

        if(user==null||user.getToken()==null){
            listener.onAuthCheck(false);
        }
        else ApiManager.checkAuth(user.getToken(),listener);
    }

    public  User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
