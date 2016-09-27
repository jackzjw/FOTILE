package com.example.sg280.fotile.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.app.Constants;
import com.example.sg280.fotile.model.bean.MySelfInfo;
import com.example.sg280.fotile.model.bean.VedioDetailsBean;
import com.example.sg280.fotile.presents.Interface.IIMContacts;
import com.example.sg280.fotile.presents.Interface.ILiveContacts;
import com.example.sg280.fotile.presents.LivePresent;
import com.example.sg280.fotile.presents.MessagePresent;
import com.example.sg280.fotile.ui.fragment.IntroduceFragment;
import com.example.sg280.fotile.ui.fragment.MessageFragment;
import com.example.sg280.fotile.ui.fragment.SeeAndBuyFragment;
import com.example.sg280.fotile.utils.DensityUtil;
import com.example.sg280.fotile.utils.LogUtil;
import com.example.sg280.fotile.utils.LoginUtil;
import com.example.sg280.fotile.utils.ToastUtil;
import com.example.sg280.fotile.widget.BarrageView;
import com.tencent.TIMMessage;
import com.tencent.TIMTextElem;
import com.tencent.rtmp.ITXLivePlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;

import java.io.UnsupportedEncodingException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.http.HEAD;


/**
 * Created by sg280 on 2016-08-02.
 */

public class LiveActivity extends BaseFragmentActivity implements ITXLivePlayListener,ILiveContacts.View,IIMContacts.View{
    private  static final String LIVE_ID="liveid";
    private static final String TAG = "LiveActivity";
    private  static final String TYPE="activity_type";
    private boolean isInitIM=false;
    static final int LIVE_ACTIVITY=1;
    static final int VEDIO_ACTIVTY=2;
    private static final int VEDIOPLAYER_HEIGHT=220;
    private int ACTIVITY_TYPE=0;
    public static String liveid;
    private LivePresent present;
    private TXLivePlayer livePlayer=null;
    @Bind(R.id.video_view)
     TXCloudVideoView mPlayerView;
   @Bind(R.id.ll_portait)
   LinearLayout ll_portait;
   @Bind(R.id.fr_vedioplay)
    FrameLayout fr_vedioplay;
    @Bind(R.id.img_loading)
    ImageView mLoadingView;
    @Bind(R.id.play_start)
    TextView mTextStart;
    @Bind(R.id.seekbar)
    SeekBar mSeekBar;
    @Bind(R.id.duration)
    TextView mTextDuration;
    @Bind(R.id.img_barrage)
    ImageView img_barrage;
    @Bind(R.id.btn_play)
    Button mBtnPlay;
    @Bind(android.R.id.tabs)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.ll_paly_menus)
    LinearLayout ll_play_menus;
    @Bind(R.id.rel_input)
    RelativeLayout rel_input;
    @Bind(R.id.et_input)
    EditText et_input;
    @Bind(R.id.img_screen)
    ImageView img_screen;
    @Bind(R.id.img_public)
    ImageView img_public;
    @Bind(R.id.rel_topmenu)
    RelativeLayout rel_topmenu;
    @Bind(R.id.rel_bottommenu)
    RelativeLayout rel_bottommenu;
    private int mCurrentRenderRotation;
    private boolean islandscape;
    private long  mTrackingTouchTS = 0;
    private boolean  mStartSeek = false;
    private boolean  mVideoPause = false;
    private boolean mVideoPlay=false;
    private boolean isOnPause = false;
    private int mPlayType;
    private LiveListener mliveListener;
    private MessagePresent impresent;
    private InputMethodManager imm;
    private String url;
    private Handler handler = new Handler();

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_live);
        ButterKnife.bind(this);
        if (getIntent().getStringExtra(LIVE_ID) != null) {
            liveid=getIntent().getStringExtra(LIVE_ID);
            ACTIVITY_TYPE=getIntent().getIntExtra(TYPE,0);
        }
        initView();
        present=new LivePresent(this,this);
        impresent=new MessagePresent(this,this);
        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if (ACTIVITY_TYPE==LIVE_ACTIVITY&&MySelfInfo.getInstance().islogin()) {
            impresent.joinIMChatRoom(Constants.AV_CHATROOM_ID);
            isInitIM=true;
        }
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        setListener();
        present.getLiveView("GetLiveView",liveid);

    }
    //隐藏顶部菜单栏
      Runnable   hideRunable=new Runnable(){

          @Override
          public void run() {
              if(rel_topmenu.isShown()&&rel_bottommenu.isShown()){
                  rel_bottommenu.setVisibility(View.GONE);
                  rel_topmenu.setVisibility(View.GONE);
              }
          }
      };


    private void initView() {
     handler.postDelayed(hideRunable,5000);
        //判断是从直播跳转过来还是点播
        //直播隐藏播放、进度条、时间和时长
        if(ACTIVITY_TYPE==LIVE_ACTIVITY){
            ll_play_menus.setVisibility(View.GONE);
        }
    }
    //从其他页面跳到此页面的Intent
    public static Intent newIntent(Context context,int type,String id){
        Intent newintent=new Intent(context,LiveActivity.class);
        newintent.putExtra(TYPE, type);
        newintent.putExtra(LIVE_ID,id);
        return newintent;
    }

    private void setListener() {
        mPlayerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rel_topmenu.isShown()&&rel_bottommenu.isShown()){
                    rel_bottommenu.setVisibility(View.GONE);
                    rel_topmenu.setVisibility(View.GONE);
                    handler.removeCallbacks(hideRunable);
                }else {
                    rel_bottommenu.setVisibility(View.VISIBLE);
                    rel_topmenu.setVisibility(View.VISIBLE);
                    handler.postDelayed(hideRunable, 5000);
                }
            }
        });
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTextStart.setText(String.format("%02d:%02d", progress / 60, progress % 60));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mStartSeek = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (livePlayer != null) {
                    livePlayer.seek(seekBar.getProgress());
                }
                mTrackingTouchTS = System.currentTimeMillis();
                mStartSeek = false;
            }
        });

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        MessageFragment msgfrg = new MessageFragment();
                        mliveListener = (LiveListener) msgfrg;
                        //显示或隐藏评论框
                        mliveListener.setActivityType(ACTIVITY_TYPE);
                        return msgfrg;
                    case 1:
                        return new IntroduceFragment();
                    case 2:
                        return new SeeAndBuyFragment();
                    default:
                        return new MessageFragment();
                }
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "消息";
                    case 1:
                        return "介绍";
                    case 2:
                        return "边看边买";
                    default:
                        return "消息";
                }

            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }
    //服务器接口返回回调
    @Override
    public void getLiveViewSucc(VedioDetailsBean data) {
        //1 视频 2显示图片
      //  if("1".equals(data.getLivePixType())){
            url=data.getURL();
            if (!checkPlayUrl(url)) {
                return ;
            }


            if (livePlayer == null) {
                livePlayer=new TXLivePlayer(this);
            }
            if(startPlayRtmp()){
                mVideoPlay=!mVideoPlay;
            }
       /* }else{
            img_public.setVisibility(View.VISIBLE);
            fr_vedioplay.setVisibility(View.GONE);
            Glides.getInstance().load(this,data.getLivePixSer(),img_public);
        }*/

    }
  //IM返回的弹幕
    @Override
    public void refreshText(String msg, String name) {


            final ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


            Runnable createBarrageView = new Runnable() {
                @Override
                public void run() {
                    if (!isOnPause) {
                       // Log.e("azzz", "发送弹幕");
                        //新建一条弹幕，并设置文字
                        final BarrageView barrageView = new BarrageView(LiveActivity.this);
                        barrageView.setText(msg); //随机设置文字
                        addContentView(barrageView, lp);
                    }

                }
            };
            handler.post(createBarrageView);
            et_input.setText("");
    }

    //判断用户是从点播列表跳转还是直播列表跳转，直播则显示发评论，点播不显示
    public interface LiveListener{
        void setActivityType(int type);
        void quitIM();
    }
    //播放、暂停
    @OnClick(R.id.btn_play)
    void playOrpause(){
        if (mVideoPlay) {
            if (mPlayType == TXLivePlayer.PLAY_TYPE_VOD_FLV || mPlayType == TXLivePlayer.PLAY_TYPE_VOD_HLS || mPlayType == TXLivePlayer.PLAY_TYPE_VOD_MP4) {
                if (mVideoPause) {
                    livePlayer.resume();
                    mBtnPlay.setBackgroundResource(R.drawable.play_pause);
                } else {
                    livePlayer.pause();
                    mBtnPlay.setBackgroundResource(R.drawable.play_start);
                }
                mVideoPause = !mVideoPause;

            } else {
                stopPlayRtmp();
                mVideoPlay = !mVideoPlay;
            }

        } else {
            if (startPlayRtmp()) {
                mVideoPlay = !mVideoPlay;
            }
        }


    }
    //播放视频
 private boolean startPlayRtmp(){
     //http://3800.liveplay.myqcloud.com/live/3800_992e6e955fc311e6a2cba4dcbef5e35a.flv
     //http://2527.vod.myqcloud.com/2527_117134a2343111e5b8f5bdca6cb9f38c.f20.mp4




     mBtnPlay.setBackgroundResource(R.drawable.play_pause);

     livePlayer.setPlayerView(mPlayerView);
     livePlayer.setPlayListener(this);
     mCurrentRenderRotation = TXLiveConstants.RENDER_ROTATION_PORTRAIT;
     //开启硬件加速
     livePlayer.enableHardwareDecode(true);
     livePlayer.setRenderRotation(mCurrentRenderRotation);
     livePlayer.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN);
     int result = livePlayer.startPlay(url,mPlayType); // result返回值：0 success;  -1 empty url; -2 invalid url; -3 invalid playType;
     LogUtil.e("播放结果result="+result);
     if (result == -2) {
         Toast.makeText(this, "非腾讯云链接地址，若要放开限制，请联系腾讯云商务团队", Toast.LENGTH_SHORT).show();
     }
     if (result != 0) {
         mBtnPlay.setBackgroundResource(R.drawable.play_start);
         return false;
     }
     startLoadingAnimation();

     return true;

 }
    private boolean checkPlayUrl(final String playUrl) {
        if (TextUtils.isEmpty(playUrl) || (!playUrl.startsWith("http://") && !playUrl.startsWith("https://") && !playUrl.startsWith("rtmp://"))) {
            Toast.makeText(getApplicationContext(), "播放地址不合法，目前仅支持rtmp,flv,hls,mp4播放方式!", Toast.LENGTH_SHORT).show();
           finish();
            return false;
        }
         switch (ACTIVITY_TYPE){
             case LIVE_ACTIVITY:
             {
                 if (playUrl.startsWith("rtmp://")) {
                     mPlayType = TXLivePlayer.PLAY_TYPE_LIVE_RTMP;
                 } else if ((playUrl.startsWith("http://") || playUrl.startsWith("https://"))&&( playUrl.contains(".flv")||playUrl.contains(".mp4"))) {
                     mPlayType = TXLivePlayer.PLAY_TYPE_LIVE_FLV;
                 } else {
                     Toast.makeText(getApplicationContext(), "播放地址不合法，直播目前仅支持rtmp,flv播放方式!", Toast.LENGTH_SHORT).show();
                     finish();
                     return false;
                 }
             }
             break;
             case VEDIO_ACTIVTY:
             {
                 if (playUrl.startsWith("http://") || playUrl.startsWith("https://")) {
                     if (playUrl.contains(".flv")) {
                         mPlayType = TXLivePlayer.PLAY_TYPE_VOD_FLV;
                     } else if (playUrl.contains(".m3u8")) {
                         mPlayType = TXLivePlayer.PLAY_TYPE_VOD_HLS;
                     } else if (playUrl.toLowerCase().contains(".mp4")) {
                         mPlayType = TXLivePlayer.PLAY_TYPE_VOD_MP4;
                     } else {
                         Toast.makeText(getApplicationContext(), "播放地址不合法，点播目前仅支持flv,hls,mp4播放方式!", Toast.LENGTH_SHORT).show();
                         finish();
                         return false;
                     }
                 } else {
                     Toast.makeText(getApplicationContext(), "播放地址不合法，点播目前仅支持flv,hls,mp4播放方式!", Toast.LENGTH_SHORT).show();
                     finish();
                     return false;
                 }
             }
             break;
             default:
                 Toast.makeText(getApplicationContext(), "播放地址不合法，目前仅支持rtmp,flv,hls,mp4播放方式!", Toast.LENGTH_SHORT).show();
                 finish();
                 return false;
         }

        return true;

    }
    //横竖屏切换
   @OnClick(R.id.img_screen)
     void switcScreen(){
       rel_bottommenu.setVisibility(View.VISIBLE);
       rel_topmenu.setVisibility(View.VISIBLE);
       handler.postDelayed(hideRunable,5000);
       if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
           setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
           mCurrentRenderRotation=TXLiveConstants.RENDER_ROTATION_LANDSCAPE;
           livePlayer.setRenderRotation(0);
           impresent.initTIMListener(Constants.AV_CHATROOM_ID);//添加IM监听器
           islandscape=true;

       }else{
          setPortait();
       }

}
    private void setPortait(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mCurrentRenderRotation=TXLiveConstants.RENDER_ROTATION_PORTRAIT;
        livePlayer.setRenderRotation(mCurrentRenderRotation);
        impresent.notifyQuitReady();//注销IM监听器
        islandscape=false;
    }
    //发弹幕
    @OnClick(R.id.tv_sendMsg)
    void sendBarrage(){
        LoginUtil.checkLogin(this, new LoginUtil.LoginCallback() {
            @Override
            public void onLogin() {
                if (et_input.getText().length() > 0) {
                    if (!isInitIM) {
                        impresent.joinIMChatRoom(Constants.AV_CHATROOM_ID);
                    }
                    sendText(et_input.getText().toString());
                    imm.showSoftInput(et_input, InputMethodManager.SHOW_FORCED);
                    imm.hideSoftInputFromWindow(et_input.getWindowToken(), 0);
                } else {
                    ToastUtil.showShort(LiveActivity.this, "输入消息不能为空");
                    return;
                }
            }
        });
    }
    private void sendText(String msg) {
        if (msg.length() == 0)
            return;
        try {
            byte[] byte_num = msg.getBytes("utf8");
            if (byte_num.length > 160) {
                Toast.makeText(this, "输入消息太长", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }
        TIMMessage Nmsg = new TIMMessage();
        TIMTextElem elem = new TIMTextElem();
        elem.setText(msg);
        if (Nmsg.addElement(elem) != 0) {
            return;
        }
        impresent.sendGroupText(Nmsg);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogUtil.e("onconfiguration");
        if(islandscape){
            //直播显示发弹幕，点播不显示
            if(ACTIVITY_TYPE==LIVE_ACTIVITY) {
                rel_input.setVisibility(View.VISIBLE);
            }
            img_barrage.setVisibility(View.VISIBLE);
            img_screen.setImageResource(R.drawable.live_icon_screen_vertical_white);
            ll_portait.setVisibility(View.GONE);
            fr_vedioplay.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }else {
            rel_input.setVisibility(View.GONE);
            img_barrage.setVisibility(View.GONE);
            img_screen.setImageResource(R.drawable.live_icon_screen_cross_white);
            fr_vedioplay.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dp2px(this,VEDIOPLAYER_HEIGHT)));
            ll_portait.setVisibility(View.VISIBLE);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();

        isOnPause=false;
        if (mVideoPlay && !mVideoPause) {
            //退到后台重新回来继续播放，这里加延迟目的：让上一次的关闭操作执行完成
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mPlayType == TXLivePlayer.PLAY_TYPE_VOD_FLV || mPlayType == TXLivePlayer.PLAY_TYPE_VOD_HLS || mPlayType == TXLivePlayer.PLAY_TYPE_VOD_MP4) {
                        if (livePlayer != null) {
                            livePlayer.resume();
                        }
                    } else {
                        startPlayRtmp();
                    }
                }
            }, 1200);
        }
        if (mPlayerView != null){
            mPlayerView.onResume();
        }
    }

    @Override
    protected void onPause() {
        LogUtil.e("onpause");
        super.onPause();
        isOnPause = true;

    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.e("onstop");
        if (mPlayType == TXLivePlayer.PLAY_TYPE_VOD_FLV || mPlayType == TXLivePlayer.PLAY_TYPE_VOD_HLS || mPlayType == TXLivePlayer.PLAY_TYPE_VOD_MP4) {
            if (livePlayer != null) {
                livePlayer.pause();
            }

        }
        else {
            stopPlayRtmp();
        }
        if (mPlayerView != null){
            mPlayerView.onPause();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.e("ondestory");
        if (livePlayer != null) {
            livePlayer.stopPlay(true);
        }
        if (mPlayerView != null) {
            mPlayerView.onDestroy();
        }
        handler.removeCallbacksAndMessages(null);
        impresent.notifyQuitReady();
        impresent.ondestory();
        impresent=null;
    }



    @Override
    public void onPlayEvent(int event, Bundle param) {
        if (event == TXLiveConstants.PLAY_EVT_PLAY_BEGIN) {
            stopLoadingAnimation();
        } else if (event == TXLiveConstants.PLAY_EVT_PLAY_PROGRESS ) {
            if (mStartSeek) {
                return;
            }
            int progress = param.getInt(TXLiveConstants.EVT_PLAY_PROGRESS);
            int duration = param.getInt(TXLiveConstants.EVT_PLAY_DURATION);
            long curTS = System.currentTimeMillis();

            // 避免滑动进度条松开的瞬间可能出现滑动条瞬间跳到上一个位置
            if (Math.abs(curTS - mTrackingTouchTS) < 500) {
                return;
            }
            mTrackingTouchTS = curTS;

            if (mSeekBar != null) {
                mSeekBar.setProgress(progress);
            }
            if (mTextStart != null) {
                mTextStart.setText(String.format("%02d:%02d",progress/60,progress%60));
            }
            if (mTextDuration != null) {
                mTextDuration.setText(String.format("%02d:%02d",duration/60,duration%60));
            }
            if (mSeekBar != null) {
                mSeekBar.setMax(duration);
            }
            return;
        } else if (event == TXLiveConstants.PLAY_ERR_NET_DISCONNECT || event == TXLiveConstants.PLAY_EVT_PLAY_END) {
            stopPlayRtmp();
            mVideoPlay = false;
            mVideoPause = false;
            mBtnPlay.setBackgroundResource(R.drawable.play_start);
            if (mTextStart != null) {
                mTextStart.setText("00:00");
            }
            if (mSeekBar != null) {
                mSeekBar.setProgress(0);
            }
        } else if (event == TXLiveConstants.PLAY_EVT_PLAY_LOADING){
            startLoadingAnimation();
        }
        else if (event == TXLiveConstants.PLAY_EVT_PLAY_BEGIN) {
            stopLoadingAnimation();
        }


    }
    private void startLoadingAnimation() {
        if (mLoadingView != null) {
            mLoadingView.setVisibility(View.VISIBLE);
            ((AnimationDrawable)mLoadingView.getDrawable()).start();
        }
    }

    private void stopLoadingAnimation() {
        if (mLoadingView != null) {
            mLoadingView.setVisibility(View.GONE);
            ((AnimationDrawable)mLoadingView.getDrawable()).stop();
        }
    }
    private  void stopPlayRtmp() {
        stopLoadingAnimation();
        if (livePlayer != null) {
            livePlayer.setPlayListener(null);
            livePlayer.stopPlay(true);
        }
    }
    @Override
    public void onNetStatus(Bundle bundle) {

    }
   //当屏幕为横屏时，按返回键回到竖屏，再按一次退出
    @Override
    public void onBackPressed() {
        if(islandscape&&getRequestedOrientation()==ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setPortait();
        }else {
            super.onBackPressed();
            //退出IM聊天
            mliveListener.quitIM();
        }

    }
}
