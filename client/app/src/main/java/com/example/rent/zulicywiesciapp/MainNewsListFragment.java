package com.example.rent.zulicywiesciapp;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rent.zulicywiesciapp.model.FakeNewsListFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainNewsListFragment extends android.support.v4.app.Fragment {

    RecyclerView rootView;


    public MainNewsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_news_list, container, false);

        rootView = (RecyclerView) root;

        setUpWithFakeNews();
        return root;
    }

    private void setUpWithFakeNews(){

        rootView.setHasFixedSize(false);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        NewsAdapter adapter = new NewsAdapter(FakeNewsListFactory.getFakeNewsList(20),getContext());

        rootView.setLayoutManager(layoutManager);
        rootView.setAdapter(adapter);

    }
}
