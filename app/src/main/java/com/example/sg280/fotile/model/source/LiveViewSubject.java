package com.example.sg280.fotile.model.source;

import com.example.sg280.fotile.model.bean.BaseResultEntity;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by sg280 on 2016-08-02.
 */
public class LiveViewSubject extends BaseResultEntity {

    private String mtype;
    private String mliveid;
    private Subscriber msubscrible;
    public LiveViewSubject(Subscriber subscriber,String type,String liveid){
        this.mliveid=liveid;
        this.msubscrible=subscriber;
        this.mtype=type;
    }
    @Override
    public Observable getObservable(HttpService methods) {
        return methods.getLiveView(mtype,mliveid);
    }

    @Override
    public Subscriber getSubscirber() {
        return msubscrible;
    }
}
