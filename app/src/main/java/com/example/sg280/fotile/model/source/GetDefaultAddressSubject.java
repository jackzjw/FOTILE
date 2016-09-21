package com.example.sg280.fotile.model.source;

import com.example.sg280.fotile.model.bean.BaseResultEntity;

import rx.Observable;
import rx.Subscriber;

/**
 * 获取默认地址
 * Created by Tian on 2016/8/24.
 */
public class GetDefaultAddressSubject extends BaseResultEntity {

    private Subscriber subscriber;
    private String userId;
    private String action;
    private String isDefault;

    public GetDefaultAddressSubject(Subscriber subscriber,String isDefault ,String userId, String action) {
        this.isDefault = isDefault;
        this.subscriber = subscriber;
        this.userId = userId;
        this.action = action;
    }

    @Override
    public Observable getObservable(HttpService methods) {
        return methods.getDefaultAddress(action,userId,isDefault);
    }

    @Override
    public Subscriber getSubscirber() {
        return subscriber;
    }
}
