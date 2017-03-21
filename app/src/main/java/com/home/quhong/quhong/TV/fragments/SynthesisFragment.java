package com.home.quhong.quhong.TV.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.adapter.SynthesisRecyclerViewAdapter;
import com.home.quhong.quhong.TV.base.RxLazyFragment;
import com.home.quhong.quhong.TV.entity.LiveIndex;
import com.home.quhong.quhong.TV.entity.Result;
import com.home.quhong.quhong.TV.entity.home.Synthesis;
import com.home.quhong.quhong.TV.network.RetrofitHelper;
import com.home.quhong.quhong.TV.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by aserbao on 2017/2/20.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */

public class SynthesisFragment extends RxLazyFragment {
    @BindView(R.id.recycle)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private SynthesisRecyclerViewAdapter mRecyclerViewAdapter;

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
        mRecyclerViewAdapter = new SynthesisRecyclerViewAdapter(getActivity());
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        GridLayoutManager layout = new GridLayoutManager(getActivity(), 12);
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        layout.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup()
        {
            @Override
            public int getSpanSize(int position)
            {
                return mRecyclerViewAdapter.getSpanSize(position);
            }
        });
        mRecyclerView.setLayoutManager(layout);
    }
    private void showProgressBar() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(this::getLive);
        mSwipeRefreshLayout.post(() -> {

            mSwipeRefreshLayout.setRefreshing(true);
            getLive();
        });
    }

    public void getLive()
    {
        RetrofitHelper.getLiveApi()
                .getSymthesHomeIndex()
                .compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::finishTask, throwable -> {
                    initEmptyView();
                });
    }
    private void initEmptyView()
    {
        mSwipeRefreshLayout.setRefreshing(false);
        mRecyclerView.setVisibility(View.GONE);
    }




    private void finishTask(Synthesis liveIndex)
    {
        hideEmptyView();
        mSwipeRefreshLayout.setRefreshing(false);
        mRecyclerViewAdapter.setLiveIndex(liveIndex);
        mRecyclerViewAdapter.notifyDataSetChanged();
        mRecyclerView.scrollToPosition(0);
    }


    public void hideEmptyView()
    {
        mRecyclerView.setVisibility(View.VISIBLE);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
