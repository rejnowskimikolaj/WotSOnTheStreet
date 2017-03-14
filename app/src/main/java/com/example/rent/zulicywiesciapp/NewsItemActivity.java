package com.example.rent.zulicywiesciapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< HEAD
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class NewsItemActivity extends AppCompatActivity {
=======
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rent.zulicywiesciapp.model.NewsItem;
import com.example.rent.zulicywiesciapp.retrofit.ApiConnectException;
import com.example.rent.zulicywiesciapp.retrofit.ApiManager;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsItemActivity extends AppCompatActivity implements ApiManager.OnNewsItemFetchedListener {

    private long newsId;
    private NewsItem newsItem;
    @BindView(R.id.activity_news_item_title)
    AppCompatTextView title;
    @BindView(R.id.activity_news_item_content)
    AppCompatTextView content;
    @BindView(R.id.activity_news_item_imageView)
    ImageView imageView;
    @BindView(R.id.activity_news_item_publ_author_text)
    AppCompatTextView author;
    @BindView(R.id.activity_news_item_publ_date_text)
    AppCompatTextView date;

>>>>>>> backend

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_item);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_news_item_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_return);
<<<<<<< HEAD
=======
        ButterKnife.bind(this);

        newsId = getIntent().getLongExtra(MainNewsListFragment.NEWS_ID,-1);
        if(newsId!=-1) try {
            ApiManager.getNewsItem(newsId,this);
        } catch (ApiConnectException e) {
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
        }
>>>>>>> backend
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_item_activity_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
<<<<<<< HEAD
}
=======

    @Override
    public void onNewsItemFetched(NewsItem news) {
        newsItem = news;
        setUpViewsWithNewsItem();

    }

    private void setUpViewsWithNewsItem(){
        title.setText(newsItem.getTitle());
        content.setText(newsItem.getContent());
        Picasso.with(this)
                .load(newsItem.getImg_url())
                .fit()
                .centerCrop()
                .into(imageView);
        author.setText(newsItem.getAuthor().getName()+" "+newsItem.getAuthor().getLastname());
        String weekDay =new SimpleDateFormat("EEEE", Locale.US).format(newsItem.getDate());
        date.setText("Published: "+weekDay);
    }
}
>>>>>>> backend
