package com.vine.vinemars.net.request;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.vine.vinemars.net.AppHead;
import com.vine.vinemars.net.NetworkRequestListener;
import com.vine.vinemars.net.PackageHead;
import com.vine.vinemars.net.ParseError;
import com.vine.vinemars.net.ResponseWrapper;
import com.vine.vinemars.utils.LogUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chengfei on 14-10-13.
 */
public abstract class BaseRequest<T> extends Request<ResponseWrapper<T>> {
    private static final String TAG = BaseRequest.class.getSimpleName();

    /**
     * 网络请求配置信息
     */
    public static final class Config {
        public static final String BASE_URL = "http://192.168.1.104:8081/vineapp/server";
    }


    protected String checkCode;
    protected NetworkRequestListener listener;
    protected int requestId;

    public BaseRequest(String checkCode, int requestId, NetworkRequestListener<ResponseWrapper<T>> listener) {
        super(Method.POST, Config.BASE_URL, listener);
        this.checkCode = checkCode;
        this.listener = listener;
        this.requestId = requestId;
    }

    public BaseRequest(int requestId, NetworkRequestListener listener) {
        this(null, requestId ,listener);
    }


    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<String, String>();
        PackageHead packageHead = new PackageHead(requestId);
        headers.put("packetHead", new Gson().toJson(packageHead));
        headers.put("appHead", new Gson().toJson(new AppHead()));
        return headers;
    }

    @Override
    protected Response<ResponseWrapper<T>> parseNetworkResponse(NetworkResponse response) {
        Map<String, String> header = response.headers;
        for (Map.Entry<String, String> entry : header.entrySet() ) {
            LogUtils.d(TAG, "key = %s, value = %s" ,entry.getKey(), entry.getValue());
        }

        PackageHead packageHead = parsePackageHead(response);
        AppHead appHead = parseAppHead(response);
        if (successed(packageHead)) {
            try {
                return Response.success(new ResponseWrapper<T>(packageHead, parse(appHead, response)), HttpHeaderParser.parseCacheHeaders(response));
            } catch (ParseError parseError) {
                return Response.error(parseError);
            }
        } else {
            return Response.error(new VolleyError());
        }
    }

    @Override
    protected void deliverResponse(ResponseWrapper<T> response) {
        if (listener != null) {
            listener.onResponse(response);
        }
    }

    protected boolean successed(PackageHead packageHead) {
        return packageHead.retCode == 1;
    }

    private PackageHead parsePackageHead(NetworkResponse response) {
        Map<String, String> header = response.headers;
        String packetHeadStr = header.get("packetHead");
        PackageHead packageHead = new Gson().fromJson(packetHeadStr, PackageHead.class);
        return packageHead;
    }

    private AppHead parseAppHead(NetworkResponse response) {
        Map<String, String> header = response.headers;
        String appHeadStr = header.get("appHead");
        AppHead appHead = new Gson().fromJson(appHeadStr, AppHead.class);
        return appHead;
    }

    abstract protected T parse(AppHead appHead, NetworkResponse response) throws ParseError;
}
