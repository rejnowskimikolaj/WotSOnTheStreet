package com.example.rent.zulicywiesciapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.rent.zulicywiesciapp.model.Categories;
import com.example.rent.zulicywiesciapp.model.Category;
import com.example.rent.zulicywiesciapp.model.NewsItem;
import com.example.rent.zulicywiesciapp.retrofit.ApiConnectException;
import com.example.rent.zulicywiesciapp.retrofit.ApiManager;
import com.example.rent.zulicywiesciapp.utils.CategoryUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.rent.zulicywiesciapp.MainNewsListFragment.NEWS_ID;

public class CategoryNewsListActivity extends AppCompatActivity implements ApiManager.OnCategoryFetchedListener,NewsAdapter.OnNewsListItemClickListener
                                                                            ,NavigationView.OnNavigationItemSelectedListener{

    public static final String CATEGORY_TO_LIST = "categoryToList";

    @BindView(R.id.activity_category_news_list_recyclerView)
    RecyclerView recyclerView;

    private NewsAdapter adapter;

    @BindView(R.id.activity_category_news_list_drawer_layout)
    DrawerLayout drawerLayout;

    @BindView((R.id.activity_category_news_list_main_toolbar))
    Toolbar toolbar;

//
    private int categoryId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_news_list);

        categoryId = getIntent().getIntExtra(CATEGORY_TO_LIST,-1);
        setViews();
        
        if(categoryId !=-1){
            try {
                ApiManager.getCategory(categoryId,this);
            } catch (ApiConnectException e) {
                Toast.makeText(this,"Couldn't get news from this category.",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void setViews() {

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
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
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
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setBackgroundColor(CategoryUtil.getIdOfColorFromCategoryId(categoryId,this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_toolbar_menu, menu);
        return true;
    }

    @Override
    public void onCategoryFetched(Category category) {
        adapter.setNewsList(category.getNews());

    }

    @Override
    public void OnNewsListItemClicked(NewsItem newsItem) {

        Intent newsItemActivity = new Intent(this,NewsItemActivity.class);
        newsItemActivity.putExtra(NEWS_ID,newsItem.getId());
        startActivity(newsItemActivity);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemIdid = item.getItemId();
        drawerLayout.closeDrawer(GravityCompat.START);
        if(itemIdid==R.id.nav_home){
            startActivity(new Intent(this,MainActivity.class));
            return true;
        }

        Intent categoryActivity = new Intent(this,CategoryNewsListActivity.class);
        int categoryId=-1;


        switch (itemIdid) {
            case R.id.nav_art:
                categoryId= Categories.ART.getId();
                break;
            case R.id.nav_economics:
                categoryId= Categories.ECONOMICS.getId();
                break;
            case R.id.nav_politics:
                categoryId= Categories.POLITICS.getId();
                break;
            case R.id.nav_technology:
                categoryId= Categories.TECHNOLOGY.getId();
                break;
            case R.id.nav_sport:
                categoryId= Categories.SPORT.getId();
        }

        categoryActivity.putExtra(CategoryNewsListActivity.CATEGORY_TO_LIST,categoryId);
        startActivity(categoryActivity);
        return true;
    }
}
