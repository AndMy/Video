package com.home.quhong.quhong;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.home.quhong.quhong.TV.entity.filtrate.Filtrate;
import com.home.quhong.quhong.TV.network.RetrofitHelper;

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
    RecyclerView floatRecyclerView;
    @BindView(R.id.text_zero_ll_contains)
    LinearLayout textZeroLlContains;
    @BindView(R.id.text_two_ll_contains)
    LinearLayout textTwoLlContains;
    @BindView(R.id.text_three_ll_contains)
    LinearLayout textThreeLlContains;
    private CompositeSubscription mSubscription = new CompositeSubscription();
    @BindView(R.id.text_one_ll_contains)
    LinearLayout textOneLlContains;
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
    List<String> list;
    private List<View> mTvList = new ArrayList<>();
    private List<View> mLlList = new ArrayList<>();
    private boolean isFirstOpen = false;
    private int isCheckedId = 0;

    public FiltrateActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtrate);
        ButterKnife.bind(this);
        initGetData();
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
        Iterator<Map.Entry<String, String>> iterator3 = mCountryMap.entrySet().iterator();
        while (iterator3.hasNext()) {
            Map.Entry next = iterator3.next();
            Object value = next.getValue();
            mTwoList.add(String.valueOf(value));
        }
        initView();
    }

    private void initView() {
        TextMananger tmListener = new TextMananger();
        LinearMananger llMananger = new LinearMananger();
        textOneLlContains.removeAllViews();
        inflater = LayoutInflater.from(this);
        View firstLinear = inflater.inflate(R.layout.filtrate_first_linear_layout, null);
        textOneLlContains.addView(firstLinear);
        ImageView imageView = (ImageView) firstLinear.findViewById(R.id.iv_filtrate_first);
        imageView.setOnClickListener(llMananger);
        firstLinear.setTag(0);

        if (mCategoryMap.size() > 0 && llOneCount > 0) {
            for (int i = 0; i < llOneCount; i++) {
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.setTag(i + 1);
                mLlList.add(linearLayout);
                for (int j = 0; j < 5; j++) {
                    if (mOneList != null && mOneList.size() > (i * 5 + j)) {
                        mTextView = inflater.inflate(R.layout.filtrate_text_show, null);
                        ((TextView) mTextView.findViewById(R.id.keyword)).setText(mOneList.get(i * 5 + j));
                        View view11 = inflater.inflate(R.layout.filtrate_splite_line, null);
                        linearLayout.addView(mTextView);
                        if (j != 4) {
                            linearLayout.addView(view11);
                        }
                        mTextView.setTag(i * 5 + j);
                        mTvList.add(mTextView);
                        mTextView.setOnClickListener(tmListener);
//                                view.setTag(i * 5 + j,mTextView);
                    }
                }
                textOneLlContains.addView(linearLayout);
            }
        }
    }

    private void analyzeData() {
        if(mCountryList != null){
            for (int i = 0; i < mCountryList.size(); i++) {
                String k = mCountryList.get(i).getK();
                String v = mCountryList.get(i).getV();
                mCountryMap.put(k,v);
            }
        }
        if(mClassesList != null){
            for (int i = 0; i < mClassesList.size(); i++) {
                String k = mClassesList.get(i).getK();
                String v = mClassesList.get(i).getV();
                mClassesMap.put(k,v);
            }
        }
        if(mOrderList != null){
            for (int i = 0; i < mOrderList.size(); i++) {
                String k = mOrderList.get(i).getK();
                String v = mOrderList.get(i).getV();
                mOrderMap.put(k,v);
            }
        }
        if (mCategoryList != null) {
            for (int i = 0; i < mCategoryList.size(); i++) {
                String k = mCategoryList.get(i).getK();
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

    private void initGetData() {
        Observable<Filtrate> seriesUrlAgain = RetrofitHelper.getFiltrateApi().getTestFiltrateDetail("drama", "remance", "KR", "hot", null);
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

                    }

                    @Override
                    public void onNext(Filtrate rs) {
                        mCategoryList = rs.getCategory();
                        mCountryList = rs.getCountry();
                        mClassesList = rs.getClasses();
                        mOrderList = rs.getOrder();
                    }
                }));
    }

    public class TextMananger implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            isCheckedId = (Integer) v.getTag();
            TextView textView = (TextView) v.findViewById(R.id.keyword);
            textView.setTextColor(0xff2196F3);
//            textView.setBackgroundResource(R.color.blue);
            cancleOperation(v);
        }

        public void cancleOperation(View v) {
            if (mTvList != null) {
                for (View view : mTvList) {
                    TextView cancleView = (TextView) view.findViewById(R.id.keyword);
                    if (v != view) {
                        cancleView.setTextColor(0xffB8B8B8);
                    }
                }
            }
        }
    }

    public class LinearMananger implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int whichll = isCheckedId / 5;
            if (isFirstOpen) {
                hideOperation(whichll);
            } else {
                showOperation();
            }
            isFirstOpen = !isFirstOpen;
        }

        public void hideOperation(int postion) {
            for (int i = 0; i < mLlList.size(); i++) {
                if (i != postion) {
                    mLlList.get(i).setVisibility(View.GONE);
                }
            }
        }

        public void showOperation() {
            for (View view : mLlList) {
                view.setVisibility(View.VISIBLE);
            }
        }

    }
}
