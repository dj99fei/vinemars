package com.vine.vinemars.net.request;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.google.protobuf.InvalidProtocolBufferException;
import com.vine.vinemars.domain.DeviceInfo;
import com.vine.vinemars.domain.User;
import com.vine.vinemars.net.HOpCodeEx;
import com.vine.vinemars.net.NetworkRequestListener;
import com.vine.vinemars.net.ParseError;
import com.vine.vinemars.net.pb.EnrollPacket;
import com.vine.vinemars.utils.LogUtils;

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
        EnrollPacket.Enroll.Builder builder = EnrollPacket.Enroll.newBuilder();
        builder.setEmail(user.email);
        builder.setMobileNo(DeviceInfo.imei);
        builder.setPassword(user.password);
        builder.setCheckCode(checkCode);
        return builder.build().toByteArray();
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
