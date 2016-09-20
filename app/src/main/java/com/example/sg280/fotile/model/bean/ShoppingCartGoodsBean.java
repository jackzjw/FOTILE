package com.example.sg280.fotile.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Tian on 2016/8/15.
 */
public class ShoppingCartGoodsBean implements Parcelable {

    private String ID;//购物车ID
    private String BrandID;//品牌ID
    private String UserID;//用户ID
    private String ProductID;//产品ID
    private String SkuID;//
    private String ProductQuantity;//产品数量
    private String ProductName;//产品名
    private String SkuName;//sku名"
    private String PriceCommon;//市场价
    private String PricePromo;//优惠价
    private String SkuStatus;//SKU状态
    private String ProductStatus;//产品状态
    private String ProIsVaild;//产品是否有效
    private String SkuImage;//sku图片名
    private String SkuImageSer;//sku图片地址
    private String tempUserID;
    private String IsSaleOut;
    private String ShopCartAmount;

    private boolean isChecked;//返回数据中没有，自己手动添加的一项，用来记录这个item的选择状态

    public String getShopCartAmount() {
        return ShopCartAmount;
    }

    public void setShopCartAmount(String shopCartAmount) {
        ShopCartAmount = shopCartAmount;
    }

    public String getTempUserID() {
        return tempUserID;
    }

    public void setTempUserID(String tempUserID) {
        this.tempUserID = tempUserID;
    }

    public String getIsSaleOut() {
        return IsSaleOut;
    }

    public void setIsSaleOut(String isSaleOut) {
        IsSaleOut = isSaleOut;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getSkuImageSer() {
        return SkuImageSer;
    }

    public void setSkuImageSer(String skuImageSer) {
        SkuImageSer = skuImageSer;
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

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getSkuID() {
        return SkuID;
    }

    public void setSkuID(String skuID) {
        SkuID = skuID;
    }

    public String getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        ProductQuantity = productQuantity;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
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

    public String getSkuStatus() {
        return SkuStatus;
    }

    public void setSkuStatus(String skuStatus) {
        SkuStatus = skuStatus;
    }

    public String getProductStatus() {
        return ProductStatus;
    }

    public void setProductStatus(String productStatus) {
        ProductStatus = productStatus;
    }

    public String getProIsVaild() {
        return ProIsVaild;
    }

    public void setProIsVaild(String proIsVaild) {
        ProIsVaild = proIsVaild;
    }

    public String getSkuImage() {
        return SkuImage;
    }

    public void setSkuImage(String skuImage) {
        SkuImage = skuImage;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ID);
        dest.writeString(this.BrandID);
        dest.writeString(this.UserID);
        dest.writeString(this.ProductID);
        dest.writeString(this.SkuID);
        dest.writeString(this.ProductQuantity);
        dest.writeString(this.ProductName);
        dest.writeString(this.SkuName);
        dest.writeString(this.PriceCommon);
        dest.writeString(this.PricePromo);
        dest.writeString(this.SkuStatus);
        dest.writeString(this.ProductStatus);
        dest.writeString(this.ProIsVaild);
        dest.writeString(this.SkuImage);
        dest.writeString(this.SkuImageSer);
        dest.writeString(this.tempUserID);
        dest.writeString(this.IsSaleOut);
        dest.writeString(this.ShopCartAmount);
        dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
    }

    public ShoppingCartGoodsBean() {
    }

    protected ShoppingCartGoodsBean(Parcel in) {
        this.ID = in.readString();
        this.BrandID = in.readString();
        this.UserID = in.readString();
        this.ProductID = in.readString();
        this.SkuID = in.readString();
        this.ProductQuantity = in.readString();
        this.ProductName = in.readString();
        this.SkuName = in.readString();
        this.PriceCommon = in.readString();
        this.PricePromo = in.readString();
        this.SkuStatus = in.readString();
        this.ProductStatus = in.readString();
        this.ProIsVaild = in.readString();
        this.SkuImage = in.readString();
        this.SkuImageSer = in.readString();
        this.tempUserID = in.readString();
        this.IsSaleOut = in.readString();
        this.ShopCartAmount = in.readString();
        this.isChecked = in.readByte() != 0;
    }

    public static final Creator<ShoppingCartGoodsBean> CREATOR = new Creator<ShoppingCartGoodsBean>() {
        @Override
        public ShoppingCartGoodsBean createFromParcel(Parcel source) {
            return new ShoppingCartGoodsBean(source);
        }

        @Override
        public ShoppingCartGoodsBean[] newArray(int size) {
            return new ShoppingCartGoodsBean[size];
        }
    };

    @Override
    public String toString() {
        return "ShoppingCartGoodsBean{" +
                "ID='" + ID + '\'' +
                ", BrandID='" + BrandID + '\'' +
                ", UserID='" + UserID + '\'' +
                ", ProductID='" + ProductID + '\'' +
                ", SkuID='" + SkuID + '\'' +
                ", ProductQuantity='" + ProductQuantity + '\'' +
                ", ProductName='" + ProductName + '\'' +
                ", SkuName='" + SkuName + '\'' +
                ", PriceCommon='" + PriceCommon + '\'' +
                ", PricePromo='" + PricePromo + '\'' +
                ", SkuStatus='" + SkuStatus + '\'' +
                ", ProductStatus='" + ProductStatus + '\'' +
                ", ProIsVaild='" + ProIsVaild + '\'' +
                ", SkuImage='" + SkuImage + '\'' +
                ", SkuImageSer='" + SkuImageSer + '\'' +
                ", tempUserID='" + tempUserID + '\'' +
                ", IsSaleOut='" + IsSaleOut + '\'' +
                ", ShopCartAmount='" + ShopCartAmount + '\'' +
                ", isChecked=" + isChecked +
                '}';
    }
}
