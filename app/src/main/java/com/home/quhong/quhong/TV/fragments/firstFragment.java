package com.home.quhong.quhong.TV.fragments;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.home.quhong.quhong.QuHong;
import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.adapter.FirstPagerAdapter;
import com.home.quhong.quhong.TV.base.RxLazyFragment;
import com.home.quhong.quhong.TV.utils.ToastUtil;
import com.home.quhong.quhong.TV.widght.CircleImageView;
import com.home.quhong.quhong.TV.widght.NoScrollViewPager;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aserbao on 2017/2/20.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */

public class FirstFragment extends RxLazyFragment {


    @BindView(R.id.toolbar_user_avatar)
    CircleImageView mCircleImageView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.sliding_tabs)
    SlidingTabLayout mSlidingTabs;
    @BindView(R.id.view_pager)
    NoScrollViewPager mViewPager;
    @BindView(R.id.search_view)
    MaterialSearchView mSearchView;

    //随机头像设置数组
    private static final int[] avatars = new int[]{
            R.drawable.ic_avatar1, R.drawable.ic_avatar2,
            R.drawable.ic_avatar3, R.drawable.ic_avatar4,

    };
    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_first_pager;
    }

    @Override
    public void finishCreateView(Bundle state) {
        setHasOptionsMenu(true);
        initToolBar();
        initSearchView();
        initViewPager();
    }

    private void initViewPager() {
        FirstPagerAdapter firstPagerAdapter = new FirstPagerAdapter(getChildFragmentManager()
                , getApplicationContext());
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setAdapter(firstPagerAdapter);
        mSlidingTabs.setViewPager(mViewPager);
        mViewPager.setCurrentItem(1);
    }

    private void initSearchView() {
        //初始化SearchBar
        mSearchView.setVoiceSearch(false);
        mSearchView.setCursorDrawable(R.drawable.custom_cursor);
        mSearchView.setEllipsize(true);
        mSearchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener()
        {

            @Override
            public boolean onQueryTextSubmit(String query)
            {
                //TODO:全局搜索的实现
//                TotalSationSearchActivity.launch(getActivity(), query);
                ToastUtil.ShortToast("点击实现全局搜索");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                return false;
            }
        });
    }

    private void initToolBar() {
        mToolbar.setTitle("13");
        ((QuHong) getActivity()).setSupportActionBar(mToolbar);
        mCircleImageView.setImageResource(R.drawable.ic_hotbitmapgg_avatar);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);

        // 设置SearchViewItemMenu
        MenuItem item = menu.findItem(R.id.id_action_search);
        mSearchView.setMenuItem(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.id_action_game:
                //// TODO: 2017/2/20 点击进入游戏界面 
                ToastUtil.ShortToast("点击进入游戏界面");
                break;
            case R.id.id_action_download:
                // TODO: 2017/2/20 点击进入缓存页面 
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
