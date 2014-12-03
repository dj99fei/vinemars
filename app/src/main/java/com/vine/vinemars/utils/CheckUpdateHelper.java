package com.vine.vinemars.utils;

import android.content.Context;

import com.android.volley.VolleyError;
import com.vine.vinemars.MyApplication;
import com.vine.vinemars.R;
import com.vine.vinemars.domain.Version;
import com.vine.vinemars.net.NetworkRequestListener;
import com.vine.vinemars.net.ResponseWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengfei on 14-10-21.
 */
public class CheckUpdateHelper implements NetworkRequestListener<Version> {

    private static final Object LOCK = new Object();
    private static CheckUpdateHelper helper;
    private Context context;

    List<NetworkRequestListener> listeners = new ArrayList<NetworkRequestListener>();
    private CheckUpdateHelper() {
        context = MyApplication.get();
    }

    public static CheckUpdateHelper getInstance() {
        if (helper == null) {
            synchronized (LOCK) {
                if (helper == null) {
                    helper = new CheckUpdateHelper();
                }
            }
        }
        return helper;
    }

    public CheckUpdateHelper withListener(NetworkRequestListener listener) {
        listeners.clear();
        listeners.add(listener);
        return this;

    }

    public CheckUpdateHelper check() {
//        Request request = new CheckUpdateRequest(this);
//        MyVolley.getRequestQueue().add(request);
        return this;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        if (listeners != null) {
            for (NetworkRequestListener listener : listeners) {
                listener.onErrorResponse(error);
            }
        }
    }

    @Override
    public void onResponse(ResponseWrapper<Version> response) {
        Version.setLastestVersion(response.entity);
        if (listeners != null) {
            for (NetworkRequestListener listener : listeners) {
                listener.onResponse(response);
            }
        }
    }

    public boolean needToNotify() {
        Version lastest = Version.getLastestVersion();
        if (lastest == null) {
            return false;
        }
        boolean isNeed = lastest.versionCode > Version.getVersion().versionCode;
        isNeed &= !(SharedPreferencesHelper.getInstance().withKey(R.string.key_poped_version_code).get(int.class, -1) > Version.getVersion().versionCode);
        return isNeed;
    }


}
