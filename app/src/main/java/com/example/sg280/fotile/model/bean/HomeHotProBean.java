package com.example.sg280.fotile.model.bean;

/**
 * Created by sg280 on 2016/9/5.
 */
public class HomeHotProBean {
  private String ID;// ID,
  private String BrandID;// 品牌ID,
  private String ClassID;// 产品分类,
  private String ProductName;// "产品名称",
  private String ProductSubhead;// "产品副标题",
  private String ProductCode;// "产品编码",
  private String PriceCommon;// 市场价,
  private String PricePromo;// 优惠价,
  private String ProductDetail;// "产品简介",
  private String IsHot;// 是否热门,
  private String IsFav;//"是否收藏"
  private String ProductSpec;// "规格参数",
  private String ProductUrl;//"产品链接"
  private String HitCount;// 点击次数,
  private String ProductStatus;// 产品状态,
  private String IsShown;//"是否前台显示"
  private String FavID;//"收藏ID"
  private String ShopCartCount;//"购物车数量"
  private String ProImgPix;//"图片地址"

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

    public String getIsShown() {
        return IsShown;
    }

    public void setIsShown(String isShown) {
        IsShown = isShown;
    }

    public String getFavID() {
        return FavID;
    }

    public void setFavID(String favID) {
        FavID = favID;
    }

    public String getShopCartCount() {
        return ShopCartCount;
    }

    public void setShopCartCount(String shopCartCount) {
        ShopCartCount = shopCartCount;
    }

    public String getProImgPPix() {
        return ProImgPix;
    }

    public void setProImgPPix(String proImgPPix) {
        ProImgPix = proImgPPix;
    }

    @Override
    public String toString() {
        return "HomeHotProBean{" +
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
                ", IsShown='" + IsShown + '\'' +
                ", FavID='" + FavID + '\'' +
                ", ShopCartCount='" + ShopCartCount + '\'' +
                ", ProImgPix='" + ProImgPix + '\'' +
                '}';
    }
}
