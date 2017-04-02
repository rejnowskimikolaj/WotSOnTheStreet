package com.example.rent.zulicywiesciapp.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.example.rent.zulicywiesciapp.R;

/**
 * Created by User on 2017-03-14.
 */

public class CategoryUtil {

    public static final int POLITICS = 1;
    public static final int SPORT = 2;
    public static final int ECONOMICS = 3;
    public static final int ART = 4;
    public static final int TECHNOLOGY = 5;
    public static final int SAVED_NEWS = 6;



    public static int getCategoryIdFromString(String category){
        int id = 0;
        if(category.equals("Politics")){
            id=POLITICS;
        }
        else if(category.equals("Sports")){
            id = SPORT;
        }
        else if(category.equals("Economics")){
            id = ECONOMICS;
        }
        else if(category.equals("Art")){
            id = ART;
        }
        else if(category.equals("Technology")){
            id = TECHNOLOGY;
        }

        return id;
    }
    public static int getIdOfColorFromCategoryId(int idOfCategory,Context context){

        int colorId=ContextCompat.getColor(context, R.color.colorPrimary);

        switch (idOfCategory){
            case POLITICS: colorId=ContextCompat.getColor(context, R.color.politicsCategory);
                break;
            case SPORT: colorId=ContextCompat.getColor(context, R.color.sportCategory);
                break;
            case ECONOMICS: colorId=ContextCompat.getColor(context, R.color.economicsCategory);
                break;
            case ART: colorId=ContextCompat.getColor(context, R.color.artCategory);
                break;
            case TECHNOLOGY: colorId=ContextCompat.getColor(context, R.color.technologyCategory);
                break;
            case SAVED_NEWS: colorId=ContextCompat.getColor(context, R.color.savedNewsCategory);
                break;

        }

        return colorId;
    }

    public static String getCategoryNameFromId(int id){
        String category = "";
        switch (id){
            case POLITICS:
                category="Politics";
                break;
            case SPORT:
                category="Sport";
                break;
            case ECONOMICS:
                category="Economics";
                break;
            case ART:
                category="Art";
                break;
            case TECHNOLOGY:
                category="Technology";
                break;
            case SAVED_NEWS:
                category="Saved News";
        }

        return category;
    }

}
