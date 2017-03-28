package com.example.rent.zulicywiesciapp;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.rent.zulicywiesciapp.exceptions.ApiConnectException;
import com.example.rent.zulicywiesciapp.model.Author;
import com.example.rent.zulicywiesciapp.model.Category;
import com.example.rent.zulicywiesciapp.retrofit.ApiManager;
import com.example.rent.zulicywiesciapp.utils.SessionManager;

public class MyNewsActivity extends AbstractNewsListActivity implements ApiManager.OnAuthorFetchedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_news_list);
        super.setViews();
        fetchNews();
    }

    @Override
    public void inflateDrawerMenu(NavigationView nv) {
        nv.inflateMenu(R.menu.activity_capsule_drawer);
    }

    @Override
    public void fetchNews() {

        try {
            ApiManager.getAuthor(SessionManager.getInstance().getUser().getAuthorID(),this);
        } catch (ApiConnectException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setToolbar() {
        setSupportActionBar(toolbar);
        appBarLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.myNews));
        toolbarTitle.setText(R.string.my_news);
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    @Override
    public void onAuthorFetched(Author author) {
        if(author.getNews()!=null){
            adapter.setNewsList(author.getNews());
        }

        else{

            Toast.makeText(this,"You have no news added :(",Toast.LENGTH_SHORT).show();
        }

    }
}
