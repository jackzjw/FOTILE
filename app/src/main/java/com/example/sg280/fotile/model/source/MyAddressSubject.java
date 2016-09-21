package com.example.sg280.fotile.model.source;

import com.example.sg280.fotile.model.bean.BaseEntity;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Tian on 2016/8/23.
 */
public class MyAddressSubject extends BaseEntity {

    private Subscriber subscriber;
    private String userId;
    private String limit;
    private int start;

    public MyAddressSubject(Subscriber subscriber, String userId, String limit, int start) {
        this.subscriber = subscriber;
        this.userId = userId;
        this.limit = limit;
        this.start = start;
    }

    @Override
    public Observable getObservable(HttpService methods) {
        return methods.getAddressList(userId,limit,start);
    }

    @Override
    public Subscriber getSubscirber() {
        return subscriber;
    }

}
