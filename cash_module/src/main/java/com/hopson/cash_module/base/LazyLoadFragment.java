package com.hopson.cash_module.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 创建时间： 2017/2/10
 * 创建者：hkj
 * 类描述：懒加载fragment
 **/
public abstract class LazyLoadFragment<V, P extends BasePresenter<V>> extends BaseFragment<V, P> {
    //是否可见
    protected boolean isVisible;
    // 标志位，标志Fragment已经初始化完成。
    public boolean isPrepared = false;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isPrepared = true;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInVisible();
        }
    }


    public void onInVisible() {

    }

    public void onVisible() {
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
