package com.example.rent.zulicywiesciapp.local.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.rent.zulicywiesciapp.model.NewsItem;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by User on 2017-03-19.
 */

public class DbHelper extends OrmLiteSqliteOpenHelper {

    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "news.db";
    private Dao<NewsItemEntity,Long> newsDao;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, NewsItemEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try {
            TableUtils.dropTable(connectionSource,NewsItemEntity.class,true);
            onCreate(database);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Dao<NewsItemEntity,Long> getNewsDao(){

        if (newsDao==null){
            try {
                newsDao =getDao(NewsItemEntity.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return newsDao;
    }
}
