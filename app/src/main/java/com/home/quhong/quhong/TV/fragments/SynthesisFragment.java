package com.home.quhong.quhong.TV.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.base.RxLazyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aserbao on 2017/2/20.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */

public class SynthesisFragment extends RxLazyFragment {
    @BindView(R.id.recycle)
    RecyclerView mRecycleView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    public static SynthesisFragment newIntance(){
        return new SynthesisFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_synthesis;
    }

    @Override
    public void finishCreateView(Bundle state) {
        isPrepared = true ;
        lazyLoad();
    }

    @Override
    protected void lazyLoad()
    {

        if (!isPrepared || !isVisible)
            return;

        showProgressBar();
        initRecyclerView();
        isPrepared = false;
    }

    private void initRecyclerView() {

    }

    private void showProgressBar() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(this::getQuHongSynthesis);

        mSwipeRefreshLayout.post(() -> {

            mSwipeRefreshLayout.setRefreshing(true);
            getQuHongSynthesis();
        });
    }

    private void getQuHongSynthesis() {
        // TODO: 2017/2/20 使用Retrofit请求实现网络数据加载

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
