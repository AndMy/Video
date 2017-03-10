package com.home.quhong.quhong.TV.aserbao;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.PlayerActivity;
import com.home.quhong.quhong.TV.adapter.DialogRecycleAdapter;
import com.home.quhong.quhong.TV.adapter.RecycleAdapter;
import com.home.quhong.quhong.TV.entity.home.SeriesBean;
import com.home.quhong.quhong.TV.utils.ToastUtil;

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
    TextView mPopDownall;
    @BindView(R.id.pop_view_downloaded)
    TextView mPopViewDownloaded;
    private Dialog mDialog;
    private List<String > mDatas;
    private List<SeriesBean> mSeriesBean;

    public BottomDialogFragment() {

    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mSeriesBean = ((PlayerActivity) getActivity()).getSeriesBean();
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
                ToastUtil.ShortToast(position+"被点击");
            }
        });
        mBottomRecycleView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 6);
        mBottomRecycleView.setLayoutManager(layoutManager);
    }


    @OnClick({R.id.pop_close, R.id.pop_downall, R.id.pop_view_downloaded})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pop_close:
                mDialog.dismiss();
                break;
            case R.id.pop_downall:

                break;
            case R.id.pop_view_downloaded:

                break;
        }
    }

}
