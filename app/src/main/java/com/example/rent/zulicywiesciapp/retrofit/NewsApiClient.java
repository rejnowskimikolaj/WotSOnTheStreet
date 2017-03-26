package com.example.rent.zulicywiesciapp.retrofit;


import com.example.rent.zulicywiesciapp.model.AddNewsDTO;
import com.example.rent.zulicywiesciapp.model.AddNewsResponse;
import com.example.rent.zulicywiesciapp.model.Author;
import com.example.rent.zulicywiesciapp.model.AuthorList;
import com.example.rent.zulicywiesciapp.model.Category;
import com.example.rent.zulicywiesciapp.model.CategoryList;
import com.example.rent.zulicywiesciapp.model.Login;
import com.example.rent.zulicywiesciapp.model.LoginResponse;
import com.example.rent.zulicywiesciapp.model.NewsItem;
import com.example.rent.zulicywiesciapp.model.NewsItemList;
import com.example.rent.zulicywiesciapp.model.Register;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface NewsApiClient {

    @GET("/api/news")
    Call<NewsItemList> getNewsList();

    @GET("/api/news")
    Call<NewsItemList> getNewsList(@Query("sort") String sort);

    @GET("/api/news")
    Call<NewsItem> getNewsItem(@Query("id") Long id);

    @GET("/api/categories")
    Call<CategoryList> getCategoryList();

    @GET("/api/categories")
    Call<Category> getCategory(@Query("id") Integer id);

    @GET("/api/authors")
    Call<AuthorList> getAuthorList();

    @GET("/api/authors")
    Call<Author> getAuthor(@Query("id") Integer id);

    @POST("/api/login")
    @Headers({"Content-Type: application/json"})
    Call<LoginResponse> login(@Body Login login);

    @POST("/api/register")
    @Headers({"Content-Type: application/json"})
    Call<LoginResponse> register(@Body Register register);

    @POST("/api/add_news")
    @Headers({"Content-Type: application/json"})
    Call<AddNewsResponse> addNews(@Header("Token") String token, @Body AddNewsDTO addNewsDTO);

    @Multipart
    @POST("/api/upload")
    Call<AddNewsResponse> upload(
            @Part("description") RequestBody description,
            @Part MultipartBody.Part image
    );

}
