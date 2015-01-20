package com.vine.vinemars.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.shamanland.fab.FloatingActionButton;
import com.vine.vinemars.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity {
    private ActionBarDrawerToggle toggle;

    @InjectView(R.id.toolbar)
    protected Toolbar toolbar;
    @InjectView(R.id.drawer_layout)
    protected DrawerLayout drawerLayout;
    @InjectView(R.id.fab)
    protected FloatingActionButton addTopicButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        toggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(toggle);
        addTopicButton.setOnClickListener(this);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_feedback) {
            startActivity(new Intent(this, FeedbackActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            startActivity(new Intent(this, AddTopicActivity.class));
        }
    }
}
