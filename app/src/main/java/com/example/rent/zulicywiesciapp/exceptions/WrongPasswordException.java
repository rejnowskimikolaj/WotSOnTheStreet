package com.example.rent.zulicywiesciapp.exceptions;

/**
 * Created by md on 3/25/17.
 */

public class WrongPasswordException extends Exception {
    public WrongPasswordException() {
        super("Wrong password!");
    }
}
