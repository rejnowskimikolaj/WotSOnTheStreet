package com.example.rent.zulicywiesciapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rent.zulicywiesciapp.adapters.NewsAdapter;
import com.example.rent.zulicywiesciapp.model.NewsItem;
import com.example.rent.zulicywiesciapp.retrofit.ApiManager;
import com.example.rent.zulicywiesciapp.utils.NewsListKeeper;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainNewsListFragment extends android.support.v4.app.Fragment implements ApiManager.OnNewsFetchedListener
        ,NewsAdapter.OnNewsListItemClickListener{

    RecyclerView rootView;
    public static final String NEWS_ID = "id";
    private NewsAdapter adapter;


    public MainNewsListFragment() {
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

        ApiManager.fetchNews(this);
        return root;
    }



//    private void setUpWithFakeNews() {
//        adapter.setNewsList(FakeNewsListFactory.getFakeNewsList(13));
//    }



    @Override
    public void onNewsFetched(List<NewsItem> newsList) {

        adapter.setNewsList(newsList);
        NewsListKeeper.getInstance().setList(newsList);

    }

    @Override
    public void OnNewsListItemClicked(NewsItem newsItem) {

        Intent newsItemActivity = new Intent(getActivity(),NewsItemActivity.class);
        newsItemActivity.putExtra(NEWS_ID,newsItem.getId());
        newsItemActivity.putExtra(NewsItemActivity.SOURCE,NewsItemActivity.NEWS_FROM_FEED);
        startActivity(newsItemActivity);
    }

    @Override
    public void onNewsListItemLongClick(NewsItem newsItem) {

    }
}