package com.home.quhong.quhong.TV.aserbao;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by aserbao on 2017/3/3.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */

public class CustomExpandListView extends ExpandableListView {
    public CustomExpandListView(Context context) {
        super(context);
    }

    public CustomExpandListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomExpandListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, mExpandSpec);
    }

}
