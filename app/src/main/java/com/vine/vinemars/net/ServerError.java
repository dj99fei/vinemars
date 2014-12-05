package com.vine.vinemars.net;

import android.content.res.Resources;

import com.android.volley.VolleyError;
import com.vine.vinemars.MyApplication;
import com.vine.vinemars.R;

/**
 * Created by chengfei on 14/12/5.
 */
public class ServerError extends VolleyError {

    private int errorCode;

    /* 执行成功 */
    public static final int SUCCESS = 1;
    /* 执行失败 */
    public static final int FAIL = 0;
    /* 执行异常 */
    public static final int EXCEPTION = 2;
    public static final int DB_EXCEPTION = 3;
    public static final int DATACONVERTER_EXCEPTION = 4;
    //,"需要登录"
    public static final int NEED_LOGIN = 11;


    //,"请求消息为空"
//    public static final int MESSAGE_EMPTY = 12;
    //,"请求消息解析错误"
//    public static final int MESSAGE_PARSE_ERROR = 13;
    //,"请求消息包ID为空"
//    public static final int PACKET_ID_EMPTY = 14;
    //"请求包锁定中"
//    public static final int PACKET_ID_LOCKED = 15;

    /*---------应用公共错误码51--100-------------*/
    public static final int MESSAGE_PARSE_ERROR = 13;
    public static final int TOKEN_IS_NULL = 14;

    public static final int USER_LOG_FAIL = 15;

    //98，99 is used.
//    public static final int RETCODE_PACKETID_LOCKED =  99;

    /*-------Enroll错误码101-200-------------------------------------------------------------------------------------*/
    public static final int ENROLL_MOBILE_OR_EMAIL_EXIST = 101;
    /*密码错误*/
    public static final int ENROLL_USER_OR_PASSWORD_ERROR = 102;
    /*邮箱已注册*/
    public static final int ENROLL_EMAIL_EXIST = 103;
    /*手机已注册*/
    public static final int ENROLL_MOBILE_EXIST = 104;
    /*手机已注册*/
    public static final int ENROLL_USER_NAME_EXIST = 105;
    //    mobileNo or email must input
    public static final int ENROLL_EMAIL_OR_MOBILE_EMPTY = 106;

    public static final int ENROLL_VALIDATE_CODE_ERROR = 107;
    /**手机号验证失败*/
    public static final int MOBILE_VERIFY_FAIL = 108;
    /**邮箱验证失败*/
    public static final int EMAIL_VERIFY_FAIL = 109;

    /*用户信息更新失败*/
    public static final int USER_UPDATE_ERROR = 120;

    public static final int USER_ID_IS_EMPTY = 121;

    public static final int USER_PASSWORD_IS_WRONG = 122;

    public static final int USER_NAME_NOT_EXIST = 123;


    public static final int USER_TOKEN_EXPIRED = 124;
    /*查询记录为空*/
    public static final int QUERY_RESULT_IS_NULL = 125;

    private Resources resources;
    public ServerError(int errorCode) {
        this.errorCode = errorCode;
        this.resources = MyApplication.get().getResources();
    }

    @Override
    public String getMessage() {
        switch (errorCode) {
            case USER_NAME_NOT_EXIST:
            case USER_PASSWORD_IS_WRONG:
                return resources.getString(R.string.invalid_username_password);
            default:
                return "";
        }
    }
}
