package com.example.rent.zulicywiesciapp.model;

import com.example.rent.zulicywiesciapp.utils.PassEncryption;

import static com.example.rent.zulicywiesciapp.utils.PassEncryption.encryptString;

/**
 * Created by md on 3/25/17.
 */

public class Login {

    private String login;
    private String passwordHash;

    public Login(String login, String password) {
        this.login = login;
        passwordHash = encryptString(password);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String password) {
        this.passwordHash =  encryptString(password);
    }
}
