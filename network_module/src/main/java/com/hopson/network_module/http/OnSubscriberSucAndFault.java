package com.hopson.network_module.http;

import android.util.Log;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import rx.Subscriber;

/**
 * Created by zgm on 2017/11/1/001.
 */

public class OnSubscriberSucAndFault extends Subscriber<ResponseBody> {
    private static final String TAG = "OnSubscriberSucAndFault";
    private RequestManagerListener requestManagerListener;

    public OnSubscriberSucAndFault(RequestManagerListener requestManagerListener) {
        this.requestManagerListener = requestManagerListener;
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCompleted() {
        //请求完成后 解除订阅操作
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.e("ProgressSubscriber", "onError:" + e.getMessage());
    }

    /**
     * 服务器返回数据处理
     *
     * @param responseBody
     */
    @Override
    public void onNext(ResponseBody responseBody) {
        try {
            String result = responseBody.string();
            JSONObject jsonObject = new JSONObject(result);
            //正常请求回调
            int resultCode = jsonObject.getInt("code");
            if (resultCode == 200) {
                requestManagerListener.onSuccess(result);
            } else {
                requestManagerListener.onFail(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
