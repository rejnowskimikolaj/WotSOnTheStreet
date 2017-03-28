package com.example.rent.zulicywiesciapp;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.rent.zulicywiesciapp.model.Login;
import com.example.rent.zulicywiesciapp.model.LoginResponse;
import com.example.rent.zulicywiesciapp.model.Status;
import com.example.rent.zulicywiesciapp.model.User;
import com.example.rent.zulicywiesciapp.retrofit.ApiManager;
import com.example.rent.zulicywiesciapp.utils.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements ApiManager.OnLoginListener {

    @BindView((R.id.toolbar))
    Toolbar toolbar;

    @BindView((R.id.toolbar_title))
    AppCompatTextView toolbarTitle;

    @BindView(R.id.login_edit)
    TextInputEditText loginInput;

    @BindView(R.id.password_edit)
    TextInputEditText passwordInput;

    @BindView(R.id.password_layout)
    TextInputLayout passwordLayout;
    @BindView(R.id.login_layout)
    TextInputLayout loginLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setViews();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    }

    private void setViews() {

        ButterKnife.bind(this);
        setToolbar();
        setTextWatchers();
    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        toolbarTitle.setText(R.string.login);
        toolbar.setNavigationIcon(R.drawable.ic_return);
    }

    private void setTextWatchers(){
        setTextWatcher(loginInput,loginLayout);
        setTextWatcher(passwordInput,passwordLayout);
    }
    private void setTextWatcher(final TextInputEditText editText, final TextInputLayout inputLayout) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (inputLayout.isErrorEnabled()) {
                    inputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.login_button)
    public void onLoginButtonClicked(){
        boolean error = false;

        if (TextUtils.isEmpty(loginInput.getText().toString())) {
            loginLayout.setError(getString(R.string.empty_login_error));
            error = true;
        }

        if (TextUtils.isEmpty(passwordInput.getText().toString())) {
            passwordLayout.setError(getString(R.string.empty_password_error));
            error = true;
        }

        if (!error) {

            tryLogin();
        }
    }

    private void tryLogin() {

        Login login = new Login(loginInput.getText().toString(),passwordInput.getText().toString());
        ApiManager.login(login,this);
    }

    @Override
    public void onLogin(LoginResponse response) {
        Status status = response.getStatus();
        switch(status){
            case OK:
                onLoggedIn(response.getUser());
                break;
            case WRONG_PASSWORD:
                Toast.makeText(this,"Password incorrect",Toast.LENGTH_SHORT).show();
                break;
            case NO_USER:
                Toast.makeText(this,"User doesn't exist",Toast.LENGTH_SHORT).show();
                break;
            case ERROR:
                Toast.makeText(this,R.string.server_problem,Toast.LENGTH_SHORT).show();
        }

    }

    private void onLoggedIn(User user) {
        Toast.makeText(this,"Logged In, "+user.getLastname(),Toast.LENGTH_SHORT).show();
        SessionManager.getInstance().setUser(user);

    }


}
