package com.vine.vinemars.app;

import android.support.v4.app.Fragment;

import com.vine.vinemars.app.fragment.ProfileFragment;

/**
 * Created by chengfei on 14/10/29.
 */
public class ProfileActivity extends CommonActivity {


    @Override
    Fragment getFragment() {
        return ProfileFragment.newInstance();
    }
}
