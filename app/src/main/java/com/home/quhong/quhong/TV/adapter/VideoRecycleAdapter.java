package com.home.quhong.quhong.TV.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by aserbao on 2017/2/24.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */
//// TODO: 2017/2/24 待完成界面
public class VideoRecycleAdapter extends RecyclerView.Adapter<VideoRecycleAdapter.MyViewHolder> {
    private Context mContext;
    private List<String> mStrings;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
