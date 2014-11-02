package com.vine.vinemars.net;

/**
 * Created by chengfei on 14-10-25.
 */
public class PackageHead {
    public int packetId;
    public long stamp;
    public int retCode;
    public int flag;

    public PackageHead(int packetId) {
        this.packetId = packetId;
    }
}
