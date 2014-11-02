package com.vine.vinemars.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.vine.vinemars.R;
import com.vine.vinemars.app.ChangePasswordActivity;
import com.vine.vinemars.net.NetworkRequestListener;

import butterknife.InjectView;

/**
 * Created by chengfei on 14/10/29.
 */
public class ProfileFragment extends BaseFragment implements NetworkRequestListener {

    @InjectView(R.id.change_password)
    protected TextView changePasswordText;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_profile;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        changePasswordText.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.change_password) {
            startActivity(new Intent(getActivity(), ChangePasswordActivity.class));
//            MyVolley.getRequestQueue().add(new ChangePasswordRequest(this));
        }
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {

    }

    @Override
    public void onResponse(Object o) {
        Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
    }
}
