package com.home.quhong.quhong.TV;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TableLayout;

import com.home.quhong.quhong.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DownActivity extends Activity {

    @BindView(R.id.down_image_view)
    ImageView mDownImageView;
    @BindView(R.id.down_tab_layout)
    TableLayout mDownTabLayout;
    @BindView(R.id.down_view_pager)
    ViewPager mDownViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down);
        ButterKnife.bind(this);
        initViewPager();
    }

    public void initViewPager(){

    }
}
