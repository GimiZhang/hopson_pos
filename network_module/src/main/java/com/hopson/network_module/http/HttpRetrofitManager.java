package com.hopson.network_module.http;

import android.app.Application;

import com.apkfuns.logutils.LogUtils;
import com.hopson.network_module.BuildConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 网络请求封装
 * Created by 张莞民 on 2017/11/1/001.
 */

public class HttpRetrofitManager {

    private static final int DEFAULT_TIME_OUT = 120;
    private static final int DEFAULT_READ_OUT = 120;
    private static final int RETRY_COUNT = 0;//默认请求失败，不重试
    private Retrofit mRetrofit;
    private static Map<String, Object> pubParams;
    private static Application mApplication;

    private HttpRetrofitManager() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();


        //添加证书
        InputStream server = null;
        try {
            server = mApplication.getAssets().open("chain.der");
        } catch (IOException e) {
            e.printStackTrace();
        }
        builder.sslSocketFactory(NetConfig.setCertificates(mApplication, new InputStream[]{server}));

        //设置头信息
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request oldRequest = chain.request();
                // 添加新的参数
                HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                        .newBuilder()
                        .scheme(oldRequest.url().scheme())
                        .host(oldRequest.url().host());
                if (pubParams != null) {
                    for (String key : pubParams.keySet()) {
                        authorizedUrlBuilder.addQueryParameter(key, pubParams.get(key).toString());
                    }
                }
                // 新的请求
                Request newRequest = oldRequest.newBuilder()
                        .method(oldRequest.method(), oldRequest.body())
                        .url(authorizedUrlBuilder.build())
                        .build();
                return chain.proceed(newRequest);
            }
        };
        builder.addInterceptor(interceptor);

        //拦截器设置
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //非正式环境打log
                if (!BuildConfig.ENVIRONMENT) {
                    LogUtils.e(message);
                }
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(httpLoggingInterceptor);

        //设置超时时间
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_READ_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_READ_OUT, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);

        //创建retrofit实例
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BaseUrlConfig.BASE_URL)
                .build();
    }


    /**
     * 获取单例Retrofit对象
     */
    private static class RetrofitSingleTon {
        private static final HttpRetrofitManager INSTANCE = new HttpRetrofitManager();
    }

   /* public static HttpRetrofitManager getInstance(Map<String, Object> pubParams, Application application) {
        HttpRetrofitManager.pubParams = pubParams;
        mApplication = application;
        return RetrofitSingleTon.INSTANCE;
    }*/

    public static HttpRetrofitManager getInstance(Application application) {
        mApplication = application;
        return RetrofitSingleTon.INSTANCE;
    }

    /**
     * 获取接口服务对象
     *
     * @return
     */
    public HttpService getHttpService() {
        return mRetrofit.create(HttpService.class);
    }


    public <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(RETRY_COUNT)//请求失败重连次数
                .subscribe(s);

    }

}
