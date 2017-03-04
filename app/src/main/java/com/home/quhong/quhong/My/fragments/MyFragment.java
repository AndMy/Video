package com.home.quhong.quhong.My.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.home.quhong.quhong.My.adapter.MyRecycleAdapter;
import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.adapter.RecycleAdapter;
import com.home.quhong.quhong.TV.base.RxLazyFragment;
import com.home.quhong.quhong.TV.fragments.PlayFragment;
import com.home.quhong.quhong.TV.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends RxLazyFragment {


    @BindView(R.id.my_recycle_view)
    RecyclerView mMyRecycleView;
    @BindView(R.id.text)
    TextView mText;
    private MyRecycleAdapter mAdapter;
    private List<String> mDatas;
    @Override
    public int getLayoutResId() {
        return  R.layout.fragment_my;
    }

    @Override
    public void finishCreateView(Bundle state) {

        mAdapter = new MyRecycleAdapter();
        mAdapter.setOnItemClickListener(new RecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtil.ShortToast(position+"被点击了");
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mMyRecycleView.setLayoutManager(layoutManager);
        mMyRecycleView.setAdapter(mAdapter);
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

  /*  @Override
    public int getLayoutResId() {
        return R.layout.fragment_my;
    }


    @Override
    public void finishCreateView(Bundle state) {
        mText.setText("测试中……");
        MyRecycleAdapter adapter = new MyRecycleAdapter();
        mMyRecycleView.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 6);
        mMyRecycleView.setLayoutManager(manager);
    }

    @Override
    protected void lazyLoad() {

    }*/

}
