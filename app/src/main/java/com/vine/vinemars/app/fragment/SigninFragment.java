package com.vine.vinemars.app.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.vine.vinemars.R;
import com.vine.vinemars.app.MainActivity;
import com.vine.vinemars.domain.User;
import com.vine.vinemars.net.NetworkRequestListener;
import com.vine.vinemars.utils.Constant;
import com.vine.vinemars.utils.ImageUtils;
import com.vine.vinemars.utils.Validator;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;

/**
 * Created by chengfei on 14-10-25.
 */
public class SigninFragment extends DialogFragment implements View.OnFocusChangeListener, NetworkRequestListener<User>, View.OnClickListener {

    @InjectView(R.id.btn_signin)
    protected Button okButton;
    @InjectView(R.id.et_password)
    protected EditText passwordEdit;
    @InjectView(R.id.et_user_name)
    protected EditText userNameEdit;
    @Optional
    @InjectView(R.id.action_signup)
    protected View signupView;
    @InjectView(R.id.toolbar)
    protected Toolbar toolbar;

    public static SigninFragment newInstance() {
        SigninFragment fragment = new SigninFragment();
        return fragment;
    }

    private Bitmap background;

    public static SigninFragment newInstance(Bitmap background) {
        SigninFragment fragment = newInstance();
        Bundle args = new Bundle();
        args.putParcelable(Constant.INTENT_KEY.BITMAP, background);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        //must setBackgroundDrawable(TRANSPARENT) in onActivityCreated()
//        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f9d0d0d0")));
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Dialog_Signin);
        background = getArguments() != null ? (Bitmap) getArguments().getParcelable(Constant.INTENT_KEY.BITMAP) : null;

//        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
        userNameEdit.setOnFocusChangeListener(this);
        passwordEdit.setOnFocusChangeListener(this);
        okButton.setOnClickListener(this);
        signupView.setOnClickListener(this);
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

        if (background != null) {
            view.setBackgroundDrawable(new BitmapDrawable(ImageUtils.fastblur(background, 20)));
        }
    }

    @Override
    public void onFocusChange(View view, boolean focused) {
//        if (focused) {
//            ((View) view.getParent()).setBackgroundResource(R.drawable.shape_editbox_focused);
//        } else {
//            ((View) view.getParent()).setBackgroundResource(R.drawable.shape_editbox_unfocused);
//        }
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
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
//                MyVolley.getRequestQueue().add(new EnrollRequest(user, "", this));
                getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
            } catch (Validator.ValidateException e) {
                MessageDialogFragment.newInstance(e.getMessage(), getString(R.string.dialog_title_error))
                        .show(getFragmentManager(), null);
            }
        } else if (v.getId() == R.id.action_signup) {
            dismiss();
            SignupFragment.newInstance().show(getFragmentManager(), null);
        }
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        MessageDialogFragment.newInstance(volleyError.getMessage(), getString(R.string.dialog_title_error))
                .show(getFragmentManager(), null);
    }

    @Override
    public void onResponse(User user) {
        Toast.makeText(getActivity(), "sigup success. userId = " + user.userId + " token " + user.token, Toast.LENGTH_SHORT).show();
//        getFragmentManager().popBackStack();
        dismiss();
    }
}
