package com.vine.vinemars.bus;

/**
 * Created by chengfei on 14/11/23.
 */
public class LoginEvent {

    private boolean login;

    public LoginEvent(boolean login) {
        this.login = login;
    }

    public boolean isLogin() {
        return login;
    }
}
