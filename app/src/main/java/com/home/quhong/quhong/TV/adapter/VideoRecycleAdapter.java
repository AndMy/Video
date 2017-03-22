package com.home.quhong.quhong.TV.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.entity.detail.VideoDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aserbao on 2017/2/24.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */
public class VideoRecycleAdapter extends RecyclerView.Adapter<VideoRecycleAdapter.ItemViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;
    private VideoDetail mVideoDetail = null;
    private List<VideoDetail.RecommendBean> mRBList = new ArrayList<>();

    public VideoRecycleAdapter(Context context, VideoDetail mDatas) {
        mContext = context;
        mVideoDetail = mDatas;
        mRBList =mVideoDetail.getRecommend();
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(mInflater.inflate(R.layout.item_video_summary, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
       Glide.with(mContext).load(mVideoDetail.getInfo().getCover()).into(holder.mIv);
        holder.mItemVideoText.setText(mVideoDetail.getInfo().getTitle());
        if (mOnItemClickListener != null) {
            if (!holder.itemView.hasOnClickListeners()) {
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
        int ret = 0;
        if (mRBList != null) {
           ret = mRBList.size();
        }
        return ret;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);

        public void onItemLongClick(View view, int position);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        ImageView mIv;
        @BindView(R.id.item_video_text)
        TextView mItemVideoText;
        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
