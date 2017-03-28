package com.example.rent.zulicywiesciapp.retrofit;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NewsApiClientFactory {

    OkHttpClient.Builder builder =  new OkHttpClient.Builder();
    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

    public NewsApiClient create() {

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(httpLoggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://news.dweb.pl/")
                .addConverterFactory(GsonConverterFactory.create())
//                .client(builder.build())
                .build();

        return retrofit.create(NewsApiClient.class);
    }


}
