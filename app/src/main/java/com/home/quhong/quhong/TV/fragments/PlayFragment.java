package com.home.quhong.quhong.TV.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.adapter.RecycleAdapter;
import com.home.quhong.quhong.TV.entity.home.SeriesBean;
import com.home.quhong.quhong.TV.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayFragment extends Fragment {

    private OnButtonClickListener mListener;
    private  RecyclerView mPlayerRecycler;
    private RecycleAdapter mAdapter;
    private List<String> mDatas;
    private List<SeriesBean> mSeries = new ArrayList<>();
    public static PlayFragment newIntance() {
        return new PlayFragment();
    }

    public PlayFragment() {
        // Required empty public constructor
    }
    public void setData(List<SeriesBean> m){
        if (m != null) {
            mSeries = m;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_play, container, false);
        mPlayerRecycler = (RecyclerView) view.findViewById(R.id.player_recycler);
        initData();
        init();
        return view;
    }

    private void init() {
        mAdapter = new RecycleAdapter(getContext());
        mAdapter.setOnItemClickListener(new RecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mListener.OnButtonClickListener(position);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 5);
        mPlayerRecycler.setLayoutManager(layoutManager);
        mPlayerRecycler.setAdapter(mAdapter);
    }
    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 1; i < 18; i++) {
            mDatas.add(String.valueOf(i));
        }
    }

    public interface OnButtonClickListener{
         void OnButtonClickListener(int message);
    }
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try {
            mListener = (OnButtonClickListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + "must implement OnGridViewSelectedListener");
        }
    }
}
