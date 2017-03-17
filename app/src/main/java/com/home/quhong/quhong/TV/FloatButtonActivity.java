package com.home.quhong.quhong.TV;

import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.adapter.FloatButtonRecyclerViewAdapter;
import com.home.quhong.quhong.TV.aserbao.CustomExpandListView;
import com.home.quhong.quhong.TV.aserbao.FlowRadioGroup;
import com.home.quhong.quhong.TV.aserbao.MyRadioGroup;
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
    private Boolean isFirstOpen = false;
    private Boolean isSecondOpen = false;


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
        private FlowRadioGroup mFlowRadioGroup;
        private FlowRadioGroup mSecondFlowRadioGroup;
        private FlowRadioGroup mThirdFlowRadioGroup;
        private FlowRadioGroup mSecondFlowRadioChild;
        private FlowRadioGroup mThirdFlowRadioChild;
        private ImageView mImageView;
        private int firstChecked;
        private int secondChecked;
        private int thirdChecked;
        private int fourChecked;
        int[] group_state_array = new int[]{R.drawable.group_down,
                R.drawable.group_up};
        private RadioButton mSecondRadioButton;
        private RadioButton mThirdRadioButton;


        @Override
        public void registerDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public int getGroupCount() {
            return 4;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return 1;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return null;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return null;
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
        public void setGroupOnClick(View itemView){
            mFlowRadioGroup = (FlowRadioGroup) itemView.findViewById(R.id.frg_group);
            RadioButton radioButton = (RadioButton) itemView.findViewById(R.id.radio_checked);
            if (radioButton != null) {
                radioButton.setChecked(true);
            }
            mFlowRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton radioButton = (RadioButton) itemView.findViewById(checkedId);
                }
            });

        }
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

            switch (groupPosition){
                case 0:
                    Log.d(TAG, "getGroupView: "+0);
                    convertView = (LinearLayout) LinearLayout.inflate(getBaseContext(),
                            R.layout.float_button_expaned_list_classes_group0, null);
//                    setGroupOnClick(convertView);
                    mFlowRadioGroup = (FlowRadioGroup) convertView.findViewById(R.id.frg_group);
                    RadioButton radioButton;
                    if (firstChecked > 0 ) {
                        radioButton = (RadioButton) convertView.findViewById(firstChecked);
                    }else{
                       radioButton = (RadioButton) convertView.findViewById(R.id.radio_checked);
                    }
                    if (radioButton != null) {
                        radioButton.setChecked(true);
                    }
                    mFlowRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            firstChecked = checkedId;
                            RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                        }
                    });
                    return  convertView;
                case 1:
                    Log.d(TAG, "getGroupView: "+1);
                    convertView = (LinearLayout) LinearLayout.inflate(getBaseContext(),
                            R.layout.float_button_expaned_list_classes_group1, null);

                    mSecondFlowRadioGroup = (FlowRadioGroup) convertView.findViewById(R.id.frg_group);
                    if (secondChecked > 0) {
                        mSecondRadioButton = (RadioButton) convertView.findViewById(secondChecked);
                    }else {
                        mSecondRadioButton = (RadioButton) convertView.findViewById(R.id.radio_checked);
                    }
                    if (mSecondRadioButton != null) {
                        mSecondRadioButton.setChecked(true);
                    }

                    mImageView = (ImageView) convertView.findViewById(R.id.sel_downarrow);
                    mSecondFlowRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            secondChecked = checkedId;
                            if (mSecondFlowRadioChild != null) {
                                mSecondFlowRadioChild.clearCheck();
                            }
                            RadioButton radioButton = (RadioButton)group.findViewById(checkedId);
                            Toast.makeText(FloatButtonActivity.this, radioButton.getText(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    mImageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            isFirstOpen = !isFirstOpen;
                            if (isFirstOpen){
                                mFloatCelClasses.expandGroup(groupPosition);
                                Toast.makeText(FloatButtonActivity.this, "显示", Toast.LENGTH_SHORT).show();
                            }else {
                                mFloatCelClasses.collapseGroup(groupPosition);
                                Toast.makeText(FloatButtonActivity.this, "消失", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    return  convertView;
                case 2:
                    Log.d(TAG, "getGroupView: "+2);
                    convertView = (LinearLayout) LinearLayout.inflate(getBaseContext(),
                            R.layout.float_button_expaned_list_classes_group2, null);
                    mThirdFlowRadioGroup = (FlowRadioGroup) convertView.findViewById(R.id.frg_group);
                    if (thirdChecked > 0) {
                        mThirdRadioButton = (RadioButton) convertView.findViewById(thirdChecked);
                    }else {
                        mThirdRadioButton = (RadioButton) convertView.findViewById(R.id.radio_checked);
                    }
                    if (mThirdRadioButton != null) {
                        mThirdRadioButton.setChecked(true);
                    }
                    mImageView = (ImageView) convertView.findViewById(R.id.sel_downarrow);
                    mThirdFlowRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            thirdChecked = checkedId;
                            if (mThirdFlowRadioChild != null) {
                                mThirdFlowRadioChild.clearCheck();
                            }
                            RadioButton radioButton = (RadioButton)group.findViewById(checkedId);
                            Toast.makeText(FloatButtonActivity.this, radioButton.getText(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    mImageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            isSecondOpen = !isSecondOpen;
                            if (isSecondOpen){
                                mFloatCelClasses.expandGroup(groupPosition);
                                Toast.makeText(FloatButtonActivity.this, "显示", Toast.LENGTH_SHORT).show();
                            }else {
//                                mImageView.setBackgroundResource(group_state_array[0]);
                                mFloatCelClasses.collapseGroup(groupPosition);
                                Toast.makeText(FloatButtonActivity.this, "消失", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    return  convertView;
                case 3:
                    Log.d(TAG, "getGroupView: "+3);
                    convertView = (LinearLayout) LinearLayout.inflate(getBaseContext(),
                            R.layout.float_button_expaned_list_classes_group3, null);
//                    setGroupOnClick(convertView);
                    mFlowRadioGroup = (FlowRadioGroup) convertView.findViewById(R.id.frg_group);
                    RadioButton radioButton1;
                    if (fourChecked > 0 ) {
                        radioButton1 = (RadioButton) convertView.findViewById(secondChecked);
                    }else{
                        radioButton1 = (RadioButton) convertView.findViewById(R.id.radio_checked);
                    }
                    if (radioButton1 != null) {
                        radioButton1.setChecked(true);
                    }
                    mFlowRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            fourChecked = checkedId;
                            RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                        }
                    });
                    return  convertView;
                default:

                    return null;
            }


        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            switch (groupPosition){
                case 1:
                    Log.d(TAG, "getChildView: "+1);
                    convertView = (LinearLayout) LinearLayout.inflate(getBaseContext(),
                            R.layout.float_button_expaned_list_classes_child1, null);
                    mSecondFlowRadioChild = (FlowRadioGroup) convertView.findViewById(R.id.frg_group);
                    mSecondFlowRadioChild.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            Log.d(TAG, "onCheckedChanged: "+1);

                            if (mSecondRadioButton != null) {
                                mSecondRadioButton.setChecked(false);
                            }
                        }
                    });
                    return convertView;
                case 2:
                    Log.d(TAG, "getChildView: "+2);
                    convertView = (LinearLayout) LinearLayout.inflate(getBaseContext(),
                            R.layout.float_button_expaned_list_classes_child2, null);
                    mThirdFlowRadioChild = (FlowRadioGroup) convertView.findViewById(R.id.frg_group);
                    mThirdFlowRadioChild.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {

                            Log.d(TAG, "onCheckedChanged: "+2);
                        }
                    });
                    return convertView;
                default:
                    return null;
            }
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return true;
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

        private RadioButton getRadioButtonLayout(String title,View v){

            RadioButton radioButton = new RadioButton(v.getContext());
            radioButton.setLayoutParams(new MyRadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            radioButton.setText(title);
            radioButton.setTextColor(getResources().getColorStateList(R.color.cb_color_sel));
            Bitmap a = null;
            radioButton.setButtonDrawable(new BitmapDrawable(a));
            Drawable drawable = getResources().getDrawable(R.drawable.line);
//            radioButton.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null, drawable,null);
            return radioButton;
        }


    }
}
