package com.example.sg280.fotile.model.bean;

import java.util.List;

/**
 * Created by sg280 on 2016-08-22.
 */
public class ProductsBean  {


    /**
     * ID : 产品id
     * BrandID : 品牌ID
     * ClassID : 产品分类
     * ProductName : 产品名称
     * ProductSubhead : 产品副标题
     * ProductCode : 产品编码
     * PriceCommon : 市场价
     * PricePromo : 优惠价
     * ProductDetail : 产品简介
     * IsHot : 是否热门
     * IsFav : 是否收藏
     * ProductSpec : 规格参数
     * ProductUrl : 产品链接
     * HitCount : 点击次数
     * ProductStatus : 产品状态
     * IsShown : 是否前台显示
     * FavID : 收藏ID
     * ShopCartCount : 购物车数量
     * ProImgPix : 图片地址
     */

    private String ID;
    private String BrandID;
    private String ClassID;
    private String ProductName;
    private String ProductSubhead;
    private String ProductCode;
    private String PriceCommon;
    private String PricePromo;
 //   private String ProductDetail;
    private String IsHot;
    private String IsFav;
    private String ProductSpec;
    private String ProductUrl;
    private String HitCount;
    private String ProductStatus;
    private String IsShown;
    private String FavID;
    private String ShopCartCount;
    private String ProImgPix;
    private List<ImageListBean> Imglist;
    private List<SkuListBean> SkuList;

    public List<ImageListBean> getImglist() {
        return Imglist;
    }

    public void setImglist(List<ImageListBean> imglist) {
        Imglist = imglist;
    }

    public List<SkuListBean> getSkuList() {
        return SkuList;
    }

    public void setSkuList(List<SkuListBean> skuList) {
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

    public void setBrandID(String BrandID) {
        this.BrandID = BrandID;
    }

    public String getClassID() {
        return ClassID;
    }

    public void setClassID(String ClassID) {
        this.ClassID = ClassID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getProductSubhead() {
        return ProductSubhead;
    }

    public void setProductSubhead(String ProductSubhead) {
        this.ProductSubhead = ProductSubhead;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String ProductCode) {
        this.ProductCode = ProductCode;
    }

    public String getPriceCommon() {
        return PriceCommon;
    }

    public void setPriceCommon(String PriceCommon) {
        this.PriceCommon = PriceCommon;
    }

    public String getPricePromo() {
        return PricePromo;
    }

    public void setPricePromo(String PricePromo) {
        this.PricePromo = PricePromo;
    }

  /*  public String getProductDetail() {
        return ProductDetail;
    }

    public void setProductDetail(String ProductDetail) {
        this.ProductDetail = ProductDetail;
    }
*/
    public String getIsHot() {
        return IsHot;
    }

    public void setIsHot(String IsHot) {
        this.IsHot = IsHot;
    }

    public String getIsFav() {
        return IsFav;
    }

    public void setIsFav(String IsFav) {
        this.IsFav = IsFav;
    }

    public String getProductSpec() {
        return ProductSpec;
    }

    public void setProductSpec(String ProductSpec) {
        this.ProductSpec = ProductSpec;
    }

    public String getProductUrl() {
        return ProductUrl;
    }

    public void setProductUrl(String ProductUrl) {
        this.ProductUrl = ProductUrl;
    }

    public String getHitCount() {
        return HitCount;
    }

    public void setHitCount(String HitCount) {
        this.HitCount = HitCount;
    }

    public String getProductStatus() {
        return ProductStatus;
    }

    public void setProductStatus(String ProductStatus) {
        this.ProductStatus = ProductStatus;
    }

    public String getIsShown() {
        return IsShown;
    }

    public void setIsShown(String IsShown) {
        this.IsShown = IsShown;
    }

    public String getFavID() {
        return FavID;
    }

    public void setFavID(String FavID) {
        this.FavID = FavID;
    }

    public String getShopCartCount() {
        return ShopCartCount;
    }

    public void setShopCartCount(String ShopCartCount) {
        this.ShopCartCount = ShopCartCount;
    }

    public String getProImgPix() {
        return ProImgPix;
    }

    public void setProImgPix(String ProImgPix) {
        this.ProImgPix = ProImgPix;
    }
    public static class ImageListBean{

        /**
         * ID : 图片ID
         * ProductID : 产品ID
         * ImageName : 图片名
         * ImageURL : 图片文件名
         * ImageUrlSer : 图片完整地址
         * IsPix : 是否主图
         */

        private String ID;
        private String ProductID;
        private String ImageName;
        private String ImageURL;
        private String ImageUrlSer;
        private String IsPix;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getProductID() {
            return ProductID;
        }

        public void setProductID(String ProductID) {
            this.ProductID = ProductID;
        }

        public String getImageName() {
            return ImageName;
        }

        public void setImageName(String ImageName) {
            this.ImageName = ImageName;
        }

        public String getImageURL() {
            return ImageURL;
        }

        public void setImageURL(String ImageURL) {
            this.ImageURL = ImageURL;
        }

        public String getImageUrlSer() {
            return ImageUrlSer;
        }

        public void setImageUrlSer(String ImageUrlSer) {
            this.ImageUrlSer = ImageUrlSer;
        }

        public String getIsPix() {
            return IsPix;
        }

        public void setIsPix(String IsPix) {
            this.IsPix = IsPix;
        }

        @Override
        public String toString() {
            return "ImageListBean{" +
                    "ID='" + ID + '\'' +
                    ", ProductID='" + ProductID + '\'' +
                    ", ImageName='" + ImageName + '\'' +
                    ", ImageURL='" + ImageURL + '\'' +
                    ", ImageUrlSer='" + ImageUrlSer + '\'' +
                    ", IsPix='" + IsPix + '\'' +
                    '}';
        }
    }
  public    static class SkuListBean{

        /**
         * ID : skuID
         * ProductID : 产品ID
         * SkuName : SKU名称
         * PriceCommon : sku市场价
         * PricePromo : sku优惠价
         * SkuImage : SKU图片
         * SkuImageSer : sku图片地址
         * SkuStatus : sku状态
         */

        private String ID;
        private String ProductID;
        private String SkuName;
        private String PriceCommon;
        private String PricePromo;
        private String SkuImage;
        private String SkuImageSer;
        private String SkuStatus;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getProductID() {
            return ProductID;
        }

        public void setProductID(String ProductID) {
            this.ProductID = ProductID;
        }

        public String getSkuName() {
            return SkuName;
        }

        public void setSkuName(String SkuName) {
            this.SkuName = SkuName;
        }

        public String getPriceCommon() {
            return PriceCommon;
        }

        public void setPriceCommon(String PriceCommon) {
            this.PriceCommon = PriceCommon;
        }

        public String getPricePromo() {
            return PricePromo;
        }

        public void setPricePromo(String PricePromo) {
            this.PricePromo = PricePromo;
        }

        public String getSkuImage() {
            return SkuImage;
        }

        public void setSkuImage(String SkuImage) {
            this.SkuImage = SkuImage;
        }

        public String getSkuImageSer() {
            return SkuImageSer;
        }

        public void setSkuImageSer(String SkuImageSer) {
            this.SkuImageSer = SkuImageSer;
        }

        public String getSkuStatus() {
            return SkuStatus;
        }

        public void setSkuStatus(String SkuStatus) {
            this.SkuStatus = SkuStatus;
        }

      @Override
      public String toString() {
          return "SkuListBean{" +
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

    @Override
    public String toString() {
        return "ProductsBean{" +
                "ID='" + ID + '\'' +
                ", BrandID='" + BrandID + '\'' +
                ", ClassID='" + ClassID + '\'' +
                ", ProductName='" + ProductName + '\'' +
                ", ProductSubhead='" + ProductSubhead + '\'' +
                ", ProductCode='" + ProductCode + '\'' +
                ", PriceCommon='" + PriceCommon + '\'' +
                ", PricePromo='" + PricePromo + '\'' +
                ", ProductDetail='" + "" + '\'' +
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
                ", imageListBeans=" + Imglist +
                ", skuListBeans=" + SkuList +
                '}';
    }
}
