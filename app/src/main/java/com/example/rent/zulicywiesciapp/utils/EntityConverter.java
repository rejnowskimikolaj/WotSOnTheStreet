package com.example.rent.zulicywiesciapp.utils;

import com.example.rent.zulicywiesciapp.local.db.NewsItemEntity;
import com.example.rent.zulicywiesciapp.model.Author;
import com.example.rent.zulicywiesciapp.model.Category;
import com.example.rent.zulicywiesciapp.model.NewsItem;
import com.j256.ormlite.field.DatabaseField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 2017-03-19.
 */

public class EntityConverter {

    public static final Category[] categories = {new Category(6,CategoryUtil.getCategoryNameFromId(6))};
    public static final int localAuthorId = -1;

    public static NewsItemEntity getNewsEntityFromNewsItem(NewsItem newsItem){
        Long id = newsItem.getId();
        String title = newsItem.getTitle();
        String content = newsItem.getContent();
        String author = newsItem.getAuthor().getName()+" "+newsItem.getAuthor().getLastname();
        Date date = newsItem.getDate();
        String img_url = newsItem.getImg_url();
        return new NewsItemEntity(id,title,content,author,date,img_url);
    }

    public static NewsItem getNewsItemFromEntity(NewsItemEntity entity){

        Long id = entity.getId();
        String title = entity.getTitle();
        String content = entity.getContent();
        Integer priority = 1;
        Author author =  getAuthorFromString(entity.getAuthor());
        Date date = entity.getDate();
        String img_url = entity.getImg_url();
        return new NewsItem(id,title,content,priority,author,date,img_url, Arrays.asList(categories));

    }

    public static Author getAuthorFromString(String authorString){

        String [] names =authorString.split(" ");
        String firstName = names[0];
        String lastName = "";
        for(int i = 1;i<names.length;i++){
            lastName+=names[i]+" ";
        }
        lastName.trim();

        return new Author(localAuthorId,firstName,lastName);

    }
}
