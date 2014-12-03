package com.vine.vinemars.net;

/**
 * Created by chengfei on 14/12/1.
 */
public class ResponseWrapper<T> {
    public T entity;
    public  PackageHead packageHead;

    public ResponseWrapper(PackageHead packageHead, T response) {
        this.entity = response;
        this.packageHead = packageHead;
    }
}
