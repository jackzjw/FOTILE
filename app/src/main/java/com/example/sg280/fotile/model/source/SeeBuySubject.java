package com.example.sg280.fotile.model.source;

import com.example.sg280.fotile.model.bean.BaseEntity;

import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by sg280 on 2016-08-11.
 */
public class SeeBuySubject extends BaseEntity {

    private String mliveid;
    private String maction;
    private int mstart;
    private int mlimit;
    private Subscriber msubscriber;
    public SeeBuySubject(Subscriber subscriber,String liveid,String action,int start,int limit){
         this.maction=action;
        this.mlimit=limit;
        this.mliveid=liveid;
        this.mstart=start;
        this.msubscriber=subscriber;
    }
    @Override
    public Observable getObservable(HttpService methods) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("Action",maction);
        map.put("liveid",mliveid);
        map.put("start",mstart);
        map.put("limit",mlimit);
        return methods.getLiveProList(map);
    }

    @Override
    public Subscriber getSubscirber() {
        return msubscriber;
    }
}
