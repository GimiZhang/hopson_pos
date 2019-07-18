package com.hopson.flight_module.base;

/**
 * Created by hkj on 2017/12/11.
 */

public abstract class BasePresenter<V> {
    protected V mView;

    /**
     * 绑定V层
     *
     * @param view
     */
    public void attachView(V view) {
        mView = view;
    }

    /**
     * 解绑V层
     */
    public void detachView() {
        mView = null;
    }

    /**
     * 中断网络请求
     */
    public abstract void interruptHttp();

    /**
     * 获取V层
     */

    public V getView() {
        return mView;
    }


}
