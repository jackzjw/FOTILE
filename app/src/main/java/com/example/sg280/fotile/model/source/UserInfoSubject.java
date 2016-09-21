package com.example.sg280.fotile.model.source;

import com.example.sg280.fotile.model.bean.BaseResultEntity;

import rx.Observable;
import rx.Subscriber;

/**
 * 获取用户相信信息
 * Created by Tian on 2016/9/5.
 */
public class UserInfoSubject extends BaseResultEntity {

    private Subscriber subscriber;
    private String userId;

    public UserInfoSubject(Subscriber subscriber, String userId) {
        this.subscriber = subscriber;
        this.userId = userId;
    }

    @Override
    public Observable getObservable(HttpService methods) {
        return methods.userInfo(userId);
    }

    @Override
    public Subscriber getSubscirber() {
        return subscriber;
    }
}
