package com.home.quhong.quhong;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.home.quhong.quhong.TV.adapter.DialogRecycleAdapter;
import com.home.quhong.quhong.TV.aserbao.BottomDialogFragment;
import com.home.quhong.quhong.TV.fragments.DownFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestActivity extends AppCompatActivity {

    @BindView(R.id.btn_show)
    Button mBtnShow;
    @BindView(R.id.btn_hide)
    Button mBtnHide;
    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
    }

    public void showPopupWindow() {
        View contentView = LayoutInflater.from(TestActivity.this).inflate(R.layout.popup_window_test, null);
        DownFragment downFragment = new DownFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_contain, downFragment).commit();
        mPopupWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setContentView(contentView);
        View rootview = LayoutInflater.from(TestActivity.this).inflate(R.layout.player_recycle_estimate, null);
        mPopupWindow.showAtLocation(rootview, Gravity.BOTTOM | Gravity.CENTER, 0, 0);
    }

    @OnClick({R.id.btn_show, R.id.btn_hide})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_show:
                BottomDialogFragment dialogFragment = new BottomDialogFragment();
                dialogFragment.show(getFragmentManager(), "");
                break;
            case R.id.btn_hide:

                break;
        }
    }

}
