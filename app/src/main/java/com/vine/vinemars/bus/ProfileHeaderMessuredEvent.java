package com.vine.vinemars.bus;

/**
 * Created by chengfei on 14/11/16.
 */
public class ProfileHeaderMessuredEvent {

    private int height;

    public ProfileHeaderMessuredEvent(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }
}
