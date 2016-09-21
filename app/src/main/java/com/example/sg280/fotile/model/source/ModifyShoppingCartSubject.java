package com.example.sg280.fotile.model.source;

import com.example.sg280.fotile.model.bean.BaseNoResultEntity;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by sg027 on 2016/8/31.
 */
public class ModifyShoppingCartSubject extends BaseNoResultEntity{

    private Subscriber subscriber;
    private String action;
    private String shopCarId;
    private String userId;

    public ModifyShoppingCartSubject(Subscriber subscriber, String action, String shopCarId, String userId) {
        this.subscriber = subscriber;
        this.action = action;
        this.shopCarId = shopCarId;
        this.userId = userId;
    }

    @Override
    public Observable getObservable(HttpService methods) {
        return methods.modifyGoodsNum(action,shopCarId,userId);
    }

    @Override
    public Subscriber getSubscirber() {
        return subscriber;
    }
}
