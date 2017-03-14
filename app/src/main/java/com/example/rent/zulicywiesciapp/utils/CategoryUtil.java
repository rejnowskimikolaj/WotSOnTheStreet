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

        }

        return colorId;
    }

}
