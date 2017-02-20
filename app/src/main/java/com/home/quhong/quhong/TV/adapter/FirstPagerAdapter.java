package com.home.quhong.quhong.TV.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.fragments.SynthesisFragment;

/**
 * Created by aserbao on 2017/2/20.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 * <p/>
 * 主界面Fragment模块Adapter
 */

public class FirstPagerAdapter extends FragmentPagerAdapter {

    private final String[] TITLES;

    private Fragment[] fragments;

    public FirstPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        TITLES = context.getResources().getStringArray(R.array.sections);
        fragments = new Fragment[TITLES.length];
    }


    @Override
    public Fragment getItem(int position)
    {

        if (fragments[position] == null)
        {
            switch (position)
            {
                case 0:
                    fragments[position] = SynthesisFragment.newIntance();
                    break;
                case 1:
//                    fragments[position] = HomeRecommendedFragment.newInstance();
                    break;
                case 2:
//                    fragments[position] = HomeBangumiFragment.newInstance();
                    break;
                case 3:
//                    fragments[position] = HomeMoreFragment.newInstance();
                    break;
                case 4:
//                    fragments[position] = HomeFocusFragment.newIntance();
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
    public CharSequence getPageTitle(int position)
    {
        return TITLES[position];
    }
}
