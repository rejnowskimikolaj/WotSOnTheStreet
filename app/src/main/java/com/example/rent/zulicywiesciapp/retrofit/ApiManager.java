package com.example.rent.zulicywiesciapp.retrofit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.rent.zulicywiesciapp.NewsAdapter;
import com.example.rent.zulicywiesciapp.model.NewsItem;
import com.example.rent.zulicywiesciapp.model.NewsItemList;

import java.util.ArrayList;
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
                    Log.d("!!! API DATA", response.body().getNews().get(0).getTitle());
                    listener.onNewsFetched(list);
                }
            }

            @Override
            public void onFailure(Call<NewsItemList> call, Throwable t) {
                Log.e("failure", String.valueOf(t.getCause()));
                Log.d("API MANAGER", "onFailure: ");
            }
        });

    }




    public interface OnNewsFetchedListener{
        void onNewsFetched(List<NewsItem> newsList);
    }

//    public NewsItem getNews(String id) throws ApiConnectException {
//        newsApiClient.getNewsItem(id).enqueue(new Callback<NewsItem>() {
//            @Override
//            public void onResponse(Call<NewsItem> call, Response<NewsItem> response) {
//               if (response.isSuccessful()) {
//                    response.body();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<NewsItem> call, Throwable t) {
//
//            }
//        });
//
//    }

}
