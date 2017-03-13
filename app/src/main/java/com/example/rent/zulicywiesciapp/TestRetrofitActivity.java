package com.example.rent.zulicywiesciapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import com.example.rent.zulicywiesciapp.model.Author;
import com.example.rent.zulicywiesciapp.retrofit.ApiConnectException;
import com.example.rent.zulicywiesciapp.retrofit.ApiManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestRetrofitActivity extends AppCompatActivity implements ApiManager.OnAuthorFetchedListener {

    @BindView(R.id.test)
    TextView testField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_retrofit);
        ButterKnife.bind(this);
        try {
            ApiManager.getAuthor(2, this);
        } catch (ApiConnectException e) {
            Log.d("!!! ApiConnect ", e.getMessage());
        }

    }

    @Override
    public void onAuthorFetched(Author author) {
        testField.setText(author.toString());
    }
}
