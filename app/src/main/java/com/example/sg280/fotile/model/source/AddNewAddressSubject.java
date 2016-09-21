package com.example.sg280.fotile.model.source;

import com.example.sg280.fotile.model.bean.BaseResultEntity;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Tian on 2016/8/22.
 */
public class AddNewAddressSubject extends BaseResultEntity {

    private Subscriber subscriber;
    private String Action;
    private String userid;
    private String pcr;
    private String address;
    private String tel;
    private String postcode;
    private String recipients;
    private String isdefault;

    /**
     * 添加收货地址
     *
     * @param action     action类型
     * @param userid     用户id
     * @param pcr        省市区
     * @param address    详细地址
     * @param tel        电话
     * @param postcode   邮政
     * @param recipients 收货人
     * @param isdefault  是否默认
     */
    public AddNewAddressSubject(Subscriber subscriber, String action, String userid, String pcr, String address, String tel, String postcode, String recipients, String isdefault) {
        this.subscriber = subscriber;
        this.isdefault = isdefault;
        Action = action;
        this.userid = userid;
        this.pcr = pcr;
        this.address = address;
        this.tel = tel;
        this.postcode = postcode;
        this.recipients = recipients;
    }

    @Override
    public Observable getObservable(HttpService methods) {
        return methods.addAddress(Action, userid, pcr, address, tel, postcode, recipients, isdefault);
    }

    @Override
    public Subscriber getSubscirber() {
        return subscriber;
    }

}
