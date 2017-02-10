package com.sniper.yunbo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnBufferingUpdateListener;
import io.vov.vitamio.MediaPlayer.OnCompletionListener;
import io.vov.vitamio.MediaPlayer.OnErrorListener;
import io.vov.vitamio.MediaPlayer.OnInfoListener;
import io.vov.vitamio.MediaPlayer.OnPreparedListener;
import io.vov.vitamio.MediaPlayer.OnSeekCompleteListener;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

//import io.vov.vitamio.widget.MediaController.PlayControl;
//import io.vov.vitamio.widget.MediaController.onPauseListener;

public class VitamioActivity extends Activity implements OnClickListener, OnCompletionListener, OnInfoListener,
        OnPreparedListener, OnErrorListener, OnBufferingUpdateListener, OnSeekCompleteListener {
    int xianlu = 2;
    String urlen;
    private MediaController mMediaController;
    private View mLoadingView;
    private String mPath = "http://58.221.78.58/v4.music.126.net/20160223154937/e9d49174981587df543fc15283c74bcd/cloudmusic/JiEhMDIwJDA5NDIxIyQzNQ==/mv/5289042/dd510c771a4d718512d8759a9e4e8a5f.mp4?wshc_tag=0&wsts_tag=56cc0101&wsid_tag=b46e4e88&wsiphost=ipdbm";
    private VideoView mVideoView;
    private int mLayout = VideoView.VIDEO_LAYOUT_STRETCH;
    private TextView mTv_NoPlay;
    private long mLastPosition;
    private View mVolumeBrightnessLayout;
    private ImageView mOperationBg;
    private ImageView mOperationPercent;
    private AudioManager mAudioManager;
    VideoInfo videoInfo;

    /**
     * 最大声音
     */
    private int mMaxVolume;
    /**
     * 当前声音
     */
    private int mVolume = -1;
    /**
     * 当前亮度
     */
    private float mBrightness = -1f;
    /**
     * 当前缩放模式
     */
    private GestureDetector mGestureDetector;
    private float mFast_forward;
    private View mFl_Progress;
    private TextView mTv_progress;
    private ImageView mIv_Progress_bg;
    private boolean isFast_Forword;
    private boolean isUp_downScroll;
    private RelativeLayout mRl_PlayView;
    /**
     * 定时隐藏
     */
    private Handler mDismissHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            isFast_Forword = false;
            isUp_downScroll = false;
            mVolumeBrightnessLayout.setVisibility(View.GONE);
            mFl_Progress.setVisibility(View.GONE);
        }
    };
    /**
     * 是否�?��自动恢复播放，用于自动暂停，恢复播放
     */
    private boolean needResume;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vitamio.isInitialized(this);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.vitamio);
        initVideoView();
        initData();
    }

    public void initData() {
        Intent intent = getIntent();
        videoInfo = intent.getParcelableExtra("videoInfo");
        try {
            urlen = URLEncoder.encode(videoInfo.getUrl(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HashMap localHashMap = new HashMap();
        localHashMap.put("headers", "ContentType:" + "application/x-www-form-urlencoded" + "\r\n");
        localHashMap.put("headers", "CacheControl:" + "no-cache" + "\r\n");
        localHashMap.put("headers", "UserAgent:" + "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)" + "\r\n");
        localHashMap.put("headers", "Accept:" + "image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*" + "\r\n");
        localHashMap.put("headers", "AcceptLanguage:" + "zh-cn" + "\r\n");
        localHashMap.put("headers", "Cookie: " + videoInfo.getCookie() + "\r\n");
        mVideoView.setVideoURI(Uri.parse(videoInfo.getUrl()), localHashMap);
//            // 设置显示名称
            mMediaController = new MediaController(this);

            mVideoView.setMediaController(mMediaController);
            mMediaController.setFileName("哈哈哈");

        int mCurrentOrientation = getResources().getConfiguration().orientation;
        if (mCurrentOrientation == Configuration.ORIENTATION_PORTRAIT) {
//				Utils.full(false, VitamioActivity.this);
            mRl_PlayView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 400));
            if (mVideoView != null) {
//					mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_STRETCH, 0);
            }
        } else if (mCurrentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
//				Utils.full(true, VitamioActivity.this);
            mRl_PlayView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            if (mVideoView != null)
                mVideoView.setVideoLayout(mLayout, 0);
        }
        mVideoView.requestFocus();
        mGestureDetector = new GestureDetector(new MyGestureListener());
    }

    private void initVideoView() {
        mVideoView = (VideoView) findViewById(R.id.surface_view);
        mLoadingView = findViewById(R.id.video_loading);
        mTv_NoPlay = (TextView) findViewById(R.id.tv_noPlay);
        mRl_PlayView = (RelativeLayout) findViewById(R.id.rl_playView);

        mVolumeBrightnessLayout = findViewById(R.id.operation_volume_brightness);
        mOperationBg = (ImageView) findViewById(R.id.operation_bg);
        mOperationPercent = (ImageView) findViewById(R.id.operation_percent);
        mTv_progress = (TextView) findViewById(R.id.tv_progress);
        mFl_Progress = (FrameLayout) findViewById(R.id.fl_set_progress);
        mIv_Progress_bg = (ImageView) findViewById(R.id.iv_progress_bg);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        mMaxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        mVideoView.setOnCompletionListener(this);
        mVideoView.setOnInfoListener(this);
        mVideoView.setOnPreparedListener(this);
        mVideoView.setOnErrorListener(this);
        mVideoView.setOnBufferingUpdateListener(this);
        mVideoView.setOnSeekCompleteListener(this);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        int mCurrentOrientation = getResources().getConfiguration().orientation;
//        if (mCurrentOrientation == Configuration.ORIENTATION_PORTRAIT) {
////			Utils.full(false, MainActivity.this);
//            mRl_PlayView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 400));
//            if (mVideoView != null)
//                mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
//            mMediaController = new MediaController(this, mVideoView);
//            mMediaController.setOnPauseListener(mPauseListener);
//            mVideoView.setMediaController(mMediaController);
//        } else if (mCurrentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
////			Utils.full(true, MainActivity.this);
//            mRl_PlayView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
//            if (mVideoView != null)
//                mVideoView.setVideoLayout(mLayout, 0);
//            mMediaController = new MediaController(this, mVideoView);
//            mMediaController.setOnPauseListener(mPauseListener);
//            mVideoView.setMediaController(mMediaController);
//        }

        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mGestureDetector.onTouchEvent(event))
            return true;

        // 处理手势结束
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                endGesture();
                break;
        }

        return super.onTouchEvent(event);
    }

    /**
     * 手势结束
     */
    private void endGesture() {
        mVolume = -1;
        mBrightness = -1f;
        if (isFast_Forword) {
            onSeekProgress(mFast_forward);
        }
        // 隐藏
        mDismissHandler.removeMessages(0);
        mDismissHandler.sendEmptyMessageDelayed(0, 800);
    }

    private void onSeekProgress(float dis) {
        Log.e("position ==", mVideoView.getCurrentPosition() + 500 * (long) dis + "/" + mVideoView.getDuration());
        mVideoView.seekTo(mVideoView.getCurrentPosition() + 500 * (long) dis);
    }

    private void fast_ForWord(float dis) {
        long currentProgress;
        long duration = mVideoView.getDuration();
        if (mVideoView.getCurrentPosition() + 500 * (long) dis < 0)
            currentProgress = 0;
        else
            currentProgress = mVideoView.getCurrentPosition() + 500 * (long) dis;
//		mTv_progress.setText(Utils.generateTime(currentProgress) + "/" + Utils.generateTime(duration));
        if (dis > 0)
            mIv_Progress_bg.setImageResource(R.drawable.btn_fast_forword);
        else
            mIv_Progress_bg.setImageResource(R.drawable.btn_back_forword);
        mFl_Progress.setVisibility(View.VISIBLE);
    }

    /**
     * 滑动改变声音大小
     *
     * @param percent
     */
    private void onVolumeSlide(float percent) {
        isUp_downScroll = true;
        if (mVolume == -1) {
            mVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            if (mVolume < 0)
                mVolume = 0;

            // 显示
//            mOperationBg.setImageResource(R.drawable.video_volumn_bg);
            mVolumeBrightnessLayout.setVisibility(View.VISIBLE);
        }

        int index = (int) (percent * mMaxVolume) + mVolume;
        if (index > mMaxVolume)
            index = mMaxVolume;
        else if (index < 0)
            index = 0;

        // 变更声音
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, index, 0);

        // 变更进度条
        ViewGroup.LayoutParams lp = mOperationPercent.getLayoutParams();
        lp.width = findViewById(R.id.operation_full).getLayoutParams().width * index / mMaxVolume;
        mOperationPercent.setLayoutParams(lp);
    }

    /**
     * 滑动改变亮度
     *
     * @param percent
     */
    private void onBrightnessSlide(float percent) {
        isUp_downScroll = true;
        if (mBrightness < 0) {
            mBrightness = getWindow().getAttributes().screenBrightness;
            if (mBrightness <= 0.00f)
                mBrightness = 0.50f;
            if (mBrightness < 0.01f)
                mBrightness = 0.01f;

            // 显示
//            mOperationBg.setImageResource(R.drawable.video_brightness_bg);
            mVolumeBrightnessLayout.setVisibility(View.VISIBLE);
        }
        WindowManager.LayoutParams lpa = getWindow().getAttributes();
        lpa.screenBrightness = mBrightness + percent;
        if (lpa.screenBrightness > 1.0f)
            lpa.screenBrightness = 1.0f;
        else if (lpa.screenBrightness < 0.01f)
            lpa.screenBrightness = 0.01f;
        getWindow().setAttributes(lpa);

        ViewGroup.LayoutParams lp = mOperationPercent.getLayoutParams();
        lp.width = (int) (findViewById(R.id.operation_full).getLayoutParams().width * lpa.screenBrightness);
        mOperationPercent.setLayoutParams(lp);
    }

    private void stopPlayer() {
        if (mVideoView != null)
            mVideoView.pause();
    }

    private void startPlayer() {
        if (mVideoView != null)
            mVideoView.start();
    }

    private boolean isPlaying() {
        return mVideoView != null && mVideoView.isPlaying();
    }

    @Override
    public boolean onInfo(MediaPlayer arg0, int arg1, int arg2) {
        switch (arg1) {
            case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                // �?��缓存，暂停播�?
                if (isPlaying()) {
                    stopPlayer();
                    needResume = true;
                }
                mLoadingView.setVisibility(View.VISIBLE);
                break;
            case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                // 缓存完成，继续播�?
                if (needResume) {
                    startPlayer();
                }
                mLoadingView.setVisibility(View.GONE);
                break;
            case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                // 显示 下载速度
                break;
        }
        return true;
    }

    /**
     * 播放完成
     */
    @Override
    public void onCompletion(MediaPlayer arg0) {
    }

    /**
     * //在视频预处理完成后调用。在视频预处理完成后被调用。此时视频的宽度、高度、宽高比信息已经获取到，此时可调用seekTo让视频从指定位置开始播放。
     */
    @Override
    public void onPrepared(MediaPlayer arg0) {
        startPlayer();
    }

    /**
     * 在异步操作调用过程中发生错误时调用。例如视频打开失败。
     */
    @Override
    public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
        mLoadingView.setVisibility(View.GONE);
        mTv_NoPlay.setVisibility(View.VISIBLE);
        return false;
    }

    /**
     * 在网络视频流缓冲变化时调用。
     *
     * @param arg0
     * @param arg1
     */
    @Override
    public void onBufferingUpdate(MediaPlayer arg0, int arg1) {
        mTv_NoPlay.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.VISIBLE);
    }

    /**
     * 在seek操作完成后调用。
     */
    @Override
    public void onSeekComplete(MediaPlayer arg0) {
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub

    }

    private class MyGestureListener extends SimpleOnGestureListener {

        /**
         * 双击
         */
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            if (mLayout == VideoView.VIDEO_LAYOUT_ZOOM)
                mLayout = VideoView.VIDEO_LAYOUT_ORIGIN;
            else
                mLayout++;
            if (mVideoView != null)
                mVideoView.setVideoLayout(mLayout, 0);
            return true;
        }

        /**
         * 滑动
         */
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            mMediaController.hide();
            float mOldX = e1.getX(), mOldY = e1.getY();
            int y = (int) e2.getRawY();
            int x = (int) e2.getRawX();
            Display disp = getWindowManager().getDefaultDisplay();
            int windowWidth = disp.getWidth();
            int windowHeight = disp.getHeight();

            if (Math.abs(x - mOldX) > 20 && !isUp_downScroll) { //执行快进快退
                isFast_Forword = true;
                mFast_forward = x - mOldX;
                fast_ForWord(mFast_forward);
            } else if (mOldX > windowWidth * 1.0 / 2 && Math.abs(mOldY - y) > 3 && !isFast_Forword)// 右边滑动
                onVolumeSlide((mOldY - y) / windowHeight);
            else if (mOldX < windowWidth / 2.0 && Math.abs(mOldY - y) > 3 && !isFast_Forword)// 左边滑动
                onBrightnessSlide((mOldY - y) / windowHeight);
            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }

}
