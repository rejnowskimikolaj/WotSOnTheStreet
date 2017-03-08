package com.example.rent.zulicywiesciapp.model;

import java.util.Date;

/**
 * Created by User on 2017-03-08.
 */

public class NewsItem {
    private Long id;
    private String title;
    private String content;
    private Integer priority;
    private String author;
    private Date date;
    private String img_url;

    public NewsItem(Long id, String title, String content, Integer priority, String author, Date date, String img_url) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.priority = priority;
        this.author = author;
        this.date = date;
        this.img_url = img_url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
