package com.home.quhong.quhong.TV;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ExpandedMenuView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.adapter.FloatButtonRecyclerViewAdapter;
import com.home.quhong.quhong.TV.aserbao.CustomExpandListView;
import com.home.quhong.quhong.TV.entity.floatButton.FloatButtonDetail;
import com.home.quhong.quhong.TV.network.RetrofitHelper;
import com.home.quhong.quhong.TV.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class FloatButtonActivity extends AppCompatActivity {
    private static final String TAG = "FloatButtonActivity";
    @BindView(R.id.float_recycler_view)
    RecyclerView mFloatRecyclerView;
    @BindView(R.id.float_cel_classes)
    ExpandableListView mFloatCelClasses;
    @BindView(R.id.float_cel_country)
    CustomExpandListView mFloatCelCountry;
    @BindView(R.id.float_cel_category)
    CustomExpandListView mFloatCelCategory;
    @BindView(R.id.float_cel_order)
    CustomExpandListView mFloatCelOrder;

    private CompositeSubscription mSubscription = new CompositeSubscription();
    private FloatButtonDetail.CategoryBean mCategoryBean;
    private FloatButtonDetail.CountryBean mCountryBean;
    private FloatButtonDetail.ClassesBean mClassesBean;
    private List<FloatButtonDetail.DataBean> mDataBeanList = new ArrayList<>();
    private FloatButtonDetail.OrderBean mOrderBean;
    private Boolean isOpen = false;
    private RadioGroup mGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_button);
        ButterKnife.bind(this);
        initGetData();
    }

    private void initGetData() {
        Observable<FloatButtonDetail> floatButtonDetail = RetrofitHelper.getFloatButtonApi()
                .getFloatButtonDetail("drama", "romance", "KR", "hot", null);
        mSubscription.add(floatButtonDetail.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FloatButtonDetail>() {
                    @Override
                    public void onCompleted() {
                        initView();
                        initExpandedListView();
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.ShortToast("网络数据获取失败" + "\n" + "请稍候再试");
                    }

                    @Override
                    public void onNext(FloatButtonDetail floatButtonDetail) {
                        mCategoryBean = floatButtonDetail.getCategory();
                        mCountryBean = floatButtonDetail.getCountry();
                        mClassesBean = floatButtonDetail.getClasses();
                        mDataBeanList = floatButtonDetail.getData();
                        mOrderBean = floatButtonDetail.getOrder();
                        Toast.makeText(FloatButtonActivity.this, floatButtonDetail.getCategory().get_$18(), Toast.LENGTH_SHORT).show();
                    }
                }));

    }

    private void initExpandedListView() {
        mFloatCelClasses.setGroupIndicator(null);
        MyExpandableListAdapter adapter = new MyExpandableListAdapter();
        mFloatCelClasses.setAdapter(adapter);
        mFloatCelClasses.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                ToastUtil.ShortToast("点击");
                return false;
            }
        });

    }

    private void initView() {
        FloatButtonRecyclerViewAdapter adapter = new FloatButtonRecyclerViewAdapter(this, mDataBeanList);
        mFloatRecyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        mFloatRecyclerView.setLayoutManager(layoutManager);
    }
    public class MyExpandableListAdapter implements ExpandableListAdapter {
        int[] logos = new int[]{
                R.drawable.down,
                R.drawable.uparrow,
                R.drawable.email,
                R.drawable.downarrow
        };
        private String[] armTypes = new String[]{
                "WORD", "EXCEL", "EMAIL", "PPT"
        };
        private String[][] arms = new String[][]{
                {"文档编辑", "文档排版", "文档处理", "文档打印"},
                {"表格编辑", "表格排版", "表格打印"},
                {"收发邮件", "管理邮箱", "登录登出", "注册绑定", "表格处理"},
                {"演示编辑", "演示排版", "演示处理", "演示打印"},
        };

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public int getGroupCount() {
            return armTypes.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
//            return arms[groupPosition].length;
            return 1;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return armTypes[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return arms[groupPosition][childPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if (groupPosition > 0) {

            }
            convertView = (LinearLayout) LinearLayout.inflate(getBaseContext(),
                    R.layout.float_button_expaned_list_classes_group, null);

            mGroup = (RadioGroup) convertView.findViewById(R.id.rg_classes);
            LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.linear_classes);

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFloatCelClasses.expandGroup(groupPosition);
                    Toast.makeText(FloatButtonActivity.this, "被点击", Toast.LENGTH_SHORT).show();
                }
            });
            mGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    Toast.makeText(FloatButtonActivity.this, String.valueOf(checkedId), Toast.LENGTH_SHORT).show();
                }
            });
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            /*TextView textView = getTextView();
            textView.setText(getChild(groupPosition, childPosition).toString());*/
            convertView = (LinearLayout) LinearLayout.inflate(getBaseContext(),
                    R.layout.float_button_expaned_list_classes_group, null);
            mGroup = (RadioGroup) convertView.findViewById(R.id.rg_classes);
            LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.linear_classes);

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public void onGroupExpanded(int groupPosition) {

        }

        @Override
        public void onGroupCollapsed(int groupPosition) {

        }

        @Override
        public long getCombinedChildId(long groupId, long childId) {
            return 0;
        }

        @Override
        public long getCombinedGroupId(long groupId) {
            return 0;
        }

        private TextView getTextView() {
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 64);
            TextView textView = new TextView(FloatButtonActivity.this);
            textView.setLayoutParams(lp);
            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            textView.setPadding(36, 0, 0, 0);
            textView.setTextSize(20);
            return textView;
        }
    }
}
