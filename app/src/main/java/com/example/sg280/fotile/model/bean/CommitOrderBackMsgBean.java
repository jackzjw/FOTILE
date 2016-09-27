package com.example.sg280.fotile.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 提交订单后返回的数据
 * Created by sg027 on 2016/9/2.
 */
public class CommitOrderBackMsgBean implements Parcelable {

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


    @Override
    public String toString() {
        return "CommitOrderBackMsgBean{" +
                "PayType='" + PayType + '\'' +
                ", PayAmount='" + PayAmount + '\'' +
                ", OrderNo='" + OrderNo + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.PayType);
        dest.writeString(this.PayAmount);
        dest.writeString(this.OrderNo);
    }

    public CommitOrderBackMsgBean() {
    }

    protected CommitOrderBackMsgBean(Parcel in) {
        this.PayType = in.readString();
        this.PayAmount = in.readString();
        this.OrderNo = in.readString();
    }

    public static final Parcelable.Creator<CommitOrderBackMsgBean> CREATOR = new Parcelable.Creator<CommitOrderBackMsgBean>() {
        @Override
        public CommitOrderBackMsgBean createFromParcel(Parcel source) {
            return new CommitOrderBackMsgBean(source);
        }

        @Override
        public CommitOrderBackMsgBean[] newArray(int size) {
            return new CommitOrderBackMsgBean[size];
        }
    };
}
