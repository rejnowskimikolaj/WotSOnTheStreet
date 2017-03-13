package com.example.rent.zulicywiesciapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.rent.zulicywiesciapp.model.Category;
import com.example.rent.zulicywiesciapp.model.NewsItem;
import com.example.rent.zulicywiesciapp.retrofit.ApiConnectException;
import com.example.rent.zulicywiesciapp.retrofit.ApiManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryNewsListActivity extends AppCompatActivity implements ApiManager.OnCategoryFetchedListener,NewsAdapter.OnNewsListItemClickListener{

    public static final String CATEGORY_TO_LIST = "categoryToList";

    @BindView(R.id.activity_category_news_list_recyclerView)
    RecyclerView recyclerView;

    private NewsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_news_list);
        ButterKnife.bind(this);
        recyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new NewsAdapter(this,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        int categoryId = getIntent().getIntExtra(CATEGORY_TO_LIST,-1);
        if(categoryId!=-1){
            try {
                ApiManager.getCategory(categoryId,this);
            } catch (ApiConnectException e) {
                Toast.makeText(this,"Couldn't get news from this category.",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    public void onCategoryFetched(Category category) {
        adapter.setNewsList(category.getNews());

    }

    @Override
    public void OnNewsListItemClicked(NewsItem newsItem) {
    }
}
