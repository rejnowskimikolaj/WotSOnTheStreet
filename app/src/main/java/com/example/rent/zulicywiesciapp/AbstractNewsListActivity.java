package com.example.rent.zulicywiesciapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.rent.zulicywiesciapp.model.NewsItem;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.rent.zulicywiesciapp.MainNewsListFragment.NEWS_ID;

public abstract class AbstractNewsListActivity extends AppCompatActivity implements NewsAdapter.OnNewsListItemClickListener
        ,NavigationView.OnNavigationItemSelectedListener{


    @BindView(R.id.activity_category_news_list_recyclerView)
    RecyclerView recyclerView;

    NewsAdapter adapter;

    @BindView(R.id.activity_category_news_list_drawer_layout)
    DrawerLayout drawerLayout;

    @BindView((R.id.toolbar))
    Toolbar toolbar;

    @BindView((R.id.app_bar_layout))
    AppBarLayout appBarLayout;

    @BindView((R.id.toolbar_title))
    AppCompatTextView toolbarTitle;


    //
    private int categoryId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_news_list);


    }


    public void setViews() {

        ButterKnife.bind(this);
        setRecyclerView();
        setToolbar();
        setTabsAndNavigationView();
    }

    private void setTabsAndNavigationView(){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        inflateDrawerMenu(navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }

    public abstract void inflateDrawerMenu(NavigationView nv);
    public abstract void fetchNews();
    private void setRecyclerView(){
        recyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new NewsAdapter(this,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public abstract void setToolbar();


    @Override
    public void OnNewsListItemClicked(NewsItem newsItem) {

        Intent newsItemActivity = new Intent(this,NewsItemActivity.class);
        newsItemActivity.putExtra(NEWS_ID,newsItem.getId());
        newsItemActivity.putExtra(NewsItemActivity.SOURCE,NewsItemActivity.NEWS_FROM_FEED);
        startActivity(newsItemActivity);
    }

}
