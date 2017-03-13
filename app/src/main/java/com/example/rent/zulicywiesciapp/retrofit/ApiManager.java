package com.example.rent.zulicywiesciapp.retrofit;

import android.util.Log;

import com.example.rent.zulicywiesciapp.model.Author;
import com.example.rent.zulicywiesciapp.model.AuthorList;
import com.example.rent.zulicywiesciapp.model.Category;
import com.example.rent.zulicywiesciapp.model.CategoryList;
import com.example.rent.zulicywiesciapp.model.NewsItem;
import com.example.rent.zulicywiesciapp.model.NewsItemList;
import com.example.rent.zulicywiesciapp.model.Sort;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by md on 3/9/17.
 */

public class ApiManager {

    private static NewsApiClient newsApiClient = new NewsApiClientFactory().create();

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

                Log.d("API MANAGER", "onFailure: " + t.getMessage());
            }
        });

    }

    public static void getNewsItem(Integer id, final OnNewsItemFetchedListener listener) throws ApiConnectException {
        newsApiClient.getNewsItem(id).enqueue(new Callback<NewsItem>() {
            @Override
            public void onResponse(Call<NewsItem> call, Response<NewsItem> response) {
                if (response.isSuccessful()) {
                    Log.d("!!! API DATA", response.body().toString());
                    listener.onNewsItemFetched(response.body());

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
}
