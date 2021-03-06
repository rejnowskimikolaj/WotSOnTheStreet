package com.example.rent.zulicywiesciapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rent.zulicywiesciapp.R;
import com.example.rent.zulicywiesciapp.model.NewsItem;
import com.example.rent.zulicywiesciapp.utils.CategoryUtil;
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
    public static final int FIRST_ON_PAGE=4;
    public static final int MAX_CONTENT_LENGTH=100;

    public List<NewsItem> getNewsList() {
        return newsList;
    }

    public NewsAdapter(Context context, OnNewsListItemClickListener clickListener) {
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
    public int getItemViewType(int position) {
        if(position==0) return FIRST_ON_PAGE;
        return newsList.get(position).getPriority();
    }

    @Override
    public NewsItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        if(viewType==FIRST_ON_PAGE){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listed_first_on_page_news_item, parent, false);

        }
        else if(viewType==1) {
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listed_small_news_item, parent, false);
        }
        else {
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listed_big_news_item, parent, false);

        }

        return new NewsItemHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsItemHolder holder, int position) {

        holder.newsItem = newsList.get(position);
        if(newsList.size()-1==position) holder.isLast=true;
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
        View divider;
        boolean isLast=false;

        public void setViews(){

            titleTextView.setText(newsItem.getTitle());
            if(newsItem.getContent().length() > 100) {
                concentTextView.setText(newsItem.getContent().substring(0,MAX_CONTENT_LENGTH));
            } else {
                concentTextView.setText(newsItem.getContent());
            }
            Picasso.with(context)
                    .load(newsItem.getImg_url())
                    .fit()
                    .centerCrop()
                    .into(imageView);
           // if(isLast) divider.setVisibility(View.INVISIBLE);
            setDividerColor();

        }

        private void setDividerColor(){
            if(newsItem.getCategories()!=null && newsItem.getCategories().size()!=0){
                divider.setBackgroundColor(CategoryUtil.getIdOfColorFromCategoryId(newsItem.getCategories().get(0).getId(),context));
            }
        }

        public NewsItemHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.listed_news_item_imageView);
            concentTextView = (TextView) itemView.findViewById(R.id.listed_news_item_content);
            titleTextView = (TextView) itemView.findViewById(R.id.listed_news_item_title);
            divider = itemView.findViewById(R.id.news_divider);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.OnNewsListItemClicked(newsItem);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    clickListener.onNewsListItemLongClick(newsItem);
                    return true;
                }
            });

        }
    }


    public interface OnNewsListItemClickListener{
        void OnNewsListItemClicked(NewsItem newsItem);
        void onNewsListItemLongClick(NewsItem newsItem);
    }

}
