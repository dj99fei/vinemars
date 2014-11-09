package com.vine.vinemars.net.request;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.vine.vinemars.net.HOpCodeEx;
import com.vine.vinemars.net.NetworkRequestListener;
import com.vine.vinemars.net.ParseError;
import com.vine.vinemars.net.pb.EnrollPacket;

/**
 * Created by chengfei on 14/10/29.
 */
public class ChangePasswordRequest extends BaseRequest<Boolean> {

    private String oldPassword;
    private String newPassword;

    public ChangePasswordRequest(String oldPassword, String newPassword, NetworkRequestListener listener) {
        super(HOpCodeEx.ResetPassword, listener);
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        EnrollPacket.ResetPassword.Builder builder = EnrollPacket.ResetPassword.newBuilder();
        builder.setCheckCode("");
        builder.setNewPassword(newPassword);
        builder.setOldPassword(oldPassword);
        builder.setUserId("c21c3d193bd3440693fa1b3dfca5705b");
        return builder.build().toByteArray();
    }

    @Override
    protected Boolean parse(NetworkResponse response) throws ParseError {
        return true;
    }


}
