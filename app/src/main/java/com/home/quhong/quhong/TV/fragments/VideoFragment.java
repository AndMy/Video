package com.home.quhong.quhong.TV.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.adapter.VideoFragmentAdapter;
import com.home.quhong.quhong.TV.base.RxLazyFragment;
import com.home.quhong.quhong.TV.entity.video.Data;
import com.home.quhong.quhong.TV.entity.video.VideoDetail;
import com.home.quhong.quhong.TV.network.RetrofitHelper;
import com.home.quhong.quhong.TV.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends RxLazyFragment implements SwipeRefreshLayout.OnRefreshListener{


    @BindView(R.id.fargment_video_recycle)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    private List<Data> mDatas;
    private VideoFragmentAdapter mAdapter;
    private int mLastVisibleItem;
    private boolean mRefreshing;
    private LinearLayoutManager mLayoutManager;


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_video;
    }

    @Override
    public void finishCreateView(Bundle state) {
        getVideoDetail();
        initListeners();
        initRecycleView();
    }

    private void initListeners() {
       /*mRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.addOnScrollListener(
                new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        mLastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
                        if (mLastVisibleItem == mAdapter.getItemCount() - 1 && !mRefreshing) {
                            // TODO: 2017/3/4 加载更多Video信息
                            ToastUtil.ShortToast("加载更多Video信息");
                        }
                        *//*if (mAdapter.getVideoViewHolder() != null) {
                            mAdapter.getVideoViewHolder().videoReset();
                        }*//*
                    }
                }

        );*/
    }

    private void initRecycleView() {
        mAdapter = new VideoFragmentAdapter(getActivity());
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void getVideoDetail() {
        RetrofitHelper.getVideoApi()
                .getVideoDetail()
                .compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::finishTask, throwable -> {
                    initEmptyView();
                });
    }

    private void finishTask(VideoDetail videoDetail) {
        mDatas = videoDetail.data;
        ToastUtil.ShortToast("出数据了");
        mAdapter.setVideoDetail(mDatas);
        mAdapter.notifyDataSetChanged();
    }


    private void initEmptyView() {
        ToastUtil.ShortToast("失败");
    }


    @Override
    protected void lazyLoad() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onRefresh() {
        //Todo:数据刷新
        ToastUtil.ShortToast("刷新？条数据");
    }
}
