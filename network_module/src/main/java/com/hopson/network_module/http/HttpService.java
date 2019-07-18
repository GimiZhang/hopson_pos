package com.hopson.network_module.http;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * 服务器接口地址
 * Created by zgm on 2017/11/1/001.
 */

public interface HttpService {

    /**
     * GET请求
     *
     * @param url
     * @param map 地址拼接请求参数
     * @return
     */
    @GET("business/getMarkets/{url}/{b}")
    Observable<ResponseBody> get(@Path("url") String url, @Path("b") String b, @QueryMap Map<String, Object> map);


    /**
     * POST请求
     *
     * @param url
     * @param map body请求参数
     * @return
     */
    @POST("{url}")
    Observable<ResponseBody> post(@Path("url") String url, @QueryMap Map<String, Object> map);

}
