package com.vine.vinemars.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.vine.vinemars.MyApplication;
import com.vine.vinemars.R;
import com.vine.vinemars.app.MainActivity;
import com.vine.vinemars.domain.Feedback;
import com.vine.vinemars.net.MyVolley;
import com.vine.vinemars.net.NetworkRequestListener;
import com.vine.vinemars.net.ResponseWrapper;
import com.vine.vinemars.net.request.FeedbackRequest;

import butterknife.InjectView;

/**
 * Created by chengfei on 14/12/6.
 */
public class FeedbackFragment extends BaseFragment implements View.OnClickListener, NetworkRequestListener<Feedback> {
    @InjectView(R.id.btn_feedback_submit)
    protected Button submitButton;
    @InjectView(R.id.feedback)
    protected EditText feedbackEdit;
    @InjectView(R.id.feedback_contact)
    protected EditText contactEdit;

    public static FeedbackFragment newInstance() {
        return new FeedbackFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feedback, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_feedback_submit) {
            String feedbackTxt = feedbackEdit.getText().toString();
            String contactTxt = contactEdit.getText().toString();
            Log.d("FEEDBACK","反馈信息："+feedbackTxt);
            Log.d("FEEDBACK","联系方式："+contactTxt);
            try {
                Feedback feedback = new Feedback();
                feedback.feedback = feedbackTxt;
                feedback.contact = contactTxt;
                MyApplication.setFeedback(feedback);
                setShowProgress(true);
                MyVolley.getRequestQueue().add(new FeedbackRequest(feedback, this));
            } catch (Exception e) {
                MessageDialogFragment.newInstance(e.getMessage(), getString(R.string.dialog_title_error))
                        .show(getFragmentManager(), null);
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        setShowProgress(false);
        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(ResponseWrapper<Feedback> response) {
        Feedback feedback = response.entity;
        feedback.save();

        setShowProgress(false);
        getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }

}
