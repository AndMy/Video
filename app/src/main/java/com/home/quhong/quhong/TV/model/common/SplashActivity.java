package com.home.quhong.quhong.TV.model.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


import com.home.quhong.quhong.QuHong;
import com.home.quhong.quhong.R;
import com.home.quhong.quhong.Splash;
import com.home.quhong.quhong.TV.utils.PreferenceUtils;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HandshakeCompletedEvent;

import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

import static android.provider.Contacts.SettingsColumns.KEY;


/**
 * Imitation by Abybxc on 16/8/7 14:12
 * weixin:aserbao
 * <p/>
 * 欢迎页
 */
public class SplashActivity extends Activity
{

    private final long SPLASH_LENGTH = 5000;
    Handler handler = new Handler();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, QuHong.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_LENGTH);//3秒后跳转
    }

}
