package com.home.quhong.quhong.TV.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.entity.detail.VideoDetail;
import com.home.quhong.quhong.TV.entity.home.SeriesBean;
import com.home.quhong.quhong.TV.fragments.PlayFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aserbao on 2017/3/1.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */

public class DownloadAdapter extends FragmentPagerAdapter {
    private final String[] TITLES;
    private Fragment[] fragments;
    private List<VideoDetail.InfoBean.SeriesBean> mSeries = new ArrayList<>();
    public DownloadAdapter(FragmentManager fm, Context context,List<VideoDetail.InfoBean.SeriesBean> m) {
        super(fm);
        TITLES = context.getResources().getStringArray(R.array.player_section);
        fragments = new Fragment[TITLES.length];
        mSeries = m ;
    }

    @Override
    public Fragment getItem(int position) {
        if (fragments[position] == null)
        {
            PlayFragment playFragment = PlayFragment.newIntance();
            switch (position)
            {
                case 0:
                    fragments[position] = playFragment;
                    break;
                case 1:
                    fragments[position] = playFragment;
                    break;
                default:
                    break;
            }
        }
        return fragments[position];
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }
}
