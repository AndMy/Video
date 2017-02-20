package com.home.quhong.quhong.TV.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.bilibili.magicasakura.utils.ThemeUtils;
import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.utils.ThemeHelper;
import com.home.quhong.quhong.TV.widght.dialog.CardPickerDialog;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;


/**
 * Imitation by Abybxc on 16/8/7 21:18
 * weixin:aserbao
 * <p/>
 * Activity基类
 */
public abstract class RxAppCompatBaseActivity extends RxAppCompatActivity
        implements CardPickerDialog.ClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        //设置布局内容
        setContentView(getLayoutId());
        //初始化黄油刀控件绑定框架
        ButterKnife.bind(this);
        //初始化控件
        initViews(savedInstanceState);
        //初始化ToolBar
        initToolBar();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    public abstract int getLayoutId();

    public abstract void initViews(Bundle savedInstanceState);

    public abstract void initToolBar();

    @Override
    public void onConfirm(int currentTheme)
    {

        if (ThemeHelper.getTheme(RxAppCompatBaseActivity.this) != currentTheme)
        {
            ThemeHelper.setTheme(RxAppCompatBaseActivity.this, currentTheme);
            ThemeUtils.refreshUI(RxAppCompatBaseActivity.this, new ThemeUtils.ExtraRefreshable()
                    {


                        @Override
                        public void refreshGlobal(Activity activity)
                        {

                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
                            {
                                final RxAppCompatBaseActivity context = RxAppCompatBaseActivity.this;
                                ActivityManager.TaskDescription taskDescription = new ActivityManager.TaskDescription(null,
                                        null, ThemeUtils.getThemeAttrColor(context, android.R.attr.colorPrimary));
                                setTaskDescription(taskDescription);
                                getWindow().setStatusBarColor(ThemeUtils.getColorById(context,
                                        R.color.theme_color_primary_dark));
                            }
                        }

                        @Override
                        public void refreshSpecificView(View view)
                        {

                        }
                    }
            );
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState)
    {

        super.onPostCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
        {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ThemeUtils.getColorById(this, R.color.theme_color_primary_dark));
            ActivityManager.TaskDescription description = new ActivityManager.TaskDescription(null, null,
                    ThemeUtils.getThemeAttrColor(this, android.R.attr.colorPrimary));
            setTaskDescription(description);
        }
    }
}
