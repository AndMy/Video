package com.home.quhong.quhong.TV;

import android.app.Notification;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.tablayout.SlidingTabLayout;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.adapter.DownloadAdapter;
import com.home.quhong.quhong.TV.adapter.VideoRecycleAdapter;
import com.home.quhong.quhong.TV.utils.ToastUtil;
import com.home.quhong.quhong.TV.widght.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayerActivity extends AppCompatActivity {


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
    private VideoRecycleAdapter mAdapter;
    private List<String> mDatas;
    private int mWidth;
    private Boolean isOpen = false;
    private String URL_HLS = "https://www.vidio.com/videos/615090/vjs_playlist.m3u8";
    private List<String> mStrings = new ArrayList<>();
    private List<String> mChildStrings = new ArrayList<>();
//    private ExpandableListAdapter mListAdapter;


    public PlayerActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_recycle_estimate);
        ButterKnife.bind(this);
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        mWidth = metric.widthPixels;     // 屏幕宽度（像素）

        mStrings.add("Type:comedy,drama,musical \nLanguage:English");
        mChildStrings.add("Release on:2016-12-01");
        mChildStrings.add("Director:Damien Chazelle");
        mChildStrings.add("Case:Amiee Conn,Emma Stone,Ryan Gosing,Terry Walters");
        mChildStrings.add("Release on:2016-12-01Director:Damien ChazelleCase:Amiee Conn,Emma Stone,Ryan Gosing,Terry Walters");

        initData();
        initExpandListView();
        initViews();
    }

    private void initViews() {
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


        initPlayerView(URL_HLS);
    }

    private void initExpandListView() {

        // 设置默认图标为不显示状态
        mPlayerExpandableListview.setGroupIndicator(null);


        // 设置一级item点击的监听器
        mPlayerExpandableListview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                isOpen = !isOpen;
                ToastUtil.ShortToast("123");

                ((BaseExpandableListAdapter) mListAdapter).notifyDataSetChanged();
                /*Intent intent = new Intent(PlayerActivity.this, PlayerActivity.class);
                startActivity(intent);*/
                return false;
            }
        });
        mPlayerExpandableListview.setAdapter(mListAdapter);
    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            mDatas.add(String.valueOf(i));
        }
    }


    public void initPlayerView(String uri) {
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        AdaptiveVideoTrackSelection.Factory factory = new AdaptiveVideoTrackSelection.Factory(bandwidthMeter);
        DefaultTrackSelector selector = new DefaultTrackSelector(factory);
        DefaultLoadControl loadControl = new DefaultLoadControl();
        SimpleExoPlayer exoPlayer = ExoPlayerFactory.newSimpleInstance(this, selector, loadControl);
        mPlayerView.setPlayer(exoPlayer);
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "Test02"), bandwidthMeter);
        // Produces Extractor instances for parsing the media data.
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
// This is the MediaSource representing the media to be played.
//        MediaSource videoSource = new ExtractorMediaSource(Uri.parse(URL_MP4),
//                dataSourceFactory, extractorsFactory, null, null);

        MediaSource mediaSource = new HlsMediaSource(Uri.parse(uri),
                new DefaultDataSourceFactory(this, "HlsPlayActivity"),
                null, null);
        exoPlayer.prepare(mediaSource);
    }
    final ExpandableListAdapter mListAdapter = new BaseExpandableListAdapter() {
        // 一级标签上的logo图片数据源
        // 一级标签上的标题数据源
		/*private String[] group_title_arry = new String[] { "颈椎测试", "腰部测试" };
		// 子视图显示文字
		private String[][] child_text_array = new String[][] {
				{ "是否经常感到左臂疼痛？", "是否经常熬夜？", "您的踝关节有刺痛的现象吗？", "是否经常用凉水洗头？" },
				{ "心累", "心碎？", "心脏？", "洗头？" },
				{ "是否经常感到左臂疼痛？", "是否经常熬夜？", "您的踝关节有刺痛的现象吗？", "是否经常用凉水洗头？" },
				{ "是否经常感到左臂疼痛？", "是否经常熬夜？", "您的踝关节有刺痛的现象吗？", "是否经常用凉水洗头？" } };*/
        // 一级标签上的状态图片数据源
        int[] group_state_array = new int[] { R.drawable.group_down,
                R.drawable.group_up };

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
            child_text.setText(mChildStrings.get(0)+"\n"+mChildStrings.get(1)+"\n"+mChildStrings.get(2));
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

}
