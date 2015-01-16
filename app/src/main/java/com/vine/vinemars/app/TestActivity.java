package com.vine.vinemars.app;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vine.vinemars.MyApplication;
import com.vine.vinemars.R;
import com.vine.vinemars.adapter.ProfileAdapter;
import com.vine.vinemars.app.BaseActivity;

/**
 * Created by chengfei on 14/12/10.
 */
public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new ProfileAdapter(MyApplication.getUser()));
    }
}
