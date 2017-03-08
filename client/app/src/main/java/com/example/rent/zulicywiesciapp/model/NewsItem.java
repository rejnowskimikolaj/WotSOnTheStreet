package com.example.rent.zulicywiesciapp.model;

/**
 * Created by User on 2017-03-08.
 */

public class NewsItem {
    String title;
    String content;
    String url;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    public NewsItem(String title, String content, String url) {
        this.title = title;
        this.content = content;
        this.url = url;
    }


}
