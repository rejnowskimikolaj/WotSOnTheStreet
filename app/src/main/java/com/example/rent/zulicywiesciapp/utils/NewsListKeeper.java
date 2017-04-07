package com.example.rent.zulicywiesciapp.utils;

/**
 * Created by User on 2017-04-07.
 */

public class NewsListKeeper {
    private static final NewsListKeeper ourInstance = new NewsListKeeper();

    public static NewsListKeeper getInstance() {
        return ourInstance;
    }

    private NewsListKeeper() {
    }
}
