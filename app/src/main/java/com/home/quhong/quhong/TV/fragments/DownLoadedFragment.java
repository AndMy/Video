package com.home.quhong.quhong.TV.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.adapter.DownLoadedBaseFragment;
import com.home.quhong.quhong.TV.adapter.DownloadedRecycylerAdapter;
import com.home.quhong.quhong.TV.utils.ConstantUtil;
import com.home.quhong.quhong.TV.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class DownLoadedFragment extends DownLoadedBaseFragment {
    private onRecyclerItemClick mListener;
    @BindView(R.id.down_recycler_view)
    RecyclerView mDownRecyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_downloaded, null);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    private void initView() {
        DownloadedRecycylerAdapter adapter = new DownloadedRecycylerAdapter(getActivity());
        adapter.setOnRecyclerItemClick(new DownloadedRecycylerAdapter.onRecyclerItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                mListener.onRecyclerItemClick(position);
                Log.d("Test", "onItemClick: " + position);
            }
        });
        mDownRecyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mDownRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public String getFragmentTitle() {
        return "Play";
    }

    public interface onRecyclerItemClick{
        void onRecyclerItemClick(int message);
    }
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try {
            mListener = (DownLoadedFragment.onRecyclerItemClick) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + "must implement OnGridViewSelectedListener");
        }
    }

}
