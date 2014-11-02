package com.vine.vinemars.app.fragment;

import android.os.Bundle;
import android.view.View;

import com.vine.vinemars.R;

/**
 * Created by chengfei on 14-10-25.
 */
public class SignupFragment extends SigninFragment {

    public static SignupFragment newInstance() {
        return new SignupFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_signup;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        okButton.setText(R.string.signup);
    }

    @Override
    public void onClick(View v) {

    }
}
