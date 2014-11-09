package com.vine.vinemars.net;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.vine.vinemars.net.pb.EnrollPacket;

/**
 * Created by chengfei on 14/10/29.
 */
public class ChangePasswordRequest extends BaseRequest<Boolean> {

    private String oldPassword;
    private String newPassword;

    public ChangePasswordRequest(String oldPassword, String newPassword, NetworkRequestListener listener) {
        super(listener);
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    @Override
    public int getRequestId() {
        return HOpCodeEx.ResetPassword;
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
