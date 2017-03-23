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
    List<String> list;
    private List<View> mZeroTvList = new ArrayList<>();
    private List<View> mOneTvList = new ArrayList<>();
    private List<View> mTwoTvList = new ArrayList<>();
    private List<View> mThreeTvList = new ArrayList<>();

    private List<View> mZeroLlList = new ArrayList<>();
    private List<View> mOneLlList = new ArrayList<>();
    private List<View> mTwoLlList = new ArrayList<>();
    private List<View> mThreeLlList = new ArrayList<>();

    private boolean isFirstOpen = false;
    private int zeroLinkId = 0;
    private int oneLinkId = 0;
    private int twoLinkId = 0;
    private int threeLinkId = 0;

    private int classesCheckedId = 0;
    private int categoryCheckedId = 0;
    private int countryheckedId = 0;
    private int orderCheckedId = 0;

    private String classes = "all";
    private String category = "all";
    private String country = "all";
    private String order = "hot";

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
                        Toast.makeText(FiltrateActivity.this, "失敗", Toast.LENGTH_SHORT).show();
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
                        ((TextView) mTextView.findViewById(R.id.keyword)).setText(mZeroList.get(i * 5 + j));
                        View view11 = inflater.inflate(R.layout.filtrate_splite_line, null);
                        linearLayout.addView(mTextView);
                        if (j != 4 && mZeroList.size() - (i * 5) != j+1 ) {
                                linearLayout.addView(view11);
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
                mOneLlList.add(linearLayout);
                for (int j = 0; j < 5; j++) {
                    if (mOneList != null && mOneList.size() > (i * 5 + j)) {
                        mTextView = inflater.inflate(R.layout.filtrate_text_show, null);
                        ((TextView) mTextView.findViewById(R.id.keyword)).setText(mOneList.get(i * 5 + j));
                        View view11 = inflater.inflate(R.layout.filtrate_splite_line, null);
                        linearLayout.addView(mTextView);
                        if (j != 4 && mOneList.size() - (i * 5) != j+1 ) {
                            linearLayout.addView(view11);
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
        LinearOneManager llMananger = new LinearOneManager();
        /*第二条*/
        View secondLinear = inflater.inflate(R.layout.filtrate_first_linear_layout, null);
        TextView textView = (TextView) secondLinear.findViewById(R.id.tv_filtrate_first);
        textView.setText("CATEGORY");
        mContains.addView(secondLinear);
        ImageView imageView = (ImageView) secondLinear.findViewById(R.id.iv_filtrate_first);
        secondLinear.setTag(0);

        if (mCountryMap.size() > 0 && llTwoCount > 0) {
            for (int i = 0; i < llTwoCount; i++) {
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.setTag(i + 1);
                mTwoLlList.add(linearLayout);
                for (int j = 0; j < 5; j++) {
                    if (mTwoList != null && mTwoList.size() > (i * 5 + j)) {
                        mTextView = inflater.inflate(R.layout.filtrate_text_show, null);
                        ((TextView) mTextView.findViewById(R.id.keyword)).setText(mTwoList.get(i * 5 + j));
                        View view11 = inflater.inflate(R.layout.filtrate_splite_line, null);
                        linearLayout.addView(mTextView);
                        if (j != 4 && mTwoList.size() - (i * 5) != j+1 ) {
                            linearLayout.addView(view11);
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
        View secondLinear = inflater.inflate(R.layout.filtrate_first_linear_layout, null);
        TextView textView = (TextView) secondLinear.findViewById(R.id.tv_filtrate_first);
        textView.setText("ORDER");
        mContains.addView(secondLinear);
        ImageView imageView = (ImageView) secondLinear.findViewById(R.id.iv_filtrate_first);
        secondLinear.setTag(0);

        if (mOrderMap.size() > 0 && llThreeCount > 0) {
            for (int i = 0; i < llThreeCount; i++) {
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.setTag(i + 1);
                mThreeLlList.add(linearLayout);
                for (int j = 0; j < 5; j++) {
                    if (mThreeList != null && mThreeList.size() > (i * 5 + j)) {
                        mTextView = inflater.inflate(R.layout.filtrate_text_show, null);
                        ((TextView) mTextView.findViewById(R.id.keyword)).setText(mThreeList.get(i * 5 + j));
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
            } else {
                showOperation();
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
}
