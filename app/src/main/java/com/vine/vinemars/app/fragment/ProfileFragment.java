package com.vine.vinemars.app.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.vine.vinemars.MyApplication;
import com.vine.vinemars.R;
import com.vine.vinemars.adapter.ProfileAdapter;
import com.vine.vinemars.bus.ProfileHeaderMessuredEvent;
import com.vine.vinemars.net.NetworkRequestListener;
import com.vine.vinemars.view.SpacesItemDecoration;

import butterknife.InjectView;
import de.greenrobot.event.EventBus;

/**
 * Created by chengfei on 14/10/29.
 */
public class ProfileFragment extends BaseFragment implements NetworkRequestListener {


    @InjectView(R.id.list)
    protected RecyclerView recyclerView;
    private int headerHeight;
    private int tabsHeight;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_profile;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(20));
        recyclerView.setAdapter(new ProfileAdapter(MyApplication.getUser()));
//        headerHeight = getResources().getDimensionPixelOffset(R.dimen.profile_cover_height) + getResources().getDimensionPixelOffset(R.dimen.profile_header_tabs_height);
        tabsHeight = getResources().getDimensionPixelOffset(R.dimen.profile_header_tabs_height);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View header = recyclerView.getChildAt(0);
                headerHeight = header.getMeasuredHeight();
                if (layoutManager.findFirstVisibleItemPosition() == 0) {
                    ViewCompat.setTranslationY( header.findViewById(R.id.profile_cover), -header.getTop() / 2);
                }
                int top = header.getTop();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }
        });
//        load();
    }

    private class MyTouchListener implements View.OnTouchListener{
        private float xDistance, yDistance, lastX, lastY;

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return recyclerView.onTouchEvent(motionEvent);

        }
    }

    // TODO: Load data from server
    private void load() {
    }


    @Override
    public void onClick(View v) {
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {

    }

    @Override
    public void onResponse(Object o) {
        Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
    }

    public void onEventMainThread(ProfileHeaderMessuredEvent event) {
        headerHeight = event.getHeight();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}

