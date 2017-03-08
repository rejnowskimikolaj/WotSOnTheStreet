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

import java.util.List;

/**
 * Created by User on 2017-03-07.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsItemHolder> {

    List<NewsItem> newsList;
    Context context;

    public NewsAdapter(List<NewsItem> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @Override
    public NewsItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new NewsItemHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsItemHolder holder, int position) {


        holder.titleTextView.setText(newsList.get(position).getTitle());
        holder.concentTextView.setText(newsList.get(position).getContent());
        Picasso.with(context)
                .load(newsList.get(position).getUrl())
                .fit()
                .centerCrop()
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsItemHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView concentTextView;
        ImageView imageView;

        public NewsItemHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.news_item_imageView);
            concentTextView = (TextView) itemView.findViewById(R.id.news_item_text_textView);
            titleTextView = (TextView) itemView.findViewById(R.id.news_item_title_textView);
        }
    }
}
