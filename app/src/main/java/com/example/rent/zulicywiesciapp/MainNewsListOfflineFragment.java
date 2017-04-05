package com.example.rent.zulicywiesciapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rent.zulicywiesciapp.adapters.NewsAdapter;
import com.example.rent.zulicywiesciapp.local.db.DbHelper;
import com.example.rent.zulicywiesciapp.local.db.NewsItemEntity;
import com.example.rent.zulicywiesciapp.model.FakeNewsListFactory;
import com.example.rent.zulicywiesciapp.model.NewsItem;
import com.example.rent.zulicywiesciapp.utils.EntityConverter;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MainNewsListOfflineFragment extends android.support.v4.app.Fragment implements NewsAdapter.OnNewsListItemClickListener{

    RecyclerView rootView;
    public static final String NEWS_ID = "id";
    private NewsAdapter adapter;
    Dao<NewsItemEntity,Long> dao;
    DbHelper dbHelper;




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
        dbHelper = new DbHelper(getActivity().getApplicationContext());

        dao = dbHelper.getNewsDao();

        return root;
    }

    private void setUpWithOfflineNews() {
        adapter.setNewsList(getNewsList());
    }

    private List<NewsItem> getNewsList() {
        List<NewsItemEntity> entities = getNewsFromDb();
        List<NewsItem> newsList = new ArrayList<>();
        for(NewsItemEntity entity: entities){

            newsList.add(EntityConverter.getNewsItemFromEntity(entity));
        }

        return newsList;
    }


    private List<NewsItemEntity> getNewsFromDb(){

        List<NewsItemEntity> list = new ArrayList<>();
        try {
            list = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    private void setUpWithFakeNews() {
        adapter.setNewsList(FakeNewsListFactory.getFakeNewsList(13));
    }


    @Override
    public void onResume() {
        super.onResume();
        setUpWithOfflineNews();
    }

    @Override
    public void OnNewsListItemClicked(NewsItem newsItem) {

        Intent newsItemActivity = new Intent(getActivity(),NewsItemActivity.class);
        newsItemActivity.putExtra(NEWS_ID,newsItem.getId());
        newsItemActivity.putExtra(NewsItemActivity.SOURCE,NewsItemActivity.NEWS_FROM_LOCAL_DB);
        startActivity(newsItemActivity);
    }

    @Override
    public void onNewsListItemLongClick(NewsItem newsItem) {

    }
}
