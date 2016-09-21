package com.example.sg280.fotile.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 优惠券的实体类
 * Created by Tian on 2016/8/11.
 */
public class CouponsBean implements Parcelable {

    private String ID;//优惠券ID
    private String CouponID;//优惠券卡号
    private String CouponName;//优惠券名称
    private String StartTime;//开始时间
    private String EndTime;//结束时间
    private String CodeLen;//码长度
    private String CouponQuantity;//有效次数
    private String CouponInfo;//优惠券信息
    private String LimitAmount;//限制金额
    private String ValType;//限制类型,    1： 满减   2 ：折扣
    private String ValValue;//优惠金额或折扣
    private String CouponCode;//红包码
    private String UserID;//用户ID
    private String GetTime;//领取时间
    private String IsUsed;//是否使用,   0 未使用  1 使用
    private String UseTime;//使用时间
    private String OrderID;//订单ID

    private boolean ruleIsClicked;

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCouponID() {
        return CouponID;
    }

    public void setCouponID(String couponID) {
        CouponID = couponID;
    }

    public String getCouponName() {
        return CouponName;
    }

    public void setCouponName(String couponName) {
        CouponName = couponName;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getCodeLen() {
        return CodeLen;
    }

    public void setCodeLen(String codeLen) {
        CodeLen = codeLen;
    }

    public String getCouponQuantity() {
        return CouponQuantity;
    }

    public void setCouponQuantity(String couponQuantity) {
        CouponQuantity = couponQuantity;
    }

    public String getCouponInfo() {
        return CouponInfo;
    }

    public void setCouponInfo(String couponInfo) {
        CouponInfo = couponInfo;
    }

    public String getLimitAmount() {
        return LimitAmount;
    }

    public void setLimitAmount(String limitAmount) {
        LimitAmount = limitAmount;
    }

    public String getValType() {
        return ValType;
    }

    public void setValType(String valType) {
        ValType = valType;
    }

    public String getValValue() {
        return ValValue;
    }

    public void setValValue(String valValue) {
        ValValue = valValue;
    }

    public String getCouponCode() {
        return CouponCode;
    }

    public void setCouponCode(String couponCode) {
        CouponCode = couponCode;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getGetTime() {
        return GetTime;
    }

    public void setGetTime(String getTime) {
        GetTime = getTime;
    }

    public String getIsUsed() {
        return IsUsed;
    }

    public void setIsUsed(String isUsed) {
        IsUsed = isUsed;
    }

    public String getUseTime() {
        return UseTime;
    }

    public void setUseTime(String useTime) {
        UseTime = useTime;
    }

    public boolean isRuleIsClicked() {
        return ruleIsClicked;
    }

    public void setRuleIsClicked(boolean ruleIsClicked) {
        this.ruleIsClicked = ruleIsClicked;
    }

    @Override
    public String toString() {
        return "CouponsBean{" +
                "ID='" + ID + '\'' +
                ", CouponID='" + CouponID + '\'' +
                ", CouponName='" + CouponName + '\'' +
                ", StartTime='" + StartTime + '\'' +
                ", EndTime='" + EndTime + '\'' +
                ", CodeLen='" + CodeLen + '\'' +
                ", CouponQuantity='" + CouponQuantity + '\'' +
                ", CouponInfo='" + CouponInfo + '\'' +
                ", LimitAmount='" + LimitAmount + '\'' +
                ", ValType='" + ValType + '\'' +
                ", ValValue='" + ValValue + '\'' +
                ", CouponCode='" + CouponCode + '\'' +
                ", UserID='" + UserID + '\'' +
                ", GetTime='" + GetTime + '\'' +
                ", IsUsed='" + IsUsed + '\'' +
                ", UseTime='" + UseTime + '\'' +
                ", OrderID='" + OrderID + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ID);
        dest.writeString(this.CouponID);
        dest.writeString(this.CouponName);
        dest.writeString(this.StartTime);
        dest.writeString(this.EndTime);
        dest.writeString(this.CodeLen);
        dest.writeString(this.CouponQuantity);
        dest.writeString(this.CouponInfo);
        dest.writeString(this.LimitAmount);
        dest.writeString(this.ValType);
        dest.writeString(this.ValValue);
        dest.writeString(this.CouponCode);
        dest.writeString(this.UserID);
        dest.writeString(this.GetTime);
        dest.writeString(this.IsUsed);
        dest.writeString(this.UseTime);
        dest.writeString(this.OrderID);
        dest.writeByte(this.ruleIsClicked ? (byte) 1 : (byte) 0);
    }

    public CouponsBean() {
    }

    protected CouponsBean(Parcel in) {
        this.ID = in.readString();
        this.CouponID = in.readString();
        this.CouponName = in.readString();
        this.StartTime = in.readString();
        this.EndTime = in.readString();
        this.CodeLen = in.readString();
        this.CouponQuantity = in.readString();
        this.CouponInfo = in.readString();
        this.LimitAmount = in.readString();
        this.ValType = in.readString();
        this.ValValue = in.readString();
        this.CouponCode = in.readString();
        this.UserID = in.readString();
        this.GetTime = in.readString();
        this.IsUsed = in.readString();
        this.UseTime = in.readString();
        this.OrderID = in.readString();
        this.ruleIsClicked = in.readByte() != 0;
    }

    public static final Parcelable.Creator<CouponsBean> CREATOR = new Parcelable.Creator<CouponsBean>() {
        @Override
        public CouponsBean createFromParcel(Parcel source) {
            return new CouponsBean(source);
        }

        @Override
        public CouponsBean[] newArray(int size) {
            return new CouponsBean[size];
        }
    };
}
