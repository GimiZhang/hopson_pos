package com.hopson.cash_module.main;

import android.view.View;

import com.hopson.cash_module.R;
import com.hopson.cash_module.base.BaseActivity;
import com.hopson.network_module.http.BaseUrlConfig;
import com.xiaojinzi.component.anno.RouterAnno;

@RouterAnno(host = "cash", path = "cashActivity")
public class CashMainActivity extends BaseActivity<CashContractor.View, CashPresenter> implements CashContractor.View {


    @Override
    public CashPresenter createPresenter() {
        return new CashPresenter();
    }

    @Override
    public View getInflateView() {
        return View.inflate(this, R.layout.activity_cash_main, null);
    }

    @Override
    public void initViews() {
        mPresenter.getMarkets();
    }
}
