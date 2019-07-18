package com.hopson.cash_module.api;

import com.hopson.base_module.BaseApp;
import com.hopson.network_module.http.HttpRetrofitManager;

import java.util.HashMap;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;

public class CashApi {

    public static HttpRetrofitManager instance = HttpRetrofitManager.getInstance(BaseApp.baseAppContext);

    public static void getMarktList(Subscriber<ResponseBody> subscriber) {
        HashMap<String, Object> paramsMap = new HashMap<>();
        Observable<ResponseBody> observable = instance.getHttpService().get("116.467784","39.891225", paramsMap);
        instance.toSubscribe(observable, subscriber);
    }

}
