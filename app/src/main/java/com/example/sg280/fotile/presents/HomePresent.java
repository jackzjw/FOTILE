package com.example.sg280.fotile.presents;

import android.app.Activity;

import com.example.sg280.fotile.http.FotileRetrofit;
import com.example.sg280.fotile.http.ProgressSubscriber;
import com.example.sg280.fotile.model.bean.HomeLiveList;
import com.example.sg280.fotile.model.source.HomeDataSouceImp;
import com.example.sg280.fotile.model.source.HomeDataSource;
import com.example.sg280.fotile.model.source.HttpOnNextListener;

import java.util.List;

/**
 * Created by sg280 on 2016-07-19.
 */
public class HomePresent implements HomeContacts.Present {

    private HomeContacts.View mview;
    private HomeDataSource source;
    private Activity act;
    public HomePresent(HomeContacts.View view,Activity activity){
        this.mview=view;
        this.act=activity;
    }

    @Override
    public void start() {
        HomeDataSouceImp imp=new HomeDataSouceImp(new ProgressSubscriber(homeOnNextListener,act));
        FotileRetrofit.getInstance().doHttpDeal(imp);
       /* source.getHomeData(new HomeDataSource.CallBack() {
            @Override
            public void onHomeLoad(List<HomeLiveList> data) {
                 mview.load(data);
                 mview.showNormal();
            }

            @Override
            public void onDataNotAvaiable() {
                    mview.showError();
            }

            @Override
            public void onError(String msg) {
               mview.onError(msg);
            }
        });*/
    }
    //   回调一一对应
    HttpOnNextListener homeOnNextListener = new HttpOnNextListener<List<HomeLiveList>>() {
        @Override
        public void onNext(List<HomeLiveList> subjects) {
           mview.load(subjects);
        }
    };
    @Override
    public void ondestory() {
           mview=null;

    }
}
