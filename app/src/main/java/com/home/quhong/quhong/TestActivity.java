package com.home.quhong.quhong;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.home.quhong.quhong.TV.adapter.DownloadAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestActivity extends AppCompatActivity {


    @BindView(R.id.btn_fragment)
    Button mBtnFragment;
    @BindView(R.id.text_view_pager)
    ViewPager mTextViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        TestAdapter adapter = new TestAdapter(getSupportFragmentManager());
        mTextViewPager.setAdapter(adapter);
    }


    @OnClick(R.id.btn_fragment)
    public void onClick() {
    }
}
