package com.vine.vinemars.net;

import com.vine.vinemars.MyApplication;

/**
 * Created by chengfei on 14-10-25.
 */
public class AppHead {

    /*macId*/
    public  String macId;
    /*apk version*/
    public String versionCode;
    /* 操作系统版本 */
    public String deviceSystem;
    /* 设备名称  */
    public String deviceName;
    /* 设备编号*/
    public String deviceNo;
    /* 网络类型*/
    public String networkType ;
    /*设备厂商*/
    public String deviceBrand;
    /*机型*/
    public String deviceType;
    /*网络运营商*/
    public String operator;
    /*登录地区名称*/
    public String area;
    /*登录国家名称*/
    public String country;
    /*渠道号*/
    public String channelId;
    /*是否越狱*/
    public String prisonBreak;
    /*服务器号*/
    public String serverId;
    /*用户ID*/
    public String userId;
    /*令牌*/
    public String token;


    public AppHead() {
        versionCode = "1";
        if (MyApplication.isSignined()) {
            userId = MyApplication.getUser().userId;
            token = MyApplication.getUser().token;
        }
    }
}
