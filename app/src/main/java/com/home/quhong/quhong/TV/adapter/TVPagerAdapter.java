package com.home.quhong.quhong.TV.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.fragments.KdFragment;
import com.home.quhong.quhong.TV.fragments.MovieFragment;
import com.home.quhong.quhong.TV.fragments.SynthesisFragment;
import com.home.quhong.quhong.TV.fragments.WsFragment;

/**
 * Created by aserbao on 2017/2/20.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 * <p/>
 * 主界面Fragment模块Adapter
 */

public class TVPagerAdapter extends FragmentPagerAdapter {

    private final String[] TITLES;

    private Fragment[] fragments;

    public TVPagerAdapter(FragmentManager fm, Context context) {
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
                    fragments[position] = MovieFragment.newIntance();
                    break;
                case 2:
                    fragments[position] = SynthesisFragment.newIntance();
                    break;
                case 3:
                    fragments[position] = MovieFragment.newIntance();
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
