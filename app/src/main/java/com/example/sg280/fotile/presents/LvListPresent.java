package com.example.sg280.fotile.presents;

import android.content.Context;

import com.example.sg280.fotile.http.FotileRetrofit;
import com.example.sg280.fotile.http.ProgressSubscriber;
import com.example.sg280.fotile.model.bean.VedioDetailsBean;
import com.example.sg280.fotile.model.source.HttpOnNextListener;
import com.example.sg280.fotile.model.source.LiveVedioSubject;
import com.example.sg280.fotile.presents.Interface.ILiveVedioContacts;

import java.util.List;

/**
 * Created by sg280 on 2016-07-29.
 */
public class LvListPresent implements ILiveVedioContacts.Present{
    private Context context;
    private ILiveVedioContacts.View mview;
    private String classid;
    private boolean isRefresh;
    public LvListPresent(Context context, ILiveVedioContacts.View view,String id){
        this.context=context;
        this.mview=view;
        this.classid=id;
    }
//根据classid获取直播，点播的视频列表
    @Override
    public void getVedioResource(int start, int limit, boolean isRefresh) {
        this.isRefresh=isRefresh;
        LiveVedioSubject subject=new LiveVedioSubject(new ProgressSubscriber(livevediolistener,context),start,limit,classid);
        FotileRetrofit.getInstance().doHttpDeal(subject);
    }
  HttpOnNextListener livevediolistener=new HttpOnNextListener<List<VedioDetailsBean>>() {
      @Override
      public void onNext(List<VedioDetailsBean> vediodata) {
          if(isRefresh){
              mview.refresh(vediodata);
          }else {
              mview.fetchSucc(vediodata);
          }
      }
  };
    @Override
    public void start() {
        getVedioResource(1,10,false);
    }

    @Override
    public void ondestory() {

    }
}
