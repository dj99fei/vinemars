package com.vine.vinemars.net.request;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.vine.vinemars.domain.DeviceInfo;
import com.vine.vinemars.domain.User;
import com.vine.vinemars.net.HOpCodeEx;
import com.vine.vinemars.net.NetworkRequestListener;
import com.vine.vinemars.net.ParseError;
import com.vine.vinemars.net.pb.EnrollMessage;

/**
 * Created by chengfei on 14-10-25.
 */
public class EnrollRequest extends BaseRequest<User> {

    private static final String TAG = EnrollRequest.class.getSimpleName();
    private User user;
    public EnrollRequest(User user,String checkCode, NetworkRequestListener listener) {
        super(checkCode, HOpCodeEx.Enroll, listener);
        this.user = user;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        EnrollMessage.Enroll.Builder builder = EnrollMessage.Enroll.newBuilder();
        builder.setEmail(user.email);
        builder.setMobile(DeviceInfo.imei);
        builder.setPassword(user.password);
        builder.setCheckCode(checkCode);
        return builder.build().toByteArray();
    }

    @Override
    protected User parse(NetworkResponse response) throws ParseError {
        return null;
    }
}
