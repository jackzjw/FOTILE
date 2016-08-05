package com.example.sg280.fotile.model.source;

import com.example.sg280.fotile.model.bean.BaseResultEntity;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by sg280 on 2016-07-26.
 */
public class RegisterSubject extends BaseResultEntity {
    private String tel;
    private String code;
    private String pwd;
    private Subscriber msubscriber;
    public RegisterSubject(Subscriber subscriber,String tel,String dycode,String pwd){
        this.code=dycode;
        this.pwd=pwd;
        this.tel=tel;
        this.msubscriber=subscriber;
    }
    @Override
    public Observable getObservable(HttpService methods) {
        return methods.register(tel,code,pwd);
    }

    @Override
    public Subscriber getSubscirber() {
        return msubscriber;
    }
}
