package com.home.quhong.quhong.TV.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.entity.LiveIndex;
import com.home.quhong.quhong.TV.widght.banner.BannerEntity;
import com.home.quhong.quhong.TV.widght.banner.BannerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.id;

/**
 * Created by aserbao on 2017/2/20.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */

public class SynthesisRecyclerViewAdapter extends RecyclerView.Adapter{
    private Context mContext;
    private List<BannerEntity> banner;
    private List<Integer> liveSizes = new ArrayList<>();
    private LiveIndex liveIndex;
    private int entranceSize;
    //综合页Banner
    private static final int TYPE_BANNER = 3;

    private int[] entranceIconRes = new int[]{
            R.drawable.live_home_follow_anchor,
            R.drawable.live_home_live_center,
            R.drawable.live_home_search_room,
            R.drawable.live_home_all_category
    };

    public SynthesisRecyclerViewAdapter(Context context) {
        mContext = context;
    }
    public void setLiveIndex(LiveIndex data)
    {

        this.liveIndex = data;
        entranceSize = 4;
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
                view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_synthesis_banner, null);
                return new SynthesisBannerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        position = -1 ;
        if(holder instanceof SynthesisBannerViewHolder){
            ((SynthesisBannerViewHolder) holder).banner.delayTime(5).build(banner);
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
        // TODO: 2017/2/20 其他部分编写

        return super.getItemViewType(position);
    }
    public int getSpanSize(int pos)
    {

        int viewType = getItemViewType(pos);
        switch (viewType)
        {
            /*case TYPE_ENTRANCE:
                return 3;
            case TYPE_LIVE_ITEM:
                return 6;
            case TYPE_PARTITION:
                return 12;*/
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
}
