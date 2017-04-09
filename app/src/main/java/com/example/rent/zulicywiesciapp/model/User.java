package com.example.rent.zulicywiesciapp.model;

import com.google.gson.annotations.Expose;

import java.util.Date;

/**
 * Created by md on 3/25/17.
 */

public class User {

    private Integer userID;

    private String username;

    private String name;

    private String lastname;

    private Date lastLogin;

    private Integer authorID;

    private String token;

    private Integer role;


    public User() {}


    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getAuthorID() {
        return authorID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setAuthorID(Integer authorID) {
        this.authorID = authorID;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", lastLogin=" + lastLogin +
                ", authorID=" + authorID +
                ", token='" + token + '\'' +
                '}';
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
