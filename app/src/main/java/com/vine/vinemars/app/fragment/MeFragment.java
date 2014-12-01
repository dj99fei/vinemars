package com.vine.vinemars.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vine.vinemars.R;
import com.vine.vinemars.app.ProfileActivity;

import butterknife.InjectView;

/**
 * Created by chengfei on 14-10-24.
 */
public class MeFragment extends BaseFragment {

    @InjectView(R.id.layout_info)
    protected View infoLayout;
    @InjectView(R.id.info_other2)
    protected View changePassword;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        infoLayout.setOnClickListener(this);
        changePassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_info:
//                Intent intent = new Intent(getActivity(), SigninActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                getActivity().startActivity(intent);
//                getActivity().getSupportFragmentManager()
//                        .beginTransaction()
//                        .add(R.id.container_name, SigninFragment.newInstance())
//                        .commit();
                SignInFragment.newInstance().show(getFragmentManager(), null);
                break;
            case R.id.info_other2:
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                getActivity().startActivity(intent);
                break;
        }

    }


}
