package com.home.quhong.quhong;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.home.quhong.quhong.TV.adapter.DownloadedAdapter;
import com.home.quhong.quhong.TV.adapter.DownLoadedBaseFragment;
import com.home.quhong.quhong.TV.fragments.DownLoadedFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity {


    @BindView(R.id.test_tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.text_view_pager)
    ViewPager mTextViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        List<DownLoadedBaseFragment> fragments = new ArrayList<>();
        fragments.add(new DownLoadedFragment());
        fragments.add(new DownLoadedFragment());
        DownloadedAdapter adapter = new DownloadedAdapter(getSupportFragmentManager(), fragments);
        mTextViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mTextViewPager);
    }


}
