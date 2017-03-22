package com.home.quhong.quhong.TV;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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
import com.home.quhong.quhong.TV.aserbao.AutoHeightViewPager;
import com.home.quhong.quhong.TV.aserbao.BottomDialogFragment;
import com.home.quhong.quhong.TV.entity.detail.RequestSeries;
import com.home.quhong.quhong.TV.entity.detail.VideoDetail;
import com.home.quhong.quhong.TV.fragments.PlayFragment;
import com.home.quhong.quhong.TV.network.RetrofitHelper;
import com.home.quhong.quhong.TV.utils.ConstantUtil;
import com.home.quhong.quhong.TV.utils.ToastUtil;
import com.home.quhong.quhong.TestSortCmparator;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class PlayerActivity extends AppCompatActivity implements PlayFragment.OnButtonClickListener {
    private static final String TAG = "CompatActivity";

    @BindView(R.id.player_vip)
    ImageView mPlayerVip;
    @BindView(R.id.player_title)
    TextView mPlayerTitle;
    @BindView(R.id.player_textView)
    TextView mPlayerTextView;
    @BindView(R.id.player_sliding_tabs)
    SlidingTabLayout mPlayerSlidingTabs;
    @BindView(R.id.palyer_view_pager)
    AutoHeightViewPager mPalyerViewPager;
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
    @BindView(R.id.iv_cover)
    ImageView ivCover;
    @BindView(R.id.iv_player)
    ImageView ivPlayer;
    @BindView(R.id.pb_loaded)
    ProgressBar pbLoaded;
    @BindView(R.id.linearlayout)
    LinearLayout linearlayout;


    private VideoRecycleAdapter mAdapter;
    private int mWidth;
    private Boolean isOpen = false;
    private static final DefaultBandwidthMeter BANDWIDTH_METER = new DefaultBandwidthMeter();
    private CompositeSubscription mSubscription = new CompositeSubscription();
    private CompositeSubscription mSubscription1 = new CompositeSubscription();
    private List<String> mStrings = new ArrayList<>();
    private List<String> mChildStrings = new ArrayList<>();
    private MediaSource mMediaSource;
    private DataSource.Factory mediaDataSourceFactory;
    private String dramaId;
    private String URL = "https://r4---sn-cvh7kn7l.googlevideo.com/videoplayback?mn=sn-cvh7kn7l&upn=-TwLXzLfRfk&signature=6F13E4004B8A43A42C0E47B50C5778EFEBE0F0F5.38AEB86656525053ADE47479332F6B3A4B614A7C&mm=31&id=o-APTRJbIFwx9DCB4ArD6dQC6Gzwn1fQLPyNSFUALS_VCy&dur=160.519&initcwndbps=3322500&mt=1490172924&lmt=1473505144898306&ip=35.154.0.251&key=yt6&mv=m&source=youtube&mime=video%2Fmp4&pl=16&ratebypass=yes&sparams=dur%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cupn%2Cexpire&ipbits=0&ms=au&requiressl=yes&itag=22&expire=1490194652";
    private List<VideoDetail.InfoBean.SeriesBean> mSeries = new ArrayList<>();
    private VideoDetail mVideoDetail = null;
    private RequestSeries requestSeries;
    private String mTitle;
    private String location = null;
    private boolean isLike = false;

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
        Observable<VideoDetail> homeVideoDetail = RetrofitHelper.getHomeVideoApi().getHomeVideoDetail(dramaId);
        mSubscription.add(homeVideoDetail
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VideoDetail>() {
                    @Override
                    public void onCompleted() {
                        if (mVideoDetail != null) {
                            initUrlAgain(mVideoDetail.getInfo().getSeries().get(0).getPurl());
                            List<String> category = mVideoDetail.getInfo().getCategory();
                            mStrings.add("Type:" + category.get(0)+ category.get(0)+ "\nLanguage:" + mVideoDetail.getInfo().getCountry());
                            mChildStrings.add("Release on:" + mVideoDetail.getInfo().getRelease());
                            mChildStrings.add("Director:" + mVideoDetail.getInfo().getDirector());
                            mChildStrings.add("Case:" + mVideoDetail.getInfo().getStars());
                            // TODO: 2017/3/22 需要为详情页添加一个字段，添加视频详情
//                        mChildStrings.add(mVideoDetail.getInfo().getIntroduction());
                            initExpandListView();
                        }
                        /*暂时不适用GetLocation方法*/
                        /*initGetLocation(mSeries.get(0).getDownload_url());*/
                        initViews();
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.ShortToast("显示失败");
                    }

                    @Override
                    public void onNext(VideoDetail homeVideoDetail) {
                        mVideoDetail = homeVideoDetail;
                        if (homeVideoDetail != null) {
                            mSeries = homeVideoDetail.getInfo().getSeries();
                            if(mSeries.size() >= 1){
                                TestSortCmparator cmparator = new TestSortCmparator();
                                Collections.sort(mSeries, cmparator);
                            }
                        }
                    }
                }));
    }

    void initUrlAgain(String url){
        Observable<RequestSeries> seriesUrlAgain = RetrofitHelper.getRequestSeriesApi().getSeriesUrlAgain();
        mSubscription1.add(seriesUrlAgain.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<RequestSeries>() {
                            @Override
                            public void onCompleted() {
                                Toast.makeText(PlayerActivity.this, requestSeries.getUrl(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(RequestSeries rs) {
                                requestSeries = rs;
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
        Glide.with(this).load(mVideoDetail.getInfo().getCover()).into(ivCover);
        /*判断是否显示vip*/
         if (mVideoDetail.getInfo().isVip()) {
            mPlayerVip.setVisibility(View.VISIBLE);
        }
        mTitle = mVideoDetail.getInfo().getTitle();
        mPlayerTitle.setText(mTitle);
        // TODO: 2017/3/22 需要添加评分字段
        /*mPlayerTextView.setText(mVideoDetail.getRating());*/

        mAdapter = new VideoRecycleAdapter(this, mVideoDetail);
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

        DownloadAdapter adapter = new DownloadAdapter(getSupportFragmentManager(), this, mSeries);
        mPalyerViewPager.setAdapter(adapter);
        mPlayerSlidingTabs.setTabWidth(mWidth / 4);

        mPlayerSlidingTabs.setViewPager(mPalyerViewPager);

    }

    private void initExpandListView() {
        Log.d(TAG, "initExpandListView() called");
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
            if(mChildStrings.size() == 4) {
                child_text1.setText(mChildStrings.get(3));
            }

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

    public void initPlayerView(String uri) {
        Log.d(TAG, "initPlayerView() called with: uri = [" + uri + "]");
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        AdaptiveVideoTrackSelection.Factory factory = new AdaptiveVideoTrackSelection.Factory(bandwidthMeter);
        DefaultTrackSelector selector = new DefaultTrackSelector(factory);
        DefaultLoadControl loadControl = new DefaultLoadControl();
        SimpleExoPlayer exoPlayer = ExoPlayerFactory.newSimpleInstance(this, selector, loadControl);
        mPlayerView.setPlayer(exoPlayer);
        exoPlayer.setPlayWhenReady(true);
        mMediaSource = new ExtractorMediaSource(Uri.parse(uri), mediaDataSourceFactory, new DefaultExtractorsFactory(),
                null, null);
        boolean isReady = exoPlayer.getPlayWhenReady();
        if (isReady){
            pbLoaded.setVisibility(View.INVISIBLE);
            exoPlayer.prepare(mMediaSource);
        }
        ivCover.setVisibility(View.INVISIBLE);
    }
    //todo:传参
    public static void launch(Activity activity, String dramaId) {
        Log.d(TAG, "launch() called with: activity = [" + activity + "], dramaId = [" + dramaId + "]");
        Intent intent = new Intent(activity, PlayerActivity.class);
        intent.putExtra(ConstantUtil.PASS_URL, dramaId);
        activity.startActivity(intent);
    }


    private DataSource.Factory buildDataSourceFactory(boolean useBandwidthMeter) {
        return ((QuHongApp) getApplication())
                .buildDataSourceFactory(useBandwidthMeter ? BANDWIDTH_METER : null);
    }
    /**
     * @param view
     */
    @OnClick({R.id.image_email, R.id.iamge_down, R.id.iamge_share, R.id.iamge_love,R.id.iv_player})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_email:
                break;
            case R.id.iamge_down:
                showPopupWindow();
                break;
            case R.id.iamge_share:
                String msgText = "This is a magical application, hurry up to share with your friends:https://www.baidu.com/img/bd_logo1.png";
                shareMsg("", "分享标题", msgText, null);
                break;
            case R.id.iamge_love:
                isLike = !isLike;
                if(isLike){
                    mIamgeLove.setImageDrawable(getResources().getDrawable(R.drawable.like_pre));
                }else{
                    mIamgeLove.setImageDrawable(getResources().getDrawable(R.drawable.like_nor));
                }
                break;
            case R.id.iv_player:
                Log.d(TAG, "onClick() called with: view = [" + view + "]");
                ivPlayer.setVisibility(View.INVISIBLE);
                pbLoaded.setVisibility(View.VISIBLE);
                initPlayerView(URL);
                break;
        }
    }

    private void showPopupWindow() {
        Log.d(TAG, "showPopupWindow() called");
        BottomDialogFragment dialogFragment = new BottomDialogFragment();
        dialogFragment.show(getFragmentManager(), "");
    }

    public VideoDetail getHomeVideoDetail1() {
        Log.d(TAG, "getHomeVideoDetail1() called");
        return mVideoDetail;
    }

    @Override
    public void OnButtonClickListener(int message) {
        Log.d(TAG, "OnButtonClickListener() called with: message = [" + message + "]");
        String download_url = mSeries.get(message).getPurl();
        /*暂时不适用getLocation方法*/
//        initGetLocation(download_url);
        if (location != null) {
            initPlayerView(location);
        }
    }

    /**
     * 分享功能
     *
     * @param activityTitle Activity的名字
     * @param msgTitle      消息标题
     * @param msgText       消息内容
     * @param imgPath       图片路径，不分享图片则传null
     */
    public void shareMsg(String activityTitle, String msgTitle, String msgText,
                         String imgPath) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (imgPath == null || imgPath.equals("")) {
            intent.setType("text/*"); // 纯文本
        } else {
            File f = new File(imgPath);
            if (f != null && f.exists() && f.isFile()) {
                intent.setType("image/*");
                Uri u = Uri.fromFile(f);
                intent.putExtra(Intent.EXTRA_STREAM, u);
            }
        }
        intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
        intent.putExtra(Intent.EXTRA_TEXT, msgText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, activityTitle));
    }

}
