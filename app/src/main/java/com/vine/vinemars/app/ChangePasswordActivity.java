package com.vine.vinemars.app;

import android.support.v4.app.Fragment;

import com.vine.vinemars.app.fragment.EditProfileFragment;

/**
 * Created by chengfei on 14/10/29.
 */
public class ChangePasswordActivity extends CommonActivity {

    @Override
    Fragment getFragment() {
        return EditProfileFragment.newInstance();
    }
}
