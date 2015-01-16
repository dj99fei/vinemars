package com.vine.vinemars.net.request;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.vine.vinemars.domain.Feedback;
import com.vine.vinemars.net.AppHead;
import com.vine.vinemars.net.HOpCodeEx;
import com.vine.vinemars.net.NetworkRequestListener;
import com.vine.vinemars.net.ParseError;
import com.vine.vinemars.net.pb.StarMessage;

import java.util.Map;

/**
 * Created by lili on 14/12/10.
 */
public class FeedbackRequest extends BaseRequest<Feedback>  {

    private static final String TAG = EnrollRequest.class.getSimpleName();

    private Feedback feedback;

    public FeedbackRequest(Feedback feedback,  NetworkRequestListener listener) {
        super(HOpCodeEx.PostFeedback, listener);
        this.feedback = feedback;
        setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, 0));
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        StarMessage.PostFeedback.Builder builder = StarMessage.PostFeedback.newBuilder();
//        builder.setEmail(user.email);
        builder.setFeedback(feedback.feedback);
        builder.setContact(feedback.contact);

        return builder.build().toByteArray();
    }

    @Override
    protected Feedback parse(AppHead appHead, NetworkResponse response) throws ParseError {

        Map<String, String> headers = response.headers;
        String sessionId = headers.get("sessionId");
        String ret = headers.get("statusCode");
        Log.d(TAG,"ret:" + ret);
        return feedback;

    }
}
