package com.home.quhong.quhong.TV;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.adapter.DownLoadedBaseFragment;
import com.home.quhong.quhong.TV.adapter.DownloadedAdapter;
import com.home.quhong.quhong.TV.fragments.DownLoadedFragment;
import com.home.quhong.quhong.TV.services.DownLoadService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DownLoadedActivity extends AppCompatActivity implements DownLoadedFragment.onRecyclerItemClick, ServiceConnection ,Runnable{
    private static final String TAG = "DownLoadedActivity";
    private DownLoadService.DownController mController;
    private boolean running;
    @BindView(R.id.down_image_view)
    ImageView mDownImageView;
    @BindView(R.id.down_tab_layout)
    TabLayout mDownTabLayout;
    @BindView(R.id.down_view_pager)
    ViewPager mDownViewPager;
    @BindView(R.id.down_delete_iamge)
    ImageView mDownDeleteIamge;
    private int mProgress;
    private DownLoadedFragment fragment1;

    @Override
    protected void onStart() {
        super.onStart();
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down);
        ButterKnife.bind(this);
        Intent intent = new Intent(this, DownLoadService.class);
        bindService(intent,this,BIND_AUTO_CREATE);
        initView();

    }


    private void initView() {
        List<DownLoadedBaseFragment> fragments = new ArrayList<>();
        fragment1 = new DownLoadedFragment();

        fragments.add(fragment1);
        fragments.add(new DownLoadedFragment());
        DownloadedAdapter adapter = new DownloadedAdapter(getSupportFragmentManager(), fragments);
        mDownViewPager.setAdapter(adapter);
        mDownTabLayout.setupWithViewPager(mDownViewPager);
    }


    @Override
    public void onRecyclerItemClick(int message) {
        Toast.makeText(this, String.valueOf(message), Toast.LENGTH_SHORT).show();
    }


    @OnClick({R.id.down_image_view, R.id.down_delete_iamge})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.down_image_view:
                break;
            case R.id.down_delete_iamge:
                break;
        }
    }


    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        mController = ((DownLoadService.DownController) service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        mController = null;
    }

    @Override
    public void run() {
        running = true;
        try {
            while (running){
                if (mController != null) {
                    mProgress = mController.getProgress();
                    Log.d(TAG, "run: "+ mProgress);
                }
            }
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
