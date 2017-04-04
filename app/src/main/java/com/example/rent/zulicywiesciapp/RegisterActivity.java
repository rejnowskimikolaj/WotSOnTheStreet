package com.example.rent.zulicywiesciapp;

import android.content.Intent;
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
import android.widget.Button;
import android.widget.Toast;

import com.example.rent.zulicywiesciapp.model.LoginResponse;
import com.example.rent.zulicywiesciapp.model.Register;
import com.example.rent.zulicywiesciapp.model.Status;
import com.example.rent.zulicywiesciapp.model.User;
import com.example.rent.zulicywiesciapp.retrofit.ApiManager;
import com.example.rent.zulicywiesciapp.utils.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements ApiManager.OnLoginListener{

    @BindView((R.id.toolbar))
    Toolbar toolbar;

    @BindView((R.id.toolbar_title))
    AppCompatTextView toolbarTitle;

    @BindView(R.id.firstName_edit)
    TextInputEditText firstNameInput;
    @BindView(R.id.lastName_edit)
    TextInputEditText lastNameInput;
    @BindView(R.id.login_edit)
    TextInputEditText loginInput;
    @BindView(R.id.password_edit)
    TextInputEditText passwordInput;
    @BindView(R.id.password2_edit)
    TextInputEditText password2Input;
    @BindView(R.id.login_layout)
    TextInputLayout loginLayout;
    @BindView(R.id.firstName_layout)
    TextInputLayout fistNameLayout;
    @BindView(R.id.lastName_layout)
    TextInputLayout lastNameLayout;
    @BindView(R.id.password_layout)
    TextInputLayout passwordLayout;
    @BindView(R.id.password2_layout)
    TextInputLayout password2Layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setViews();
    }

    private void setViews() {

        ButterKnife.bind(this);
        setToolbar();
        setTextWatchers();
    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        toolbarTitle.setText(R.string.register);
        toolbar.setNavigationIcon(R.drawable.ic_return);
    }

    private void setTextWatchers() {
        setTextWatcher(loginInput,loginLayout);
        setTextWatcher(passwordInput,passwordLayout);
        setTextWatcher(password2Input,password2Layout);
        setTextWatcher(firstNameInput,fistNameLayout);
        setTextWatcher(lastNameInput,lastNameLayout);
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

    @OnClick(R.id.register_button)
    public void onRegisterButtonClicked(){
        boolean error = false;

        if (TextUtils.isEmpty(loginInput.getText().toString())) {
            loginLayout.setError(getString(R.string.empty_login_error));
            error = true;
        }

        if (TextUtils.isEmpty(firstNameInput.getText().toString())) {
            fistNameLayout.setError(getString(R.string.empty_field_error));
            error = true;
        }

        if (TextUtils.isEmpty(lastNameInput.getText().toString())) {
            lastNameInput.setError(getString(R.string.empty_field_error));
            error = true;
        }

        String password = passwordInput.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordLayout.setError(getString(R.string.empty_password_error));
            error = true;
        }
        else if(password.length()<6){
            passwordLayout.setError(getString(R.string.to_short_pass_error));
            error=true;

        }
        if (TextUtils.isEmpty(password2Input.getText().toString())) {
            passwordLayout.setError(getString(R.string.empty_password_error));
            error = true;
        }
        if(!password2Input.getText().toString().equals(passwordInput.getText().toString())){
            passwordLayout.setError(getString(R.string.not_exact_passes_error));
            password2Layout.setError(getString(R.string.not_exact_passes_error));
            error = true;
        }


        if (!error) {

            tryRegister();
        }
    }

    private void tryRegister() {

        Register register = new Register(loginInput.getText().toString()
                                        ,firstNameInput.getText().toString()
                                        ,lastNameInput.getText().toString()
                                        ,passwordInput.getText().toString());
        ApiManager.register(register,this);

    }

    @Override
    public void onLogin(LoginResponse response) {
        Status status = response.getStatus();
        switch(status){
            case OK:
                onRegistered(response.getUser());
                break;
            case LOGIN_UNAVAILABLE:
                Toast.makeText(this,getString(R.string.login_unavailable),Toast.LENGTH_SHORT).show();
                break;
            case ERROR:
                Toast.makeText(this,R.string.server_problem,Toast.LENGTH_SHORT).show();
        }
    }

    private void onRegistered(User user) {

        Toast.makeText(this,"Registered, "+user.getLastname(),Toast.LENGTH_SHORT).show();
        SessionManager.getInstance().setUser(user);
        finish();

        startActivity(new Intent(this,CapsuleActivity.class));

    }
}
