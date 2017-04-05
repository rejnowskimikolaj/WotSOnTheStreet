package com.example.rent.zulicywiesciapp.adapters;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rent.zulicywiesciapp.R;
import com.example.rent.zulicywiesciapp.utils.NothingToDisplayMessage;

import static com.example.rent.zulicywiesciapp.utils.NothingToDisplayMessage.NO_NEWS_ADDED_YET;

/**
 * Created by User on 2017-04-05.
 */

public class NothingToLoadAdapter extends RecyclerView.Adapter<NothingToLoadAdapter.NothingToLoadHolder>{

    private NothingToDisplayMessage message;
    private Context context;

    public NothingToLoadAdapter(NothingToDisplayMessage message, Context context) {
        this.message = message;
        this.context = context;
    }


    public void setMessage(NothingToDisplayMessage message){
        this.message=message;
        notifyDataSetChanged();
    }

    @Override
    public NothingToLoadHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.nothing_to_display_layout, viewGroup, false);

        return new NothingToLoadHolder(view);
    }

    @Override
    public void onBindViewHolder(NothingToLoadHolder nothingToLoadHolder, int i) {

        nothingToLoadHolder.message.setText(message.toString());
        if(message== NO_NEWS_ADDED_YET){
            nothingToLoadHolder.imageView.setImageDrawable(context.getDrawable(R.drawable.ic_no_added_yet));
        }

        String displayMessage = "";
        switch (message){
            case NO_NEWS_ADDED_YET: displayMessage=context.getString(R.string.no_news_added_yet);
                break;
            case NOTHING_TO_DISPLAY: displayMessage = context.getString(R.string.nothing_to_display);
                break;
            case SOMETHING_WENT_WRONG: displayMessage=context.getString(R.string.something_went_wrong);
                break;
            case NO_NEWS: displayMessage= context.getString(R.string.no_news);
        }
        nothingToLoadHolder.message.setText(displayMessage);
    }


    @Override
    public int getItemCount() {
        return 1;
    }

    public class NothingToLoadHolder extends RecyclerView.ViewHolder {
        TextView message;
        ImageView imageView;


        public NothingToLoadHolder(View itemView) {
            super(itemView);
            message = (TextView)itemView.findViewById(R.id.nothing_message);
            imageView = (ImageView) itemView.findViewById(R.id.nothing_imageview);
        }
    }

    }
