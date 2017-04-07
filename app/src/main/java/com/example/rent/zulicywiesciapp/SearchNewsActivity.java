package com.example.rent.zulicywiesciapp;

import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SearchViewCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.rent.zulicywiesciapp.adapters.NewsAdapter;
import com.example.rent.zulicywiesciapp.model.NewsItem;
import com.example.rent.zulicywiesciapp.utils.CategoryUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchNewsActivity extends AppCompatActivity implements NewsAdapter.OnNewsListItemClickListener, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.activity_category_news_list_recyclerView)
    RecyclerView recyclerView;

    private NewsAdapter adapter;

    @BindView(R.id.activity_category_news_list_drawer_layout)
    DrawerLayout drawerLayout;

    @BindView((R.id.toolbar))
    Toolbar toolbar;

    @BindView((R.id.search_edit))
    SearchView searchEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_news);
        setViews();

    }

    private void setViews() {

        ButterKnife.bind(this);
        setRecyclerView();
        setToolbar();
        setTabsAndNavigationView();
        setSearchView();
    }

    private void setSearchView(){

        searchEdit.setIconifiedByDefault(false);
    }

    private void setTabsAndNavigationView(){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        setDrawerLayout(navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }

    private void setDrawerLayout(NavigationView nv){
        nv.inflateMenu(R.menu.activity_main_drawer);

    }

    private void setRecyclerView(){
        recyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new NewsAdapter(this,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
    }

    @Override
    public void OnNewsListItemClicked(NewsItem newsItem) {

    }

    @Override
    public void onNewsListItemLongClick(NewsItem newsItem) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}
