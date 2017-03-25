package com.example.rent.zulicywiesciapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import com.example.rent.zulicywiesciapp.model.Author;
import com.example.rent.zulicywiesciapp.exceptions.ApiConnectException;
import com.example.rent.zulicywiesciapp.model.Login;
import com.example.rent.zulicywiesciapp.model.LoginResponse;
import com.example.rent.zulicywiesciapp.model.Register;
import com.example.rent.zulicywiesciapp.model.Status;
import com.example.rent.zulicywiesciapp.retrofit.ApiManager;
import com.example.rent.zulicywiesciapp.utils.PassEncryption;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestRetrofitActivity extends AppCompatActivity implements ApiManager.OnAuthorFetchedListener, ApiManager.OnLoginListener {

    @BindView(R.id.test)
    TextView testField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_retrofit);
        ButterKnife.bind(this);
        ApiManager.register(new Register("test3", "name", "lastname", PassEncryption.encryptString("zaq")), this);


    }

    @Override
    public void onAuthorFetched(Author author) {
        testField.setText(author.toString());
    }

    @Override
    public void onLogin(LoginResponse response) {
        if(response.getStatus().equals(Status.OK)){
            testField.setText(response.getStatus() + ": " + response.getUser());
        } else {
            testField.setText(response.getStatus().toString());
        }
    }
}
