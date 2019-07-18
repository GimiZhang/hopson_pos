package com.hopson.change_flight_module.base;

/**
 * Created by hkj on 2017/11/29.
 */

public interface BaseIView {
    void showFail(String msg);

    void showRequestLoading();

    void hideRequestLoading();
}
