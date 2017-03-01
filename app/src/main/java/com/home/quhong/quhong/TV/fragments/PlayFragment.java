package com.home.quhong.quhong.TV.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.adapter.RecycleAdapter;
import com.home.quhong.quhong.TV.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayFragment extends Fragment {


    private  RecyclerView mPlayerRecycler;
    private RecycleAdapter mAdapter;
    private List<String> mDatas;
    public static PlayFragment newIntance() {
        return new PlayFragment();
    }

    public PlayFragment() {
        // Required empty public constructor
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
        mAdapter = new RecycleAdapter(getContext(), mDatas);
        mAdapter.setOnItemClickListener(new RecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtil.ShortToast(position+"被点击了");
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 6);
        mPlayerRecycler.setLayoutManager(layoutManager);
        mPlayerRecycler.setAdapter(mAdapter);
    }
    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 1; i < 9; i++) {
            mDatas.add(String.valueOf(i));
        }
    }
}
