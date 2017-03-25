package com.example.rent.zulicywiesciapp.exceptions;

/**
 * Created by md on 3/25/17.
 */

public class NoUserException extends Exception {

    public NoUserException() {
        super("User not found!");
    }
}
