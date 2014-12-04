package com.vine.vinemars.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
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
import com.vine.vinemars.net.request.EnrollRequest;
import com.vine.vinemars.utils.Constant;
import com.vine.vinemars.utils.Validator;
import com.vine.vinemars.view.CrossInEditTouchListener;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by chengfei on 14/12/3.
 */
public class VerificationCodeDialogFragment extends DialogFragment implements View.OnClickListener, NetworkRequestListener<User> {

    private String phoneNumber;
    private String country;

    @InjectView(R.id.btn_next)
    protected Button nextBtn;
    @InjectView(R.id.phone_number)
    protected TextView phoneNumberText;
    @InjectView(R.id.edit_code)
    protected EditText codeEdit;
    @InjectView(R.id.toolbar)
    protected Toolbar toolbar;
    @InjectView(R.id.text_get_checkcode)
    protected TextView getCheckCodeText;


    public static VerificationCodeDialogFragment newInstance(String country, String phoneNumber) {
        VerificationCodeDialogFragment fragment = new VerificationCodeDialogFragment();
        Bundle args = new Bundle();
        args.putString(Constant.INTENT_KEY.COUNTRY, country);
        args.putString(Constant.INTENT_KEY.PHONE_NUMBER, phoneNumber);
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
        setStyle(STYLE_NORMAL, R.style.Dialog_Signin);
        phoneNumber = getArguments().getString(Constant.INTENT_KEY.PHONE_NUMBER);
        country = getArguments().getString(Constant.INTENT_KEY.COUNTRY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_verification_code, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
        phoneNumberText.setText(format());
        codeEdit.setOnTouchListener(new CrossInEditTouchListener(codeEdit));
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

        getCheckCodeText.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        SMSSDK.registerEventHandler(eventHandler);
    }

    private String format() {
        return new StringBuilder("+").append(country).append(" ").append(phoneNumber).toString();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.text_get_checkcode) {

        } else if (v.getId() == R.id.btn_next) {
            String checkCode = codeEdit.getText().toString();
            Validator validator = new Validator();
            try {
                validator.notEmpty(checkCode, R.string.verification_code);
                SMSSDK.submitVerificationCode(country, phoneNumber, checkCode);
            } catch (Validator.ValidateException e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }
    }

    private EventHandler eventHandler = new EventHandler() {

        @Override
        public void afterEvent(int event, int result, Object data) {
            super.afterEvent(event, result, data);
            if (getActivity() == null) {
                return;
            }
            if (result == SMSSDK.RESULT_COMPLETE) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "verify successed", Toast.LENGTH_LONG).show();
                    }
                });
                EnrollRequest request = new EnrollRequest(MyApplication.getUser(), "", VerificationCodeDialogFragment.this);
                MyVolley.getRequestQueue().add(request);
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        SMSSDK.unregisterEventHandler(eventHandler);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(ResponseWrapper<User> response) {
        User user = response.entity;
        user.save();
        getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }
}
