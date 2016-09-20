package com.example.sg280.fotile.model.source;

import com.example.sg280.fotile.model.bean.BaseEntity;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by sg027 on 2016/8/31.
 */
public class GetShoppingCartGoodsSubject extends BaseEntity{

    private Subscriber subscriber;
    private String userId;

    public GetShoppingCartGoodsSubject(Subscriber subscriber, String userId) {
        this.subscriber = subscriber;
        this.userId = userId;
    }

    @Override
    public Observable getObservable(HttpService methods) {
        return methods.getShoppingCartGoods(userId);
    }

    @Override
    public Subscriber getSubscirber() {
        return subscriber;
    }
}
