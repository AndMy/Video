package com.home.quhong.quhong.TV.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.entity.filtrate.DataBean;
import com.home.quhong.quhong.TV.utils.ToastUtil;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by aserbao on 2017/3/15.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */

public class FiltrateRecyclerViewAdapter extends RecyclerView.Adapter<FiltrateRecyclerViewAdapter.MyViewHolder> {

    public static final int FOOT = 0;
    public static final int OTHER = 1;
    private Context mContext;
    private List<DataBean> mDataBeanList;

    public FiltrateRecyclerViewAdapter(Context context, List<DataBean> dataBeanList) {
        mContext = context;
        mDataBeanList = dataBeanList;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mDataBeanList.size()){
            return FOOT;
        }else{
            return OTHER;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case FOOT:
                View inflate = LayoutInflater.from(mContext).inflate(R.layout.foot_item_more, parent, false);
                return new FootViewHolder(inflate);
            case OTHER:
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_live_partition, parent, false);
                return new MyViewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(holder instanceof FootViewHolder){

        }else {
            DataBean bean = mDataBeanList.get(position);
            Glide.with(mContext)
                    .load("http://api.cooshows.com" + bean.getCover())
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.default_image_tv)
                    .dontAnimate()
                    .into(holder.mItemLiveCover);
            holder.mItemLiveTitle.setText(bean.getTitle());
            holder.mItemLiveLayout.setOnClickListener(v ->
                    ToastUtil.ShortToast("点击" + position));
//                PlayerActivity.launch((Activity) mContext,"/v1/info/?dramaId=58b38ab8488255bd7d3ea11a&language=id") );

            if (position == mDataBeanList.size()) {
                FootViewHolder foorViewHolder = (FootViewHolder) holder;
                foorViewHolder.bindView();
                return;
            }
        }
    }
    public void setData(List<DataBean> m ){
        if (m != null) {
            mDataBeanList.addAll(m);
            int size = mDataBeanList.size();
            Toast.makeText(mContext, size, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public int getItemCount() {
        return mDataBeanList.size()+1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mItemLiveCover;
        TextView mItemLiveTitle;
        CardView mItemLiveLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mItemLiveCover = ((ImageView) itemView.findViewById(R.id.item_live_cover));
            mItemLiveTitle = ((TextView) itemView.findViewById(R.id.item_live_title));
            mItemLiveLayout = ((CardView) itemView.findViewById(R.id.item_live_layout));

        }
    }

    private class FootViewHolder extends MyViewHolder {
        private final TextView mFinishTextView;
        private final TextView mTextView;
        private final ProgressBar mProgressBar;

        public FootViewHolder(View itemView) {
            super(itemView);
            mFinishTextView = ((TextView) itemView.findViewById(R.id.item_more_finishTextView));
            mTextView = ((TextView) itemView.findViewById(R.id.item_more_textView));
            mProgressBar = ((ProgressBar) itemView.findViewById(R.id.item_more_progressBar));
        }

        private void bindView() {
            if (mDataBeanList.size() == 0) {
                itemView.setVisibility(View.GONE);
            } else {
                itemView.setVisibility(View.VISIBLE);
                mFinishTextView.setVisibility(View.GONE);
                mTextView.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.VISIBLE);
            }
        }
    }
}
