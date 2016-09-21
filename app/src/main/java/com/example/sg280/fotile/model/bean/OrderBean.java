package com.example.sg280.fotile.model.bean;

import java.util.List;

/**
 * Created by sg027 on 2016/8/30.
 */
public class OrderBean {


    /**
     * ID : 100438
     * UserID : 957
     * BrandID : 1
     * BrandLogo : http://zhiboimg01.efotile.net/UploadFiles/brand/fotile_logo.png
     * OrderStatus : 2
     * PayType : 2
     * PayTypeName : 货到付款
     * PayStatus : 0
     * PayTime : null
     * OrderRemark :
     * ProductAmount : 16370
     * Freight : 0
     * DiscountAmount : 0
     * StringegralAmount : 0
     * UtmSource :
     * PayAmount : 16370
     * CouponID : null
     * OrderDetails : [{"ID":461,"OrderID":100438,"ProductID":76,"ProductName":"方太 EM02TE＋HA2G.B云魔方烟灶套餐","SkuID":115,"SkuName":"EM02TE＋HA2G.B 天然气","ProductQuantity":1,"PriceCommon":8388,"PricePromo":6990,"SkuImg":"http://zhiboimg01.efotile.net/UploadFiles/productImage/20160811165106502_0.jpg"},{"ID":462,"OrderID":100438,"ProductID":77,"ProductName":"方太 EMD2T＋HC78BE 云魔方烟灶套餐","SkuID":117,"SkuName":"EMD2T＋HC78BE 天然气","ProductQuantity":1,"PriceCommon":5999,"PricePromo":4990,"SkuImg":"http://zhiboimg01.efotile.net/UploadFiles/productImage/20160811170225299_0.jpg"},{"ID":463,"OrderID":100438,"ProductID":59,"ProductName":"方太劲魔方 EN05E＋HC21BE  烟灶套餐","SkuID":90,"SkuName":"EN05E＋HC21BE天然气","ProductQuantity":1,"PriceCommon":5580,"PricePromo":4390,"SkuImg":"http://zhiboimg01.efotile.net/UploadFiles/productImage/20160818135503493_0.jpg"}]
     * OrderDealLog : null
     * OrderAddress : null
     * ExpressID : null
     * ExpressName :
     * ExpressNo :
     * ProvinceID : null
     * CityID : null
     * RegionID : null
     * Address : 上海市黄浦区 好神奇
     * Recipients : 不会吧
     * Tel : 15238048187
     * Postcode : 123455
     * InvoiceStatus : 1
     */

    private String ID;
    private String UserID;
    private String BrandID;
    private String BrandLogo;
    private String OrderStatus;
    private String PayType;
    private String PayTypeName;
    private String PayStatus;
    private String PayTime;
    private String OrderRemark;
    private String ProductAmount;
    private String Freight;
    private String DiscountAmount;
    private String StringegralAmount;
    private String UtmSource;
    private String PayAmount;
    private String CouponID;
    private String OrderDealLog;
    private String OrderAddress;
    private String ExpressID;
    private String ExpressName;
    private String ExpressNo;
    private String ProvinceID;
    private String CityID;
    private String RegionID;
    private String Address;
    private String Recipients;
    private String Tel;
    private String Postcode;
    private String InvoiceStatus;
    private List<ProductBean> OrderDetails;//该订单中的产品列表

    public List<ProductBean> getOrderDetails() {
        return OrderDetails;
    }

    public void setOrderDetails(List<ProductBean> orderDetails) {
        OrderDetails = orderDetails;
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

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getBrandID() {
        return BrandID;
    }

    public void setBrandID(String BrandID) {
        this.BrandID = BrandID;
    }

    public String getBrandLogo() {
        return BrandLogo;
    }

    public void setBrandLogo(String BrandLogo) {
        this.BrandLogo = BrandLogo;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String OrderStatus) {
        this.OrderStatus = OrderStatus;
    }

    public String getPayType() {
        return PayType;
    }

    public void setPayType(String PayType) {
        this.PayType = PayType;
    }

    public String getPayTypeName() {
        return PayTypeName;
    }

    public void setPayTypeName(String PayTypeName) {
        this.PayTypeName = PayTypeName;
    }

    public String getPayStatus() {
        return PayStatus;
    }

    public void setPayStatus(String PayStatus) {
        this.PayStatus = PayStatus;
    }

    public String getPayTime() {
        return PayTime;
    }

    public void setPayTime(String PayTime) {
        this.PayTime = PayTime;
    }

    public String getOrderRemark() {
        return OrderRemark;
    }

    public void setOrderRemark(String OrderRemark) {
        this.OrderRemark = OrderRemark;
    }

    public String getProductAmount() {
        return ProductAmount;
    }

    public void setProductAmount(String ProductAmount) {
        this.ProductAmount = ProductAmount;
    }

    public String getFreight() {
        return Freight;
    }

    public void setFreight(String Freight) {
        this.Freight = Freight;
    }

    public String getDiscountAmount() {
        return DiscountAmount;
    }

    public void setDiscountAmount(String DiscountAmount) {
        this.DiscountAmount = DiscountAmount;
    }

    public String getStringegralAmount() {
        return StringegralAmount;
    }

    public void setStringegralAmount(String StringegralAmount) {
        this.StringegralAmount = StringegralAmount;
    }

    public String getUtmSource() {
        return UtmSource;
    }

    public void setUtmSource(String UtmSource) {
        this.UtmSource = UtmSource;
    }

    public String getPayAmount() {
        return PayAmount;
    }

    public void setPayAmount(String PayAmount) {
        this.PayAmount = PayAmount;
    }

    public String getCouponID() {
        return CouponID;
    }

    public void setCouponID(String CouponID) {
        this.CouponID = CouponID;
    }

    public String getOrderDealLog() {
        return OrderDealLog;
    }

    public void setOrderDealLog(String OrderDealLog) {
        this.OrderDealLog = OrderDealLog;
    }

    public String getOrderAddress() {
        return OrderAddress;
    }

    public void setOrderAddress(String OrderAddress) {
        this.OrderAddress = OrderAddress;
    }

    public String getExpressID() {
        return ExpressID;
    }

    public void setExpressID(String ExpressID) {
        this.ExpressID = ExpressID;
    }

    public String getExpressName() {
        return ExpressName;
    }

    public void setExpressName(String ExpressName) {
        this.ExpressName = ExpressName;
    }

    public String getExpressNo() {
        return ExpressNo;
    }

    public void setExpressNo(String ExpressNo) {
        this.ExpressNo = ExpressNo;
    }

    public String getProvinceID() {
        return ProvinceID;
    }

    public void setProvinceID(String ProvinceID) {
        this.ProvinceID = ProvinceID;
    }

    public String getCityID() {
        return CityID;
    }

    public void setCityID(String CityID) {
        this.CityID = CityID;
    }

    public String getRegionID() {
        return RegionID;
    }

    public void setRegionID(String RegionID) {
        this.RegionID = RegionID;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getRecipients() {
        return Recipients;
    }

    public void setRecipients(String Recipients) {
        this.Recipients = Recipients;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
    }

    public String getPostcode() {
        return Postcode;
    }

    public void setPostcode(String Postcode) {
        this.Postcode = Postcode;
    }

    public String getInvoiceStatus() {
        return InvoiceStatus;
    }

    public void setInvoiceStatus(String InvoiceStatus) {
        this.InvoiceStatus = InvoiceStatus;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "ID='" + ID + '\'' +
                ", UserID='" + UserID + '\'' +
                ", BrandID='" + BrandID + '\'' +
                ", BrandLogo='" + BrandLogo + '\'' +
                ", OrderStatus='" + OrderStatus + '\'' +
                ", PayType='" + PayType + '\'' +
                ", PayTypeName='" + PayTypeName + '\'' +
                ", PayStatus='" + PayStatus + '\'' +
                ", PayTime='" + PayTime + '\'' +
                ", OrderRemark='" + OrderRemark + '\'' +
                ", ProductAmount='" + ProductAmount + '\'' +
                ", Freight='" + Freight + '\'' +
                ", DiscountAmount='" + DiscountAmount + '\'' +
                ", StringegralAmount='" + StringegralAmount + '\'' +
                ", UtmSource='" + UtmSource + '\'' +
                ", PayAmount='" + PayAmount + '\'' +
                ", CouponID='" + CouponID + '\'' +
                ", OrderDealLog='" + OrderDealLog + '\'' +
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
                ", InvoiceStatus='" + InvoiceStatus + '\'' +
                ", OrderDetails=" + OrderDetails +
                '}';
    }
}
