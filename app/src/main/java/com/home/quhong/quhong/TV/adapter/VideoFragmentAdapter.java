package com.home.quhong.quhong.TV.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.home.quhong.quhong.R;
import com.home.quhong.quhong.TV.entity.video.Data;
import com.home.quhong.quhong.TV.fragments.VideoFragment;
import com.home.quhong.quhong.TV.utils.ToastUtil;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.width;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static java.security.AccessController.getContext;

/**
 * Created by aserbao on 2017/3/3.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */

public class VideoFragmentAdapter extends RecyclerView.Adapter {
    public static final int Foor = 1;
    public static final int VIDEO = 2;


    private Context mContext;
    private List<Data> mDataBean1List;

    public VideoFragmentAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
            return VIDEO;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_fragment_item_video_view, parent, false);
            return new VideoViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mDataBean1List != null && position < mDataBean1List.size() && holder instanceof VideoViewHolder) {
            VideoViewHolder hotViewHolder = (VideoViewHolder) holder;
            hotViewHolder.bindView(mDataBean1List.get(position));
            return;
        }
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if (mDataBean1List != null) {
            ret = mDataBean1List.size();
        }else{
            ret = 12;
        }
        return ret;
    }

    public void setVideoDetail(List<Data> data){
        this.mDataBean1List = data;
    }


    public class FoorViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_more_progressBar)
        ProgressBar mProgressBar;
        @BindView(R.id.item_more_textView)
        TextView mTextView;
        @BindView(R.id.item_more_finishTextView)
        TextView mFinishTextView;

        public FoorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }

        private void bindView() {
            if (mDataBean1List.size() == 0) {
                itemView.setVisibility(View.GONE);
            } else {
                itemView.setVisibility(View.VISIBLE);
                mFinishTextView.setVisibility(View.GONE);
                mTextView.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.VISIBLE);
            }
        }
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
        private SparseArrayCompat<View> mSparseArrayCompat;
        private TextView mContentTextView;
        private TextView mBuryTextView;
        private ImageView mShareImageView;
        private ImageView mPictureImageView;
        private ImageView mIndicatorImageView;
        private VideoView mVideoView;
        private ProgressBar mProgressBar;
        private MediaController mMediaController;
        private Data mData;
        public VideoViewHolder(View inflate) {
            super(inflate);
            mMediaController = new MediaController(mContext);
            mSparseArrayCompat = new SparseArrayCompat<>();
        }
        void bindView(Data data){
            mData = data;
            initFindView();
            initData(data);
            setListeners();
        }
        private void setListeners() {
            mShareImageView.setOnClickListener(this);
            mIndicatorImageView.setOnClickListener(this);
        }
        public void initData(Data data){
          /*  Bitmap scaledBitmap = Bitmap.createScaledBitmap(mBitmap, mSystemWidth-20, mSystemHeight/4, false);
            mOutImageView.setImageBitmap(scaledBitmap);
            Drawable drawable = mOutImageView.getDrawable();*/
            Glide.with(mContext).load(data.getThumb_url()).into(mPictureImageView);
            mBuryTextView.setText(data.getTitle());
        }
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case  R.id.item_video_shareImage:
                    String msgText = "This is a magical application, hurry up to share with your friends:https://www.baidu.com/img/bd_logo1.png";
                    shareMsg("","分享标题",msgText,null);
                    break;
                case R.id.item_video_indicator:
                    mIndicatorImageView.setVisibility(View.INVISIBLE);
                    mProgressBar.setVisibility(View.VISIBLE);
                    mVideoView.setVisibility(View.VISIBLE);
                    mVideoView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mPictureImageView.getHeight()));
                    mVideoView.setMediaController(mMediaController);
//                    mVideoView.setVideoPath("http://music.wufazhuce.com/lmyuVg_Y-L37x6g93quntpGF2tyH");
                    mVideoView.setVideoPath("https://www.vidio.com/videos/106728/vjs_playlist.m3u8");
                    mVideoView.setOnCompletionListener(this);
                    mVideoView.setOnErrorListener(this);
                    mVideoView.setOnPreparedListener(this);
                    mVideoView.start();
                    break;
                default:
                    ToastUtil.ShortToast("默认点击");
            }
        }


        /**
         * 分享功能
         *

         * @param activityTitle
         *            Activity的名字
         * @param msgTitle
         *            消息标题
         * @param msgText
         *            消息内容
         * @param imgPath
         *            图片路径，不分享图片则传null
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
            mContext.startActivity(Intent.createChooser(intent, activityTitle));
        }

        private void initFindView() {
            mBuryTextView = (TextView) customFindViewByID(R.id.item_video_buryText);
            mShareImageView = (ImageView) customFindViewByID(R.id.item_video_shareImage);
            mVideoView = (VideoView) customFindViewByID(R.id.item_video_video);
            mPictureImageView = (ImageView) customFindViewByID(R.id.item_video_image);
            mIndicatorImageView = (ImageView) customFindViewByID(R.id.item_video_indicator);
            mProgressBar = (ProgressBar) customFindViewByID(R.id.item_video_progressBar);
        }

        public View customFindViewByID(int resourceID) {
            View ret = null;
            ret = mSparseArrayCompat.get(resourceID);
            if (ret == null) {
                ret = itemView.findViewById(resourceID);
                mSparseArrayCompat.put(resourceID, ret);
            }
            return ret;
        }

        @Override
        public void onCompletion(MediaPlayer mp) {
            videoReset();
        }
        public void videoReset() {
            mPictureImageView.setVisibility(View.VISIBLE);
            mIndicatorImageView.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.INVISIBLE);
            mVideoView.setVisibility(View.INVISIBLE);
            mVideoView.stopPlayback();
        }
        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            mPictureImageView.setVisibility(View.VISIBLE);
            mIndicatorImageView.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.INVISIBLE);
            mVideoView.setVisibility(View.INVISIBLE);
            mVideoView.stopPlayback();
            return false;
        }

        @Override
        public void onPrepared(MediaPlayer mp) {
            mProgressBar.setVisibility(View.INVISIBLE);
            mPictureImageView.setVisibility(View.INVISIBLE);
            mVideoView.setVisibility(View.VISIBLE);
        }
    }




}
