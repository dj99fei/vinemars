package com.vine.vinemars.app;

import android.support.v4.app.Fragment;

import com.vine.vinemars.app.fragment.FeedbackFragment;

/**
 * Created by chengfei on 14/12/6.
 */
public class FeedbackActivity extends CommonActivity {

    @Override
    Fragment getFragment() {
        //TODO: return FeedbackFragment
//        return null;
        return FeedbackFragment.newInstance();
    }
}
