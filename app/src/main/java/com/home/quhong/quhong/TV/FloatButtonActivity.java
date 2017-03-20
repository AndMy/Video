package com.home.quhong.quhong.TV;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.adapter.FloatButtonRecyclerViewAdapter;
import com.home.quhong.quhong.TV.entity.floatButton.FloatButtonDetail;
import com.home.quhong.quhong.TV.network.RetrofitHelper;
import com.home.quhong.quhong.TV.utils.ToastUtil;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class FloatButtonActivity extends AppCompatActivity {
    private static final String TAG = "FloatButtonActivity";
    @BindView(R.id.float_recycler_view)
    RecyclerView mFloatRecyclerView;
    @BindView(R.id.iv_show_two)
    ImageView mIvShow;
    @BindView(R.id.rb_checked)
    RadioButton mRbChecked;
    @BindView(R.id.rg_one)
    RadioGroup mRgOne;
    @BindView(R.id.rg_two)
    RadioGroup mRgTwo;
    @BindView(R.id.rg_three)
    RadioGroup mRgThree;
    @BindView(R.id.rg_zero)
    RadioGroup mRgZero;
    @BindView(R.id.iv_show_three)
    ImageView mIvShowThree;
    @BindView(R.id.rb_two_checked)
    RadioButton mRbTwoChecked;
    @BindView(R.id.rg_two_one)
    RadioGroup mRgTwoOne;
    @BindView(R.id.rg_two_two)
    RadioGroup mRgTwoTwo;
    @BindView(R.id.rg_two_three)
    RadioGroup mRgTwoThree;
    @BindView(R.id.rg_four)
    RadioGroup mRgFour;
    @BindView(R.id.one_rb_checked)
    RadioButton mRbOneChecked;
    @BindView(R.id.four_rb_checked)
    RadioButton mRbFourChecked;

    private CompositeSubscription mSubscription = new CompositeSubscription();
    private FloatButtonDetail.CategoryBean mCategoryBean;
    private FloatButtonDetail.CountryBean mCountryBean;
    private FloatButtonDetail.ClassesBean mClassesBean;
    private List<FloatButtonDetail.DataBean> mDataBeanList = new ArrayList<>();
    private FloatButtonDetail.OrderBean mOrderBean;
    private boolean isFirstOpen = false;
    private Boolean isSecondOpen = false;
    private int firstCheckedGroupId = 1;
    private int secondCheckedGroupId = 1;

    private RadioButton mZeroRadioButton;

    private RadioButton mOneRadioButton;
    private RadioButton mTwoRadioButton;
    private RadioButton mThreeRadioButton;

    private RadioButton mTwoOneRadioButton;
    private RadioButton mTwoTwoRadioButton;
    private RadioButton mTwoThreeRadioButton;

    private RadioButton mFourRadioButton;
    private Observable<FloatButtonDetail> mFloatButtonVideoDetail;

    private String classes = "all";
    private String category = "all";
    private String country = "all";
    private String order = "new";
    private String language = null;
    private FloatButtonRecyclerViewAdapter mAdapter;

    private boolean isRefresh = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_button);
        ButterKnife.bind(this);
        mRbChecked.setChecked(true);
        mRbTwoChecked.setChecked(true);
        mRbOneChecked.setChecked(true);
        mRbFourChecked.setChecked(true);
        mOneRadioButton = mRbChecked;
        mTwoOneRadioButton = mRbTwoChecked;

        mFourRadioButton = mRbFourChecked;
        initGetData();
        /*组一*/
        mRgZero.setOnCheckedChangeListener((group, checkedId) -> {
            mZeroRadioButton = (RadioButton) group.findViewById(checkedId);
            refreshClassesData(mZeroRadioButton);
        });
        /*组二*/
        mRgOne.setOnCheckedChangeListener((group, checkedId) -> {
            mOneRadioButton = (RadioButton) group.findViewById(checkedId);

            if (firstCheckedGroupId == 2 && mTwoRadioButton != null && mTwoRadioButton.isChecked()) {
                isRefresh = false;
                mTwoRadioButton.setChecked(false);
                isRefresh = true;
                mOneRadioButton.setChecked(true);
            }
            if (firstCheckedGroupId == 3 && mThreeRadioButton != null && mThreeRadioButton
                    .isChecked()) {
                isRefresh = false;
                mThreeRadioButton.setChecked(false);
                isRefresh = true;
                mOneRadioButton.setChecked(true);
            }
            if(isRefresh) {
                refrshCountryData(mOneRadioButton);
            }
            firstCheckedGroupId = 1;
        });
        mRgTwo.setOnCheckedChangeListener((group, checkedId) -> {
            mTwoRadioButton = (RadioButton) group.findViewById(checkedId);
            if (firstCheckedGroupId == 1) {
                if (mOneRadioButton != null && mOneRadioButton.isChecked()) {
                    isRefresh = false;
                    mOneRadioButton.setChecked(false);
                    isRefresh = true;
                    mTwoRadioButton.setChecked(true);
                }
            }
            if (firstCheckedGroupId == 3 && mThreeRadioButton != null && mThreeRadioButton
                    .isChecked()) {
                isRefresh = false;
                mThreeRadioButton.setChecked(false);
                isRefresh = true;
                mTwoRadioButton.setChecked(true);
            }
            if(isRefresh) {
                refrshCountryData(mTwoRadioButton);
            }
            firstCheckedGroupId = 2;
        });
        mRgThree.setOnCheckedChangeListener((group, checkedId) -> {
            mThreeRadioButton = (RadioButton) group.findViewById(checkedId);
            if (firstCheckedGroupId == 1 && mOneRadioButton != null && mOneRadioButton.isChecked()) {
                isRefresh = false;
                mOneRadioButton.setChecked(false);
                isRefresh = true;
                mThreeRadioButton.setChecked(true);
            }
            if (firstCheckedGroupId == 2 && mTwoRadioButton != null && mTwoRadioButton.isChecked()) {
                isRefresh = false;
                mTwoRadioButton.setChecked(false);
                isRefresh = true;
                mThreeRadioButton.setChecked(true);
            }
            if(isRefresh) {
                refrshCountryData(mThreeRadioButton);
            }
            firstCheckedGroupId = 3;
        });


        /*组三*/
        mRgTwoOne.setOnCheckedChangeListener((group, checkedId) -> {
            mTwoOneRadioButton = (RadioButton) group.findViewById(checkedId);
            if (secondCheckedGroupId == 2 && mTwoTwoRadioButton != null && mTwoTwoRadioButton.isChecked()) {
                isRefresh = !isRefresh;
                mTwoTwoRadioButton.setChecked(false);
                isRefresh = !isRefresh;
                mTwoOneRadioButton.setChecked(true);
            }
            if (secondCheckedGroupId == 3 && mTwoThreeRadioButton != null && mTwoThreeRadioButton
                    .isChecked()) {
                isRefresh = !isRefresh;
                mTwoThreeRadioButton.setChecked(false);
                isRefresh = !isRefresh;
                mTwoOneRadioButton.setChecked(true);
            }
            if(isRefresh) {
                refreshCategoryData(mTwoOneRadioButton);
            }
            secondCheckedGroupId = 1;
        });
        mRgTwoTwo.setOnCheckedChangeListener((group, checkedId) -> {
            mTwoTwoRadioButton = (RadioButton) group.findViewById(checkedId);

            if (secondCheckedGroupId == 1) {
                if (mTwoOneRadioButton != null && mTwoOneRadioButton.isChecked()) {
                    isRefresh = !isRefresh;
                    mTwoOneRadioButton.setChecked(false);
                    isRefresh = !isRefresh;
                    mTwoTwoRadioButton.setChecked(true);
                }
            }
            if (secondCheckedGroupId == 3 && mTwoThreeRadioButton != null && mTwoThreeRadioButton
                    .isChecked()) {
                isRefresh = !isRefresh;
                mTwoThreeRadioButton.setChecked(false);
                isRefresh = !isRefresh;
                mTwoTwoRadioButton.setChecked(true);
            }
            if(isRefresh) {
                refreshCategoryData(mTwoTwoRadioButton);
            }
            secondCheckedGroupId = 2;
        });
        mRgTwoThree.setOnCheckedChangeListener((group, checkedId) -> {
            mTwoThreeRadioButton = (RadioButton) group.findViewById(checkedId);
            if (secondCheckedGroupId == 1 && mTwoOneRadioButton != null && mTwoOneRadioButton.isChecked()) {
                isRefresh = !isRefresh;
                mTwoOneRadioButton.setChecked(false);
                isRefresh = !isRefresh;
                mTwoThreeRadioButton.setChecked(true);
            }
            if (secondCheckedGroupId == 2 && mTwoTwoRadioButton != null && mTwoTwoRadioButton.isChecked()) {
                isRefresh = !isRefresh;
                mTwoTwoRadioButton.setChecked(false);
                isRefresh = !isRefresh;
                mTwoThreeRadioButton.setChecked(true);
            }
            if (isRefresh){
                refreshCategoryData(mTwoThreeRadioButton);
            }
            secondCheckedGroupId = 3;
        });

        /*组四*/
        mRgFour.setOnCheckedChangeListener((group, checkedId) -> {
            mFourRadioButton = (RadioButton) group.findViewById(checkedId);
            refrshOrderData(mFourRadioButton);
        });
    }

    private void initGetData() {
        mFloatButtonVideoDetail = RetrofitHelper.getFloatButtonApi()
                .getFloatButtonDetail(classes,category, country,order,language);
        mSubscription.add(mFloatButtonVideoDetail.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FloatButtonDetail>() {
                    @Override
                    public void onCompleted() {
                        initView();
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
                    }
                }));
    }
    private void refreshClassesData(RadioButton rg){
        if (rg != null) {
            String text = String.valueOf(rg.getText());
            String sort = sort(text);
            classes = sort;
            initGetData();
            mAdapter.notifyDataSetChanged();
            Log.d(TAG, "refreshClassesData() called with: rg = [" + rg + "]"+classes);
        }
    }
    private void refreshCategoryData(RadioButton rg){
        if (rg != null) {
            String text = String.valueOf(rg.getText());
            String sort = sort(text);
            category = sort;
            initGetData();
            mAdapter.notifyDataSetChanged();
            Log.d(TAG, "refreshCategoryData() called with: rg = [" + rg + "]"+category);
        }
    }
    private void refrshCountryData(RadioButton rg){
        if (rg != null) {
            String text = String.valueOf(rg.getText());
            String sort = sort(text);
            country = sort;
            initGetData();
            mAdapter.notifyDataSetChanged();
            Log.d(TAG, "refrshCountryData() called with: rg = [" + rg + "]"+ country);
        }
    }
    private void refrshOrderData(RadioButton rg){
        if (rg != null) {
            String text = String.valueOf(rg.getText());
            String sort = sort(text);
            order = sort;
            initGetData();
            mAdapter.notifyDataSetChanged();
            Log.d(TAG, "refrshOrderData() called with: rg = [" + rg + "]"+order);
        }
    }
    private void initView() {
        mAdapter = new FloatButtonRecyclerViewAdapter(this, mDataBeanList);
        mFloatRecyclerView.setAdapter(mAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        mFloatRecyclerView.setLayoutManager(layoutManager);
    }

    @OnClick({R.id.iv_show_two, R.id.iv_show_three})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_show_two:
                if (isFirstOpen) {
                    mIvShow.setImageResource(R.drawable.group_up);
                    switch (firstCheckedGroupId) {
                        case 1:
                            mRgTwo.setVisibility(View.GONE);
                            mRgThree.setVisibility(View.GONE);
                            break;
                        case 2:
                            mRgOne.setVisibility(View.GONE);
                            mRgThree.setVisibility(View.GONE);
                            break;
                        case 3:
                            mRgOne.setVisibility(View.GONE);
                            mRgTwo.setVisibility(View.GONE);
                            break;
                    }
                } else {
                    mIvShow.setImageResource(R.drawable.group_down);
                    if (mRgOne.getVisibility() == View.GONE) {
                        mRgOne.setVisibility(View.VISIBLE);
                    }
                    if (mRgTwo.getVisibility() == View.GONE) {
                        mRgTwo.setVisibility(View.VISIBLE);
                    }
                    if (mRgThree.getVisibility() == View.GONE) {
                        mRgThree.setVisibility(View.VISIBLE);
                    }
                }
                isFirstOpen = !isFirstOpen;
                break;
            case R.id.iv_show_three:
                if (isSecondOpen) {
                    mIvShowThree.setImageResource(R.drawable.group_up);
                    switch (secondCheckedGroupId) {
                        case 1:
                            mRgTwoTwo.setVisibility(View.GONE);
                            mRgTwoThree.setVisibility(View.GONE);
                            break;
                        case 2:
                            mRgTwoOne.setVisibility(View.GONE);
                            mRgTwoThree.setVisibility(View.GONE);
                            break;
                        case 3:
                            mRgTwoOne.setVisibility(View.GONE);
                            mRgTwoTwo.setVisibility(View.GONE);
                            break;
                    }
                } else {
                    mIvShowThree.setImageResource(R.drawable.group_down);
                    if (mRgTwoOne.getVisibility() == View.GONE) {
                        mRgTwoOne.setVisibility(View.VISIBLE);
                    }
                    if (mRgTwoTwo.getVisibility() == View.GONE) {
                        mRgTwoTwo.setVisibility(View.VISIBLE);
                    }
                    if (mRgTwoThree.getVisibility() == View.GONE) {
                        mRgTwoThree.setVisibility(View.VISIBLE);
                    }
                }
                isSecondOpen = !isSecondOpen;
                break;
        }
    }
    public String sort(String s){
        switch (s){
        /*one Rdiogroup*/
            case "  All  ":
                return "all";
            case "  Film  ":
                return "movie";
            case "  Serial Barat  ":
                return "drama";
        /*two Rdiogroup*/
            case "  China  ":
                return "CN";
            case "  Hong Kong  ":
                return "HK";
            case "  Japan  ":
                return "JP";
            case "  France  ":
                return "FR";
            case "  United States  ":
                return "US";
            case "  Korea_South  ":
                return "KS";
            case "  Great Britain  ":
                return "GB";
            case "  Indian  ":
                return "IN";
            case "  Thailand  ":
                return "TH";
            case "  Indonesia  ":
                return "ID";
        /*three Rdiogroup*/
            case "  18+  ":
                return "18";
            case "  Mystery  ":
                return "mystery";
            case "  Drama  ":
                return "drama";
            case "  Sci-fi  ":
                return "sci-fi";
            case "  Family  ":
                return "family";
            case "  Horroe  ":
                return "horror";
            case "  Crime  ":
                return "crime";
            case "  Romance  ":
                return "romance";
            case "  Fantasy  ":
                return "fantasy";
            case "  Animation  ":
                return "animation";
            case "  Adventure  ":
                return "adventure";
            case "  Biography  ":
                return "biography";
            case "  Action  ":
                return "action";
            case "  Comedy  ":
                return "comedy";
            case "  Thriller  ":
                return "thriller";
        /*four Rdiogroup*/
            case "  Newest  ":
                return "new";
            case "  Hottest  ":
                return "hot";
        }
        return "";
    }
}
