package com.vine.vinemars.app;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.TextView;

import com.vine.vinemars.R;
import com.vine.vinemars.app.fragment.CalendarFragment;
import com.vine.vinemars.app.fragment.MeFragment;
import com.vine.vinemars.app.fragment.MessageListFragment;

public class MainActivity extends BaseActivity implements MessageListFragment.OnFragmentInteractionListener {

    protected FragmentTabHost mTabHost;

    public static final String TAG_MESSAGE = "tag_message";
    public static final String TAG_FRIEND = "tag_friend";
    public static final String TAG_DISCOVER= "tag_discover";
    public static final String TAG_ME = "tag_me";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabHost = (FragmentTabHost) findViewById(R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.content_frame);
        mTabHost.addTab(mTabHost.newTabSpec(TAG_MESSAGE).setIndicator(getFindIndicatorView(R.string.tab_text_calendar, R.drawable.ic_tab_find)), CalendarFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(TAG_FRIEND).setIndicator(getFindIndicatorView(R.string.tab_text_message, R.drawable.ic_tab_find)), MessageListFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(TAG_DISCOVER).setIndicator(getFindIndicatorView(R.string.tab_text_message, R.drawable.ic_tab_find)), MessageListFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(TAG_ME).setIndicator(getFindIndicatorView(R.string.tab_text_message, R.drawable.ic_tab_find)), MeFragment.class, null);

    }

    public View getFindIndicatorView(int textId, int drawableId) {
        View indicatorView = View.inflate(this, R.layout.tab_indicator, null);
        TextView textView = (TextView) indicatorView.findViewById(R.id.tab_text);
        textView.setText(textId);
        textView.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(drawableId), null, null);
        return indicatorView;
    }

    @Override
    public void onFragmentInteraction(String id) {

    }
}
