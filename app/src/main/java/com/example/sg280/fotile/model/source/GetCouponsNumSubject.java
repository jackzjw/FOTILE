package com.example.sg280.fotile.model.source;

import com.example.sg280.fotile.model.bean.BaseResultEntity;

import rx.Observable;
import rx.Subscriber;

/**
 * 获取当前可用优惠券的数量
 * Created by sg027 on 2016/9/5.
 */
public class GetCouponsNumSubject extends BaseResultEntity {

    private Subscriber subscriber;
    private String userID;
    private String action;
    private String shopCarId;
    private String productid;//产品ID
    private String skuid; //规格ID
    private String productnum; //产品数量

    public GetCouponsNumSubject(Subscriber subscriber, String userID, String action, String shopCarId) {
        this.subscriber = subscriber;
        this.userID = userID;
        this.action = action;
        this.shopCarId = shopCarId;
    }

    public GetCouponsNumSubject(Subscriber subscriber, String userID, String action, String productid, String skuid, String productnum) {
        this.subscriber = subscriber;
        this.userID = userID;
        this.action = action;
        this.productid = productid;
        this.skuid = skuid;
        this.productnum = productnum;
    }

    @Override
    public Observable getObservable(HttpService methods) {
        if(null == shopCarId || "".equals(shopCarId)){
            return methods.getCouponsNumAtOnce(action,userID,productid,skuid,productnum);
        }else{
            return methods.getCouponsNum(action,userID,shopCarId);
        }
    }

    @Override
    public Subscriber getSubscirber() {
        return subscriber;
    }
}
