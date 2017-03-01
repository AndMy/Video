package com.home.quhong.quhong.TV;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.tablayout.SlidingTabLayout;
import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.adapter.VideoRecycleAdapter;
import com.home.quhong.quhong.TV.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayerActivity extends AppCompatActivity {


    @BindView(R.id.player_vip)
    ImageView mPlayerVip;
    @BindView(R.id.player_title)
    TextView mPlayerTitle;
    @BindView(R.id.player_textView)
    TextView mPlayerTextView;
    @BindView(R.id.player_text_type)
    TextView mPlayerTextType;
    @BindView(R.id.imageView)
    ImageView mImageView;
    @BindView(R.id.player_sliding_tabs)
    SlidingTabLayout mPlayerSlidingTabs;
    @BindView(R.id.palyer_view_pager)
    ViewPager mPalyerViewPager;
    @BindView(R.id.player_recycler)
    RecyclerView mPlayerRecycler;
    private VideoRecycleAdapter mAdapter;
    private List<String> mDatas;
    public PlayerActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_recycle_estimate);
        ButterKnife.bind(this);
        initData();
        initViews();
    }

    private void initViews() {
        mAdapter =  new VideoRecycleAdapter(this,mDatas);
        mAdapter.setOnItemClickListener(new VideoRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(PlayerActivity.this, position+"被点击", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        mPlayerRecycler.setAdapter(mAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mPlayerRecycler.setLayoutManager(linearLayoutManager);
        mPlayerRecycler.setItemAnimator(new DefaultItemAnimator());
    }
    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            mDatas.add(String.valueOf(i));
        }
    }
}
