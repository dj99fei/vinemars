package com.vine.vinemars.net;

import com.android.volley.Response;

/**
 * Created by chengfei on 14-10-13.
 */
public interface NetworkRequestListener<T> extends Response.Listener<T>, Response.ErrorListener {
}
