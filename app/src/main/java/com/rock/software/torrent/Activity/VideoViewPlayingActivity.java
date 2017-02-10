package com.rock.software.torrent.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.Process;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.cyberplayer.core.BVideoView;
import com.baidu.cyberplayer.core.BVideoView.OnCompletionListener;
import com.baidu.cyberplayer.core.BVideoView.OnErrorListener;
import com.baidu.cyberplayer.core.BVideoView.OnInfoListener;
import com.baidu.cyberplayer.core.BVideoView.OnPlayingBufferCacheListener;
import com.baidu.cyberplayer.core.BVideoView.OnPreparedListener;
import com.baidu.cyberplayer.dlna.DLNADeviceType;
import com.baidu.cyberplayer.dlna.DLNAProviderFactory;
import com.baidu.cyberplayer.dlna.IDLNAServiceProvider;
import com.rock.software.torrent.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class VideoViewPlayingActivity extends Activity implements OnPreparedListener, OnCompletionListener,
		OnErrorListener, OnInfoListener, OnPlayingBufferCacheListener,IDLNAServiceProvider.IEnableDLNACallBack,IDLNAServiceProvider.IDisableDLNACallBack
	{
	
	private final String TAG = "VideoViewPlayingActivity";
		
	/**
     * 您的AK 
     * 请到http://console.bce.baidu.com/iam/#/iam/accesslist获取
     */
    private String AK = "bec733aae6e24748b9d917329e9abaff";   //请录入您的AK !!!
	
	private String mVideoSource = null;

	private ImageButton mPlaybtn = null;
	private ImageButton mPrebtn = null;
	private ImageButton mForwardbtn = null;
	private TextView dlna;
	private LinearLayout mController = null;
	
	private SeekBar mProgress = null;
	private TextView mDuration = null;
	private TextView mCurrPostion = null;
	
	/**
	 * 记录播放位置
	 */
	private int mLastPos = 0;

		@Override
		public void onEnableDLNA(boolean b, int i, String s) {

		}

		@Override
		public void onDisableDLNA(boolean b, int i, String s) {

		}

		/**
	 * 播放状态
	 */
	private  enum PLAYER_STATUS {
		PLAYER_IDLE, PLAYER_PREPARING, PLAYER_PREPARED,
	}
	
	private PLAYER_STATUS mPlayerStatus = PLAYER_STATUS.PLAYER_IDLE;
	
	private BVideoView mVV = null;
	
	private EventHandler mEventHandler;
	private HandlerThread mHandlerThread;
	
	private final Object SYNC_Playing = new Object();
	
	private WakeLock mWakeLock = null;
	private static final String POWER_LOCK = "VideoViewPlayingActivity";
	
	private boolean mIsHwDecode = false;
	
	private final int EVENT_PLAY = 0;
	private final int UI_EVENT_UPDATE_CURRPOSITION = 1;
			
	class EventHandler extends Handler {
		public EventHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case EVENT_PLAY:
				/**
				 * 如果已经播放了，等待上一次播放结束
				 */
				if (mPlayerStatus != PLAYER_STATUS.PLAYER_IDLE) {
					synchronized (SYNC_Playing) {
						try {
							SYNC_Playing.wait();
//							Log.v(TAG, "wait player status to idle");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				/**
				 * 设置播放url
				 */
				mVV.setVideoPath(mVideoSource);
				
				/**
				 * 续播，如果需要如此
				 */
				if (mLastPos > 0) {

					mVV.seekTo(mLastPos);
					mLastPos = 0;
				}

				/**
				 * 显示或者隐藏缓冲提示 
				 */
				mVV.showCacheInfo(true);
				
				/**
				 * 开始播放
				 */
				mVV.start();

				mPlayerStatus = PLAYER_STATUS.PLAYER_PREPARING;
				break;
			default:
				break;
			}
		}
	}

    Handler mUIHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
            /**
            * 更新进度及时间
            */
            case UI_EVENT_UPDATE_CURRPOSITION:
                int currPosition = mVV.getCurrentPosition();
                int duration = mVV.getDuration();
                updateTextViewWithTimeFormat(mCurrPostion, currPosition);
                updateTextViewWithTimeFormat(mDuration, duration);
                mProgress.setMax(duration);
                if (mVV.isPlaying()){
                    mProgress.setProgress(currPosition);
                }
                mUIHandler.sendEmptyMessageDelayed(UI_EVENT_UPDATE_CURRPOSITION, 200);
                break;
            default:
                break;
            }
        }
    };

		private static final int REQUEST_CODE_WRITE_SETTINGS = 1;
		private void requestWriteSettings() {
			Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
			intent.setData(Uri.parse("package:" + getPackageName()));
			startActivityForResult(intent, REQUEST_CODE_WRITE_SETTINGS );
		}

		@RequiresApi(api = Build.VERSION_CODES.M)
		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			super.onActivityResult(requestCode, resultCode, data);
			if (requestCode == REQUEST_CODE_WRITE_SETTINGS) {
				if (Settings.System.canWrite(this)) {
					DLNAControlActivity.serviceProvider.initialize("AK", "bec733aae6e24748b9d917329e9abaff");
			//		DLNAControlActivity.serviceProvider.initialize("Ak","bec733aae6e24748b9d917329e9abaff");
//		DLNAControlActivity.serviceProvider.initialize(AK, "cd3675490e74463e81c976c272238d81");
//		DLNAControlActivity.serviceProvider.addActionCallBack(mActionListener);
					DLNAControlActivity.serviceProvider.enableDLNA(VideoViewPlayingActivity.this);
				}
			}
		}



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setBrightnessMode(this,0);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			if(Settings.System.canWrite(this)) {
                DLNAControlActivity.serviceProvider = DLNAProviderFactory.getProviderInstance(this);
                if (DLNAControlActivity.serviceProvider == null)
                    finish();

			//	requestWriteSettings();
				DLNAControlActivity.serviceProvider.initialize("AK","bec733aae6e24748b9d917329e9abaff");
//		DLNAControlActivity.serviceProvider.initialize(AK, "cd3675490e74463e81c976c272238d81");
//		DLNAControlActivity.serviceProvider.addActionCallBack(mActionListener);
				DLNAControlActivity.serviceProvider.enableDLNA(VideoViewPlayingActivity.this);
            }
		}else{
			DLNAControlActivity.serviceProvider = DLNAProviderFactory.getProviderInstance(this);
			if (DLNAControlActivity.serviceProvider == null)
				finish();
			DLNAControlActivity.serviceProvider.initialize("AK", "bec733aae6e24748b9d917329e9abaff");
//		DLNAControlActivity.serviceProvider.initialize(AK, "cd3675490e74463e81c976c272238d81");
//		DLNAControlActivity.serviceProvider.addActionCallBack(mActionListener);
			DLNAControlActivity.serviceProvider.enableDLNA(VideoViewPlayingActivity.this);
		}
//		if (ContextCompat.checkSelfPermission(VideoViewPlayingActivity.this,
//				Manifest.permission.WRITE_SETTINGS)
//				!= PackageManager.PERMISSION_GRANTED) {
//			DLNAControlActivity.serviceProvider.initialize(AK, "bec733aae6e24748b9d917329e9abaff");
//		}else{
//			//
//			ActivityCompat.requestPermissions(VideoViewPlayingActivity.this,
//					new String[]{Manifest.permission.WRITE_SETTINGS},
//					MY_PERMISSIONS_REQUEST_READ_CONTACTS);
//		}


		setContentView(R.layout.controllerplaying);
		
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		mWakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, POWER_LOCK);
		
		mIsHwDecode = getIntent().getBooleanExtra("isHW", false);
//		Uri uriPath = getIntent().getData();
//		if (null != uriPath) {
//			String scheme = uriPath.getScheme();
//			if (null != scheme) {
//				mVideoSource = uriPath.toString();
//			} else {
//				mVideoSource = uriPath.getPath();
//			}
//		}
		mVideoSource = getIntent().getStringExtra("url");
		initUI();
		
		/**
		 * 开启后台事件处理线程
		 */
		mHandlerThread = new HandlerThread("event handler thread",
				Process.THREAD_PRIORITY_BACKGROUND);
		mHandlerThread.start();
		mEventHandler = new EventHandler(mHandlerThread.getLooper());
		
	}

		public final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 10011;
		@Override
		public void onRequestPermissionsResult(int requestCode,
											   String permissions[], int[] grantResults) {
			switch (requestCode) {
				case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
					// If request is cancelled, the result arrays are empty.
					if (grantResults.length > 0
							&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {
						DLNAControlActivity.serviceProvider.initialize("AK", "cd3675490e74463e81c976c272238d81");
						// permission was granted, yay! Do the
						// contacts-related task you need to do.

					} else {

						// permission denied, boo! Disable the
						// functionality that depends on this permission.
					}
					return;
				}
			}
		}

	/**
	 * 初始化界面
	 */
	private void initUI() {
		mPlaybtn = (ImageButton)findViewById(R.id.play_btn);
		mPrebtn = (ImageButton)findViewById(R.id.pre_btn);
		mForwardbtn = (ImageButton)findViewById(R.id.next_btn);		
		mController = (LinearLayout)findViewById(R.id.controlbar);
		
		mProgress = (SeekBar)findViewById(R.id.media_progress);
		mDuration = (TextView)findViewById(R.id.time_total);
		mCurrPostion = (TextView)findViewById(R.id.time_current);
		dlna = (TextView) findViewById(R.id.dlna);
		dlna.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				getRenderList();
			}
		});
		registerCallbackForControl();
		
		/**
		 * 设置ak
		 */
		BVideoView.setAK(AK);
		
		/**
		 *获取BVideoView对象
		 */
		mVV = (BVideoView) findViewById(R.id.video_view);
		
		/**
		 * 注册listener
		 */
		mVV.setOnPreparedListener(this);
		mVV.setOnCompletionListener(this);
		mVV.setOnErrorListener(this);
		mVV.setOnInfoListener(this);
		
		/**
		 * 设置解码模式
		 */
		mVV.setDecodeMode(mIsHwDecode? BVideoView.DECODE_HW: BVideoView.DECODE_SW);
	}


		private final int UPDATE_INFO = 0;

		private final int GET_RENDER_SUC = 103;
		private final int GET_RENDER_FAIL = 104;
		private final int ENABLE_SUC = 105;
		private final int ENABLE_FAIL = 106;

		private Handler uiHandler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {

					case ENABLE_SUC:

						break;
					case ENABLE_FAIL:

						break;
					case GET_RENDER_SUC:


						showRenderList();
						break;
					case GET_RENDER_FAIL:


						Toast.makeText(VideoViewPlayingActivity.this, "null renders",
								Toast.LENGTH_LONG).show();
						break;

					default:
						break;
				}
			}
		};
		// 渲染出的设备列表
		private Map<String,String> dlnarenderList;

		List<String> renderList = null;
		// 获取渲染出的render设备列表
		public Map<String,String> dlnaGetRenderList(){
			Map<String,String> dlnaRenderList = null;
			if(DLNAControlActivity.serviceProvider != null){
				dlnaRenderList = DLNAControlActivity.serviceProvider.getDeviceMap(DLNADeviceType.MEDIA_RENDERER);
			}
			return dlnaRenderList;
		}
		private void getRenderList() {
			dlnarenderList = dlnaGetRenderList();
			if (dlnarenderList == null) {
				uiHandler.sendEmptyMessage(GET_RENDER_FAIL);
				return;
			}
			uiHandler.sendEmptyMessage(GET_RENDER_SUC);
			if ((dlnarenderList != null)&&(dlnarenderList.size()>0)) {

			}
			renderList = new ArrayList<String>();
			Iterator<String> it = dlnarenderList.keySet().iterator();

			final String[] renderItems = new String[dlnarenderList.size()];//(String[]) mDlnaRenderList.toArray(new String[mDlnaRenderList.size()]);
			int i = 0;
			while(it.hasNext()){
				String key = it.next();
				renderItems[i] = dlnarenderList.get(key);
				renderList.add(renderItems[i]);
				i++;
			}
		}

		private AlertDialog selectDialog;

		private void showRenderList() {
			ListView listView = new ListView(VideoViewPlayingActivity.this);
			listView.setAdapter(new ArrayAdapter<String>(VideoViewPlayingActivity.this,
					android.R.layout.simple_list_item_1, renderList));
			listView.setBackgroundColor(Color.WHITE);
			listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
										long arg3) {
					// TODO Auto-generated method stub
					selectDialog.dismiss();
					String selectDevice = renderList.get(arg2);
					String playUrl = mVideoSource;

					Intent intent = new Intent(VideoViewPlayingActivity.this,
							DLNAControlActivity.class);
					intent.putExtra("device", selectDevice);
					intent.putExtra("url", playUrl);
					VideoViewPlayingActivity.this.startActivity(intent);
					finish();
				}
			});
			selectDialog = new AlertDialog.Builder(VideoViewPlayingActivity.this)
					.setView(listView).setCancelable(true).create();
			selectDialog.show();
		}




		/**
	 * 为控件注册回调处理函数
	 */
	private void registerCallbackForControl(){
		mPlaybtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if (mVV.isPlaying()) {
					mPlaybtn.setImageResource(R.drawable.play_btn_style);
					/**
					 * 暂停播放
					 */
					mVV.pause();	
				} else {
					mPlaybtn.setImageResource(R.drawable.pause_btn_style);
					/**
					 * 继续播放
					 */
					mVV.resume();					
				}
				
			}
		});
			
		/**
		 * 实现切换示例
		 */
		mPrebtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				/**
				 * 如果已经播放了，等待上一次播放结束
				 */
				if(mPlayerStatus != PLAYER_STATUS.PLAYER_IDLE){
					mVV.stopPlayback();
				}
				
				/**
				 * 发起一次新的播放任务
				 */
				if(mEventHandler.hasMessages(EVENT_PLAY))
					mEventHandler.removeMessages(EVENT_PLAY);
				mEventHandler.sendEmptyMessage(EVENT_PLAY);
			}
		});

		
		mForwardbtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub				

			}
		});
		
		OnSeekBarChangeListener osbc1 = new OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				//Log.v(TAG, "progress: " + progress);
				updateTextViewWithTimeFormat(mCurrPostion, progress);
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				/**
				 * SeekBar开始seek时停止更新
				 */
				mUIHandler.removeMessages(UI_EVENT_UPDATE_CURRPOSITION);
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				int iseekPos = seekBar.getProgress();
				/**
				 * SeekBark完成seek时执行seekTo操作并更新界面
				 *
				 */
				mVV.seekTo(iseekPos);
//				Log.v(TAG, "seek to " + iseekPos);
				mUIHandler.sendEmptyMessage(UI_EVENT_UPDATE_CURRPOSITION);
			}
		};
		mProgress.setOnSeekBarChangeListener(osbc1);
	}

	private void updateTextViewWithTimeFormat(TextView view, int second){
		int hh = second / 3600;
		int mm = second % 3600 / 60;
		int ss = second % 60;
		String strTemp = null;
		if (0 != hh) {
			strTemp = String.format("%02d:%02d:%02d", hh, mm, ss);
		} else {
			strTemp = String.format("%02d:%02d", mm, ss);
		}
		view.setText(strTemp);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		/**
		 * 在停止播放前 你可以先记录当前播放的位置,以便以后可以续播
		 */
		if (mPlayerStatus == PLAYER_STATUS.PLAYER_PREPARED) {
			mLastPos = mVV.getCurrentPosition();
			mVV.stopPlayback();
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
//		Log.v(TAG, "onResume");
		if (null != mWakeLock && (!mWakeLock.isHeld())) {
			mWakeLock.acquire();
		}
		/**
		 * 发起一次播放任务,当然您不一定要在这发起
		 */
		mEventHandler.sendEmptyMessage(EVENT_PLAY);	
	}
	
	private long mTouchTime;
	private boolean barShow = true;

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		// TODO Auto-generated method stub
		if (event.getAction() == MotionEvent.ACTION_DOWN)
			mTouchTime = System.currentTimeMillis();
		else if (event.getAction() == MotionEvent.ACTION_UP) {
			long time = System.currentTimeMillis() - mTouchTime;
			if (time < 400) {
				updateControlBar(!barShow);
			}
		}

		return true;
	}

	public void updateControlBar(boolean show) {

		if (show) {
			mController.setVisibility(View.VISIBLE);
		} else {
			mController.setVisibility(View.INVISIBLE);
		}
		barShow = show;
	}
	
	@Override
	protected void onStop(){
		super.onStop();
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		/**
		 * 退出后台事件处理线程
		 */
		mHandlerThread.quit();
		if (DLNAControlActivity.serviceProvider != null)
			DLNAControlActivity.serviceProvider.disableDLNA(this);
	}

	@Override
	public boolean onInfo(int what, int extra) {
		// TODO Auto-generated method stub
		switch(what){
		/**
		 * 开始缓冲
		 */
		case BVideoView.MEDIA_INFO_BUFFERING_START:
			break;
		/**
		 * 结束缓冲
		 */
		case BVideoView.MEDIA_INFO_BUFFERING_END:
			break;
		default:
			break;
		}
		return true;
	}
	
	/**
	 * 当前缓冲的百分比， 可以配合onInfo中的开始缓冲和结束缓冲来显示百分比到界面
	 */
	@Override
	public void onPlayingBufferCache(int percent) {
		// TODO Auto-generated method stub
		
	}	
	
	/**
	 * 播放出错
	 */
	@Override
	public boolean onError(int what, int extra) {
		// TODO Auto-generated method stub
//		Log.v(TAG, "onError");
		synchronized (SYNC_Playing) {
			SYNC_Playing.notify();
		}
		mPlayerStatus = PLAYER_STATUS.PLAYER_IDLE;
		mUIHandler.removeMessages(UI_EVENT_UPDATE_CURRPOSITION);
		return true;
	}

	/**
	 * 播放完成
	 */
	@Override
	public void onCompletion() {
		// TODO Auto-generated method stub
//		Log.v(TAG, "onCompletion");
		synchronized (SYNC_Playing) {
			SYNC_Playing.notify();
		}
		mPlayerStatus = PLAYER_STATUS.PLAYER_IDLE;
		mUIHandler.removeMessages(UI_EVENT_UPDATE_CURRPOSITION);
	}

	/**
	 * 准备播放就绪
	 */
	@Override
	public void onPrepared() {
		// TODO Auto-generated method stub
//		Log.v(TAG, "onPrepared");
		mPlayerStatus = PLAYER_STATUS.PLAYER_PREPARED;
		mUIHandler.sendEmptyMessage(UI_EVENT_UPDATE_CURRPOSITION);
	}
}
