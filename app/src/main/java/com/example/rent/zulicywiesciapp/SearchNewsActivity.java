package com.example.rent.zulicywiesciapp;

import android.content.Context;
import android.content.Intent;
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
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rent.zulicywiesciapp.adapters.NewsAdapter;
import com.example.rent.zulicywiesciapp.adapters.NothingToLoadAdapter;
import com.example.rent.zulicywiesciapp.model.NewsItem;
import com.example.rent.zulicywiesciapp.utils.CategoryUtil;
import com.example.rent.zulicywiesciapp.utils.NewsListKeeper;
import com.example.rent.zulicywiesciapp.utils.NothingToDisplayMessage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.rent.zulicywiesciapp.MainNewsListFragment.NEWS_ID;

public class SearchNewsActivity extends AppCompatActivity implements NewsAdapter.OnNewsListItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private NewsAdapter adapter;

    @BindView((R.id.toolbar))
    Toolbar toolbar;

    @BindView((R.id.search_edit))
    AppCompatEditText searchEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_news);
        setViews();
        loadNews();
    }

    private void setViews() {

        ButterKnife.bind(this);
        setRecyclerView();
        setToolbar();
        setSearchView();
    }

    private void loadNews(){
        adapter.setNewsList(NewsListKeeper.getList());
    }

    private void setSearchView(){

        searchEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch();
                    return true;
                }
                return false;
            }
        });
    }

    private void performSearch() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        String phrase = searchEdit.getText().toString();
        List<NewsItem> listToSearchFrom = adapter.getNewsList();
        List<NewsItem> searchOutput = new ArrayList<>();
        for(NewsItem item:listToSearchFrom){
            if(itemContainsPhrase(item,phrase)) searchOutput.add(item);
        }
        if(searchOutput.isEmpty()) recyclerView.setAdapter(new NothingToLoadAdapter(NothingToDisplayMessage.NO_NEWS,this));
        else recyclerView.setAdapter(new NewsAdapter(searchOutput,this,this));
        Toast.makeText(this,getString(R.string.search_records)+" "+searchOutput.size(),Toast.LENGTH_SHORT).show();
    }

    private boolean itemContainsPhrase(NewsItem item, String phrase) {
        String lowerCasePhrase = phrase.toLowerCase();
        if(item.getTitle().toLowerCase().contains(lowerCasePhrase))return true;
        if(item.getContent().toLowerCase().contains(lowerCasePhrase)) return true;
        return false;
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
        toolbar.setNavigationIcon(R.drawable.ic_return);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_search_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_clean:
                searchEdit.setText("");
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void OnNewsListItemClicked(NewsItem newsItem) {


            Intent newsItemActivity = new Intent(this,NewsItemActivity.class);
            newsItemActivity.putExtra(NEWS_ID,newsItem.getId());
            newsItemActivity.putExtra(NewsItemActivity.SOURCE,NewsItemActivity.NEWS_FROM_FEED);
            startActivity(newsItemActivity);

    }

    @Override
    public void onNewsListItemLongClick(NewsItem newsItem) {

    }
}
