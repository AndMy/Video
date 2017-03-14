package com.home.quhong.quhong.TV.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.home.quhong.quhong.R;

import butterknife.ButterKnife;

/**
 * Created by aserbao on 2017/3/14.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */

public class DownloadedRecycylerAdapter extends RecyclerView.Adapter<DownloadedRecycylerAdapter.MyViewHolder>{
    private Context mContext;
    private onRecyclerItemClick mOnRecyclerItemClick;
    public DownloadedRecycylerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.down_loaded_recycler_view_item, parent, false);
        ButterKnife.bind(view);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (mOnRecyclerItemClick != null){
            if(!holder.itemView.hasOnClickListeners()){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getPosition();
                        mOnRecyclerItemClick.onItemClick(v,pos);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return 12;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
    public interface onRecyclerItemClick {
        void onItemClick(View view, int position);
    }
    public void setOnRecyclerItemClick(onRecyclerItemClick mOnRecyclerItemClick) {
        this.mOnRecyclerItemClick = mOnRecyclerItemClick;
    }
}
