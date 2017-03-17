package com.example.rent.zulicywiesciapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by User on 2017-03-08.
 */

public class FakeNewsListFactory {

    private final static String [] urlArr = {"https://images.pexels.com/photos/104827/cat-pet-animal-domestic-104827.jpeg?h=350&auto=compress&cs=tinysrgb",
            "https://images.pexels.com/photos/96938/pexels-photo-96938.jpeg?h=350&auto=compress&cs=tinysrgb",
            "https://images.pexels.com/photos/126407/pexels-photo-126407.jpeg?h=350&auto=compress&cs=tinysrgb"
    };
    private final static String lorem ="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim venia.";
    private final static String []titles = {"Omg this happened again!","New TwinPeaks season coming out","Scarlett Johannson has new boobs"};

    private final static Category [] categories ={new Category(0,""),new Category(1,""),new Category(2,"")};
    private final static Author author = new Author(1,"Jacek","Placek");
    public static List<NewsItem> getFakeNewsList(int size){

        List<NewsItem> list = new ArrayList<>();

        for(int i=1;i<=size;i++){

            NewsItem item = new NewsItem(1L, getRandomTitle(),lorem,1, author, new Date(), getRandomUrl());
            item.setCategories(Arrays.asList(categories));

            list.add(item);

        }

        return list;
    }

    private static String getRandomUrl(){
        Random r = new Random();
        return urlArr[r.nextInt(urlArr.length)];
    }

    private static String getRandomTitle(){
        Random r = new Random();
        return titles[r.nextInt(titles.length)];
    }
}
