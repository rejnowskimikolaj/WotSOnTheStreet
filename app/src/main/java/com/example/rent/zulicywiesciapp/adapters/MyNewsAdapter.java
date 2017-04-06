package com.example.rent.zulicywiesciapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.rent.zulicywiesciapp.R;
import com.example.rent.zulicywiesciapp.model.NewsItem;
import com.example.rent.zulicywiesciapp.utils.NewsItemIconAction;

import java.util.List;

/**
 * Created by User on 2017-04-06.
 */

public class MyNewsAdapter extends NewsAdapter {

    OnMyNewsItemIconClickListener iconClickListener;
    public MyNewsAdapter(Context context, OnNewsListItemClickListener clickListener,OnMyNewsItemIconClickListener iconClickListener) {
        super(context, clickListener);
        this.iconClickListener = iconClickListener;
    }

    public MyNewsAdapter(List<NewsItem> newsList, Context context,
                         OnNewsListItemClickListener clickListener,OnMyNewsItemIconClickListener iconClickListener) {
        super(newsList, context, clickListener);
        this.iconClickListener= iconClickListener;
    }

    @Override
    public NewsItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listed_my_news_item, parent, false);

        return new MyNewsItemHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsItemHolder holder, int position) {
        holder.newsItem = newsList.get(position);
        holder.setViews();
    }

    class MyNewsItemHolder extends NewsItemHolder{

        ImageButton deleteButton;
        public MyNewsItemHolder(View itemView) {
            super(itemView);
            deleteButton = (ImageButton)itemView.findViewById(R.id.news_item_delete_button);
        }

        @Override
        public void setViews() {
            super.setViews();
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iconClickListener.onDeleteIconClick(NewsItemIconAction.DELETE,newsItem);
                }
            });
        }
    }

    public interface OnMyNewsItemIconClickListener{
        public void onDeleteIconClick(NewsItemIconAction action,NewsItem item);
    }
}
