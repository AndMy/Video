package com.home.quhong.quhong.TV;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.adapter.DownLoadedBaseFragment;
import com.home.quhong.quhong.TV.adapter.DownloadedAdapter;
import com.home.quhong.quhong.TV.aserbao.BottomDialogFragment;
import com.home.quhong.quhong.TV.entity.home.HomeVideoDetail;
import com.home.quhong.quhong.TV.entity.home.SeriesBean;
import com.home.quhong.quhong.TV.fragments.DownLoadedFragment;
import com.home.quhong.quhong.TV.utils.ConstantUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DownLoadedActivity extends AppCompatActivity implements DownLoadedFragment.onRecyclerItemClick{

    private HomeVideoDetail mHomeVideoDetail1;
    @BindView(R.id.down_image_view)
    ImageView mDownImageView;
    @BindView(R.id.down_tab_layout)
    TabLayout mDownTabLayout;
    @BindView(R.id.down_view_pager)
    ViewPager mDownViewPager;
    @BindView(R.id.down_delete_iamge)
    ImageView mDownDeleteIamge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        List<DownLoadedBaseFragment> fragments = new ArrayList<>();
        fragments.add(new DownLoadedFragment());
        fragments.add(new DownLoadedFragment());
        DownloadedAdapter adapter = new DownloadedAdapter(getSupportFragmentManager(), fragments);
        mDownViewPager.setAdapter(adapter);
        mDownTabLayout.setupWithViewPager(mDownViewPager);
    }


    @Override
    public void onRecyclerItemClick(int message) {
        Toast.makeText(this, String.valueOf(message), Toast.LENGTH_SHORT).show();
    }
    public static void setHomeVideoDetail(HomeVideoDetail homeVideoDetail){

    }
    public static void down(int position){

    }
    @OnClick({R.id.down_image_view, R.id.down_delete_iamge})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.down_image_view:

                break;
            case R.id.down_delete_iamge:

                break;
        }
    }


}
