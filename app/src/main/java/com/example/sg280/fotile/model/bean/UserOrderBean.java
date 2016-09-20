package com.example.sg280.fotile.model.bean;

import java.util.List;

/**
 * Created by Tian on 2016/8/8.
 */
public class UserOrderBean {

    private String ID;//订单号
    private String UserID;//用户
    private String BrandID;//品牌
    private String OrderStatus;//订单状态  1：待付款；2： 待发货  3：待收货  4：交易完成
    private String PayType;//支付方式
    private String PayStatus;//支付状态
    private String PayTime;//支付时间
    private String OrderRemark;//订单备注
    private String ProductAmount;//订单金额
    private String Freight;//运费
    private String DiscountAmount;//优惠金额
    private String PayAmount;//实付金额
    private String CouponID;//优惠券ID
    private List<ProductBean> OrderDetails;//该订单中的产品列表
    private String OrderAddress;//订单收货地址
    private String ExpressID;//快递公司ID
    private String ExpressName;//快递公司名
    private String ExpressNo;//快递单号
    private String ProvinceID;//省
    private String CityID;//市
    private String RegionID;//区
    private String Address;//地址
    private String Recipients;//收货人
    private String Tel;//电话
    private String Postcode;//邮编

    public String getPostcode() {
        return Postcode;
    }

    public void setPostcode(String postcode) {
        Postcode = postcode;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getBrandID() {
        return BrandID;
    }

    public void setBrandID(String brandID) {
        BrandID = brandID;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }

    public String getPayType() {
        return PayType;
    }

    public void setPayType(String payType) {
        PayType = payType;
    }

    public String getPayStatus() {
        return PayStatus;
    }

    public void setPayStatus(String payStatus) {
        PayStatus = payStatus;
    }

    public String getPayTime() {
        return PayTime;
    }

    public void setPayTime(String payTime) {
        PayTime = payTime;
    }

    public String getOrderRemark() {
        return OrderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        OrderRemark = orderRemark;
    }

    public String getProductAmount() {
        return ProductAmount;
    }

    public void setProductAmount(String productAmount) {
        ProductAmount = productAmount;
    }

    public String getFreight() {
        return Freight;
    }

    public void setFreight(String freight) {
        Freight = freight;
    }

    public String getDiscountAmount() {
        return DiscountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        DiscountAmount = discountAmount;
    }

    public String getPayAmount() {
        return PayAmount;
    }

    public void setPayAmount(String payAmount) {
        PayAmount = payAmount;
    }

    public String getCouponID() {
        return CouponID;
    }

    public void setCouponID(String couponID) {
        CouponID = couponID;
    }

    public List<ProductBean> getOrderDetails() {
        return OrderDetails;
    }

    public void setOrderDetails(List<ProductBean> orderDetails) {
        OrderDetails = orderDetails;
    }

    public String getOrderAddress() {
        return OrderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        OrderAddress = orderAddress;
    }

    public String getExpressID() {
        return ExpressID;
    }

    public void setExpressID(String expressID) {
        ExpressID = expressID;
    }

    public String getExpressName() {
        return ExpressName;
    }

    public void setExpressName(String expressName) {
        ExpressName = expressName;
    }

    public String getExpressNo() {
        return ExpressNo;
    }

    public void setExpressNo(String expressNo) {
        ExpressNo = expressNo;
    }

    public String getProvinceID() {
        return ProvinceID;
    }

    public void setProvinceID(String provinceID) {
        ProvinceID = provinceID;
    }

    public String getCityID() {
        return CityID;
    }

    public void setCityID(String cityID) {
        CityID = cityID;
    }

    public String getRegionID() {
        return RegionID;
    }

    public void setRegionID(String regionID) {
        RegionID = regionID;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getRecipients() {
        return Recipients;
    }

    public void setRecipients(String recipients) {
        Recipients = recipients;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    @Override
    public String toString() {
        return "UserOrderBean{" +
                "ID='" + ID + '\'' +
                ", UserID='" + UserID + '\'' +
                ", BrandID='" + BrandID + '\'' +
                ", OrderStatus='" + OrderStatus + '\'' +
                ", PayType='" + PayType + '\'' +
                ", PayStatus='" + PayStatus + '\'' +
                ", PayTime='" + PayTime + '\'' +
                ", OrderRemark='" + OrderRemark + '\'' +
                ", ProductAmount='" + ProductAmount + '\'' +
                ", Freight='" + Freight + '\'' +
                ", DiscountAmount='" + DiscountAmount + '\'' +
                ", PayAmount='" + PayAmount + '\'' +
                ", CouponID='" + CouponID + '\'' +
                ", OrderDetails=" + OrderDetails +
                ", OrderAddress='" + OrderAddress + '\'' +
                ", ExpressID='" + ExpressID + '\'' +
                ", ExpressName='" + ExpressName + '\'' +
                ", ExpressNo='" + ExpressNo + '\'' +
                ", ProvinceID='" + ProvinceID + '\'' +
                ", CityID='" + CityID + '\'' +
                ", RegionID='" + RegionID + '\'' +
                ", Address='" + Address + '\'' +
                ", Recipients='" + Recipients + '\'' +
                ", Tel='" + Tel + '\'' +
                ", Postcode='" + Postcode + '\'' +
                '}';
    }
}
