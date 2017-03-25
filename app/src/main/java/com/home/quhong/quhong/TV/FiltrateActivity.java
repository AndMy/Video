package com.home.quhong.quhong.TV;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.adapter.FiltrateRecyclerViewAdapter;
import com.home.quhong.quhong.TV.entity.filtrate.DataBean;
import com.home.quhong.quhong.TV.entity.filtrate.Filtrate;
import com.home.quhong.quhong.TV.entity.filtrate.RefreshFiltrate;
import com.home.quhong.quhong.TV.network.RetrofitHelper;
import com.home.quhong.quhong.TV.utils.ConstantUtil;
import com.home.quhong.quhong.TV.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class FiltrateActivity extends AppCompatActivity {
    private static final String TAG = "FiltrateActivity";
    @BindView(R.id.float_recycler_view)
    RecyclerView mFloatRecyclerView;
    @BindView(R.id.text_zero_ll_contains)
    LinearLayout textZeroLlContains;
    @BindView(R.id.text_two_ll_contains)
    LinearLayout textTwoLlContains;
    @BindView(R.id.text_three_ll_contains)
    LinearLayout textThreeLlContains;
    private CompositeSubscription mSubscription = new CompositeSubscription();
    @BindView(R.id.text_one_ll_contains)
    LinearLayout mContains;
    private LayoutInflater inflater;
    private List<Filtrate.CountryBean> mCountryList = new ArrayList<>();
    private List<Filtrate.CategoryBean> mCategoryList = new ArrayList<>();
    private List<Filtrate.ClassesBean> mClassesList = new ArrayList<>();
    private List<Filtrate.OrderBean> mOrderList = new ArrayList<>();

    private HashMap<String, String> mCategoryMap = new LinkedHashMap<>();
    private HashMap<String, String> mCountryMap = new LinkedHashMap<>();
    private HashMap<String, String> mClassesMap = new LinkedHashMap<>();
    private HashMap<String, String> mOrderMap = new LinkedHashMap<>();
    private List<String> mZeroList = new ArrayList<>();
    private List<String> mOneList = new ArrayList<>();
    private List<String> mTwoList = new ArrayList<>();
    private List<String> mThreeList = new ArrayList<>();

    private int llZeroCount;
    private int llOneCount;
    private int llTwoCount;
    private int llThreeCount;
    private View mTextView;
    private List<View> mZeroTvList = new ArrayList<>();
    private List<View> mOneTvList = new ArrayList<>();
    private List<View> mTwoTvList = new ArrayList<>();
    private List<View> mThreeTvList = new ArrayList<>();

    private List<View> mZeroLlList = new ArrayList<>();
    private List<View> mOneLlList = new ArrayList<>();
    private List<View> mTwoLlList = new ArrayList<>();
    private List<View> mThreeLlList = new ArrayList<>();

    private boolean isFirstOpen = false;
    private boolean isSecondOpen = false;
    private int zeroLinkId = 0;
    private int oneLinkId = 0;
    private int twoLinkId = 0;
    private int threeLinkId = 0;

    private int classesCheckedId = 0;
    private int categoryCheckedId = 0;
    private int countryCheckedId = 0;
    private int orderCheckedId = 0;

    private String classes = "all";
    private String category = "adventure";
    private String country = "all";
    private String order = "hot";
    private FiltrateRecyclerViewAdapter mAdapter;
    private List<DataBean> mDataBeanList = new ArrayList<>();
    private List<DataBean> mAgainBeanList = new ArrayList<>();
    private GridLayoutManager mGLManager;
    private int mLastVisibleItem;
    private int page = 1;
    /*  private TextOneManager tmListener;
    private LinearOneMananger llMananger;*/

    public FiltrateActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtrate);
        ButterKnife.bind(this);
        initGetData();
    }
    private void initGetData() {
        Observable<Filtrate> seriesUrlAgain = RetrofitHelper.getFiltrateApi().getFirstFiltrateDetail("drama", "remance", "KR", "hot", null);
        mSubscription.add(seriesUrlAgain.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Filtrate>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(FiltrateActivity.this, "完成", Toast.LENGTH_SHORT).show();
                        analyzeData();
                    }
                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(FiltrateActivity.this, "失敗", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onNext(Filtrate rs) {
                        mCategoryList = rs.getCategory();
                        mCountryList = rs.getCountry();
                        mClassesList = rs.getClasses();
                        mOrderList = rs.getOrder();
                        mDataBeanList = rs.getData();
                    }
                }));
    }
    private void initTwoGetData() {
        Observable<RefreshFiltrate> seriesUrlAgain = RetrofitHelper.getRefreshFiltrateApi().getSecondFiltrateDetail("drama", "remance", "KR", "hot", null,page);
        mSubscription.add(seriesUrlAgain.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RefreshFiltrate>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(FiltrateActivity.this, "完成", Toast.LENGTH_SHORT).show();
                        analyzeData();
                    }
                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(FiltrateActivity.this, "失敗", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onNext(RefreshFiltrate rs) {
                        String next_url = rs.getNext_url();
                        ToastUtil.LongToast(next_url);
                        mAgainBeanList = rs.getData();
                    }
                }));
    }
    private void analyzeData() {
        if(mCountryList != null){
            for (int i = 0; i < mCountryList.size(); i++) {
                String k = mCountryList.get(i).getK();
                if(country.equals(k)){
                    countryCheckedId = i;
                    twoLinkId = i;
                }
                String v = mCountryList.get(i).getV();
                mCountryMap.put(k,v);
            }
        }
        if(mClassesList != null){
            for (int i = 0; i < mClassesList.size(); i++) {
                String k = mClassesList.get(i).getK();
                if(classes.equals(k)){
                    classesCheckedId = i;
                    zeroLinkId = i;
                }
                String v = mClassesList.get(i).getV();
                mClassesMap.put(k,v);
            }
        }
        if(mOrderList != null){
            for (int i = 0; i < mOrderList.size(); i++) {
                String k = mOrderList.get(i).getK();
                if(order.equals(k)){
                    orderCheckedId = i;
                    threeLinkId = i;
                }
                String v = mOrderList.get(i).getV();
                mOrderMap.put(k,v);
            }
        }
        if (mCategoryList != null) {
            for (int i = 0; i < mCategoryList.size(); i++) {
                String k = mCategoryList.get(i).getK();
                if (category.equals(k)){
                    categoryCheckedId = i;
                    oneLinkId = i;
                }
                String v = mCategoryList.get(i).getV();
                mCategoryMap.put(k, v);
            }
        }
        calData();
        traverseData();
    }
    private void calData() {
        if (mClassesList.size() % 5 != 0) {
            llZeroCount = mClassesMap.size() / 5 + 1;
        } else {
            llZeroCount = mClassesMap.size() / 5;
        }

        if (mCategoryMap.size() % 5 != 0) {
            llOneCount = mCategoryMap.size() / 5 + 1;
        } else {
            llOneCount = mCategoryMap.size() / 5;
        }
        if (mCountryMap.size() % 5 != 0) {
            llTwoCount = mCountryMap.size() / 5 + 1;
        } else {
            llTwoCount = mCountryMap.size() / 5;
        }
        if (mOrderMap.size() % 5 != 0) {
            llThreeCount = mOrderMap.size() / 5 + 1;
        } else {
            llThreeCount = mOrderMap.size() / 5;
        }
    }
    private void traverseData() {
        Iterator<Map.Entry<String, String>> iterator0 = mClassesMap.entrySet().iterator();
        while (iterator0.hasNext()) {
            Map.Entry next = iterator0.next();
            Object value = next.getValue();
            mZeroList.add(String.valueOf(value));
        }
        Iterator<Map.Entry<String, String>> iterator1 = mCategoryMap.entrySet().iterator();
        while (iterator1.hasNext()) {
            Map.Entry next = iterator1.next();
            Object value = next.getValue();
            mOneList.add(String.valueOf(value));
        }
        Iterator<Map.Entry<String, String>> iterator2 = mCountryMap.entrySet().iterator();
        while (iterator2.hasNext()) {
            Map.Entry next = iterator2.next();
            Object value = next.getValue();
            mTwoList.add(String.valueOf(value));
        }
        Iterator<Map.Entry<String, String>> iterator3 = mOrderMap.entrySet().iterator();
        while (iterator3.hasNext()) {
            Map.Entry next = iterator3.next();
            Object value = next.getValue();
            mThreeList.add(String.valueOf(value));
        }
        initView();
    }
    private void initView() {
        inflater = LayoutInflater.from(this);
        initZeroLL();
        initFirstLL();
        initSecondLL();
        initThirdLL();
        initRecyclerView();
    }
    private void initRecyclerView() {
        if (mDataBeanList != null) {
            mAdapter = new FiltrateRecyclerViewAdapter(this, mDataBeanList);
            mFloatRecyclerView.setAdapter(mAdapter);
            mGLManager = new GridLayoutManager(this, 3);
            mFloatRecyclerView.setLayoutManager(mGLManager);
        }
        initListeners();
    }
    private void initListeners() {
        mFloatRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastVisibleItemPosition = mGLManager.findLastVisibleItemPosition();
                mLastVisibleItem = lastVisibleItemPosition;
                Log.d(TAG, "onScrollStateChanged: 刷新状态");
                int itemCount = mAdapter.getItemCount();
                if (mLastVisibleItem == itemCount - 1) {
                    initTwoGetData();
                    if (mAgainBeanList != null) {
                        mAdapter.setData(mDataBeanList);
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d(TAG, "onScrolled: 刷新完成");
            }
        });
    }
    private void initZeroLL() {
        TextZeroManager tvZeroManager = new TextZeroManager();
        /*第0条*/
        View zeroLinear = inflater.inflate(R.layout.filtrate_first_linear_layout, null);
        textZeroLlContains.addView(zeroLinear);
        TextView textView = (TextView) zeroLinear.findViewById(R.id.tv_filtrate_first);
        textView.setText("CLASSES");
        if(llZeroCount == 1) {
            ImageView imageView = (ImageView) zeroLinear.findViewById(R.id.iv_filtrate_first);
            imageView.setVisibility(View.INVISIBLE);
        }
        if (mClassesMap.size() > 0 && llZeroCount > 0) {
            for (int i = 0; i < llZeroCount; i++) {
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.setTag(i + 1);
                mZeroLlList.add(linearLayout);
                for (int j = 0; j < 5; j++) {
                    if (mZeroList != null && mZeroList.size() > (i * 5 + j)) {
                        mTextView = inflater.inflate(R.layout.filtrate_text_show, null);
                        TextView textView1 = (TextView) mTextView.findViewById(R.id.keyword);
                        textView1.setText(mZeroList.get(i * 5 + j));
                        if(classesCheckedId == i * 5 +j){
                            textView1.setTextColor(0xfff9bc01);
                        }
                        View lineView = inflater.inflate(R.layout.filtrate_splite_line, null);
                        linearLayout.addView(mTextView);
                        if (j != 4 && mZeroList.size() - (i * 5) != j+1 ) {
                                linearLayout.addView(lineView);
                        }
                        mTextView.setTag(i * 5 + j);
                        mZeroTvList.add(mTextView);
                        mTextView.setOnClickListener(tvZeroManager);
                    }
                }
                mContains.addView(linearLayout);
            }
        }
    }
    private void initFirstLL() {
        TextOneManager tmListener = new TextOneManager();
        LinearOneManager llMananger = new LinearOneManager();
        /*第一条*/
        View firstLinear = inflater.inflate(R.layout.filtrate_first_linear_layout, null);
        TextView textView = (TextView) firstLinear.findViewById(R.id.tv_filtrate_first);
        textView.setText("CATEGORY");
        mContains.addView(firstLinear);
        ImageView imageView = (ImageView) firstLinear.findViewById(R.id.iv_filtrate_first);
        imageView.setOnClickListener(llMananger);
        firstLinear.setTag(0);

        if (mCategoryMap.size() > 0 && llOneCount > 0) {
            for (int i = 0; i < llOneCount; i++) {
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.setTag(i + 1);
                if ((categoryCheckedId / 5 != i)) {
                    linearLayout.setVisibility(View.GONE);
                }
                mOneLlList.add(linearLayout);
                for (int j = 0; j < 5; j++) {
                    if (mOneList != null && mOneList.size() > (i * 5 + j)) {
                        mTextView = inflater.inflate(R.layout.filtrate_text_show, null);
                        TextView textView1 = (TextView) mTextView.findViewById(R.id.keyword);
                        textView1.setText(mOneList.get(i * 5 + j));
                        if(categoryCheckedId == i * 5 +j){
                            textView1.setTextColor(0xfff9bc01);
                        }
                        View lineView = inflater.inflate(R.layout.filtrate_splite_line, null);
                        linearLayout.addView(mTextView);
                        if (j != 4 && mOneList.size() - (i * 5) != j+1 ) {
                            linearLayout.addView(lineView);
                        }
                        mTextView.setTag(i * 5 + j);
                        mOneTvList.add(mTextView);
                        mTextView.setOnClickListener(tmListener);
//                                view.setTag(i * 5 + j,mTextView);
                    }
                }
                mContains.addView(linearLayout);
            }
        }
    }
    private void initSecondLL() {
        TextTwoManager txTwoManager = new TextTwoManager();
        LinearTwoManager llMananger = new LinearTwoManager();
        /*第二条*/
        View secondLinear = inflater.inflate(R.layout.filtrate_first_linear_layout, null);
        TextView textView = (TextView) secondLinear.findViewById(R.id.tv_filtrate_first);
        textView.setText("COUNTRY");
        mContains.addView(secondLinear);
        ImageView imageView = (ImageView) secondLinear.findViewById(R.id.iv_filtrate_first);
        imageView.setOnClickListener(llMananger);
        secondLinear.setTag(0);

        if (mCountryMap.size() > 0 && llTwoCount > 0) {
            for (int i = 0; i < llTwoCount; i++) {
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.setTag(i + 1);
                if ((countryCheckedId / 5 != i)) {
                    linearLayout.setVisibility(View.GONE);
                }
                mTwoLlList.add(linearLayout);
                for (int j = 0; j < 5; j++) {
                    if (mTwoList != null && mTwoList.size() > (i * 5 + j)) {
                        mTextView = inflater.inflate(R.layout.filtrate_text_show, null);
                        TextView textView1 = (TextView) mTextView.findViewById(R.id.keyword);
                        textView1.setText(mTwoList.get(i * 5 + j));
                        if(countryCheckedId == i * 5 +j){
                            textView1.setTextColor(0xfff9bc01);
                        }
                        View lineView = inflater.inflate(R.layout.filtrate_splite_line, null);
                        linearLayout.addView(mTextView);
                        if (j != 4 && mTwoList.size() - (i * 5) != j+1 ) {
                            linearLayout.addView(lineView);
                        }
                        mTextView.setTag(i * 5 + j);
                        mTwoTvList.add(mTextView);
                        mTextView.setOnClickListener(txTwoManager);
//                                view.setTag(i * 5 + j,mTextView);
                    }
                }
                mContains.addView(linearLayout);
            }
        }
    }
    private void initThirdLL() {
        TextThreeManager txThreeManager = new TextThreeManager();
        /*第二条*/
        View thirdLinear = inflater.inflate(R.layout.filtrate_first_linear_layout, null);
        TextView textView = (TextView) thirdLinear.findViewById(R.id.tv_filtrate_first);
        textView.setText("ORDER");
        mContains.addView(thirdLinear);
        if(llZeroCount == 1) {
            ImageView imageView = (ImageView) thirdLinear.findViewById(R.id.iv_filtrate_first);
            imageView.setVisibility(View.INVISIBLE);
        }

        if (mOrderMap.size() > 0 && llThreeCount > 0) {
            for (int i = 0; i < llThreeCount; i++) {
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.setTag(i + 1);
                mThreeLlList.add(linearLayout);
                for (int j = 0; j < 5; j++) {
                    if (mThreeList != null && mThreeList.size() > (i * 5 + j)) {
                        mTextView = inflater.inflate(R.layout.filtrate_text_show, null);
                        TextView textView1 = (TextView) mTextView.findViewById(R.id.keyword);
                        textView1.setText(mThreeList.get(i * 5 + j));
                        if(orderCheckedId == i * 5 +j){
                            textView1.setTextColor(0xfff9bc01);
                        }
                        View lineView = inflater.inflate(R.layout.filtrate_splite_line, null);
                        linearLayout.addView(mTextView);
                        if (j != 4 && mThreeList.size() - (i * 5) != j+1 ) {
                            linearLayout.addView(lineView);
                        }
                        mTextView.setTag(i * 5 + j);
                        mThreeTvList.add(mTextView);
                        mTextView.setOnClickListener(txThreeManager);
//                                view.setTag(i * 5 + j,mTextView);
                    }
                }
                mContains.addView(linearLayout);
            }
        }
    }

    public static void launch(Activity context, String more_url) {
        Intent intent = new Intent(context, FloatButtonActivity.class);
        intent.putExtra(ConstantUtil.PASS_URL, more_url);
        context.startActivity(intent);
    }

    public class TextZeroManager implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            zeroLinkId = (Integer) v.getTag();
            TextView textView = (TextView) v.findViewById(R.id.keyword);
            textView.setTextColor(0xfff9bc01);
            cancleOperation(v);
        }

        public void cancleOperation(View v) {
            if (mZeroTvList != null) {
                for (View view : mZeroTvList) {
                    TextView cancleView = (TextView) view.findViewById(R.id.keyword);
                    if (v != view) {
                        cancleView.setTextColor(0xffB8B8B8);
                    }
                }
            }
        }
    }
    public class TextOneManager implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            oneLinkId = (Integer) v.getTag();
            TextView textView = (TextView) v.findViewById(R.id.keyword);
            textView.setTextColor(0xfff9bc01);
            cancleOperation(v);
        }

        public void cancleOperation(View v) {
            if (mOneTvList != null) {
                for (View view : mOneTvList) {
                    TextView cancleView = (TextView) view.findViewById(R.id.keyword);
                    if (v != view) {
                        cancleView.setTextColor(0xffB8B8B8);
                    }
                }
            }
        }
    }
    public class TextTwoManager implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            twoLinkId = (Integer) v.getTag();
            TextView textView = (TextView) v.findViewById(R.id.keyword);
            textView.setTextColor(0xfff9bc01);
            cancleOperation(v);
        }
        public void cancleOperation(View v) {
            if (mTwoTvList != null) {
                for (View view : mTwoTvList) {
                    TextView cancleView = (TextView) view.findViewById(R.id.keyword);
                    if (v != view) {
                        cancleView.setTextColor(0xffB8B8B8);
                    }
                }
            }
        }
    }
    public class TextThreeManager implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            threeLinkId = (Integer) v.getTag();
            TextView textView = (TextView) v.findViewById(R.id.keyword);
            textView.setTextColor(0xfff9bc01);
            cancleOperation(v);
        }

        public void cancleOperation(View v) {
            if (mThreeTvList != null) {
                for (View view : mThreeTvList) {
                    TextView cancleView = (TextView) view.findViewById(R.id.keyword);
                    if (v != view) {
                        cancleView.setTextColor(0xffB8B8B8);
                    }
                }
            }
        }
    }
    public class LinearOneManager implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int whichll = oneLinkId / 5;
            if (isFirstOpen) {
                hideOperation(whichll);
                ((ImageView) v).setImageResource(R.drawable.drop_up);
            } else {
                showOperation();
                ((ImageView) v).setImageResource(R.drawable.drop_down);
            }
            isFirstOpen = !isFirstOpen;
        }

        public void hideOperation(int postion) {
            for (int i = 0; i < mOneLlList.size(); i++) {
                if (i != postion) {
                    mOneLlList.get(i).setVisibility(View.GONE);
                }
            }
        }

        public void showOperation() {
            for (View view : mOneLlList) {
                view.setVisibility(View.VISIBLE);
            }
        }
    }

    public class LinearTwoManager implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int whichll = twoLinkId / 5;
            if (isSecondOpen) {
                ((ImageView) v).setImageResource(R.drawable.drop_up);
                hideOperation(whichll);
            } else {
                ((ImageView) v).setImageResource(R.drawable.drop_down);
                showOperation();
            }
            isSecondOpen = !isSecondOpen;
        }

        public void hideOperation(int postion) {
            for (int i = 0; i < mTwoLlList.size(); i++) {
                if (i != postion) {
                    mTwoLlList.get(i).setVisibility(View.GONE);
                }
            }
        }

        public void showOperation() {
            for (View view : mTwoLlList) {
                view.setVisibility(View.VISIBLE);
            }
        }

    }
}
