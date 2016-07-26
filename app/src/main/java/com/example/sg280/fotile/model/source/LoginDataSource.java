package com.example.sg280.fotile.model.source;

import com.example.sg280.fotile.model.bean.BaseResultEntity;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by sg280 on 2016-07-25.
 */
public class LoginDataSource extends BaseResultEntity {
    private Subscriber msubscriber;
    private String mid;
    private String mpwd;
    public LoginDataSource(Subscriber subscriber,String id,String pwd){
        this.msubscriber=subscriber;
        this.mid=id;
        this.mpwd=pwd;
    }
    @Override
    public Observable getObservable(HttpService methods) {
        return methods.getUserInfo(mid,mpwd);
    }

    @Override
    public Subscriber getSubscirber() {
        return msubscriber;
    }
}
