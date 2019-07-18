package com.hopson.hopson_pos.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hopson.base_module.utils.TitleBar;
import com.hopson.base_module.utils.UiUtil;
import com.hopson.hopson_pos.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 类描述：
 * 创建者：zgm on 2017/11/18/018.
 */

public abstract class BaseActivity<V, P extends BasePresenter<V>> extends AppCompatActivity {

    FrameLayout frameLayout;
    ProgressBar progressBar;
    View maskView;
    protected TitleBar titleBar;
    protected TextView btRetryError;
    private LinearLayout llBaseEmptyView;
    private ImageView ivBaseEmptyView;
    private TextView tvBaseEmptyView;
    protected ImageView ivBg;
    private List<View> views = new ArrayList<>();
    protected P mPresenter;
    private RelativeLayout relRetryError;
    private Dialog shareDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = getInflateView();
        UiUtil.setStatusBarColor(this, R.color.colorAccent);
        setContentView(R.layout.common_activity_base_layout);
        frameLayout = (FrameLayout) findViewById(R.id.fl_layout);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        btRetryError = (TextView) findViewById(R.id.bt_retry_error);
        relRetryError = (RelativeLayout) findViewById(R.id.rel_retry_error);
        llBaseEmptyView = (LinearLayout) findViewById(R.id.ll_base_empty);
        ivBaseEmptyView = (ImageView) findViewById(R.id.iv_base_empty);
        tvBaseEmptyView = (TextView) findViewById(R.id.tv_base_empty);
        maskView = findViewById(R.id.view_mask);
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        ivBg = (ImageView) findViewById(R.id.ivBg);
        frameLayout.addView(contentView);
        maskView.setOnClickListener(null);
        titleBar.setLeftImageResource(R.mipmap.back_black);
        titleBar.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        titleBar.setTitleColor(getResources().getColor(R.color._ffffff));
        if (null == mPresenter) {
            mPresenter = createPresenter();
        }
        if (null != mPresenter) {
            mPresenter.attachView((V) this);
        }
        initViews();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public abstract P createPresenter();

    /**
     * 获取Presenter
     *
     * @return 返回子类创建的Presenter
     */
    public P getPresenter() {
        return mPresenter;
    }

    /**
     * 布局文件
     *
     * @return
     */
    public abstract View getInflateView();

    /**
     * 初始化数据
     */
    public abstract void initViews();


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {


    }

    /**
     * 显示progressBar
     */
    public void showProgress() {
        maskView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }


    /**
     * 隐藏progressBar
     */
    public void hideProgress() {
        maskView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    /**
     * 重试按钮
     */
    public void showRetryErrorView() {
        relRetryError.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏重试按钮
     */
    public void hideRetryErrorView() {
        relRetryError.setVisibility(View.GONE);
    }

    /**
     * 显示空布局
     */
    public void showEmptyView() {
        llBaseEmptyView.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏空布局
     */
    public void hideEmptyView() {
        llBaseEmptyView.setVisibility(View.GONE);
    }

    /**
     * 设置空布局图片
     *
     * @param resId
     */
    public void setEmptyViewImageRes(@DrawableRes int resId) {
        ivBaseEmptyView.setImageResource(resId);
    }

    /**
     * 设置空布局文字
     *
     * @param emptyText
     */
    public void setEmptyViewText(String emptyText) {
        tvBaseEmptyView.setText(emptyText);
    }

    /**
     * 设置空布局图片和文字
     *
     * @param resId
     * @param emptyText
     */
    public void setEmptyViewImageAndText(@DrawableRes int resId, String emptyText) {
        ivBaseEmptyView.setImageResource(resId);
        tvBaseEmptyView.setText(emptyText);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.detachView();
            mPresenter.interruptHttp();
        }
    }


    /**
     * 返回按钮监听
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

}
