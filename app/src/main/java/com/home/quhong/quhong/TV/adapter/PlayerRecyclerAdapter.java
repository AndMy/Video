package com.home.quhong.quhong.TV.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.home.quhong.quhong.R;

import static com.home.quhong.quhong.TV.adapter.BerandaAdapter.TYPE_BANNER;

/**
 * Created by aserbao on 2017/2/23.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */

public class PlayerRecyclerAdapter extends RecyclerView.Adapter{

    public static final int TYPE_NUM = 1;
    private Context mContext;
    public PlayerRecyclerAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view;
        switch (viewType){
            case TYPE_NUM:
                view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_live_entrance, null);
                return new EstimateViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }


    static class EstimateViewHolder extends RecyclerView.ViewHolder{

        public EstimateViewHolder(View itemView) {
            super(itemView);
        }
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
