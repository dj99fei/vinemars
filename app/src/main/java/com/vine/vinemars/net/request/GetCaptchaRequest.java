package com.vine.vinemars.net.request;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.vine.vinemars.net.AppHead;
import com.vine.vinemars.net.HOpCodeEx;
import com.vine.vinemars.net.NetworkRequestListener;
import com.vine.vinemars.net.ParseError;
import com.vine.vinemars.net.pb.EnrollMessage;

/**
 * Created by chengfei on 14/12/1.
 */
public class GetCaptchaRequest extends BaseRequest<String> {

    public GetCaptchaRequest(NetworkRequestListener listener) {
        super(HOpCodeEx.GetValidateCode, listener);
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        EnrollMessage.GetValidateCode.Builder builder = EnrollMessage.GetValidateCode.newBuilder();
        return builder.build().toByteArray();
    }

//    @Override
//    protected String parse(NetworkResponse response) throws ParseError {
//        try {
//            EnrollMessage.GetValidateCodeRet ret = EnrollMessage.GetValidateCodeRet.parseFrom(response.data);
//            return ret.getValidateCode();
//        } catch (InvalidProtocolBufferException e) {
//            throw new ParseError();
//        }
//    }

    @Override
    protected String parse(AppHead appHead, NetworkResponse response) throws ParseError {
        return null;
    }
}
