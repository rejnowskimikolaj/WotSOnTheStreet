package com.example.rent.zulicywiesciapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.rent.zulicywiesciapp.utils.CategoryUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CapsuleActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView((R.id.toolbar))
    Toolbar toolbar;

    @BindView((R.id.app_bar_layout))
    AppBarLayout appBarLayout;

    @BindView((R.id.toolbar_title))
    AppCompatTextView toolbarTitle;

    @BindView((R.id.fragment_holder))
    FrameLayout fragmentHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capsule);

        setViews();

        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }


    private void setViews() {

        ButterKnife.bind(this);
        setToolbar();
        setTabsAndNavigationView();
    }

    private void setTabsAndNavigationView(){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }



    private void setToolbar(){
        setSupportActionBar(toolbar);
        toolbarTitle.setText(R.string.capsule);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
