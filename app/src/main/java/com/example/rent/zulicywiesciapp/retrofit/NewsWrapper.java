package com.example.rent.zulicywiesciapp.retrofit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.rent.zulicywiesciapp.NewsAdapter;
import com.example.rent.zulicywiesciapp.model.NewsItem;
import com.example.rent.zulicywiesciapp.model.NewsItemList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by md on 3/9/17.
 */

public class NewsWrapper {

    private NewsApiClient newsApiClient = new NewsApiClientFactory().create();
    private final RecyclerView recyclerView;
    private final NewsAdapter adapter;
    private final Context context;


    public NewsWrapper(RecyclerView recyclerView, Context context) {
        this.recyclerView = recyclerView;
        this.adapter = (NewsAdapter) recyclerView.getAdapter();
        this.context = context;
    }

    public void getNewsList() {

        newsApiClient.getNewsList().enqueue(new Callback<NewsItemList>() {
            @Override
            public void onResponse(Call<NewsItemList> call, Response<NewsItemList> response) {
                if (response.isSuccessful()) {
                    if(adapter == null) {
                        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    adapter.setNewsList(response.body().getNews());
                    Log.d("!!! API DATA", response.body().getNews().get(0).getTitle());
                }
            }

            @Override
            public void onFailure(Call<NewsItemList> call, Throwable t) {
            }
        });

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
