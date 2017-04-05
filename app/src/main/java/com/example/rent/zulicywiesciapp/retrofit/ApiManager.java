package com.example.rent.zulicywiesciapp.retrofit;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.util.Log;

import com.example.rent.zulicywiesciapp.exceptions.ApiConnectException;
import com.example.rent.zulicywiesciapp.exceptions.NoUserException;
import com.example.rent.zulicywiesciapp.exceptions.WrongPasswordException;
import com.example.rent.zulicywiesciapp.model.AddNewsDTO;
import com.example.rent.zulicywiesciapp.model.AddNewsResponse;
import com.example.rent.zulicywiesciapp.model.AuthResponse;
import com.example.rent.zulicywiesciapp.model.Author;
import com.example.rent.zulicywiesciapp.model.AuthorList;
import com.example.rent.zulicywiesciapp.model.Category;
import com.example.rent.zulicywiesciapp.model.CategoryList;
import com.example.rent.zulicywiesciapp.model.DeleteResponse;
import com.example.rent.zulicywiesciapp.model.Login;
import com.example.rent.zulicywiesciapp.model.LoginResponse;
import com.example.rent.zulicywiesciapp.model.NewsItem;
import com.example.rent.zulicywiesciapp.model.NewsItemList;
import com.example.rent.zulicywiesciapp.model.Register;
import com.example.rent.zulicywiesciapp.model.Sort;
import com.example.rent.zulicywiesciapp.model.Status;
import com.example.rent.zulicywiesciapp.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.rent.zulicywiesciapp.model.Status.*;

/**
 * Created by md on 3/9/17.
 */

public class ApiManager {

    private static NewsApiClient newsApiClient = new NewsApiClientFactory().create();

    public static void login(final Login login, final OnLoginListener listener) {
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(login);
        System.out.println(json);
        newsApiClient.login(login).enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()) {
                    System.out.println(response.body().getStatus());
                   listener.onLogin(response.body());
                } else {
                    listener.onLogin(new LoginResponse(ERROR, null));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("API MANAGER", "onFailure: " + t.getMessage());
                Log.d("API MANAGER", "onFailure: " + t.getCause());
                Log.d("API MANAGER", "onFailure: " + t.getStackTrace());
            }
        });
    }

    public static void register(final Register register, final OnLoginListener listener) {
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(register);
       newsApiClient.register(register).enqueue(new Callback<LoginResponse>() {
           @Override
           public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
               if(response.isSuccessful()) {
                   System.out.println(response.body().getStatus());
                   listener.onLogin(response.body());
               } else {
                   listener.onLogin(new LoginResponse(ERROR, null));
               }
           }

           @Override
           public void onFailure(Call<LoginResponse> call, Throwable t) {
               Log.d("API MANAGER", "onFailure: " + t.getMessage());
               Log.d("API MANAGER", "onFailure: " + t.getCause());
               Log.d("API MANAGER", "onFailure: " + t.getStackTrace());
           }
       });
    }


    public static void addNews(String token, AddNewsDTO newsDTO,File fileToUpload, final OnNewsAddedListener listener) {

      //  File file = new File(newsDTO.getImagePath());
        Gson gson = new GsonBuilder().create();
        String news = gson.toJson(newsDTO);

        RequestBody newsPart = RequestBody.create(MultipartBody.FORM, news);
       // RequestBody filePart = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part uploadFile = MultipartBody.Part.createFormData("file", fileToUpload.getName(), RequestBody.create(MediaType.parse("image/*"), fileToUpload));
        System.out.println("!~~!~!~!~!~!~!~!~!~!~!~!~!~!~! multipartfile " + uploadFile.toString());
        newsApiClient.upload(token, newsPart, uploadFile).enqueue(new Callback<AddNewsResponse>() {
            @Override
            public void onResponse(Call<AddNewsResponse> call, Response<AddNewsResponse> response) {
                if (response.isSuccessful()) {
                    listener.onNewsAdded(response.body());
                } else {
                    listener.onNewsAdded(new AddNewsResponse(ERROR, null));
                }
            }

            @Override
            public void onFailure(Call<AddNewsResponse> call, Throwable t) {

                System.out.println("!~~!~!~!~!~!~!~!~!~!~!~!~!~!~! failed: " + t.getMessage());
                t.printStackTrace();

            }
        });
    }

    public static void checkAuth(String token, final OnAuthCheckListener listener) {

        newsApiClient.checkAuth(token).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                listener.onAuthCheck(response.body().isStatus());
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.d("API MANAGER", "onFailure: " + t.getMessage());
                Log.d("API MANAGER", "onFailure: " + t.getCause());
                Log.d("API MANAGER", "onFailure: " + t.getStackTrace());

            }
        });

    }

    public static void fetchNews(final OnNewsFetchedListener listener) {

        newsApiClient.getNewsList().enqueue(new Callback<NewsItemList>() {
            @Override
            public void onResponse(Call<NewsItemList> call, Response<NewsItemList> response) {
                if (response.isSuccessful()) {

                    List<NewsItem> list = response.body().getNews();
                    Log.d("!!! API DATA", response.body().getNews().get(0).toString());
                    listener.onNewsFetched(list);
                }
            }

            @Override
            public void onFailure(Call<NewsItemList> call, Throwable t) {

                Log.d("API MANAGER", "onFailure: " + t.getMessage());
                Log.d("API MANAGER", "onFailure: " + t.getCause());
                Log.d("API MANAGER", "onFailure: " + t.getStackTrace());



            }
        });

    }

    public static void fetchNews(Sort sort, final OnNewsFetchedListener listener) {

        newsApiClient.getNewsList(sort.getSort()).enqueue(new Callback<NewsItemList>() {
            @Override
            public void onResponse(Call<NewsItemList> call, Response<NewsItemList> response) {
                if (response.isSuccessful()) {

                    List<NewsItem> list = response.body().getNews();
                    Log.d("!!! API DATA", response.body().getNews().get(0).toString());
                    listener.onNewsFetched(list);
                }
            }

            @Override
            public void onFailure(Call<NewsItemList> call, Throwable t) {

                Log.e("API MANAGER", "onFailure: " + t.getMessage());
            }
        });

    }

    public static void deleteNews(String token, Long id, final OnDeleteResultListener listener) {
        newsApiClient.deleteNews(token, id).enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                if (response.isSuccessful()) {
                    listener.onDeleteResult(response.body().getStatus());
                } else {
                    listener.onDeleteResult(Status.ERROR);
                }
            }

            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {

            }
        });
    }

    public static void getNewsItem(Long id, final OnNewsItemFetchedListener listener) throws ApiConnectException {
        newsApiClient.getNewsItem(id).enqueue(new Callback<NewsItem>() {
            @Override
            public void onResponse(Call<NewsItem> call, Response<NewsItem> response) {
                if (response.isSuccessful()) {
                    Log.d("!!! API DATA", response.body().toString());
                    NewsItem newsItem = response.body();
                    listener.onNewsItemFetched(newsItem);

                }
            }

            @Override
            public void onFailure(Call<NewsItem> call, Throwable t) {
                Log.d("API MANAGER", "onFailure: " + t.getMessage());
            }
        });

    }

    public static void getCategory(Integer id, final OnCategoryFetchedListener listener) throws ApiConnectException {
        newsApiClient.getCategory(id).enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if(response.isSuccessful()) {
                    Log.d("!!! API DATA", response.body().toString());
                    Log.d("!!! API DATA", response.body().getNews().get(0).toString());
                    listener.onCategoryFetched(response.body());

                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                Log.d("API MANAGER", "onFailure: " + t.getMessage());
            }
        });

    }

    public static void getCategoryList(final OnCategoryListFetchedListener listener) throws ApiConnectException {
        newsApiClient.getCategoryList().enqueue(new Callback<CategoryList>() {
            @Override
            public void onResponse(Call<CategoryList> call, Response<CategoryList> response) {
                if(response.isSuccessful()) {
                    Log.d("!!! API DATA", response.body().getCategories().get(0).toString());
                    listener.onCategoryListFetched(response.body().getCategories());

                }
            }

            @Override
            public void onFailure(Call<CategoryList> call, Throwable t) {
                Log.d("API MANAGER", "onFailure: " + t.getMessage());
            }
        });

    }

    public static void getAuthor(Integer id, final OnAuthorFetchedListener listener) throws ApiConnectException {
        newsApiClient.getAuthor(id).enqueue(new Callback<Author>() {
            @Override
            public void onResponse(Call<Author> call, Response<Author> response) {
                if(response.isSuccessful()) {
                    Log.d("!!! API DATA", response.body().toString());
                    listener.onAuthorFetched(response.body());

                }
            }

            @Override
            public void onFailure(Call<Author> call, Throwable t) {
                Log.d("API MANAGER", "onFailure: " + t.getMessage());
            }
        });

    }

    public static void getAuthorList(final OnAuthorListFetchedListener listener) throws ApiConnectException {
        newsApiClient.getAuthorList().enqueue(new Callback<AuthorList>() {
            @Override
            public void onResponse(Call<AuthorList> call, Response<AuthorList> response) {
                if(response.isSuccessful()) {
                    Log.d("!!! API DATA", response.body().getAuthors().get(0).toString());
                    listener.onAuthorListFetched(response.body().getAuthors());
                }
            }

            @Override
            public void onFailure(Call<AuthorList> call, Throwable t) {
                Log.d("API MANAGER", "onFailure: " + t.getMessage());
            }
        });

    }

//interface generyczny?

    public interface OnNewsFetchedListener{
        void onNewsFetched(List<NewsItem> newsList);
    }

    public interface OnNewsItemFetchedListener {
        void onNewsItemFetched(NewsItem news);
    }

    public interface OnCategoryFetchedListener {
        void onCategoryFetched(Category category);
    }

    public interface OnCategoryListFetchedListener {
        void onCategoryListFetched(List<Category> categories);
    }

    public interface OnAuthorFetchedListener {
        void onAuthorFetched(Author author);
    }

    public interface OnAuthorListFetchedListener {
        void onAuthorListFetched(List<Author> authors);
    }

    public interface OnLoginListener {
        void onLogin(LoginResponse response);
    }

    public interface OnNewsAddedListener {
        void onNewsAdded(AddNewsResponse response);
    }

    public interface OnAuthCheckListener {
        void onAuthCheck(Boolean response);
    }

    public interface OnDeleteResultListener {
        void onDeleteResult(Status response);
    }
}
