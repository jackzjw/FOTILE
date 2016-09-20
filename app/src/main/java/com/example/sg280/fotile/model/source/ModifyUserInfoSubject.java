package com.example.sg280.fotile.model.source;

import com.example.sg280.fotile.model.bean.BaseResultEntity;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Tian on 2016/8/5.
 */
public class ModifyUserInfoSubject extends BaseResultEntity {

    private Subscriber subscriber;
    private String userid;
    private String username;
    private String usertel;
    private String pwdold;
    private String pwdnew;

    public ModifyUserInfoSubject(Subscriber subscriber, String userid,
                                 String username, String usertel, String pwdold,String pwdnew) {
        this.subscriber = subscriber;
        this.userid = userid;
        this.username = username;
        this.usertel = usertel;
        this.pwdold = pwdold;
        this.pwdnew = pwdnew;
    }

    @Override
    public Observable getObservable(HttpService methods) {
        return methods.modifyUserInfo(userid,username,usertel,pwdold,pwdnew);
    }

    @Override
    public Subscriber getSubscirber() {
        return subscriber;
    }
}
