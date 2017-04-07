package com.example.rent.zulicywiesciapp.utils;

import com.example.rent.zulicywiesciapp.model.NewsItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2017-04-07.
 */

public class NewsListKeeper {
    private static final NewsListKeeper ourInstance = new NewsListKeeper();
    private static List<NewsItem> list;

    public static NewsListKeeper getInstance() {
        return ourInstance;
    }

    public static List<NewsItem> getList() {
        return list;
    }

    public static void setList(List<NewsItem> list) {
        NewsListKeeper.list = list;
    }

    private NewsListKeeper() {
    }
}
