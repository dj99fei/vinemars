package com.vine.vinemars.net;

import com.vine.vinemars.domain.Version;

/**
 * Created by chengfei on 14-10-21.
 */
public abstract class CheckUpdateRequest extends BaseRequest<Version> {


    public CheckUpdateRequest(String checkCode, NetworkRequestListener listener) {
        super(checkCode, listener);
    }
}
