package com.vine.vinemars.app.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.vine.vinemars.R;
import com.vine.vinemars.adapter.ProfileAdapter;
import com.vine.vinemars.bus.ProfileHeaderMessuredEvent;
import com.vine.vinemars.domain.User;
import com.vine.vinemars.net.NetworkRequestListener;
import com.vine.vinemars.view.VerticalScrollDetctor;

import butterknife.InjectView;
import de.greenrobot.event.EventBus;

/**
 * Created by chengfei on 14/10/29.
 */
public class ProfileFragment extends BaseFragment implements NetworkRequestListener {


    @InjectView(R.id.list)
    protected RecyclerView recyclerView;
    @InjectView(R.id.layout_profile_tabs)
    protected View profileTabsView;
    @InjectView(R.id.profile_tabs)
    protected RadioGroup tabRadioGroup;
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
        recyclerView.setAdapter(new ProfileAdapter(new User()));
//        headerHeight = getResources().getDimensionPixelOffset(R.dimen.profile_cover_height) + getResources().getDimensionPixelOffset(R.dimen.profile_header_tabs_height);
        tabsHeight = getResources().getDimensionPixelOffset(R.dimen.profile_header_tabs_height);
        tabRadioGroup.check(R.id.profile_tabs_about);
        final GestureDetector gestureDetector = new GestureDetector(new VerticalScrollDetctor());
        for (int i = 0; i < tabRadioGroup.getChildCount(); i++) {
//            tabRadioGroup.getChildAt(i).setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View view, MotionEvent motionEvent) {
//                    if (gestureDetector.onTouchEvent(motionEvent)) {
//                        return recyclerView.onTouchEvent(motionEvent);
//                    }
//                    view.onTouchEve Ont(motionEvent);
////
//                    return true;
//                }
//            });
            tabRadioGroup.getChildAt(i).getParent().requestDisallowInterceptTouchEvent(false);
//            tabRadioGroup.getChildAt(i).setOnTouchListener(new MyTouchListener());
        }
        tabRadioGroup.setOnTouchListener(new MyTouchListener());
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View header = recyclerView.getChildAt(0);
                headerHeight = header.getMeasuredHeight();
                if (layoutManager.findFirstVisibleItemPosition() == 0) {
                    ViewCompat.setTranslationY( header.findViewById(R.id.profile_cover), -header.getTop() / 2);
                }
                if (layoutManager.findFirstVisibleItemPosition() > 0) {
                    ViewCompat.setTranslationY(profileTabsView, 0);
                    return;
                }
                int top = header.getTop();
                translateTabs(top);
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
//            switch (motionEvent.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//                    xDistance = yDistance = 0f;
//                    lastX = motionEvent.getX();
//                    lastY = motionEvent.getY();
//                    break;
//                case MotionEvent.ACTION_MOVE:
//                    final float curX = motionEvent.getX();
//                    final float curY = motionEvent.getY();
//                    xDistance += Math.abs(curX - lastX);
//                    yDistance += Math.abs(curY - lastY);
//                    lastX = curX;
//                    lastY = curY;
//                    if(xDistance < yDistance && yDistance > 20)
//                        return recyclerView.onTouchEvent(motionEvent);
//            }
//            return view.onTouchEvent(motionEvent);
            return recyclerView.onTouchEvent(motionEvent);

        }
    }

    private void translateTabs(int top) {
        int delta = headerHeight - tabsHeight;
        ViewCompat.setTranslationY(profileTabsView, Math.max(0, delta + top));
    }

    // TODO: Load data from server
    private void load() {
    }


    @Override
    public void onClick(View v) {
//        if (v.getId() == R.id.change_password) {
//            startActivity(new Intent(getActivity(), ChangePasswordActivity.class));
//            MyVolley.getRequestQueue().add(new ChangePasswordRequest(this));
//        }
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
        translateTabs(0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
