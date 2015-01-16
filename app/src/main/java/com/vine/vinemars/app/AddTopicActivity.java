package com.vine.vinemars.app;

import android.support.v4.app.Fragment;

import com.vine.vinemars.app.fragment.AddTopicFragment;

/**
 * Created by chengfei on 15/1/15.
 */
public class AddTopicActivity extends CommonActivity {

    @Override
    Fragment getFragment() {
        return AddTopicFragment.newInstance();
    }
}
