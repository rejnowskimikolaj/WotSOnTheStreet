package com.example.rent.zulicywiesciapp.model;


import android.support.annotation.Nullable;

import java.util.List;

public class Category {

    private Integer id;
    private String name;
    @Nullable
    private List<NewsItem> news;


    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @Nullable
    public List<NewsItem> getNews() {
        return news;
    }

    public void setNews(@Nullable List<NewsItem> news) {
        this.news = news;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
