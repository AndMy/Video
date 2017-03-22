package com.home.quhong.quhong.TV.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.entity.home.SeriesBean;

import java.util.List;

/**
 * Created by aserbao on 2017/3/1.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
    private List<SeriesBean> mSeriesBeen;
    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;
    private MyViewHolder mHolder   ;

    public RecycleAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        mHolder = myViewHolder;
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder itemViewHolder, int position) {
//        itemViewHolder.mTextView.setText(mSeriesBeen.get(position).getTitle());

        if(mOnItemClickListener != null) {
            /**
             * ????????ж??itemViewHolder.itemView.hasOnClickListeners()
             * ????????????????????????view??????click???????,??????????????
             * ?????ε???onBindViewHolder????????????????????????????????????????
             */
            if(!itemViewHolder.itemView.hasOnClickListeners()) {
                Log.e("ListAdapter", "setOnClickListener");
                itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = itemViewHolder.getPosition();
                        mOnItemClickListener.onItemClick(v, pos);
                        mHolder.mTextView.setBackgroundResource(R.color.detail_text_bg_unsel);
                        itemViewHolder.mTextView.setBackgroundResource(R.color.detail_text_bg_sel);
                        mHolder = itemViewHolder;
                    }
                });
                itemViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = itemViewHolder.getPosition();
                        mOnItemClickListener.onItemLongClick(v, pos);
                        return true;
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return 8;
    }
    static class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = ((TextView) itemView.findViewById(R.id.textview));
        }
    }
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
        public void onItemLongClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
