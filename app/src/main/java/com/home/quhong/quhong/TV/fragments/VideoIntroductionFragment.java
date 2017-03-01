package com.home.quhong.quhong.TV.fragments;

import android.os.Bundle;

import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.base.RxLazyFragment;
import com.home.quhong.quhong.TV.network.RetrofitHelper;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by aserbao on 2017/2/28.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */

public class VideoIntroductionFragment extends RxLazyFragment {
    private int av;
    public static VideoIntroductionFragment newInstance(int aid) {

        VideoIntroductionFragment fragment = new VideoIntroductionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("extra_av", aid);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.player_recycle_estimate;
    }

    @Override
    public void finishCreateView(Bundle state) {
        av = getArguments().getInt("extra_av");
        loadData();
    }

    protected void loadData() {


    }

    @Override
    protected void lazyLoad() {

    }
}
