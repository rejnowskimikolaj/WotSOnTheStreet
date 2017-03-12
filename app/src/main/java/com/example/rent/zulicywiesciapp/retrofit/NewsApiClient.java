package com.example.rent.zulicywiesciapp.retrofit;


import com.example.rent.zulicywiesciapp.model.Author;
import com.example.rent.zulicywiesciapp.model.AuthorList;
import com.example.rent.zulicywiesciapp.model.Category;
import com.example.rent.zulicywiesciapp.model.CategoryList;
import com.example.rent.zulicywiesciapp.model.NewsItem;
import com.example.rent.zulicywiesciapp.model.NewsItemList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiClient {

    @GET("/api/news")
    Call<NewsItemList> getNewsList();

    @GET("/api/news")
    Call<NewsItemList> getNewsList(@Query("sort") String sort);

    @GET("/api/news")
    Call<NewsItem> getNewsItem(@Query("id") String id);

    @GET("/api/categories")
    Call<CategoryList> getCategoryList();

    @GET("/api/categories")
    Call<Category> getCategory(@Query("id") String id);

    @GET("/api/authors")
    Call<AuthorList> getAuthorList();

    @GET("/api/authors")
    Call<Author> getAuthor(@Query("id") String id);


}
