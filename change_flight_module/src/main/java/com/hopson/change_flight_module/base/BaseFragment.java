package com.hopson.change_flight_module.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hopson.change_flight_module.R;

/**
 * Created by hkj on 2017/11/29.
 */

public abstract class BaseFragment<V, P extends BasePresenter<V>> extends Fragment {
    private ProgressBar progressBar;
    protected TextView btRetryError;
    protected LinearLayout llBaseEmptyView;
    protected ImageView ivBaseEmptyView;
    protected TextView tvBaseEmptyView;
    private View maskView;
    protected Activity mContext;
    protected P mPresenter;
    private RelativeLayout relRetryError;
    private FrameLayout frameLayout;
    protected View contentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = View.inflate(getActivity(), R.layout.common_fragment_base_layout, null);
        contentView = View.inflate(getActivity(), getLayoutResId(), null);
        frameLayout = (FrameLayout) root.findViewById(R.id.fl_layout);
        progressBar = (ProgressBar) root.findViewById(R.id.progress);
        relRetryError = (RelativeLayout) root.findViewById(R.id.rel_retry_error);
        btRetryError = (TextView) root.findViewById(R.id.bt_retry_error);
        llBaseEmptyView = (LinearLayout) root.findViewById(R.id.ll_base_empty);
        ivBaseEmptyView = (ImageView) root.findViewById(R.id.iv_base_empty);
        tvBaseEmptyView = (TextView) root.findViewById(R.id.tv_base_empty);
        maskView = root.findViewById(R.id.view_mask);
        frameLayout.addView(contentView);
        maskView.setOnClickListener(null);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
        mPresenter = createPresenter();
        if (null != mPresenter) {
            mPresenter.attachView((V) this);
        }
        initViews();
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

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * 获取布局文件
     *
     * @return
     */
    @LayoutRes
    public abstract int getLayoutResId();

    /**
     * 初始化view
     */
    public abstract void initViews();

    /**
     * 显示progressbar
     */
    public void showProgress() {
        if (progressBar.getVisibility() == View.GONE) {
            maskView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        }
    }


    /**
     * 隐藏progressbar
     */
    public void hideProgress() {
        if (progressBar.getVisibility() == View.VISIBLE) {
            maskView.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
        }
    }

    /**
     * 显示重试按钮页面
     */
    public void showRetryErrorView() {
        relRetryError.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏重试按钮页面
     */
    public void hideRetryErrorView() {
        relRetryError.setVisibility(View.GONE);
    }

    /**
     * 显示空页面
     */
    public void showEmptyView() {
        llBaseEmptyView.setVisibility(View.VISIBLE);
    }


    /***
     * 隐藏空页面
     */
    public void hideEmptyView() {
        llBaseEmptyView.setVisibility(View.GONE);
    }

    /**
     * 设置页面为空时的图片
     *
     * @param resId
     */
    public void setEmptyViewImageRes(@DrawableRes int resId) {
        ivBaseEmptyView.setImageResource(resId);
    }

    /**
     * 设置页面为空时的提示
     *
     * @param emptyText
     */
    public void setEmptyViewText(String emptyText) {
        tvBaseEmptyView.setText(emptyText);
    }

    /**
     * 设置页面为空时的图片和提示
     *
     * @param resId
     * @param emptyText
     */
    public void setEmptyViewImageAndText(@DrawableRes int resId, String emptyText) {
        ivBaseEmptyView.setImageResource(resId);
        tvBaseEmptyView.setText(emptyText);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mPresenter) {
            mPresenter.detachView();
            mPresenter.interruptHttp();
        }
    }


}
