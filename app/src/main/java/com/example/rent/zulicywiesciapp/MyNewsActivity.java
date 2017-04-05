package com.example.rent.zulicywiesciapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.rent.zulicywiesciapp.adapters.NothingToLoadAdapter;
import com.example.rent.zulicywiesciapp.exceptions.ApiConnectException;
import com.example.rent.zulicywiesciapp.model.Author;
import com.example.rent.zulicywiesciapp.model.Category;
import com.example.rent.zulicywiesciapp.model.NewsItem;
import com.example.rent.zulicywiesciapp.model.Status;
import com.example.rent.zulicywiesciapp.retrofit.ApiManager;
import com.example.rent.zulicywiesciapp.utils.NothingToDisplayMessage;
import com.example.rent.zulicywiesciapp.utils.SessionManager;

public class MyNewsActivity extends AbstractNewsListActivity implements ApiManager.OnAuthorFetchedListener,
                                                                        ApiManager.OnResultStatusListener{

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
        }
        if (intent != null) {
            startActivity(intent);
        }
        return true;

    }

    @Override
    public void onAuthorFetched(Author author) {
        if(author.getNews()!=null){
            adapter.setNewsList(author.getNews());
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
        }
        else{
            Toast.makeText(this, getString(R.string.something_went_wrong),Toast.LENGTH_SHORT).show();
        }
    }
}
