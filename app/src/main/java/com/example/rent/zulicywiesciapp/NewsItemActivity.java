package com.example.rent.zulicywiesciapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rent.zulicywiesciapp.local.db.DbHelper;
import com.example.rent.zulicywiesciapp.local.db.NewsItemEntity;
import com.example.rent.zulicywiesciapp.model.NewsItem;
import com.example.rent.zulicywiesciapp.exceptions.ApiConnectException;
import com.example.rent.zulicywiesciapp.retrofit.ApiManager;
import com.example.rent.zulicywiesciapp.utils.EntityConverter;
import com.j256.ormlite.dao.Dao;
import com.squareup.picasso.Picasso;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsItemActivity extends AppCompatActivity implements ApiManager.OnNewsItemFetchedListener {

    private long newsId;
    private NewsItem newsItem;
    public final static String SOURCE = "source";
    public final static int NEWS_FROM_FEED = 5;
    public final static int NEWS_FROM_LOCAL_DB = 10;

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

    CheckBox checkBox;

    Dao<NewsItemEntity,Long> dao;
    DbHelper dbHelper = new DbHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_item);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_news_item_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_return);
        ButterKnife.bind(this);

        dao = dbHelper.getNewsDao();

        newsId = getIntent().getLongExtra(MainNewsListFragment.NEWS_ID,-1);
        if(newsId!=-1) {
            if (getSource() == NEWS_FROM_FEED) {
                try {
                    ApiManager.getNewsItem(newsId, this);
                } catch (ApiConnectException e) {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
            else if(getSource()==NEWS_FROM_LOCAL_DB){
                getNewsItemFromDatabase();
            }
        }
    }

    private void getNewsItemFromDatabase() {

        try {
            NewsItemEntity newsItemEntity = dao.queryForId(newsId);
            newsItem = EntityConverter.getNewsItemFromEntity(newsItemEntity);
            setUpViewsWithNewsItem();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.news_item_activity_toolbar_menu, menu);
        checkBox = (CheckBox) menu.findItem(R.id.action_save).getActionView();
        setCheckBox();
        return true;
    }

    private void setCheckBox(){
        if(getSource()==NEWS_FROM_FEED){
            checkBox.setChecked(false);
        }
        else checkBox.setChecked(true);

        checkBox.setText(R.string.db_checkbox_text);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) saveToDatabase();
                else deleteFromDatabase();
            }
        });
    }

    private void deleteFromDatabase() {
        try {
            dao.delete(EntityConverter.getNewsEntityFromNewsItem(newsItem));
            Toast.makeText(this,R.string.deleted_toast,Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveToDatabase() {

        try {
            dao.createOrUpdate(EntityConverter.getNewsEntityFromNewsItem(newsItem));
            Toast.makeText(this,R.string.saved_toast,Toast.LENGTH_SHORT).show();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

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

    private int getSource(){
        int source = getIntent().getIntExtra(SOURCE,-1);

        return source;
    }
}