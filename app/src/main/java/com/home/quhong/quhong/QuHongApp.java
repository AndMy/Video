package com.home.quhong.quhong;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by aserbao on 2017/2/20.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */

public class QuHongApp extends Application{
   public static QuHongApp mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
    private void init()
    {
        //初始化Stetho调试工具
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
    public static QuHongApp getInstance(){
        return mInstance;
    }
}
