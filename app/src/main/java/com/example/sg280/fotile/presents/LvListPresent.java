package com.example.sg280.fotile.presents;

import android.content.Context;

import com.example.sg280.fotile.http.FotileRetrofit;
import com.example.sg280.fotile.http.ProgressSubscriber;
import com.example.sg280.fotile.model.bean.HomeAdBean;
import com.example.sg280.fotile.model.bean.HttpResult;
import com.example.sg280.fotile.model.bean.VedioCategoryBean;
import com.example.sg280.fotile.model.bean.VedioDetailsBean;
import com.example.sg280.fotile.model.source.HttpOnNextListener;
import com.example.sg280.fotile.model.source.HttpService;
import com.example.sg280.fotile.presents.Interface.ILiveVedioContacts;
import com.example.sg280.fotile.ui.fragment.LiveFragment;
import com.example.sg280.fotile.utils.NetUtil;
import com.example.sg280.fotile.utils.ToastUtil;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by sg280 on 2016-07-29.
 */
public class LvListPresent implements ILiveVedioContacts.Present{
    private Context context;
    private ILiveVedioContacts.View mview;
    private boolean isRefresh;
    private String name;

    public LvListPresent(Context c, ILiveVedioContacts.View view,String name){
        context=c;
        this.mview=view;
        this.name=name;
    }
//根据classid获取直播，点播的视频列表
    @Override
    public void getVedioResource(int start, int limit, boolean isRefresh) {
        this.isRefresh=isRefresh;
        FotileRetrofit.getInstance().getRetrofit().create(HttpService.class).getVedioCategory()
                        .flatMap(new Func1<HttpResult<List<VedioCategoryBean>>, Observable<HttpResult<List<VedioDetailsBean>>>>() {
                            @Override
                            public Observable<HttpResult<List<VedioDetailsBean>>> call(HttpResult<List<VedioCategoryBean>> listHttpResult) {
                                if (listHttpResult.getSuccess().equals("1")) {
                                    List<VedioCategoryBean> listbean = listHttpResult.getRows();
                                    for (VedioCategoryBean bean : listbean) {
                                        if (bean.getClassName().equals(name)) {
                                            return FotileRetrofit.getInstance().getRetrofit().create(HttpService.class).
                                                    getVedioList(start, limit, bean.getID());
                                        }
                                    }
                                }

                                return null;
                            }
                        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io()).
                        subscribe(new ProgressSubscriber<HttpResult<List<VedioDetailsBean>>>(nextListener,context,true));

    }
  HttpOnNextListener<HttpResult<List<VedioDetailsBean>>> nextListener=new HttpOnNextListener<HttpResult<List<VedioDetailsBean>>>() {
      @Override
      public void onNext(HttpResult<List<VedioDetailsBean>> listHttpResult) {
          if (listHttpResult.getSuccess().equals("1")) {
              if (isRefresh) {
                  mview.refresh(listHttpResult.getRows());
              } else {
                  mview.fetchSucc(listHttpResult.getRows());
              }
          }
      }
  };

//直播的轮播图
    public void getAdInfo(){
        FotileRetrofit.getInstance().getRetrofit().create(HttpService.class).getAdlnfo("LIVE-KV").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<HttpResult<List<HomeAdBean>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                NetUtil.onError(context,e);
            }

            @Override
            public void onNext(HttpResult<List<HomeAdBean>> listHttpResult) {
                           if(listHttpResult.getSuccess().equals("1")){
                               ((LiveFragment)mview).loadAdInfo(listHttpResult.getRows());
                           }else {
                               ToastUtil.showLong(context,listHttpResult.getErrorMessage());
                           }
            }
        });
    }

    @Override
    public void ondestory() {

    }
}
