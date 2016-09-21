package com.example.sg280.fotile.model.source;

import com.example.sg280.fotile.model.bean.BaseEntity;

import rx.Observable;
import rx.Subscriber;

/**
 * 获取当前可用优惠券
 * Created by Tian on 2016/9/6.
 */
public class GetNowUsableCouponsSubject extends BaseEntity {

    private Subscriber subscriber;
    private String userID;
    private String action;
    private String shopCarId;
    private String productid;//产品ID
    private String skuid; //规格ID
    private String productnum; //产品数量

    public GetNowUsableCouponsSubject(Subscriber subscriber, String userID, String action, String shopCarId) {
        this.subscriber = subscriber;
        this.userID = userID;
        this.action = action;
        this.shopCarId = shopCarId;
    }

    public GetNowUsableCouponsSubject(Subscriber subscriber, String userID, String action, String productid, String skuid, String productnum) {
        this.subscriber = subscriber;
        this.userID = userID;
        this.action = action;
        this.productid = productid;
        this.skuid = skuid;
        this.productnum = productnum;
    }

    @Override
    public Observable getObservable(HttpService methods) {
        if (null == shopCarId || "".equals(shopCarId)) {
            return methods.getNowUsableCouponsAtOnce(action, userID, productid, skuid, productnum);
        } else {
            return methods.getNowUsableCoupons(action, userID, shopCarId);
        }
    }

    @Override
    public Subscriber getSubscirber() {
        return subscriber;
    }
}
