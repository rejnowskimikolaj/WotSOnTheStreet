package com.example.rent.zulicywiesciapp.model;

import java.util.List;

/**
 * Created by md on 3/9/17.
 */

public class NewsItemList {

    private List<NewsItem> news;

    public NewsItemList(List<NewsItem> news) {
        this.news = news;
    }

    public List<NewsItem> getNews() {
        return news;
    }

    public void setNews(List<NewsItem> news) {
        this.news = news;
    }

}
