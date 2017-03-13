package com.example.rent.zulicywiesciapp.model;

import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by md on 3/12/17.
 */
public class Author {

    private Integer id;
    private String name;
    private String lastname;
    @Nullable
    private List<NewsItem> news;

    public Author(Integer id, String name, String lastname) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<NewsItem> getNews() {
        return news;
    }

    public void setNews(List<NewsItem> news) {
        this.news = news;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", news=" + news +
                '}';
    }
}
