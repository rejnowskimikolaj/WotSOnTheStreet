package com.example.rent.zulicywiesciapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.rent.zulicywiesciapp.exceptions.ApiConnectException;
import com.example.rent.zulicywiesciapp.model.Author;
import com.example.rent.zulicywiesciapp.model.NewsItem;
import com.example.rent.zulicywiesciapp.retrofit.ApiManager;
import com.example.rent.zulicywiesciapp.utils.CategoryUtil;
import com.example.rent.zulicywiesciapp.utils.SessionManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.rent.zulicywiesciapp.MainNewsListFragment.NEWS_ID;

public class CapsuleActivity extends AbstractCapsuleActivity implements ApiManager.OnAuthorFetchedListener,NewsAdapter.OnNewsListItemClickListener{

    private final int maxAmountOfDisplayedAddedNewsItems = 3;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capsule);
        setViews();
        setRecyclerView();
        checkIfLoggedIn();


    }

    @Override
    void setToolbarTitle() {
        toolbarTitle.setText(R.string.capsule);
    }

    private void setRecyclerView(){
        recyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new NewsAdapter(this,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onAuthorFetched(Author author) {

        if(author!=null){
            List<NewsItem> list = author.getNews();
            if(!list.isEmpty()) {
                List<NewsItem> smallerList = getSmallerNewsList(list);
                adapter.setNewsList(smallerList);
            }
        }
    }

    private List<NewsItem> getSmallerNewsList(List<NewsItem> source){
        List<NewsItem> smallerList = new ArrayList<>();
        smallerList.add(source.get(source.size()-1));
        for(int i=1;i<=maxAmountOfDisplayedAddedNewsItems;i++){

           //TODO: adding items to the smaller list
        }
        return smallerList;
    }

    @Override
    public void onAuthCheck(Boolean response) {
        super.onAuthCheck(response);
        if(response){
            try {
                ApiManager.getAuthor(SessionManager.getInstance().getUser().getAuthorID(),this);
            } catch (ApiConnectException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void OnNewsListItemClicked(NewsItem newsItem) {
        Intent newsItemActivity = new Intent(this,NewsItemActivity.class);
        newsItemActivity.putExtra(NEWS_ID,newsItem.getId());
        newsItemActivity.putExtra(NewsItemActivity.SOURCE,NewsItemActivity.NEWS_FROM_FEED);
        startActivity(newsItemActivity);
    }
}
