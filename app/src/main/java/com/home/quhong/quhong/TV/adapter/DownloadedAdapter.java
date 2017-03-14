package com.home.quhong.quhong.TV.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by aserbao on 2017/3/14.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */

public class DownloadedAdapter extends FragmentPagerAdapter {

    private List<DownLoadedBaseFragment> mFragments;

    public DownloadedAdapter(FragmentManager fm, List<DownLoadedBaseFragment> fragments) {
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
        if(mFragments != null){
            ret = mFragments.size();
        }
        return ret;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getFragmentTitle();
    }
}
