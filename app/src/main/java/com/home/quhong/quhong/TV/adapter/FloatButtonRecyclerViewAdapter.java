package com.home.quhong.quhong.TV.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.PlayerActivity;
import com.home.quhong.quhong.TV.entity.floatButton.FloatButtonDetail;
import com.home.quhong.quhong.TV.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by aserbao on 2017/3/15.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */

public class FloatButtonRecyclerViewAdapter extends RecyclerView.Adapter<FloatButtonRecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<FloatButtonDetail.DataBean> mDataBeanList;

    public FloatButtonRecyclerViewAdapter(Context context, List<FloatButtonDetail.DataBean> dataBeanList) {
        mContext = context;
        mDataBeanList = dataBeanList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_live_partition, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FloatButtonDetail.DataBean bean = mDataBeanList.get(position);
        Glide.with(mContext)
                .load("http://api.cooshows.com"+ bean.getCover())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate()
                .into(holder.mItemLiveCover);
        holder.mItemLiveTitle.setText(bean.getTitle());
        holder.mItemLiveLayout.setOnClickListener(v ->
                        ToastUtil.ShortToast("点击"+position));
//                PlayerActivity.launch((Activity) mContext,"/v1/info/?dramaId=58b38ab8488255bd7d3ea11a&language=id") );
    }

    @Override
    public int getItemCount() {
        return mDataBeanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_live_cover)
        ImageView mItemLiveCover;
        @BindView(R.id.item_live_title)
        TextView mItemLiveTitle;
        @BindView(R.id.item_live_layout)
        CardView mItemLiveLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
