package com.home.quhong.quhong.TV.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.base.RxLazyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends RxLazyFragment {


    @BindView(R.id.video_recycle)
    RecyclerView mVideoRecycle;

    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_video;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void finishCreateView(Bundle state) {
        initRecycleView();
    }

    private void initRecycleView() {

    }

    @Override
    protected void lazyLoad() {

    }

}
