package com.vine.vinemars;

import android.app.Application;

import com.android.volley.VolleyError;
import com.vine.vinemars.domain.Feedback;
import com.vine.vinemars.domain.User;
import com.vine.vinemars.net.MyVolley;
import com.vine.vinemars.net.NetworkRequestListener;

import java.util.Locale;

import cn.smssdk.SMSSDK;

/**
 * Created by chengfei on 14-10-21.
 */
public class MyApplication extends Application implements NetworkRequestListener {
    static MyApplication myApp;
    static User user;
    static Feedback feedback;

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        MyVolley.init(this);
        SMSSDK.initSDK(this, "47dc764dab0a", "bc1fe7c2bc7cfc4a83e2f03c308b9152");
        user = User.loadFromLocal();
        feedback = Feedback.loadFromLocal();
    }

    public static MyApplication get() {
        return myApp;
    }

    public static Locale getLocal() {
        return myApp.getResources().getConfiguration().locale;
    }

    public static void setUser(User user) {
        MyApplication.user = user;
    }

    public static boolean isSignined() {
        return user != null && user.isSigined();
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(Object response) {
    }

    public static User getUser() {
        return user;
    }

    public static void setFeedback(Feedback feedback) {
        MyApplication.feedback = feedback;
    }
    public static Feedback getFeedback() {return  feedback;}

}
