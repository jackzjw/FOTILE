package com.example.sg280.fotile.model.source;

import com.example.sg280.fotile.model.bean.BaseResultEntity;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by sg280 on 2016-08-23.
 */
public class ProductSubject extends BaseResultEntity {
    private String userid;
    private String proid;
    private Subscriber msubscriber;

    public ProductSubject(Subscriber subscriber,String userid,String proid){
        this.msubscriber=subscriber;
        this.proid=proid;
        this.userid=userid;
    }
    @Override
    public Observable getObservable(HttpService methods) {
        return methods.getProductDetails(proid,userid);
    }

    @Override
    public Subscriber getSubscirber() {
        return msubscriber;
    }
}
