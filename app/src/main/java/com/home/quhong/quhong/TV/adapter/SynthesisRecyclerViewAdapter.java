package com.home.quhong.quhong.TV.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.PlayerActivity;
import com.home.quhong.quhong.TV.entity.home.Synthesis;
import com.home.quhong.quhong.TV.widght.banner.BannerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.media.CamcorderProfile.get;

/**
 * Created by aserbao on 2017/2/20.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */

public class SynthesisRecyclerViewAdapter extends RecyclerView.Adapter{
    private static final String TAG = "SynthesisRecyclerViewAd";
    public static final int TYPE_FLOAT_BUTTON = 4;
    private Context context;
    private List<Synthesis.BannerBean.VideosBean> banner;
    private List<Integer> liveSizes = new ArrayList<>();
    private Synthesis mSynthesis;
    private int entranceSize;
    private static final int TYPE_ENTRANCE = 0;
    private static final int TYPE_LIVE_ITEM = 1;
    private static final int TYPE_PARTITION = 2;
    private static final int TYPE_BANNER = 3;

    public SynthesisRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setLiveIndex(Synthesis data)
    {
        Log.d(TAG, "setLiveIndex() called with: data = [" + data + "]");
        this.mSynthesis = data;
        entranceSize = 1;
        int partitionSize = data.getCardX().size();

        banner = new ArrayList<>();
        banner.clear();
        banner = data.getBannerX().getVideos();

        liveSizes.clear();
        int tempSize = 0;
        for (int i = 0; i < partitionSize; i++)
        {
            liveSizes.add(tempSize);
            tempSize += data.getCardX().get(i).getVideos().size();
        }
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Log.d(TAG, "onCreateViewHolder() called with: viewGroup = [" + viewGroup + "], viewType = [" + viewType + "]");
        View view;
        switch (viewType){
            case TYPE_ENTRANCE:
                view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_live_entrance, null);
                return new LiveEntranceViewHolder(view);

            case TYPE_BANNER:
                view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_synthesis_banner, null);
                return new SynthesisBannerViewHolder(view);
            case TYPE_PARTITION:
                view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_live_partition_title, null);
                return new LivePartitionViewHolder(view);
            case TYPE_LIVE_ITEM:
                view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_live_partition, null);
                return new LiveItemViewHolder(view);
            case TYPE_FLOAT_BUTTON:
                view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.synthesis_recycler_view_float_button, null);
                return new LiveViewAllViewHolder(view);
        }
          return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder() called with: holder = [" + holder + "], position = [" + position + "]");
        position -= 1 ;
        final Synthesis.CardBean.VideosBeanX item;
        final Synthesis.CardBean cardBean;
        if(holder instanceof SynthesisBannerViewHolder){
            ((SynthesisBannerViewHolder) holder).banner.delayTime(5).build(banner);
        }else if(holder instanceof LivePartitionViewHolder){
            String title = mSynthesis.getCardX().get(partitionCol(position)).getTitleX();

            ((LivePartitionViewHolder) holder).itemTitle.setText(title);
        }else if(holder instanceof LiveItemViewHolder) {
            int index = partitionCol(position);
            int index1 = position - 1 - entranceSize - partitionCol(position) * 8;
            if (index1 < mSynthesis.getCardX().get(index).getVideos().size()) {
                item = mSynthesis.getCardX().get(index)
                        .getVideos().get(index1);
                Glide.with(context)
                        .load("http://api.beemovieapp.com" + item.getCover())
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.bili_default_image_tv)
                        .dontAnimate()
                        .into(((LiveItemViewHolder) holder).itemLiveCover);
                ((LiveItemViewHolder) holder).itemLiveTitle.setText(item.getTitleX());
                /*if(index == 3) {
                    ((LiveItemViewHolder) holder).itemLiveLayout.setPadding(0,0,50,0);
                }*/
                ((LiveItemViewHolder) holder).itemLiveLayout.setOnClickListener(v -> PlayerActivity.launch((Activity) context, item.getId()));
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ((LiveItemViewHolder) holder).itemLiveLayout.getLayoutParams();
                LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) ((LiveItemViewHolder) holder).itemLiveTitle.getLayoutParams();
                switch (index1){
                    case 0:
                    case 3:
                        params.leftMargin = 30;
                        params1.leftMargin = 30;
                        break;
                    case 1:
                    case 4:
                        params.leftMargin = 20;
                        params1.leftMargin = 20;
                        break;
                    case 2:
                    case 5:
                        params.leftMargin = 10;
                        params1.leftMargin = 10;
                        break;
                }
                ((LiveItemViewHolder) holder).itemLiveLayout.setLayoutParams(params);
                ((LiveItemViewHolder) holder).itemLiveTitle.setLayoutParams(params1);


            }
        }else if(holder instanceof  LiveViewAllViewHolder){
            cardBean =mSynthesis.getCardX().get(partitionCol(position));
            String more_title = cardBean.getMore_title();
            ((LiveViewAllViewHolder) holder).mTxViewAll.setText(more_title);
            ((LiveViewAllViewHolder) holder).mLinearLayout.setOnClickListener(v -> Toast.makeText(context, "点击", Toast.LENGTH_SHORT).show());
        }
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount() called");
        if (mSynthesis != null)
        {
            int i = 2 + 9 * 8;
            return i;
        } else
        {
            return 0;
        }

    }
    @Override
    public int getItemViewType(int position) {
        Log.d(TAG, "getItemViewType() called with: position = [" + position + "]");
        if (position == 0){
            return TYPE_BANNER;
        }
        position -= 1;
        if(position < entranceSize){
            return TYPE_ENTRANCE;
        }else if(ifPartitionTitle(position)){
                return TYPE_PARTITION;
        }else if(position % 8 == 0 ){
            return TYPE_FLOAT_BUTTON;
        }else{
            return TYPE_LIVE_ITEM;
        }
    }

    public int getSpanSize(int pos)
    {
        Log.d(TAG, "getSpanSize() called with: pos = [" + pos + "]");
        int viewType = getItemViewType(pos);
        switch (viewType)
        {
            case TYPE_ENTRANCE:
                return 12;
            case TYPE_PARTITION:
                return 12;
            case TYPE_FLOAT_BUTTON:
                return 12;
            case TYPE_LIVE_ITEM:
                return 4;
            case TYPE_BANNER:
                return 12;
        }
        return 0;
    }
    /**
     * 综合界面Banner ViewHolder
     */
    static class SynthesisBannerViewHolder extends RecyclerView.ViewHolder
    {

        @BindView(R.id.item_synthesis_banner)
        public BannerView banner;

        SynthesisBannerViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    static class LiveItemViewHolder extends RecyclerView.ViewHolder
    {

        @BindView(R.id.item_live_cover)
        ImageView itemLiveCover;

        @BindView(R.id.item_live_layout)
        CardView itemLiveLayout;

        @BindView(R.id.item_live_title)
        TextView itemLiveTitle;
        LiveItemViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            itemLiveTitle.setBackgroundColor(Color.argb(0,0,0,0));
        }
    }
    static class LiveEntranceViewHolder extends RecyclerView.ViewHolder
    {
        LiveEntranceViewHolder(View itemView)
        {
            super(itemView);
        }
    }

    static class LivePartitionViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.item_live_partition_title)
        TextView itemTitle;

        LivePartitionViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    static class LiveViewAllViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ll_view_all)
        LinearLayout mLinearLayout;
        @BindView(R.id.tx_view_all)
        TextView mTxViewAll;
        public LiveViewAllViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    private boolean ifPartitionTitle(int pos)
    {
        Log.d(TAG, "ifPartitionTitle() called with: pos = [" + pos + "]");
        pos -= entranceSize;
        int i = pos % 8;
        return (i == 0);
    }
    private int partitionCol(int pos)
    {
        Log.d(TAG, "partitionCol() called with: pos = [" + pos + "]");
        pos -= entranceSize;
        return pos / 8;
    }
}
