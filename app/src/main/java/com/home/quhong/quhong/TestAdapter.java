package com.home.quhong.quhong;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.home.quhong.quhong.TV.fragments.PlayFragment;
import com.home.quhong.quhong.TV.fragments.TestFragment;

import java.util.List;

/**
 * Created by aserbao on 2017/3/10.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */

public class TestAdapter extends FragmentPagerAdapter {
    private Fragment[] mFragments  = new Fragment[2];
    public TestAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (mFragments[position] != null) {
            switch (position){
                case 0:
                    mFragments[position] = PlayFragment.newIntance();
                    break;
                case 1:
                    mFragments[position] = PlayFragment.newIntance();
                    break;
            }
        }
        return mFragments[position];
    }

    @Override
    public int getCount() {
        return 2;
    }
}
