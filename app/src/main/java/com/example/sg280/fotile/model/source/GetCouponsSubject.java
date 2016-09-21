package com.example.sg280.fotile.model.source;

import com.example.sg280.fotile.model.bean.BaseEntity;

import rx.Observable;
import rx.Subscriber;

/**
 * 获取所有可用或者不可用优惠码
 * Created by Tian on 2016/9/6.
 */
public class GetCouponsSubject extends BaseEntity {

    private Subscriber subscriber;
    private String userID;
    private String action;

    public GetCouponsSubject(Subscriber subscriber, String userID, String action) {
        this.subscriber = subscriber;
        this.userID = userID;
        this.action = action;
    }

    @Override
    public Observable getObservable(HttpService methods) {
        return methods.getUsableCoupons(action,userID);
    }

    @Override
    public Subscriber getSubscirber() {
        return subscriber;
    }
}
