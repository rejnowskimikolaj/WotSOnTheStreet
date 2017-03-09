package com.example.rent.zulicywiesciapp.retrofit;


import com.example.rent.zulicywiesciapp.model.NewsItem;
import com.example.rent.zulicywiesciapp.model.NewsItemList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiClient {

    @GET("/api/list")
    Call<NewsItemList> getNewsList();

    @GET("/api/one")
    Call<NewsItem> getNewsItem(@Query("id") String id);

}
