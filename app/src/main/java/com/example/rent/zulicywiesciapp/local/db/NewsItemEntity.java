package com.example.rent.zulicywiesciapp.local.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;
import java.util.List;

/**
 * Created by User on 2017-03-19.
 */

@DatabaseTable(tableName ="saved_news_items")
public class NewsItemEntity {

    @DatabaseField(id = true)
    private Long id;
    @DatabaseField
    private String title;
    @DatabaseField
    private String content;
    @DatabaseField
    private String author;
    @DatabaseField
    private Date date;
    @DatabaseField
    private String img_url;

    public NewsItemEntity() {
    }

    public NewsItemEntity(Long id, String title, String content, String author, Date date, String img_url) {
        this.id = id;
        this.title = title;
        this.content = content;
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
