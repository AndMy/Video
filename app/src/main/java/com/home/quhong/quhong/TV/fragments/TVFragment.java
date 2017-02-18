package com.home.quhong.quhong.TV.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.adapter.CommonFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TVFragment extends Fragment {


    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    public TVFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv, container, false);
        mTabLayout = (TabLayout) view.findViewById(R.id.tv_tab_layout);
        mViewPager = ((ViewPager) view.findViewById(R.id.tv_view_pager));
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        List<BaseFragment> fragmentList = new ArrayList<>();
        fragmentList.add(new BerandaFragment());
        fragmentList.add(new FilmFragment());
        fragmentList.add(new KdramaFragment());
        fragmentList.add(new SerialBaratFragment());

        CommonFragmentAdapter videoAdapter = new CommonFragmentAdapter(
                getChildFragmentManager(),
                fragmentList
        );

        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(videoAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        return view;
    }

}
