package com.example.sg280.fotile.model.source;

import com.example.sg280.fotile.model.bean.BaseEntity;

import rx.Observable;
import rx.Subscriber;

/**
 * 我的收藏列表
 * Created by Tian on 2016/8/26.
 */
public class MyCollectSubject extends BaseEntity {

    private Subscriber subscriber;
    private String userId;
    private int itemType;
    private String limit;
    private int start;

    public MyCollectSubject(Subscriber subscriber, String userId, int itemType, String limit, int start) {
        this.subscriber = subscriber;
        this.userId = userId;
        this.itemType = itemType;
        this.limit = limit;
        this.start = start;
    }

    @Override
    public Observable getObservable(HttpService methods) {
        if(1==itemType){
            return methods.getCollectLiveList(userId,itemType,limit,start);
        }else{
            return methods.getCollectProList(userId,itemType,limit,start);
        }
    }

    @Override
    public Subscriber getSubscirber() {
        return subscriber;
    }
}
