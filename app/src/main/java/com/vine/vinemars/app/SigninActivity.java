package com.vine.vinemars.app;

import android.support.v4.app.Fragment;

import com.vine.vinemars.app.fragment.ValidatePhoneNumberFragment;

/**
 * Created by chengfei on 14-10-25.
 */
public class SigninActivity extends CommonActivity {

    @Override
    Fragment getFragment() {
        return ValidatePhoneNumberFragment.newInstance();
    }
}
