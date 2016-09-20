package com.example.sg280.fotile.presents;

import android.content.Context;

import com.example.sg280.fotile.http.FotileRetrofit;
import com.example.sg280.fotile.http.ProgressSubscriber;
import com.example.sg280.fotile.model.bean.HomeAdBean;
import com.example.sg280.fotile.model.bean.HomeHotProBean;
import com.example.sg280.fotile.model.bean.HomeLiveList;
import com.example.sg280.fotile.model.bean.HomeRequest;
import com.example.sg280.fotile.model.bean.HttpResult;
import com.example.sg280.fotile.model.source.HttpOnNextListener;
import com.example.sg280.fotile.model.source.HttpService;
import com.example.sg280.fotile.presents.Interface.IHomeContacts;
import com.example.sg280.fotile.utils.ToastUtil;

import java.lang.ref.WeakReference;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.schedulers.Schedulers;

/**
 * Created by sg280 on 2016-07-19.
 */
public class HomePresent implements IHomeContacts.Present {

    private IHomeContacts.View mview;
    private  Context context;


    public HomePresent(IHomeContacts.View view,Context c){
        this.mview=view;
       this.context=c;
    }
    //   回调一一对应
    HttpOnNextListener homeOnNextListener = new HttpOnNextListener<HomeRequest>() {
        @Override
        public void onNext(HomeRequest request) {
          if(request.getData().getSuccess().equals("1")){
              mview.loadContent(request.getData().getRows());
          }else {
              ToastUtil.showLong(context,request.getData().getErrorMessage());
          }
            if(request.getAd().getSuccess().equals("1")){
                mview.loadAdInfo(request.getAd().getRows());
            }else {
                ToastUtil.showLong(context,request.getAd().getErrorMessage());
            }
            if(request.getProinfo().getSuccess().equals("1")){
                mview.loadHotPro(request.getProinfo().getRows());
            }else {
                ToastUtil.showLong(context,request.getProinfo().getErrorMessage());
            }
        }
    };
    @Override
    public void ondestory() {
           mview=null;

    }
//rxjava并发3个网络请求，
    @Override
    public void loadHomeData() {
        ProgressSubscriber<HomeRequest> progressSubscriber = new ProgressSubscriber<HomeRequest>(homeOnNextListener, context, true);
        HttpService service = FotileRetrofit.getInstance().getRetrofit().create(HttpService.class);
        rx.Observable.zip(service.getAdlnfo("INDEX-KV"), service.getHotLiveList(), service.getHotPro("4"), new Func3<HttpResult<List<HomeAdBean>>, HttpResult<List<HomeLiveList>>, HttpResult<List<HomeHotProBean>>, HomeRequest>() {
            @Override
            public HomeRequest call(HttpResult<List<HomeAdBean>> listHttpResult, HttpResult<List<HomeLiveList>> listHttpResult2, HttpResult<List<HomeHotProBean>> listHttpResult3) {
                HomeRequest request=new HomeRequest();
                request.setAd(listHttpResult);
                request.setData(listHttpResult2);
                request.setProinfo(listHttpResult3);
                return request;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).subscribe(progressSubscriber);
    }
}
