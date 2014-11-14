package com.vine.vinemars.app.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.DigitsAuthButton;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.vine.vinemars.R;
import com.vine.vinemars.domain.User;
import com.vine.vinemars.net.MyVolley;
import com.vine.vinemars.net.NetworkRequestListener;
import com.vine.vinemars.net.request.EnrollRequest;
import com.vine.vinemars.utils.Validator;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by chengfei on 14-10-25.
 */
public class SigninFragment extends DialogFragment implements View.OnFocusChangeListener, NetworkRequestListener<User>, View.OnClickListener, AuthCallback {

    @InjectView(R.id.btn_signin)
    protected Button okButton;
    @InjectView(R.id.et_password)
    protected EditText passwordEdit;
    @InjectView(R.id.et_user_name)
    protected EditText userNameEdit;
    @InjectView(R.id.action_signup)
    protected View signupView;

    @InjectView(R.id.auth_button)
    protected DigitsAuthButton button;

    public static SigninFragment newInstance() {
        SigninFragment fragment = new SigninFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Dialog_Signin);
//        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
        userNameEdit.setOnFocusChangeListener(this);
        passwordEdit.setOnFocusChangeListener(this);
        okButton.setOnClickListener(this);
        button.setCallback(this);
        signupView.setOnClickListener(this);
    }

    @Override
    public void onFocusChange(View view, boolean focused) {
        if (focused) {
            ((View)view.getParent()).setBackgroundResource(R.drawable.shape_editbox_focused);
        } else {
            ((View)view.getParent()).setBackgroundResource(R.drawable.shape_editbox_unfocused);
        }
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setAttributes(lp);
        return dialog;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.sigin, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_signup) {
            SignupFragment.newInstance().show(getFragmentManager(), null);
//            getActivity().getSupportFragmentManager().beginTransaction()
//                    .setCustomAnimations(R.anim.slide_right_in, R.anim.scale_out, R.anim.scale_in, R.anim.slide_left_out)
//                    .add(R.id.content_frame, SignupFragment.newInstance())
//                    .commit();
//            startActivity(new Intent(getActivity(), SignupActivity.class));
//            getActivity().finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    protected int getContentViewId() {
//        return R.layout.fragment_signin;
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_signin, container, false);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_signin) {
            String email = userNameEdit.getText().toString();
            String password = passwordEdit.getText().toString();

            Validator validator = new Validator();
            try {
                validator.notEmpty(email, R.string.username)
                    .isEmailOrPhoneNumber(email)
                    .notEmpty(password, R.string.password)
                    .isLengthValid(email, getResources().getInteger(R.integer.password_min_length), getResources().getInteger(R.integer.password_max_length), R.string.password);
                User user = new User();
                user.email = email;
                user.password = password;
                MyVolley.getRequestQueue().add(new EnrollRequest(user, "", this));
            } catch (Validator.ValidateException e) {
                MessageDialogFragment.newInstance(e.getMessage(), getString(R.string.dialog_title_error))
                        .show(getFragmentManager(), null);
            }
        } else if (v.getId() == R.id.btn_signup) {
            dismiss();
            SignupFragment.newInstance().show(getFragmentManager(), null);
        }
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        MessageDialogFragment.newInstance(volleyError.getMessage(), getString(R.string.dialog_title_error))
            .show(getFragmentManager(),null);
    }

    @Override
    public void onResponse(User user) {
        Toast.makeText(getActivity(), "sigup success. userId = " + user.userId + " token " + user.token, Toast.LENGTH_SHORT).show();
//        getFragmentManager().popBackStack();
        dismiss();
    }

    @Override
    public void success(DigitsSession digitsSession, String s) {
        Toast.makeText(getActivity(), "suc: " + s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failure(DigitsException e) {
        Toast.makeText(getActivity(), "failed", Toast.LENGTH_SHORT).show();
    }
}
