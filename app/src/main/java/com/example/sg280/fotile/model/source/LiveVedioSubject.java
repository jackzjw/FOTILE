package com.example.sg280.fotile.model.source;

import com.example.sg280.fotile.model.bean.BaseEntity;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by sg280 on 2016-07-29.
 */
public class LiveVedioSubject extends BaseEntity {
    private int start;
    private int limit;
    private String id;
    private Subscriber msubscriber;
    public LiveVedioSubject(Subscriber subscriber,int start,int limit,String classid){
        this.start=start;
        this.limit=limit;
        this.id=classid;
        this.msubscriber=subscriber;
    }
    @Override
    public Observable getObservable(HttpService methods) {
        return methods.getVedioList(start,limit,id);
    }

    @Override
    public Subscriber getSubscirber() {
        return msubscriber;
    }
}
