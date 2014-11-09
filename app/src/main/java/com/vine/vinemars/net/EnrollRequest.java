package com.vine.vinemars.net;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.protobuf.InvalidProtocolBufferException;
import com.vine.vinemars.domain.DeviceInfo;
import com.vine.vinemars.domain.User;
import com.vine.vinemars.net.pb.EnrollPacket;
import com.vine.vinemars.utils.LogUtils;

/**
 * Created by chengfei on 14-10-25.
 */
public class EnrollRequest extends BaseRequest<User> {

    private static final String TAG = EnrollRequest.class.getSimpleName();
    private User user;
    public EnrollRequest(User user,String checkCode, NetworkRequestListener listener) {
        super(checkCode, listener);
        this.user = user;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {

        EnrollPacket.Enroll.Builder builder = EnrollPacket.Enroll.newBuilder();
        builder.setEmail(user.email);
        builder.setMobileNo(DeviceInfo.imei);
        builder.setPassword(user.password);
        builder.setCheckCode(checkCode);
        return builder.build().toByteArray();
    }

    @Override
    public int getRequestId() {
        return HOpCodeEx.Enroll;
    }

    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        try {
            EnrollPacket.EnrollRet ret = EnrollPacket.EnrollRet.parseFrom(response.data);
            LogUtils.d(TAG, "ret = %s", ret.getToken());
            user.token = ret.getToken();
            user.userId = ret.getUserId();
            if (successed(response)) {
                return Response.success(user, HttpHeaderParser.parseCacheHeaders(response));
            } else {

            }

        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return super.parseNetworkResponse(response);
    }

    @Override
    protected User parse(NetworkResponse response) throws ParseError {
        try {
            EnrollPacket.EnrollRet ret = EnrollPacket.EnrollRet.parseFrom(response.data);
            LogUtils.d(TAG, "ret = %s", ret.getToken());
            user.token = ret.getToken();
            user.userId = ret.getUserId();
            return user;
        } catch (InvalidProtocolBufferException e) {
            throw new ParseError();
        }
    }
}
