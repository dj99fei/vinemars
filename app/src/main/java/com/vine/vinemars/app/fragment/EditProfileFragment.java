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
import com.vine.vinemars.net.request.ChangePasswordRequest;
import com.vine.vinemars.net.MyVolley;
import com.vine.vinemars.net.NetworkRequestListener;
import com.vine.vinemars.utils.Validator;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by chengfei on 14/10/29.
 */
public class EditProfileFragment extends DialogFragment implements View.OnClickListener, NetworkRequestListener {

    @InjectView(R.id.et_old_password)
    protected EditText oldPasswordEdit;
    @InjectView(R.id.et_new_password)
    protected EditText newPasswordEdit;
    @InjectView(R.id.btn_ok)
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
        if (view.getId() == R.id.btn_ok) {
            String newPassword = newPasswordEdit.getText().toString();
            String oldPassword = oldPasswordEdit.getText().toString();
            Validator validator = new Validator();
            try {
                validator.notEmpty(oldPassword, R.string.old_password)
                         .notEmpty(newPassword, R.string.new_password)
                         .isLengthValid(newPassword, getResources().getInteger(R.integer.password_min_length),
                                 getResources().getInteger(R.integer.password_max_length), R.string.password);
                MyVolley.getRequestQueue().add(new ChangePasswordRequest("", newPassword, this));
            } catch (Validator.ValidateException e) {
                MessageDialogFragment.newInstance(e.getMessage(), getResources().getString(R.string.dialog_title_error))
                        .show(getActivity().getSupportFragmentManager(), null);
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        MessageDialogFragment.newInstance(volleyError.getMessage(), getResources().getString(R.string.dialog_title_error))
                .show(getActivity().getSupportFragmentManager(), null);
    }

    @Override
    public void onResponse(Object o) {
        Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
    }
}
