package com.home.quhong.quhong.TV.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by aserbao on 2017/3/13.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */

public class DownLoadedRecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    @Override
    public RecycleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecycleAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MyViewHodler extends RecyclerView.ViewHolder{

        public MyViewHodler(View itemView) {
            super(itemView);
        }
    }
}
