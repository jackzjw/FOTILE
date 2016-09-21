package com.example.sg280.fotile.model.source;

import com.example.sg280.fotile.model.bean.BaseEntity;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by sg027 on 2016/8/30.
 */
public class MyOrderSubject extends BaseEntity {

    private Subscriber subscriber;
    private String userId;
    private String limit;
    private int start;
    private String orderstatus;

    public MyOrderSubject(Subscriber subscriber,String userId, String limit, int start,String orderstatus) {
        this.subscriber = subscriber;
        this.userId = userId;
        this.limit = limit;
        this.start = start;
        this.orderstatus = orderstatus;
    }

    @Override
    public Observable getObservable(HttpService methods) {
        return methods.getOrderList(userId,limit,start,orderstatus);
    }

    @Override
    public Subscriber getSubscirber() {
        return subscriber;
    }
}
