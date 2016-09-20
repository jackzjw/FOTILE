package com.example.sg280.fotile.model.bean;

import java.util.List;

/**
 * 品牌的实体类（为了适用多品牌）
 * Created by sg027 on 2016/9/2.
 */
public class BrandBean {


    /**
     * ID : 1
     * BrandName : 方太
     * BrandCode : FOTILE
     * BrandLOGO : fotile_logo.png
     * BrandLOGO2 : http://zhiboimg01.efotile.net/UploadFiles/brand/fotile_logo2.png
     * BrandLOGO2Ser : http://zhiboimg01.efotile.net/UploadFiles/brand/fotile_logo2.png
     * BrandLOGOSer : http://zhiboimg01.efotile.net/UploadFiles/brand/fotile_logo.png
     * BrandBanner : fotile_banner.jpg
     * BrandBannerSer : http://zhiboimg01.efotile.net/UploadFiles/brand/fotile_banner.jpg
     * BrandIntro : 方太集团创建于1996年，二十年来忠于初心，始终专注于高端厨电领域，坚持“专业、高端、
                    负责”的战略性定位，向着成为一家伟大的企业的愿景迈进。公司不断致力于为追求高品质生活的人们，
                    提供无与伦比的高品质产品和服务，打造健康环保有品位有文化的生活方式，
                    让千万家庭享受更加幸福安心的生活，业已成为高端厨电领导者。
     *ShopCarList:List<ShoppingCartGoodsBean>
     */

    private String ID;
    private String BrandName;
    private String BrandCode;
    private String BrandLOGO;
    private String BrandLOGO2;
    private String BrandLOGO2Ser;
    private String BrandLOGOSer;
    private String BrandBanner;
    private String BrandBannerSer;
    private String BrandIntro;
    private List<ShoppingCartGoodsBean> ShopCarList;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String BrandName) {
        this.BrandName = BrandName;
    }

    public String getBrandCode() {
        return BrandCode;
    }

    public void setBrandCode(String BrandCode) {
        this.BrandCode = BrandCode;
    }

    public String getBrandLOGO() {
        return BrandLOGO;
    }

    public void setBrandLOGO(String BrandLOGO) {
        this.BrandLOGO = BrandLOGO;
    }

    public String getBrandLOGO2() {
        return BrandLOGO2;
    }

    public void setBrandLOGO2(String BrandLOGO2) {
        this.BrandLOGO2 = BrandLOGO2;
    }

    public String getBrandLOGO2Ser() {
        return BrandLOGO2Ser;
    }

    public void setBrandLOGO2Ser(String BrandLOGO2Ser) {
        this.BrandLOGO2Ser = BrandLOGO2Ser;
    }

    public String getBrandLOGOSer() {
        return BrandLOGOSer;
    }

    public void setBrandLOGOSer(String BrandLOGOSer) {
        this.BrandLOGOSer = BrandLOGOSer;
    }

    public String getBrandBanner() {
        return BrandBanner;
    }

    public void setBrandBanner(String BrandBanner) {
        this.BrandBanner = BrandBanner;
    }

    public String getBrandBannerSer() {
        return BrandBannerSer;
    }

    public void setBrandBannerSer(String BrandBannerSer) {
        this.BrandBannerSer = BrandBannerSer;
    }

    public String getBrandIntro() {
        return BrandIntro;
    }

    public void setBrandIntro(String BrandIntro) {
        this.BrandIntro = BrandIntro;
    }

    public List<ShoppingCartGoodsBean> getList() {
        return ShopCarList;
    }

    public void setList(List<ShoppingCartGoodsBean> list) {
        this.ShopCarList = ShopCarList;
    }

    @Override
    public String toString() {
        return "BrandBean{" +
                "ID='" + ID + '\'' +
                ", BrandName='" + BrandName + '\'' +
                ", BrandCode='" + BrandCode + '\'' +
                ", BrandLOGO='" + BrandLOGO + '\'' +
                ", BrandLOGO2='" + BrandLOGO2 + '\'' +
                ", BrandLOGO2Ser='" + BrandLOGO2Ser + '\'' +
                ", BrandLOGOSer='" + BrandLOGOSer + '\'' +
                ", BrandBanner='" + BrandBanner + '\'' +
                ", BrandBannerSer='" + BrandBannerSer + '\'' +
                ", BrandIntro='" + BrandIntro + '\'' +
                ", ShopCarList=" + ShopCarList +
                '}';
    }
}
