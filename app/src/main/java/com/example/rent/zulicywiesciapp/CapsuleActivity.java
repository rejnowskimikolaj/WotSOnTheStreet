package com.example.rent.zulicywiesciapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.rent.zulicywiesciapp.adapters.NewsAdapter;
import com.example.rent.zulicywiesciapp.adapters.NothingToLoadAdapter;
import com.example.rent.zulicywiesciapp.exceptions.ApiConnectException;
import com.example.rent.zulicywiesciapp.model.Author;
import com.example.rent.zulicywiesciapp.model.NewsItem;
import com.example.rent.zulicywiesciapp.model.User;
import com.example.rent.zulicywiesciapp.retrofit.ApiManager;
import com.example.rent.zulicywiesciapp.utils.NothingToDisplayMessage;
import com.example.rent.zulicywiesciapp.utils.SessionManager;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

import static com.example.rent.zulicywiesciapp.MainNewsListFragment.NEWS_ID;

public class CapsuleActivity extends AbstractCapsuleActivity implements ApiManager.OnAuthorFetchedListener,NewsAdapter.OnNewsListItemClickListener{

    private final int maxAmountOfDisplayedAddedNewsItems = 3;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.user_data_login)
    AppCompatTextView loginData;

    @BindView(R.id.user_data_lastLogin)
    AppCompatTextView lastLoginData;

    @BindView(R.id.user_data_name)
    AppCompatTextView userNameData;

    NewsAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capsule);
        setViews();
       // checkIfLoggedIn();

    }


    private void setUserDataInFields(){
        User user = SessionManager.getInstance().getUser();
        if(user!=null){
            userNameData.setText(user.getName()+" "+user.getLastname());
            loginData.setText(user.getUsername());
            if(user.getLastLogin()!=null){
                lastLoginData.setText(user.getLastLogin().toString());
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        checkIfLoggedIn();
        setRecyclerView();

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
            else {
                recyclerView.setAdapter(new NothingToLoadAdapter(NothingToDisplayMessage.NO_NEWS,this));

            }
        }
    }

    private List<NewsItem> getSmallerNewsList(List<NewsItem> source){
        List<NewsItem> smallerList = new LinkedList<>();

        int counter = 1;
        for(int i=source.size()-1;i>=0;i--){
            if(counter>maxAmountOfDisplayedAddedNewsItems)break;

            smallerList.add(source.get(i));
            counter++;
        }

        return smallerList;
    }

    @Override
    public void onAuthCheck(Boolean response) {
        super.onAuthCheck(response);
        if(response){
            setUserDataInFields();
            if(isAuthor()){
                getAuthorData();
            }
            else {
                displayNotAuthorYet();
            }
        }

    }

    private void displayNotAuthorYet() {
        recyclerView.setAdapter(new NothingToLoadAdapter(NothingToDisplayMessage.NO_NEWS_ADDED_YET,this));

    }

    private boolean isAuthor() {
        return SessionManager.getInstance().getUser().getAuthorID()!=null;
    }

    private void getAuthorData(){
        try {

            ApiManager.getAuthor(SessionManager.getInstance().getUser().getAuthorID(),this);
        } catch (ApiConnectException e) {
            e.printStackTrace();
        }
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
