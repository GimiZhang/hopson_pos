package com.hopson.cash_module.main;

import com.hopson.cash_module.api.CashApi;
import com.hopson.cash_module.base.BasePresenter;
import com.hopson.network_module.http.OnSubscriberSucAndFault;
import com.hopson.network_module.http.RequestManagerListener;

public class CashPresenter extends BasePresenter<CashContractor.View> implements CashContractor.Presenter {
    @Override
    public void interruptHttp() {

    }

    @Override
    public void getMarkets() {
        OnSubscriberSucAndFault sucAndFault = new OnSubscriberSucAndFault(new RequestManagerListener() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onFail(String result) {

            }
        });

        CashApi.getMarktList(sucAndFault);
    }
}
