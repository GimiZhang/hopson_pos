package com.hopson.network_module.http;


/**
 * 类描述：请求接口回调
 * Created by zgm on 2017/2/22 0021.
 */

public interface RequestManagerListener {
    /**
     * @param result
     */
    void onSuccess(String result);
    void onFail(String result);


}
