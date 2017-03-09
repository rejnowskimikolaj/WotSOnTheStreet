package com.example.rent.zulicywiesciapp.retrofit;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NewsApiClientFactory {

    public NewsApiClient create() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://news.dweb.pl/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(NewsApiClient.class);
    }


}
