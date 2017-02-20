package com.home.quhong.quhong;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.home.quhong.quhong.Local.fragments.LocalFragment;
import com.home.quhong.quhong.My.fragments.MyFragment;
import com.home.quhong.quhong.TV.fragments.FirstFragment;
import com.home.quhong.quhong.TV.fragments.TVFragment;
import com.home.quhong.quhong.Video.fragments.VideoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuHong extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {


    private VideoFragment mVideoFragment;
    private TVFragment mTVFragment;
    private LocalFragment mLocalFragment;
    private MyFragment mMyFragment;
    private FirstFragment mFirstFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qu_hong);
        ButterKnife.bind(this);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        Fragment f = manager.findFragmentByTag("TV");
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.home_rg);
        if (radioGroup != null) {
            radioGroup.setOnCheckedChangeListener(this);
        }
        mVideoFragment = new VideoFragment();
        mFirstFragment = FirstFragment.newInstance();
        mTVFragment = new TVFragment();
        mLocalFragment = new LocalFragment();
        mMyFragment = new MyFragment();
        radioGroup.check(R.id.home_rb_tv);


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        switch (checkedId) {
            case R.id.home_rb_tv:
                ft.replace(R.id.home_fragment_contain, mTVFragment);
                break;
            case R.id.home_rb_video:
                ft.replace(R.id.home_fragment_contain, mFirstFragment);
                break;
            case R.id.home_rb_local:
                ft.replace(R.id.home_fragment_contain, mLocalFragment);
                break;
            case R.id.home_rb_my:
                ft.replace(R.id.home_fragment_contain, mMyFragment);
                break;
        }
        ft.commit();
    }


}
