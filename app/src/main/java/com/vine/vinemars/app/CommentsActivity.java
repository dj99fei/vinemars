package com.vine.vinemars.app;

import android.support.v4.app.Fragment;

import com.vine.vinemars.app.fragment.CommentsFragment;

/**
 * Created by chengfei on 15/1/26.
 */
public class CommentsActivity extends CommonActivity{
    public static final String ARG_DRAWING_START_LOCATION = "arg_drawing_start_location";
    @Override
    Fragment getFragment() {
        return CommentsFragment.newInstance(getIntent().getExtras());
    }
}
