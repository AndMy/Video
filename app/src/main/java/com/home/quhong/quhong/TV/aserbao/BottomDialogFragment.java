package com.home.quhong.quhong.TV.aserbao;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.DownLoadedActivity;
import com.home.quhong.quhong.TV.PlayerActivity;
import com.home.quhong.quhong.TV.adapter.DialogRecycleAdapter;
import com.home.quhong.quhong.TV.entity.detail.VideoDetail;
import com.home.quhong.quhong.TV.services.DownLoadService;
import com.home.quhong.quhong.TV.utils.ConstantUtil;
import com.home.quhong.quhong.TV.utils.ToastUtil;
import com.uutils.crypto.MD5Utils;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by aserbao on 2017/3/9.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */

public class BottomDialogFragment extends DialogFragment {

    @BindView(R.id.pop_close)
    ImageView mPopClose;
    @BindView(R.id.bottom_recycle_view)
    RecyclerView mBottomRecycleView;
    @BindView(R.id.pop_downall)
    Button mPopDownall;
    @BindView(R.id.pop_view_downloaded)
    Button mPopViewDownloaded;
    private Dialog mDialog;
    private List<VideoDetail.InfoBean.SeriesBean> mSeriesBean;
    private String location = null;
    private int nowPosition;
    private String mMd5;
    private VideoDetail mVideoDetail;
    private Context mContext;
    private int p;
    public BottomDialogFragment() {

    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mContext = getActivity();
        mVideoDetail = ((PlayerActivity) getActivity()).getHomeVideoDetail1();
        if (mVideoDetail != null) {
            mSeriesBean = mVideoDetail.getInfo().getSeries();
        }
        mDialog = new Dialog(getActivity(), R.style.BottomDialog);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        mDialog.setContentView(R.layout.popup_window);
        mDialog.setCanceledOnTouchOutside(true); // 外部点击取消
        mDialog.getWindow().setWindowAnimations(R.style.bottom_dialog_anim_style);
        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = mDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        window.setAttributes(lp);
        ButterKnife.bind(this, mDialog);
        initView();

        return mDialog;
    }

    private void initView() {
        DialogRecycleAdapter adapter = new DialogRecycleAdapter(mSeriesBean);
        adapter.setOnItemClickListener(new DialogRecycleAdapter.onItemClickListener() {
            @Override
            public void onIntemClick(View view, int position) {
                nowPosition = position;
                ToastUtil.ShortToast(mSeriesBean.get(position).getTitle()+"被点击");
                String download_url = mSeriesBean.get(position).getDurl();
                String url =  download_url;
                p = position;
                initGetLocation(url);
            }
        });
        mBottomRecycleView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 5);
        mBottomRecycleView.setLayoutManager(layoutManager);
    }


    @OnClick({R.id.pop_close, R.id.pop_downall, R.id.pop_view_downloaded})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pop_close:
                mDialog.dismiss();
                break;
            case R.id.pop_downall:
                Toast.makeText(mContext, "显示", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pop_view_downloaded:
                Intent intent = new Intent(getActivity(),DownLoadedActivity.class);
                getActivity().startActivity(intent);
                break;
        }
    }


    public String getLocationMethod(HttpGet request, Context context) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            HttpParams params = new BasicHttpParams();
            params.setParameter("http.protocol.handle-redirects", false); // 默认不让重定向
            // 这样就能拿到Location头了
            request.setParams(params);
            HttpResponse response = httpclient.execute(request);
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode == 302) {
                Header locationHeader = response.getFirstHeader("Location");
                if (locationHeader != null) {
                    location = locationHeader.getValue();
                    afterGetlocation();
                }
            } else {
                ToastUtil.ShortToast("responseCode值不为302，没有获取Location");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("exception=", e.toString());
        }
        return location;
    }

    private void initGetLocation(String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                HttpClient httpClient
                        = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet("http://api.beemovieapp.com" + url);
                httpGet.addHeader("User-Agent", "BeeMovie/3.5.0");
                getLocationMethod(httpGet, getActivity());
            }
        }).start();
    }

    public void afterGetlocation(){
        if (location != null) {
            mMd5 = MD5Utils.getFileMD5(location);
            String file = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + File.separator+"QuHong"+File.separator
                    + mVideoDetail.getInfo().getTitle()+mSeriesBean.get(p).getTitle()+".mp4";
            /**启动服务*/
            Intent intent = new Intent(mContext, DownLoadService.class);
            intent.putExtra(ConstantUtil.POSITION,nowPosition);
            intent.putExtra(ConstantUtil.FILE,file);
            intent.putExtra(ConstantUtil.PASS_URL,location);
            mContext.startService(intent);

        }
    }



}
