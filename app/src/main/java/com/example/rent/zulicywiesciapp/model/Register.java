package com.example.rent.zulicywiesciapp.model;

import static com.example.rent.zulicywiesciapp.utils.PassEncryption.encryptString;

/**
 * Created by md on 3/25/17.
 */

public class Register {
    private String login;
    private String name;
    private String lastname;
    private String passwordHash;

    public Register(String login, String name, String lastname, String password) {
        this.login = login;
        this.name = name;
        this.lastname = lastname;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
