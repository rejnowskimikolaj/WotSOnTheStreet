package com.example.rent.zulicywiesciapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rent.zulicywiesciapp.model.NewsItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2017-03-07.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsItemHolder> {

    List<NewsItem> newsList;
    Context context;
    OnNewsListItemClickListener clickListener;

    /* added by md */
    public NewsAdapter(Context context,OnNewsListItemClickListener clickListener) {
        newsList = new ArrayList<>();
        this.context = context;
        this.clickListener = clickListener;
    }

    public NewsAdapter(List<NewsItem> newsList, Context context,OnNewsListItemClickListener clickListener) {
        this.newsList = newsList;
        this.clickListener = clickListener;
        this.context = context;
    }

    /* added by md */
    public void setNewsList(List<NewsItem> newsList) {
        this.newsList = newsList;
        notifyDataSetChanged();
    }

    @Override
    public NewsItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new NewsItemHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsItemHolder holder, int position) {

        holder.newsItem = newsList.get(position);
        holder.setViews();

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsItemHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView concentTextView;
        ImageView imageView;
        NewsItem newsItem;

        public void setViews(){

            titleTextView.setText(newsItem.getTitle());
            concentTextView.setText(newsItem.getContent());
            Picasso.with(context)
                    .load(newsItem.getImg_url())
                    .fit()
                    .centerCrop()
                    .into(imageView);
        }

        public NewsItemHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.news_item_imageView);
            concentTextView = (TextView) itemView.findViewById(R.id.news_item_text_textView);
            titleTextView = (TextView) itemView.findViewById(R.id.news_item_title_textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.OnNewsListItemClicked(newsItem);
                }
            });

        }
    }

    public interface OnNewsListItemClickListener{
        void OnNewsListItemClicked(NewsItem newsItem);
    }
}