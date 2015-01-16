package com.vine.vinemars.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.vine.vinemars.MyApplication;
import com.vine.vinemars.R;
import com.vine.vinemars.app.MainActivity;
import com.vine.vinemars.domain.User;
import com.vine.vinemars.net.MyVolley;
import com.vine.vinemars.net.NetworkRequestListener;
import com.vine.vinemars.net.ResponseWrapper;
import com.vine.vinemars.net.pb.EnrollMessage;
import com.vine.vinemars.net.request.EnrollRequest;
import com.vine.vinemars.utils.Utils;
import com.vine.vinemars.utils.Validator;
import com.vine.vinemars.view.CrossInEditTouchListener;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by chengfei on 14-10-25.
 */
public class SignupFragment extends BaseDialogFragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, NetworkRequestListener<User> {

    @InjectView(R.id.btn_ok)
    protected Button okButton;
    @InjectView(R.id.edit_password)
    protected EditText passwordEdit;
    @InjectView(R.id.edit_user_name)
    protected EditText userNameEdit;
    @InjectView(R.id.toolbar)
    protected Toolbar toolbar;
    @InjectView(R.id.show_password)
    protected CheckBox showPasswordCheckBox;
    @InjectView(R.id.title)
    protected TextView titleText;

    public static SignupFragment newInstance() {
        return new SignupFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Window window = getDialog().getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Dialog_Signin);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setIME();
        return inflater.inflate(getContentId(), container, false);
    }

    private void setIME() {
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING | WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    protected int getContentId() {
        return R.layout.fragment_sign_up;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING | WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        ButterKnife.inject(this, view);
        okButton.setText(R.string.signup);
        okButton.setOnClickListener(this);
        setTitle();
        setToolbar();
        passwordEdit.setOnTouchListener(new CrossInEditTouchListener(passwordEdit));
        userNameEdit.setOnTouchListener(new CrossInEditTouchListener(userNameEdit));
        showPasswordCheckBox.setOnCheckedChangeListener(this);
    }

    protected void setToolbar() {
        toolbar.inflateMenu(R.menu.close);
        toolbar.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.action_close) {
                            dismiss();
                            return true;
                        }
                        return false;
                    }
                });
    }

    protected void setTitle() {
        titleText.setText(R.string.signup);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_ok) {
            String email = userNameEdit.getText().toString();
            String password = passwordEdit.getText().toString();
            Validator validator = new Validator();
            try {
                validator.notEmpty(email, R.string.username)
//                        .isEmailOrPhoneNumber(email)
                        .notEmpty(password, R.string.password)
                        .isLengthValid(email, getResources().getInteger(R.integer.password_min_length), getResources().getInteger(R.integer.password_max_length), R.string.password);
                Utils.hideKeyboard(passwordEdit);
                User user = new User();
                user.username = email;
                user.password = password;
                MyApplication.setUser(user);
                setShowProgress(true);
                MyVolley.getRequestQueue().add(new EnrollRequest(user, getLoginType(), this));
            } catch (Validator.ValidateException e) {
                MessageDialogFragment.newInstance(e.getMessage(), getString(R.string.dialog_title_error))
                        .show(getFragmentManager(), null);
            }
        }
    }

    protected EnrollMessage.LoginType getLoginType() {
        return EnrollMessage.LoginType.ENROLL;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int start = passwordEdit.getSelectionStart();
        int end = passwordEdit.getSelectionEnd();
        if (isChecked) {
            passwordEdit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            passwordEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        passwordEdit.setSelection(start, end);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        setShowProgress(false);
        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(ResponseWrapper<User> response) {
        User user = response.entity;
        user.save();
        setShowProgress(false);
        getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }
}
