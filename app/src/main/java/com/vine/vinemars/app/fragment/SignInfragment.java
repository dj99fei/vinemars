package com.vine.vinemars.app.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.vine.vinemars.R;
import com.vine.vinemars.net.pb.EnrollMessage;

import butterknife.InjectView;

/**
 * Created by chengfei on 14/12/4.
 */
public class SignInfragment extends SignupFragment {


    @InjectView(R.id.action_signup)
    protected View signUpView;

    public static SignInfragment newInstance() {
        return new SignInfragment();
    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_sign_in;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signUpView.setOnClickListener(this);
    }
    @Override
    protected EnrollMessage.LoginType getLoginType() {
        return EnrollMessage.LoginType.LOGIN;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.action_signup) {
            dismiss();
            SignupFragment.newInstance().show(getFragmentManager(), null);
        } else if (v.getId() == R.id.btn_ok) {
            super.onClick(v);
        }
    }
}
