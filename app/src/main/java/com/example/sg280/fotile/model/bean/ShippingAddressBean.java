package com.example.sg280.fotile.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Tian on 2016/8/22.
 */
public class ShippingAddressBean implements Parcelable {

    private String ID;//地址ID
    private String UserID;//用户ID
    private String Pcr;//省市区
    private String ProvinceID;//省
    private String CityID;//市
    private String RegionID;//区
    private String Address;//详细地址
    private String Recipients;//收货人
    private String Tel;//电话
    private String Postcode;//邮编
    private String IsDefault;//是否默认

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIsDefault() {
        return IsDefault;
    }

    public void setIsDefault(String isDefault) {
        IsDefault = isDefault;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getPcr() {
        return Pcr;
    }

    public void setPcr(String pcr) {
        Pcr = pcr;
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

    public String getPostcode() {
        return Postcode;
    }

    public void setPostcode(String postcode) {
        Postcode = postcode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ID);
        dest.writeString(this.UserID);
        dest.writeString(this.Pcr);
        dest.writeString(this.ProvinceID);
        dest.writeString(this.CityID);
        dest.writeString(this.RegionID);
        dest.writeString(this.Address);
        dest.writeString(this.Recipients);
        dest.writeString(this.Tel);
        dest.writeString(this.Postcode);
        dest.writeString(this.IsDefault);
    }

    public ShippingAddressBean() {
    }

    protected ShippingAddressBean(Parcel in) {
        this.ID = in.readString();
        this.UserID = in.readString();
        this.Pcr = in.readString();
        this.ProvinceID = in.readString();
        this.CityID = in.readString();
        this.RegionID = in.readString();
        this.Address = in.readString();
        this.Recipients = in.readString();
        this.Tel = in.readString();
        this.Postcode = in.readString();
        this.IsDefault = in.readString();
    }

    public static final Parcelable.Creator<ShippingAddressBean> CREATOR = new Parcelable.Creator<ShippingAddressBean>() {
        @Override
        public ShippingAddressBean createFromParcel(Parcel source) {
            return new ShippingAddressBean(source);
        }

        @Override
        public ShippingAddressBean[] newArray(int size) {
            return new ShippingAddressBean[size];
        }
    };

    @Override
    public String toString() {
        return "ShippingAddressBean{" +
                "ID='" + ID + '\'' +
                ", UserID='" + UserID + '\'' +
                ", Pcr='" + Pcr + '\'' +
                ", ProvinceID='" + ProvinceID + '\'' +
                ", CityID='" + CityID + '\'' +
                ", RegionID='" + RegionID + '\'' +
                ", Address='" + Address + '\'' +
                ", Recipients='" + Recipients + '\'' +
                ", Tel='" + Tel + '\'' +
                ", Postcode='" + Postcode + '\'' +
                ", IsDefault='" + IsDefault + '\'' +
                '}';
    }
}
