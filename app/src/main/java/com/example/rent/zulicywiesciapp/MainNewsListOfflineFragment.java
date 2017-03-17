package com.example.rent.zulicywiesciapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rent.zulicywiesciapp.NewsAdapter;
import com.example.rent.zulicywiesciapp.NewsItemActivity;
import com.example.rent.zulicywiesciapp.R;
import com.example.rent.zulicywiesciapp.model.FakeNewsListFactory;
import com.example.rent.zulicywiesciapp.model.NewsItem;
import com.example.rent.zulicywiesciapp.model.Sort;
import com.example.rent.zulicywiesciapp.retrofit.ApiManager;

import java.util.List;


public class MainNewsListOfflineFragment extends android.support.v4.app.Fragment implements NewsAdapter.OnNewsListItemClickListener{

    RecyclerView rootView;
    public static final String NEWS_ID = "id";
    private NewsAdapter adapter;


    public MainNewsListOfflineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_news_list, container, false);

        rootView = (RecyclerView) root;
        rootView.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapter = new NewsAdapter(getContext(),this);
        rootView.setLayoutManager(layoutManager);
        rootView.setAdapter(adapter);

        setUpWithFakeNews();

        return root;
    }



    private void setUpWithFakeNews() {
        adapter.setNewsList(FakeNewsListFactory.getFakeNewsList(13));
    }





    @Override
    public void OnNewsListItemClicked(NewsItem newsItem) {

        Intent newsItemActivity = new Intent(getActivity(),NewsItemActivity.class);
        newsItemActivity.putExtra(NEWS_ID,newsItem.getId());
        startActivity(newsItemActivity);
    }
}
