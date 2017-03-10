package com.home.quhong.quhong.TV;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.tablayout.SlidingTabLayout;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.home.quhong.quhong.QuHongApp;
import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.adapter.DownloadAdapter;
import com.home.quhong.quhong.TV.adapter.RecycleAdapter;
import com.home.quhong.quhong.TV.adapter.VideoRecycleAdapter;
import com.home.quhong.quhong.TV.aserbao.BottomDialogFragment;
import com.home.quhong.quhong.TV.entity.home.HomeVideoDetail;
import com.home.quhong.quhong.TV.entity.home.SeriesBean;
import com.home.quhong.quhong.TV.fragments.DownFragment;
import com.home.quhong.quhong.TV.fragments.PlayFragment;
import com.home.quhong.quhong.TV.network.RetrofitHelper;
import com.home.quhong.quhong.TV.utils.ConstantUtil;
import com.home.quhong.quhong.TV.utils.ToastUtil;
import com.home.quhong.quhong.TV.widght.NoScrollViewPager;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static android.R.attr.fragment;

public class PlayerActivity extends AppCompatActivity {
    private static final String TAG = "PlayerActivity";

    @BindView(R.id.player_vip)
    ImageView mPlayerVip;
    @BindView(R.id.player_title)
    TextView mPlayerTitle;
    @BindView(R.id.player_textView)
    TextView mPlayerTextView;
    @BindView(R.id.player_sliding_tabs)
    SlidingTabLayout mPlayerSlidingTabs;
    @BindView(R.id.palyer_view_pager)
    NoScrollViewPager mPalyerViewPager;
    @BindView(R.id.player_recycler)
    RecyclerView mPlayerRecycler;
    @BindView(R.id.player_view)
    SimpleExoPlayerView mPlayerView;
    @BindView(R.id.player_expandable_listview)
    ExpandableListView mPlayerExpandableListview;
    @BindView(R.id.image_email)
    ImageView mImageEmail;
    @BindView(R.id.iamge_down)
    ImageView mIamgeDown;
    @BindView(R.id.iamge_share)
    ImageView mIamgeShare;
    @BindView(R.id.iamge_love)
    ImageView mIamgeLove;


    private VideoRecycleAdapter mAdapter;
    private List<String> mDatas;
    private int mWidth;
    private Boolean isOpen = false;
    private String URL_HLS = "https://cdn3.speedplay.us/hls/,5ciyn2qrmzaqjh63omapnxs2nyek52aw4f57dpndfanpoucvbzv4zhepw2yq,.urlset/master.m3u8";
    private String URL_DASH = "https://redirector.googlevideo.com/videoplayback?id=fb106701fee9004d&itag=18&source=webdrive&requiressl=yes&ttl=transient&mm=30&mn=sn-4g5edned&ms=nxu&mv=m&pl=20&ei=SNi_WIO9K4avqQWhxIMo&mime=video/mp4&lmt=1477990495057540&mt=1488967643&ip=37.120.186.184&ipbits=0&expire=1488982152&sparams=ip,ipbits,expire,id,itag,source,requiressl,ttl,mm,mn,ms,mv,pl,ei,mime,lmt&signature=797A4C3B2139AE071D5008FC1DC440F8A262B061.B0EF85C202835AD2356F621820B0EC960552B1AD&key=ck2&type=video/mp4&title=E2-1";
    private static final DefaultBandwidthMeter BANDWIDTH_METER = new DefaultBandwidthMeter();
    private CompositeSubscription mSubscription = new CompositeSubscription();
    private List<String> mStrings = new ArrayList<>();
    private List<String> mChildStrings = new ArrayList<>();
    private MediaSource mMediaSource;
    private DataSource.Factory mediaDataSourceFactory;
    private String dramaId;
    private List<SeriesBean> mSeries = new ArrayList<>();
    private HomeVideoDetail mHomeVideoDetail1 = null;
    private String mTitle;
    private String location = null;
    private PopupWindow mPopupWindow;

    public PlayerActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_recycle_estimate);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        mWidth = metric.widthPixels;     // 屏幕宽度（像素）
        /**获取传递数据*/
        Intent intent = getIntent();
        if (intent != null) {
            dramaId = intent.getStringExtra(ConstantUtil.PASS_URL);
        }

        mediaDataSourceFactory = buildDataSourceFactory(true);
        initData();
    }

    protected void initData() {

        Observable<HomeVideoDetail> homeVideoDetail = RetrofitHelper.getHomeVideoApi().getHomeVideoDetail(dramaId);
        mSubscription.add(homeVideoDetail
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeVideoDetail>() {
                    @Override
                    public void onCompleted() {
                        ToastUtil.ShortToast("显示完成");
                        mStrings.add("Type:" + mHomeVideoDetail1.getCategory() + "\nLanguage:" + mHomeVideoDetail1.getDub());
                        mChildStrings.add("Release on:" + mHomeVideoDetail1.getRelease());
                        mChildStrings.add("Director:" + mHomeVideoDetail1.getDirector());
                        mChildStrings.add("Case:" + mHomeVideoDetail1.getStars());
                        mChildStrings.add(mHomeVideoDetail1.getIntroduction());
                        initExpandListView();
                        initGetLocation(mSeries.get(0).getDownload_url());
                        initViews();
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.ShortToast("显示失败");
                    }

                    @Override
                    public void onNext(HomeVideoDetail homeVideoDetail) {
                        mHomeVideoDetail1 = homeVideoDetail;
                        mSeries = homeVideoDetail.getSeries();
                        ToastUtil.ShortToast(String.valueOf(mSeries.size()));
                    }
                }));
    }


    public String getLocationMethod(HttpGet request, Context context) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        int responseCode = 0;
        try {
            HttpParams params = new BasicHttpParams();
            params.setParameter("http.protocol.handle-redirects", false); // 默认不让重定向
            // 这样就能拿到Location头了
            request.setParams(params);
            HttpResponse response = httpclient.execute(request);
            responseCode = response.getStatusLine().getStatusCode();
            if (responseCode == 302) {
                Header locationHeader = response.getFirstHeader("Location");
                if (locationHeader != null) {
                    location = locationHeader.getValue();
                    ToastUtil.ShortToast(location);
                }
            } else {
                ToastUtil.ShortToast("responseCode值不为302，没有获取Location");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("exception=", e.toString());
        }
        return location;
    }

    private void initGetLocation(String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                HttpClient httpClient
                        = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet("http://api.beemovieapp.com" + url);
                httpGet.addHeader("User-Agent", "BeeMovie/3.5.0");
                getLocationMethod(httpGet, getBaseContext());
            }
        }).start();
    }

    private void initViews() {
        if (mHomeVideoDetail1.isVip()) {
            mPlayerVip.setVisibility(View.VISIBLE);
        }
        mTitle = mHomeVideoDetail1.getTitle();
        mPlayerTitle.setText(mTitle);
        mPlayerTextView.setText(mHomeVideoDetail1.getRating());
        mDatas = new ArrayList<String>();
        for (int i = 0; i < mSeries.size(); i++) {
            mDatas.add(String.valueOf(i));
        }
        mAdapter = new VideoRecycleAdapter(this, mDatas);
        mAdapter.setOnItemClickListener(new VideoRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(PlayerActivity.this, position + "被点击", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        mPlayerRecycler.setAdapter(mAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mPlayerRecycler.setLayoutManager(linearLayoutManager);
        mPlayerRecycler.setItemAnimator(new DefaultItemAnimator());

        DownloadAdapter adapter = new DownloadAdapter(getSupportFragmentManager(), this);
        mPalyerViewPager.setAdapter(adapter);
        mPlayerSlidingTabs.setTabWidth(mWidth / 4);

        mPlayerSlidingTabs.setViewPager(mPalyerViewPager);


        initPlayerView(location);
    }

    private void initExpandListView() {

        // 设置默认图标为不显示状态
        mPlayerExpandableListview.setGroupIndicator(null);


        // 设置一级item点击的监听器
        mPlayerExpandableListview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                isOpen = !isOpen;
                ((BaseExpandableListAdapter) mListAdapter).notifyDataSetChanged();
                return false;
            }
        });
        mPlayerExpandableListview.setAdapter(mListAdapter);
    }

    public void initPlayerView(String uri) {
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        AdaptiveVideoTrackSelection.Factory factory = new AdaptiveVideoTrackSelection.Factory(bandwidthMeter);
        DefaultTrackSelector selector = new DefaultTrackSelector(factory);
        DefaultLoadControl loadControl = new DefaultLoadControl();
        SimpleExoPlayer exoPlayer = ExoPlayerFactory.newSimpleInstance(this, selector, loadControl);
        mPlayerView.setPlayer(exoPlayer);
        exoPlayer.setPlayWhenReady(true);
        ToastUtil.ShortToast("播放");
        mMediaSource = new ExtractorMediaSource(Uri.parse(URL_DASH), mediaDataSourceFactory, new DefaultExtractorsFactory(),
                null, null);
        exoPlayer.prepare(mMediaSource);
    }

    final ExpandableListAdapter mListAdapter = new BaseExpandableListAdapter() {
        int[] group_state_array = new int[]{R.drawable.group_down,
                R.drawable.group_up};

        // 重写ExpandableListAdapter中的各个方法

        /**
         * 获取一级标签总数
         */
        @Override
        public int getGroupCount() {
            return 1;
        }

        /**
         * 获取一级标签内容
         */
        @Override
        public Object getGroup(int groupPosition) {
            return mStrings.get(0);
        }

        /**
         * 获取一级标签的ID
         */
        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        /**
         * 获取一级标签下二级标签的总数
         */
        @Override
        public int getChildrenCount(int groupPosition) {
            return 1;
        }

        /**
         * 获取一级标签下二级标签的内容
         */
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return mChildStrings.get(childPosition);
        }

        /**
         * 获取二级标签的ID
         */
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        /**
         * 指定位置相应的组视图
         */
        @Override
        public boolean hasStableIds() {
            return true;
        }

        /**
         * 对一级标签进行设置
         */
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            // 为视图对象指定布局
            convertView = (LinearLayout) LinearLayout.inflate(getBaseContext(),
                    R.layout.group_layout, null);

            RelativeLayout myLayout = (RelativeLayout) convertView
                    .findViewById(R.id.group_layout);

            /**
             * 声明视图上要显示的控件
             */
            // 新建一个TextView对象，用来显示一级标签上的标题信息
            TextView group_title = (TextView) convertView
                    .findViewById(R.id.group_title);
            // 新建一个TextView对象，用来显示一级标签上的大体描述的信息
            ImageView group_state = (ImageView) convertView
                    .findViewById(R.id.group_state);
            /**
             * 设置相应控件的内容
             */
            // 设置标题上的文本信息
            group_title.setText(mStrings.get(groupPosition));
            // 设置整体描述上的文本信息

            if (isOpen) {
                // 设置默认的图片是选中状态
                group_state.setBackgroundResource(group_state_array[1]);
                myLayout.setBackgroundResource(R.drawable.text_item_top_bg);
            } else {
                myLayout.setBackgroundResource(R.drawable.text_item_bg);
                // 设置默认的图片是未选中状态
                group_state.setBackgroundResource(group_state_array[0]);
            }
            // 返回一个布局对象
            return convertView;
        }

        /**
         * 对一级标签下的二级标签进行设置
         */
        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {

            // 为视图对象指定布局

            convertView = (LinearLayout) LinearLayout.inflate(
                    getBaseContext(), R.layout.child_s_layout, null);

            /**
             * 声明视图上要显示的控件
             */
            // 新建一个TextView对象，用来显示具体内容
            TextView child_text1 = (TextView) convertView
                    .findViewById(R.id.exp_child_text_ins);
            TextView child_text = (TextView) convertView
                    .findViewById(R.id.exp_child_text);
            /**
             * 设置相应控件的内容
             */
            // 设置要显示的文本信息
            child_text.setText(mChildStrings.get(0) + "\n" + mChildStrings.get(1) + "\n" + mChildStrings.get(2));
            child_text1.setText(mChildStrings.get(3));
            // 判断item的位置是否相同，如相同，则表示为选中状态，更改其背景颜色，如不相同，则设置背景色为白色

            // 返回一个布局对象
            return convertView;
        }

        /**
         * 当选择子节点的时候，调用该方法
         */
        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

    };

    //todo:传参
    public static void launch(Activity activity, String dramaId) {

        Intent intent = new Intent(activity, PlayerActivity.class);
        intent.putExtra(ConstantUtil.PASS_URL, dramaId);
        activity.startActivity(intent);
    }

    /**
     * Returns a new DataSource factory.
     *
     * @param useBandwidthMeter Whether to set {@link #BANDWIDTH_METER} as a listener to the new
     *                          DataSource factory.
     * @return A new DataSource factory.
     */
    private DataSource.Factory buildDataSourceFactory(boolean useBandwidthMeter) {
        return ((QuHongApp) getApplication())
                .buildDataSourceFactory(useBandwidthMeter ? BANDWIDTH_METER : null);
    }

    @OnClick({R.id.image_email, R.id.iamge_down, R.id.iamge_share, R.id.iamge_love})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_email:

                break;
            case R.id.iamge_down:
                showPopupWindow();
                break;
            case R.id.iamge_share:
                mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
                View rootView = LayoutInflater.from(PlayerActivity.this).inflate(R.layout.player_recycle_estimate, null);
                mPopupWindow.showAtLocation(rootView,
                        Gravity.BOTTOM , 0, 0);
                mPopupWindow.update();
                break;
            case R.id.iamge_love:
                mPopupWindow.dismiss();
                break;
        }
    }

    private void showPopupWindow() {

        BottomDialogFragment dialogFragment = new BottomDialogFragment();
        dialogFragment.show(getFragmentManager(),"");
    }
    public  List<SeriesBean> getSeriesBean(){
        return mSeries;
    }
    public class SortComparator implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            SeriesBean o11 = (SeriesBean) o1;
            SeriesBean o22 = (SeriesBean) o2;
            return (Integer.getInteger(o11.getId()) - Integer.getInteger(o22.getId()));
        }
    }
}
