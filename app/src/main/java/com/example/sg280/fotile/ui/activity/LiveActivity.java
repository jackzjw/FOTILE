package com.example.sg280.fotile.ui.activity;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.model.bean.VedioDetailsBean;
import com.example.sg280.fotile.presents.Interface.ILiveContacts;
import com.example.sg280.fotile.presents.LivePresent;
import com.example.sg280.fotile.utils.LogUtil;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;

import butterknife.Bind;

/**
 * Created by sg280 on 2016-08-02.
 */
public class LiveActivity extends BaseActivity implements ILiveContacts.View{
    public static final String LIVE_ID="liveid";
    private static final String TAG = "LiveActivity";
    private LivePresent present;
    private TXLivePlayer livePlayer=null;
    @Bind(R.id.video_view)
     TXCloudVideoView mPlayerView;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_live;
    }

    @Override
    protected void onInitView() {
        if (livePlayer == null) {
            livePlayer=new TXLivePlayer(this);
        }
        String liveid=getIntent().getStringExtra(LIVE_ID);
        present=new LivePresent(this,this);
        if (liveid != null) {
            present.getLiveView("GetLiveView",liveid);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPlayerView != null) {
            mPlayerView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPlayerView != null) {
            mPlayerView.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPlayerView != null) {
            mPlayerView.onDestroy();
        }
    }

    @Override
    public void getLiveViewSucc(VedioDetailsBean data) {
        LogUtil.e("走了");
        String url="http://2527.vod.myqcloud.com/2527_117134a2343111e5b8f5bdca6cb9f38c.f20.mp4";
        livePlayer.setPlayerView(mPlayerView);
          int result=livePlayer.startPlay(url,TXLivePlayer.PLAY_TYPE_VOD_MP4);
        LogUtil.e(result+"");
    }
}
