package com.vine.vinemars.app;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import com.vine.vinemars.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity {
    private ActionBarDrawerToggle toggle;

    @InjectView(R.id.toolbar)
    protected Toolbar toolbar;
    @InjectView(R.id.drawer_layout)
    protected DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        toggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(toggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }
}
