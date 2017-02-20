package com.home.quhong.quhong.TV.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
import com.home.quhong.quhong.TV.base.RxLazyFragment;
import com.home.quhong.quhong.TV.utils.ToastUtil;
import com.home.quhong.quhong.TV.widght.CircleImageView;
import com.home.quhong.quhong.TV.widght.NoScrollViewPager;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TVFragment extends RxLazyFragment {


    @BindView(R.id.toolbar_user_avatar)
    CircleImageView mCircleImageView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.sliding_tabs)
    SlidingTabLayout mSlidingTab;
    @BindView(R.id.view_pager)
    NoScrollViewPager mViewPager;
    @BindView(R.id.search_view)
    MaterialSearchView mSearchView;


    public static TVFragment newInstance() {
        return new TVFragment();
    }

    public TVFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_tv_pager;
    }


    @Override
    public void finishCreateView(Bundle state) {
        setHasOptionsMenu(true);
        initToolBar();
        initSearchView();
        initViewPager();
    }

    private void initToolBar() {
        mToolbar.setTitle("");
        ((QuHong) getActivity()).setSupportActionBar(mToolbar);
        mCircleImageView.setImageResource(R.drawable.ic_hotbitmapgg_avatar);
    }
    private void initSearchView()
    {

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

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {

                return false;
            }
        });
    }

    private void initViewPager(){

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {

        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);

        // 设置SearchViewItemMenu
        MenuItem item = menu.findItem(R.id.id_action_search);
        mSearchView.setMenuItem(item);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id = item.getItemId();
        switch (id)
        {
            case R.id.id_action_game:
                //游戏中心
                //// TODO: 2017/2/20 点击进入游戏界面
                ToastUtil.ShortToast("点击进入游戏界面");
//                startActivity(new Intent(getActivity(), GameCentreActivity.class));
                break;

            case R.id.id_action_download:
                //离线缓存
                // TODO: 2017/2/20 点击进入下载界面
                ToastUtil.ShortToast("点击进入下载界面");
//                startActivity(new Intent(getActivity(), OffLineDownloadActivity.class));
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
