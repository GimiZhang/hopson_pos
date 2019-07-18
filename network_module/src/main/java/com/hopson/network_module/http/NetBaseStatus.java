package com.hopson.network_module.http;

/**
 * Created by Mars on 2017/2/25 09:37.
 * 描述：接口回调描述
 */

public class NetBaseStatus {
    public String msg;
    public String code;
    public String data;
    public boolean succ;
    public boolean isConnetError;

    @Override
    public String toString() {
        return "NetBaseStatus{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", succ=" + succ +
                ", isConnetError=" + isConnetError +
                '}';
    }
}
