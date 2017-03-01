package com.home.quhong.quhong.TV.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.base.BaseViewHolder;
import com.home.quhong.quhong.TV.bean.VideoSummary;
import com.home.quhong.quhong.TV.callback.OnItemClickCallback;

import java.util.List;

/**
 * Created by aserbao on 2017/2/24.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */
public class VideoRecycleAdapter extends RecyclerView.Adapter<VideoRecycleAdapter.ItemViewHolder> {
    private List<String> mDatas;
    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;
    public VideoRecycleAdapter(Context context, List<String> mDatas) {
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(context);
    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(mInflater.inflate(R.layout.item_video_summary,parent,false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        if (mOnItemClickListener != null) {
            if (!holder.itemView.hasOnClickListeners()){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getPosition();
                        mOnItemClickListener.onItemClick(v, pos);
                    }
                });
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = holder.getPosition();
                        mOnItemClickListener.onItemLongClick(v, pos);
                        return true;
                    }
                });
            }
        }
    }
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
        public void onItemLongClick(View view, int position);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder{

       public ItemViewHolder(View itemView) {
           super(itemView);
       }
   }
}
