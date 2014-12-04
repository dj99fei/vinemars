package com.vine.vinemars.net.request;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.google.protobuf.InvalidProtocolBufferException;
import com.vine.vinemars.domain.User;
import com.vine.vinemars.net.AppHead;
import com.vine.vinemars.net.HOpCodeEx;
import com.vine.vinemars.net.NetworkRequestListener;
import com.vine.vinemars.net.ParseError;
import com.vine.vinemars.net.pb.EnrollMessage;

import java.util.Map;

/**
 * Created by chengfei on 14-10-25.
 */
public class EnrollRequest extends BaseRequest<User> {

    private static final String TAG = EnrollRequest.class.getSimpleName();
    private User user;
    public EnrollRequest(User user,String checkCode, NetworkRequestListener listener) {
        super(checkCode, HOpCodeEx.Enroll, listener);
        this.user = user;
        setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, 0));
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        EnrollMessage.Enroll.Builder builder = EnrollMessage.Enroll.newBuilder();
//        builder.setEmail(user.email);
        builder.setMobile(user.username);
        builder.setPassword(user.password);
        builder.setLoginType(EnrollMessage.LoginType.ENROLL);
//        builder.setCheckCode(checkCode);
        return builder.build().toByteArray();
    }

    @Override
    protected User parse(AppHead appHead, NetworkResponse response) throws ParseError {
        try {
            Map<String, String> headers = response.headers;
            String sessionId = headers.get("sessionId");
            EnrollMessage.RetrieveUserBasicInfoRet ret = EnrollMessage.RetrieveUserBasicInfoRet.parseFrom(response.data);
            user.signature = ret.getSignature();
            user.nickname = ret.getNickname();
            user.gender = ret.getSex();
            user.sessionId = sessionId;
            user.userId = appHead.userId;
            user.token = appHead.token;
            return user;
        } catch (InvalidProtocolBufferException e) {
            throw new ParseError();
        }
    }
}
