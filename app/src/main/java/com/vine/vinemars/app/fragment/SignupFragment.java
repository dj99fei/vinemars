package com.vine.vinemars.app.fragment;

import android.os.Bundle;
import android.view.View;

import com.vine.vinemars.R;

/**
 * Created by chengfei on 14-10-25.
 */
public class SignupFragment extends SignInFragment {

    public static SignupFragment newInstance() {
        return new SignupFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

//    @Override
//    protected int getContentViewId() {
//        return R.layout.fragment_signup;
//    }

//
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_signup, container, false);
//    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        okButton.setText(R.string.signup);
        signUpView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void setTitle() {
        titleText.setText(R.string.signup);
    }
}
