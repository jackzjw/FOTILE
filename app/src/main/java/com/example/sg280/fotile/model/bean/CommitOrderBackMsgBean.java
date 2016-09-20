package com.example.sg280.fotile.model.bean;

/**
 * 提交订单后返回的数据
 * Created by sg027 on 2016/9/2.
 */
public class CommitOrderBackMsgBean {

    /**
     * PayType : 货到付款
     * PayAmount : 3890
     * OrderNo : 100445
     */

    private String PayType;
    private String PayAmount;
    private String OrderNo;

    public String getPayType() {
        return PayType;
    }

    public void setPayType(String PayType) {
        this.PayType = PayType;
    }

    public String getPayAmount() {
        return PayAmount;
    }

    public void setPayAmount(String PayAmount) {
        this.PayAmount = PayAmount;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String OrderNo) {
        this.OrderNo = OrderNo;
    }
}
