package com.example.rent.zulicywiesciapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.rent.zulicywiesciapp.retrofit.ApiManager;
import com.example.rent.zulicywiesciapp.utils.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;

abstract public class AbstractCapsuleActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
        , ApiManager.OnAuthCheckListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView((R.id.toolbar))
    Toolbar toolbar;

    @BindView((R.id.app_bar_layout))
    AppBarLayout appBarLayout;

    @BindView((R.id.toolbar_title))
    AppCompatTextView toolbarTitle;

    ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void setViews() {

        ButterKnife.bind(this);
        setToolbar();
        setTabsAndNavigationView();
    }

    public void checkIfLoggedIn() {

        progress = ProgressDialog.show(this, getString(R.string.checkin_login_dialog), "", true);
        SessionManager.checkIfLoggedIn(this);
    }

    private void setTabsAndNavigationView() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        navigationView.inflateMenu(R.menu.activity_capsule_drawer);
    }


    private void setToolbar() {
        setSupportActionBar(toolbar);
        setToolbarTitle();
    }

    abstract void setToolbarTitle();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int itemIdid = menuItem.getItemId();
        drawerLayout.closeDrawer(GravityCompat.START);
        Intent intent = null;
        switch (itemIdid) {
            case R.id.nav_home:
                intent = new Intent(this, MainActivity.class);
                break;
            case R.id.nav_capsule:
                intent = new Intent(this, CapsuleActivity.class);
                break;
            case R.id.nav_my_news:
                intent = new Intent(this, MyNewsActivity.class);
                break;
            case R.id.nav_add_new:
                intent = new Intent(this,AddNewsActivity.class);
                break;
            case R.id.nav_logout:
                SessionManager.logout();
                intent = new Intent(this, MainActivity.class);
        }
        if (intent != null) {
            startActivity(intent);
        }
        return true;

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onAuthCheck(Boolean response) {

        if (progress != null) {
            progress.dismiss();
        }
        if (!response) startLoginActivity();
    }

    private void startLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
