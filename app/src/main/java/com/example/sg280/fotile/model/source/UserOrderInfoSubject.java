package com.example.sg280.fotile.model.source;

import com.example.sg280.fotile.model.bean.BaseResultEntity;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Tian on 2016/9/20.
 */
public class UserOrderInfoSubject extends BaseResultEntity {

    private Subscriber subscriber;
    private String userId;
    private String orderId;

    public UserOrderInfoSubject(Subscriber subscriber, String userId, String orderId) {
        this.subscriber = subscriber;
        this.userId = userId;
        this.orderId = orderId;
    }

    @Override
    public Observable getObservable(HttpService methods) {
        return methods.getUserOrderInfo(userId,orderId);
    }

    @Override
    public Subscriber getSubscirber() {
        return subscriber;
    }

}
