package com.home.quhong.quhong.TV.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.entity.home.SeriesBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aserbao on 2017/3/13.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */

public class DownActivityAdapter extends FragmentPagerAdapter {

    private final String[] TITLES;
    private Fragment[] fragments;
    private List<SeriesBean> mSeries = new ArrayList<>();

    public DownActivityAdapter(FragmentManager fm, Context context) {
        super(fm);
        TITLES = context.getResources().getStringArray(R.array.down_section);
        fragments = new Fragment[TITLES.length];
    }

    @Override
    public Fragment getItem(int position) {
        if (fragments[position] != null) {

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
