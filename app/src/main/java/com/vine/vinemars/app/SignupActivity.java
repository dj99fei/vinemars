package com.vine.vinemars.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.vine.vinemars.app.fragment.SignupFragment;

/**
 * Created by chengfei on 14-10-21.
 */
public class SignupActivity extends CommonActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    Fragment getFragment() {
        return SignupFragment.newInstance();
    }


}
