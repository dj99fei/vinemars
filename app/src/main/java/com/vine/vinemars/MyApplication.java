package com.vine.vinemars;

import android.app.Application;

import com.android.volley.VolleyError;
import com.digits.sdk.android.Digits;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.vine.vinemars.domain.User;
import com.vine.vinemars.net.MyVolley;
import com.vine.vinemars.net.NetworkRequestListener;

import java.util.Locale;

import io.fabric.sdk.android.Fabric;

/**
 * Created by chengfei on 14-10-21.
 */
public class MyApplication extends Application implements NetworkRequestListener {
    static MyApplication myApp;
    static User user;

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        MyVolley.init(this);

        TwitterAuthConfig authConfig =
                new TwitterAuthConfig("ZfOmIG604MYPSL9Uwn6s8xLC9", "prGmFModqnYiRVs91X4cN6XCPiEZ1SO970dO2bHXZKT9GybhLC");
        Fabric.with(this, new TwitterCore(authConfig), new Digits());
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
}
