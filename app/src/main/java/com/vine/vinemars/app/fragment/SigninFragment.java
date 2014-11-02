package com.vine.vinemars.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.vine.vinemars.R;
import com.vine.vinemars.app.SignupActivity;
import com.vine.vinemars.domain.User;
import com.vine.vinemars.net.EnrollRequest;
import com.vine.vinemars.net.MyVolley;
import com.vine.vinemars.net.NetworkRequestListener;

import butterknife.InjectView;

/**
 * Created by chengfei on 14-10-25.
 */
public class SigninFragment extends BaseFragment implements View.OnFocusChangeListener, NetworkRequestListener<User> {

    @InjectView(R.id.btn_signin)
    protected Button okButton;
    @InjectView(R.id.et_password)
    protected EditText passwordEdit;
    @InjectView(R.id.et_user_name)
    protected EditText userNameEdit;

    public static SigninFragment newInstance() {
        SigninFragment fragment = new SigninFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userNameEdit.setOnFocusChangeListener(this);
        passwordEdit.setOnFocusChangeListener(this);
        okButton.setOnClickListener(this);
    }

    @Override
    public void onFocusChange(View view, boolean focused) {
        if (focused) {
            ((View)view.getParent()).setBackgroundResource(R.drawable.shape_editbox_focused);
        } else {
            ((View)view.getParent()).setBackgroundResource(R.drawable.shape_editbox_unfocused);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.sigin, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_signup) {
//            getActivity().getSupportFragmentManager().beginTransaction()
//                    .setCustomAnimations(R.anim.slide_right_in, R.anim.scale_out, R.anim.scale_in, R.anim.slide_left_out)
//                    .add(R.id.content_frame, SignupFragment.newInstance())
//                    .commit();
            startActivity(new Intent(getActivity(), SignupActivity.class));
//            getActivity().finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_signin;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_signin) {
            String email = userNameEdit.getText().toString();
            String password = passwordEdit.getText().toString();
            User user = new User();
            user.email = email;
            user.password = password;
            MyVolley.getRequestQueue().add(new EnrollRequest(user, "", this));
        }
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {

    }

    @Override
    public void onResponse(User user) {
        Toast.makeText(getActivity(), "sigup success. userId = " + user.userId + " token " + user.token, Toast.LENGTH_SHORT).show();
    }
}
