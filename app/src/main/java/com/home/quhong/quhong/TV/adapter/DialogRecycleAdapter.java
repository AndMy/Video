package com.home.quhong.quhong.TV.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.home.quhong.quhong.My.adapter.MyRecycleAdapter;
import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.entity.detail.VideoDetail;
import com.home.quhong.quhong.TV.entity.home.SeriesBean;

import java.util.List;

/**
 * Created by aserbao on 2017/3/10.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */

public class DialogRecycleAdapter extends RecyclerView.Adapter<DialogRecycleAdapter.MyViewHolder> {
    private List<VideoDetail.InfoBean.SeriesBean> mSeriesBeen;
    private DialogRecycleAdapter.onItemClickListener mOnItemClickListener;
    public DialogRecycleAdapter(List<VideoDetail.InfoBean.SeriesBean> sb) {
        mSeriesBeen = sb;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(DialogRecycleAdapter.MyViewHolder holder, int position) {
        if (holder != null) {
            holder.mTextView.setText(mSeriesBeen.get(position).getTitle());
        }
        if(!holder.mTextView.hasOnClickListeners()){
            holder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getPosition();
                    mOnItemClickListener.onIntemClick(v,pos);
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        if (mSeriesBeen != null) {
            return mSeriesBeen.size();
        }
        return 0;
    }
    static class MyViewHolder extends  RecyclerView.ViewHolder {
        private TextView mTextView;
       public MyViewHolder(View itemView) {
           super(itemView);
           mTextView = (TextView) itemView.findViewById(R.id.textview);
       }
    }
    public interface onItemClickListener{
        public void onIntemClick(View view,int position);
    }
    public void setOnItemClickListener(DialogRecycleAdapter.onItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
