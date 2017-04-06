package com.example.rent.zulicywiesciapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.rent.zulicywiesciapp.adapters.MyNewsAdapter;
import com.example.rent.zulicywiesciapp.adapters.NewsAdapter;
import com.example.rent.zulicywiesciapp.adapters.NothingToLoadAdapter;
import com.example.rent.zulicywiesciapp.exceptions.ApiConnectException;
import com.example.rent.zulicywiesciapp.model.Author;
import com.example.rent.zulicywiesciapp.model.Category;
import com.example.rent.zulicywiesciapp.model.NewsItem;
import com.example.rent.zulicywiesciapp.model.Status;
import com.example.rent.zulicywiesciapp.retrofit.ApiManager;
import com.example.rent.zulicywiesciapp.utils.NewsItemIconAction;
import com.example.rent.zulicywiesciapp.utils.NothingToDisplayMessage;
import com.example.rent.zulicywiesciapp.utils.SessionManager;

import java.util.List;

public class MyNewsActivity extends AbstractNewsListActivity implements ApiManager.OnAuthorFetchedListener
                                                                        ,MyNewsAdapter.OnMyNewsItemIconClickListener,
                                                                        ApiManager.OnResultStatusListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_news_list);
        super.setViews();
        fetchNews();
    }

    @Override
    public void setToolbar() {
        setSupportActionBar(toolbar);
        appBarLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.myNews));
        toolbarTitle.setText(R.string.my_news);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_news_activity_toolbar_menu, menu);
        return true;    }

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
    public boolean onOptionsItemSelected(MenuItem item) {
       super.onOptionsItemSelected(item);
        int id = item.getItemId();
        if(id == R.id.action_deleteNews) onActionDeleteNewsClicked();
        return true;
    }

    private void onActionDeleteNewsClicked() {

        if(adapter instanceof MyNewsAdapter){
            List<NewsItem> list =  adapter.getNewsList();
            adapter = new NewsAdapter(list,this,this);
            recyclerView.setAdapter(adapter);
       }
       else {
            List<NewsItem> list =  adapter.getNewsList();
            adapter = new MyNewsAdapter(list,this,this,this);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int itemIdid = menuItem.getItemId();
        drawerLayout.closeDrawer(GravityCompat.START);
        Intent intent = null;
        switch (itemIdid) {
            case R.id.nav_home:
                intent = new Intent(this, MainActivity.class);
                break;
            case R.id.nav_capsule:
                intent = new Intent(this, CapsuleActivity.class);
                break;
            case R.id.nav_my_news:
                intent = new Intent(this, MyNewsActivity.class);
                break;
            case R.id.nav_add_new:
                intent = new Intent(this,AddNewsActivity.class);
                break;
            case R.id.nav_logout:
                logout();
                intent = new Intent(this, MainActivity.class);
                finish();
        }
        if (intent != null) {
            startActivity(intent);
        }
        return true;

    }

    public void logout(){
        SessionManager.logout(this);
        Toast.makeText(this,getString(R.string.logged_out),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthorFetched(Author author) {
        if(author.getNews()!=null &&!author.getNews().isEmpty()){
            //adapter.setNewsList(author.getNews());
            adapter = new NewsAdapter(author.getNews(),this,this);
            recyclerView.setAdapter(adapter);
        }

        else{
            recyclerView.setAdapter(new NothingToLoadAdapter(NothingToDisplayMessage.NO_NEWS,this));
        }

    }

    @Override
    public void onNewsListItemLongClick(NewsItem newsItem) {

        showIfDeleteDialog(newsItem);
    }

    private void showIfDeleteDialog(final NewsItem newsItem){
        AlertDialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.delete_question))
                .setCancelable(true)
                .setIcon(R.drawable.ic_warning)
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onYesDeleteClicked(newsItem);
                        dialog.dismiss();
                    }
                });
        dialog = builder.create();
        dialog.show();
    }

    private void onYesDeleteClicked(NewsItem newsItem) {

        ApiManager.deleteNews(SessionManager.getInstance().getUser().getToken()
                                ,newsItem.getId()
                                ,this);

    }

    @Override
    public void onDeleteResult(Status response) {

        if(response==Status.UNAUTHORISED){
            startActivity(new Intent(this,LoginActivity.class));
        }
        else if(response==Status.OK){
            Toast.makeText(this,getString(R.string.news_deleted),Toast.LENGTH_SHORT).show();
            fetchNews();
        }
        else if(response==Status.ERROR){
            Toast.makeText(this, getString(R.string.something_went_wrong),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDeleteIconClick(NewsItemIconAction action,NewsItem item) {

        showIfDeleteDialog(item);
    }
}
