package com.example.sg280.fotile.model.bean;

import java.util.List;

/**
 * 我的收藏中的商品的实体类
 * Created by Tian on 2016/8/9.
 */
public class CollectProductBean {

    private String ID;//产品id
    private String BrandID;// 品牌ID
    private String ClassID;//产品分类
    private String ProductName;//产品名称
    private String ProductSubhead;//产品副标题
    private String ProductCode;//产品编码
    private String PriceCommon;//市场价
    private String PricePromo;//优惠价
    private String ProductDetail;//产品简介
    private String IsHot;//是否热门
    private String IsFav;//是否收藏
    private String ProductSpec;//规格参数
    private String ProductUrl;//产品链接
    private String HitCount;//点击次数
    private String ProductStatus;//产品状态, 1：上架；0下架
    private String FavID;//收藏ID
    private String ProImgPix;//图片地址
    private List<ProductImageBean> Imglist;//产品图片集合
    private List<SkuBean> SkuList;//产品sku集合

    public List<SkuBean> getSkuList() {
        return SkuList;
    }

    public void setSkuList(List<SkuBean> skuList) {
        SkuList = skuList;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getBrandID() {
        return BrandID;
    }

    public void setBrandID(String brandID) {
        BrandID = brandID;
    }

    public String getClassID() {
        return ClassID;
    }

    public void setClassID(String classID) {
        ClassID = classID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductSubhead() {
        return ProductSubhead;
    }

    public void setProductSubhead(String productSubhead) {
        ProductSubhead = productSubhead;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
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

    public String getProductDetail() {
        return ProductDetail;
    }

    public void setProductDetail(String productDetail) {
        ProductDetail = productDetail;
    }

    public String getIsHot() {
        return IsHot;
    }

    public void setIsHot(String isHot) {
        IsHot = isHot;
    }

    public String getIsFav() {
        return IsFav;
    }

    public void setIsFav(String isFav) {
        IsFav = isFav;
    }

    public String getProductSpec() {
        return ProductSpec;
    }

    public void setProductSpec(String productSpec) {
        ProductSpec = productSpec;
    }

    public String getProductUrl() {
        return ProductUrl;
    }

    public void setProductUrl(String productUrl) {
        ProductUrl = productUrl;
    }

    public String getHitCount() {
        return HitCount;
    }

    public void setHitCount(String hitCount) {
        HitCount = hitCount;
    }

    public String getProductStatus() {
        return ProductStatus;
    }

    public void setProductStatus(String productStatus) {
        ProductStatus = productStatus;
    }

    public String getFavID() {
        return FavID;
    }

    public void setFavID(String favID) {
        FavID = favID;
    }

    public String getProImgPix() {
        return ProImgPix;
    }

    public void setProImgPix(String proImgPix) {
        ProImgPix = proImgPix;
    }

    public List<ProductImageBean> getImglist() {
        return Imglist;
    }

    public void setImglist(List<ProductImageBean> imglist) {
        Imglist = imglist;
    }

    @Override
    public String toString() {
        return "CollectProductBean{" +
                "ID='" + ID + '\'' +
                ", BrandID='" + BrandID + '\'' +
                ", ClassID='" + ClassID + '\'' +
                ", ProductName='" + ProductName + '\'' +
                ", ProductSubhead='" + ProductSubhead + '\'' +
                ", ProductCode='" + ProductCode + '\'' +
                ", PriceCommon='" + PriceCommon + '\'' +
                ", PricePromo='" + PricePromo + '\'' +
                ", ProductDetail='" + ProductDetail + '\'' +
                ", IsHot='" + IsHot + '\'' +
                ", IsFav='" + IsFav + '\'' +
                ", ProductSpec='" + ProductSpec + '\'' +
                ", ProductUrl='" + ProductUrl + '\'' +
                ", HitCount='" + HitCount + '\'' +
                ", ProductStatus='" + ProductStatus + '\'' +
                ", FavID='" + FavID + '\'' +
                ", ProImgPix='" + ProImgPix + '\'' +
                ", Imglist=" + Imglist +
                ", SkuList=" + SkuList +
                '}';
    }
}
