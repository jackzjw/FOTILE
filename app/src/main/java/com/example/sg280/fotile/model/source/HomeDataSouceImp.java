package com.example.sg280.fotile.model.source;

import com.example.sg280.fotile.model.bean.BaseEntity;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by sg280 on 2016-07-19.
 */
public class HomeDataSouceImp extends BaseEntity {

    private Subscriber msubscrible;

    public HomeDataSouceImp(Subscriber getHomelist){
            this.msubscrible=getHomelist;
    }

    @Override
    public Observable getObservable(HttpService methods) {
        return methods.getHotLiveList();
    }

    @Override
    public Subscriber getSubscirber() {
        return msubscrible;
    }


}
