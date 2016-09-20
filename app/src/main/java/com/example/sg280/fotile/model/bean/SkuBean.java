package com.example.sg280.fotile.model.bean;

/**
 * Sku实体类（比如一个燃气灶，sku就分为液化气型和天然气型，
 * 燃气灶是一个产品，液化气和天然气就是这个产品的sku）
 * Created by Tian on 2016/8/9.
 */
public class SkuBean {

    private String ID;//skuID
    private String ProductID;//产品ID
    private String SkuName;//SKU名称
    private String PriceCommon;//市场价
    private String PricePromo;//优惠价
    private String SkuImage;//SKU图片
    private String SkuImageSer;//sku图片地址
    private String SkuStatus;//sku状态   1：上架；0下架

    public String getSkuStatus() {
        return SkuStatus;
    }

    public void setSkuStatus(String skuStatus) {
        SkuStatus = skuStatus;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getSkuName() {
        return SkuName;
    }

    public void setSkuName(String skuName) {
        SkuName = skuName;
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

    public String getSkuImage() {
        return SkuImage;
    }

    public void setSkuImage(String skuImage) {
        SkuImage = skuImage;
    }

    public String getSkuImageSer() {
        return SkuImageSer;
    }

    public void setSkuImageSer(String skuImageSer) {
        SkuImageSer = skuImageSer;
    }

    @Override
    public String toString() {
        return "SkuBean{" +
                "ID='" + ID + '\'' +
                ", ProductID='" + ProductID + '\'' +
                ", SkuName='" + SkuName + '\'' +
                ", PriceCommon='" + PriceCommon + '\'' +
                ", PricePromo='" + PricePromo + '\'' +
                ", SkuImage='" + SkuImage + '\'' +
                ", SkuImageSer='" + SkuImageSer + '\'' +
                ", SkuStatus='" + SkuStatus + '\'' +
                '}';
    }
}
