package com.example.sg280.fotile.model.bean;

/**
 * 产品图片的实体类
 * Created by Tian on 2016/8/9.
 */
public class ProductImageBean {

    private String ID;

    private String ProductID;

    private String ImageName;

    private String ImageURL;

    private String ImageUrlSer;

    private String IsPix;

    public String getIsPix() {
        return IsPix;
    }

    public void setIsPix(String isPix) {
        IsPix = isPix;
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

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getImageUrlSer() {
        return ImageUrlSer;
    }

    public void setImageUrlSer(String imageUrlSer) {
        ImageUrlSer = imageUrlSer;
    }

    @Override
    public String toString() {
        return "ProductImageBean{" +
                "ID='" + ID + '\'' +
                ", ProductID='" + ProductID + '\'' +
                ", ImageName='" + ImageName + '\'' +
                ", ImageURL='" + ImageURL + '\'' +
                ", ImageUrlSer='" + ImageUrlSer + '\'' +
                ", IsPix='" + IsPix + '\'' +
                '}';
    }
}
