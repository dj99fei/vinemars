package com.vine.vinemars.app.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.vine.vinemars.R;
import com.vine.vinemars.net.ChangePasswordRequest;
import com.vine.vinemars.net.MyVolley;
import com.vine.vinemars.net.NetworkRequestListener;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by chengfei on 14/10/29.
 */
public class EditProfileFragment extends DialogFragment implements View.OnClickListener, NetworkRequestListener {

    @InjectView(R.id.edit)
    protected EditText edit;
    @InjectView(R.id.ok)
    protected Button okButton;

    public static EditProfileFragment newInstance() {
        return new EditProfileFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_change_password, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);

        okButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ok) {
            String newPassword = edit.getText().toString();
            MyVolley.getRequestQueue().add(new ChangePasswordRequest("", newPassword, this));
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
