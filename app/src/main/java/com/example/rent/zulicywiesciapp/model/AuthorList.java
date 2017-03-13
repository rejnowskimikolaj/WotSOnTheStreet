package com.example.rent.zulicywiesciapp.model;

import java.util.List;

/**
 * Created by md on 3/12/17.
 */
public class AuthorList {

    private List<Author> authors;

    public AuthorList(List<Author> authors) {
        this.authors = authors;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
