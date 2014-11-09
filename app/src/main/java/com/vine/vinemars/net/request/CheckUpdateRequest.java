package com.vine.vinemars.net.request;

import com.vine.vinemars.domain.Version;
import com.vine.vinemars.net.NetworkRequestListener;

/**
 * Created by chengfei on 14-10-21.
 */
public abstract class CheckUpdateRequest extends BaseRequest<Version> {


    public CheckUpdateRequest(String checkCode, NetworkRequestListener listener) {
        super(checkCode, listener);
    }
}
