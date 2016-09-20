package com.example.sg280.fotile.model.source;

import com.example.sg280.fotile.model.bean.BaseResultEntity;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by sg027 on 2016/9/2.
 */
public class CommitOrderSubject extends BaseResultEntity {

    private Subscriber subscriber;
    private String productid;//产品ID
    private String skuid; //规格ID
    private String productnum; //产品数量
    private String shopCarId;//购物车ID
    private String userId; //用户ID
    private String couponcardid;//优惠券ID
    private String pcr; //省市区
    private String address; //详细地址
    private String tel; //联系电话
    private String postcode; //邮政编码
    private String recipients; //收货人
    private String paytype;//支付方式 1：微信支付 2：货到付款
    private String remark; //留言l
    private String invoicestatus; //是否开纸质发票 1：是
    private String integral;//积分 （这个参数app端不用传utmsource 来源）
    private String isliji;//是否立即购买 1：是 2：不是

    public CommitOrderSubject(Subscriber subscriber, String shopCarId, String userId,
                              String couponcardid, String pcr, String address, String tel,
                              String postcode, String recipients, String paytype, String remark,
                              String invoicestatus, String integral, String isliji) {
        this.subscriber = subscriber;
        this.shopCarId = shopCarId;
        this.userId = userId;
        this.couponcardid = couponcardid;
        this.pcr = pcr;
        this.address = address;
        this.tel = tel;
        this.postcode = postcode;
        this.recipients = recipients;
        this.paytype = paytype;
        this.remark = remark;
        this.invoicestatus = invoicestatus;
        this.integral = integral;
        this.isliji = isliji;
    }

    public CommitOrderSubject( Subscriber subscriber, String productid, String skuid,
                              String productnum, String userId, String couponcardid, String pcr,
                              String address, String tel, String postcode, String recipients,
                              String paytype, String remark, String invoicestatus, String integral,String isliji) {
        this.isliji = isliji;
        this.subscriber = subscriber;
        this.productid = productid;
        this.skuid = skuid;
        this.productnum = productnum;
        this.userId = userId;
        this.couponcardid = couponcardid;
        this.pcr = pcr;
        this.address = address;
        this.tel = tel;
        this.postcode = postcode;
        this.recipients = recipients;
        this.paytype = paytype;
        this.remark = remark;
        this.invoicestatus = invoicestatus;
        this.integral = integral;
    }

    @Override
    public Observable getObservable(HttpService methods) {
        if(isliji.equals("1")){

            return methods.commitOrderAtOnce(productid,skuid,productnum, userId, couponcardid, pcr, address, tel, postcode, recipients,
                    paytype, remark, invoicestatus, integral, isliji);
        }else{

            return methods.commitOrder(shopCarId, userId, couponcardid, pcr, address, tel, postcode, recipients,
                    paytype, remark, invoicestatus, integral, isliji);
        }

    }

    @Override
    public Subscriber getSubscirber() {
        return subscriber;
    }
}
