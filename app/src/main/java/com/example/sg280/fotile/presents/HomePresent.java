package com.example.sg280.fotile.presents;

import android.app.Activity;

import com.example.sg280.fotile.http.FotileRetrofit;
import com.example.sg280.fotile.http.ProgressSubscriber;
import com.example.sg280.fotile.model.bean.HomeLiveList;
import com.example.sg280.fotile.model.source.HomeSubject;
import com.example.sg280.fotile.model.source.HttpOnNextListener;
import com.example.sg280.fotile.presents.Interface.IHomeContacts;

import java.util.List;

/**
 * Created by sg280 on 2016-07-19.
 */
public class HomePresent implements IHomeContacts.Present {

    private IHomeContacts.View mview;
    private HomeDataSource source;
    private Activity act;
    public HomePresent(IHomeContacts.View view,Activity activity){
        this.mview=view;
        this.act=activity;
    }

    @Override
    public void start() {
        HomeSubject imp=new HomeSubject(new ProgressSubscriber(homeOnNextListener,act));
        FotileRetrofit.getInstance().doHttpDeal(imp);

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
