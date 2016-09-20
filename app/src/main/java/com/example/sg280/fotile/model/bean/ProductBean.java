package com.example.sg280.fotile.model.bean;

/**
 * 产品的实体类
 * Created by Tian on 2016/8/8.
 */
public class ProductBean {

    private String ID;//订单详情ID

    private String OrderID;//订单ID,

    private String ProductID;//产品ID,

    private String ProductName;//"产品名称",

    private String SkuID;//

    private String SkuName;//sku名称

    private String ProductQuantity;//产品数量,

    private String PriceCommon;//市场价,

    private String PricePromo;//优惠价

    private String SkuImg;//产品sku图片

    public String getSkuImg() {
        return SkuImg;
    }

    public void setSkuImg(String skuImg) {
        SkuImg = skuImg;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getSkuID() {
        return SkuID;
    }

    public void setSkuID(String skuID) {
        SkuID = skuID;
    }

    public String getSkuName() {
        return SkuName;
    }

    public void setSkuName(String skuName) {
        SkuName = skuName;
    }

    public String getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        ProductQuantity = productQuantity;
    }

    public String getPriceCommon() {
        return PriceCommon;
    }

    public void setPriceCommon(String priceCommon) {
        PriceCommon = priceCommon;
    }

    public String getPricePromo() {
        return PricePromo;
    }

    public void setPricePromo(String pricePromo) {
        PricePromo = pricePromo;
    }

    @Override
    public String toString() {
        return "ProductBean{" +
                "ID='" + ID + '\'' +
                ", OrderID='" + OrderID + '\'' +
                ", ProductID='" + ProductID + '\'' +
                ", ProductName='" + ProductName + '\'' +
                ", SkuID='" + SkuID + '\'' +
                ", SkuName='" + SkuName + '\'' +
                ", ProductQuantity='" + ProductQuantity + '\'' +
                ", PriceCommon='" + PriceCommon + '\'' +
                ", PricePromo='" + PricePromo + '\'' +
                ", SkuImg='" + SkuImg + '\'' +
                '}';
    }
}
