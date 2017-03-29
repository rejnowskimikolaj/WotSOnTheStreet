package com.example.rent.zulicywiesciapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AddNewsActivity extends AbstractCapsuleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);

        setViews();
        checkIfLoggedIn();    }

    @Override
    void setToolbarTitle() {
        toolbarTitle.setText(R.string.add_new);
    }
}
