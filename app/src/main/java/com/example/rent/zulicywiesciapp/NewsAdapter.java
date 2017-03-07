package com.example.rent.zulicywiesciapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by User on 2017-03-07.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsItemHolder> {

    List<NewsPiece> newsList;

    @Override
    public NewsItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new NewsItemHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsItemHolder holder, int position) {


        //holder.newsItemTitleTextView.setText();

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsItemHolder extends RecyclerView.ViewHolder {

        TextView newsItemTitleTextView;
        TextView newsItemTextTextview;
        ImageView newsItemImageView;

        public NewsItemHolder(View itemView) {
            super(itemView);
            newsItemImageView = (ImageView) itemView.findViewById(R.id.news_item_imageView);
            newsItemTextTextview = (TextView) itemView.findViewById(R.id.news_item_text_textView);
            newsItemTitleTextView = (TextView) itemView.findViewById(R.id.news_item_title_textView);
        }
    }
}
