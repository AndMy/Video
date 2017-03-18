package com.home.quhong.quhong.TV.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

import com.home.quhong.quhong.TV.utils.ConstantUtil;
import com.uutils.crypto.MD5Utils;
import com.uutils.net.DownloadError;
import com.uutils.net.DownloadListener;
import com.uutils.net.DownloadManager;
import com.uutils.plugin.Analytics;
import com.uutils.utils.Logs;

public class DownLoadService extends Service {
    private int progress;
    private DownloadManager mDownloadManager;
    public DownLoadService() {
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int position = intent.getIntExtra(ConstantUtil.POSITION, -1);
        String file = intent.getStringExtra(ConstantUtil.FILE);
        String url = intent.getStringExtra(ConstantUtil.PASS_URL);
        String mMd5 = MD5Utils.getFileMD5(url);

        downLoad(url,file,mMd5);
        return super.onStartCommand(intent, flags, startId);
    }
    /**onBind*/
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new DownController();
    }
    public class DownController extends Binder {

        public void ready(){

        }
        public void pause(){

        }
        public void delete(){

        }
        public int getProgress(){
            return progress;
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    public void downLoad(String url, String file, final String md5) {
        mDownloadManager = DownloadManager.getInstance(this);
        if (mDownloadManager.download(url, file, md5, new DownloadListener() {
            @Override
            public void onStart(String url, String file) {

            }

            @Override
            public void onProgress(String url, String file, float p) {
                progress = (int)Math.ceil(p*100);
                Log.d("test", "onProgress: "+p);
            }

            @Override
            public void onFinish(String url, String file, DownloadError err) {
                if (err == DownloadError.ERR_NONE) {
                    Logs.d("wbsdk_download_js_success fileName = " + file);

                } else if (err == DownloadError.ERR_NONE_SAME_MD5) {
                    Logs.d("wbsdk_download_js_success same md5 fileName =" + file);
                } else {
                    Logs.d("wbsdk_download_js_fail");
                }
            }
        })) {
            //开始下载
            Logs.d("wbsdk_download_js_start");
        } else {
            //正在下载
            Logs.d("wbsdk_download_js_running");
        }
    }
}
