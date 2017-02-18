package com.home.quhong.quhong.TV.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.home.quhong.quhong.TV.fragments.BaseFragment;

import java.util.List;

/**
 * Created by aserbao on 2017/2/18.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */

public class CommonFragmentAdapter extends FragmentPagerAdapter {


    private List<BaseFragment> mFragments;

    public CommonFragmentAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (mFragments != null) {
            ret = mFragments.size();
        }
        return ret;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getFragmentTitle();
    }
}
