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
import com.home.quhong.quhong.TV.entity.Live;
import com.home.quhong.quhong.TV.entity.LiveIndex;
import com.home.quhong.quhong.TV.entity.PartitionSub;
import com.home.quhong.quhong.TV.utils.ToastUtil;
import com.home.quhong.quhong.TV.widght.banner.BannerEntity;
import com.home.quhong.quhong.TV.widght.banner.BannerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aserbao on 2017/2/20.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */

public class SynthesisRecyclerViewAdapter extends RecyclerView.Adapter{
    private Context context;
    private List<BannerEntity> banner;
    private List<Integer> liveSizes = new ArrayList<>();
    private LiveIndex liveIndex;
    private int entranceSize;
    private static final int TYPE_ENTRANCE = 0;
    private static final int TYPE_LIVE_ITEM = 1;
    private static final int TYPE_PARTITION = 2;
    private static final int TYPE_BANNER = 3;

    private int[] entranceIconRes = new int[]{
            R.drawable.live_home_follow_anchor,
            R.drawable.live_home_live_center,
            R.drawable.live_home_search_room,
            R.drawable.live_home_all_category
    };

    public SynthesisRecyclerViewAdapter(Context context) {
        this.context = context;
    }
    public void setLiveIndex(LiveIndex data)
    {

        this.liveIndex = data;
        entranceSize = 1;
        int partitionSize = data.partitions.size();

        banner = new ArrayList<>();
        banner.clear();
        banner = data.banner;

        liveSizes.clear();
        int tempSize = 0;
        for (int i = 0; i < partitionSize; i++)
        {
            liveSizes.add(tempSize);
            tempSize += data.partitions.get(i).lives.size();
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

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

        }
          return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        position -= 1 ;
        final Live item;
        if(holder instanceof SynthesisBannerViewHolder){
            ((SynthesisBannerViewHolder) holder).banner.delayTime(5).build(banner);
        }else if(holder instanceof LivePartitionViewHolder){
            PartitionSub partition = liveIndex.partitions.get(partitionCol(position)).partition;
            ((LivePartitionViewHolder) holder).itemTitle.setText(partition.name);
        }else if(holder instanceof LiveItemViewHolder){
            item = liveIndex.partitions.get(partitionCol(position))
                    .lives.get(position - 1 - entranceSize - partitionCol(position) * 10);
            Glide.with(context)
                    .load(item.cover.src)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.bili_default_image_tv)
                    .dontAnimate()
                    .into(((LiveItemViewHolder) holder).itemLiveCover);
            ((LiveItemViewHolder) holder).itemLiveTitle.setText(item.title);
            ((LiveItemViewHolder) holder).itemLiveLayout.setOnClickListener(v -> ToastUtil.ShortToast("查看详情"));
        }
    }

    @Override
    public int getItemCount() {
        if (liveIndex != null)
        {
            return 1 + entranceIconRes.length
                    + liveIndex.partitions.size() * 5;
        } else
        {
            return 0;
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return TYPE_BANNER;
        }
        position -= 1;
        if(position < entranceSize){
            return TYPE_ENTRANCE;
        }else if(ifPartitionTitle(position)){
            return TYPE_PARTITION;
        }else{
            return TYPE_LIVE_ITEM;
        }
    }

    public int getSpanSize(int pos)
    {

        int viewType = getItemViewType(pos);
        switch (viewType)
        {
            case TYPE_ENTRANCE:
                return 12;
            case TYPE_PARTITION:
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
    private boolean ifPartitionTitle(int pos)
    {
        pos -= entranceSize;
        return (pos % 10 == 0);
    }
    private int partitionCol(int pos)
    {
        pos -= entranceSize;
        return pos / 10;
    }
}
