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
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.vine.vinemars.R;
import com.vine.vinemars.app.MainActivity;
import com.vine.vinemars.bus.LoginEvent;
import com.vine.vinemars.domain.User;
import com.vine.vinemars.net.NetworkRequestListener;
import com.vine.vinemars.utils.Captcha;
import com.vine.vinemars.utils.Constant;
import com.vine.vinemars.utils.ImageUtils;
import com.vine.vinemars.utils.TextCaptcha;
import com.vine.vinemars.utils.Validator;
import com.vine.vinemars.view.CrossInEditTextWatcher;
import com.vine.vinemars.view.CrossInEditTouchListener;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;
import de.greenrobot.event.EventBus;

/**
 * Created by chengfei on 14-10-25.
 */
public class SignInFragment extends DialogFragment implements View.OnFocusChangeListener, NetworkRequestListener<User>, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    @InjectView(R.id.btn_signin)
    protected Button okButton;
    @InjectView(R.id.et_password)
    protected EditText passwordEdit;
    @InjectView(R.id.et_user_name)
    protected EditText userNameEdit;
    @Optional
    @InjectView(R.id.action_signup)
    protected View signUpView;
    @InjectView(R.id.toolbar)
    protected Toolbar toolbar;
    @InjectView(R.id.show_password)
    protected CheckBox showPasswordCheckBox;
    @InjectView(R.id.image_captcha)
    protected ImageView captchaImage;
    @InjectView(R.id.title)
    protected TextView titleText;

    public static SignInFragment newInstance() {
        SignInFragment fragment = new SignInFragment();
        return fragment;
    }

    private Bitmap background;

    public static SignInFragment newInstance(Bitmap background) {
        SignInFragment fragment = newInstance();
        Bundle args = new Bundle();
        args.putParcelable(Constant.INTENT_KEY.BITMAP, background);
        fragment.setArguments(args);
        return fragment;
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
        background = getArguments() != null ? (Bitmap) getArguments().getParcelable(Constant.INTENT_KEY.BITMAP) : null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
        userNameEdit.setOnFocusChangeListener(this);
        passwordEdit.setOnFocusChangeListener(this);
        okButton.setOnClickListener(this);
        signUpView.setOnClickListener(this);
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

        showPasswordCheckBox.setOnCheckedChangeListener(this);
        passwordEdit.setOnTouchListener(new CrossInEditTouchListener(passwordEdit));
        userNameEdit.setOnTouchListener(new CrossInEditTouchListener(userNameEdit));
        passwordEdit.addTextChangedListener(new CrossInEditTextWatcher(passwordEdit));
        userNameEdit.addTextChangedListener(new CrossInEditTextWatcher(userNameEdit));

        Captcha c = new TextCaptcha(300, 100, 5, TextCaptcha.TextOptions.NUMBERS_AND_LETTERS);
        captchaImage.setImageBitmap(c.getImage());
        captchaImage.getLayoutParams().width = c.getWidth() * 2;

        setTitle();

    }

    protected void setTitle() {
        titleText.setText(R.string.action_sign_in);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
                dismiss();
                EventBus.getDefault().post(new LoginEvent(true));
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
        dismiss();
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
}
