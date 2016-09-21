package com.example.sg280.fotile.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sg280 on 2016-07-18.
 */
public class VedioBean implements Parcelable {

    private  String ID;//直播ID,
    private  String LiveName;// "直播名称",
    private  String  ClassID;// 直播类型ID,
    private  String  ChannelID;// "腾讯云直播频道号",
    private  String  StatusCode;// 直播状态码,
    private  String StatusName;// "直播状态名称",		1：正在直播；2：即将开始；3：直播完毕；4
    private  String  BrandID;// 品牌ID,
    private  String BrandName;//"品牌名称",
    private  String  LiveType;// 播放类型,		(用不到)
    private  String  LivePix;// "直播文件名",
    private  String LivePixSer;// "/UploadFiles/201606131642292861.jpg",封面图片地址
    private  String  StartTime;// "开始时间",
    private  String  PreStartTime;// "预热开始时间",
    private  String   EndTime;//"结束时间",
    private  String ClassName;// "直播",
    private  String URL;// "视频URL",
    private  String LiveInfo;// "视频介绍",
    private  String  HitCount;//点击次数,
    private  String  CommentCount;// 评论次数,
    private  String IsHot;// 是否热门,
    private  String  MasterUserID;// 直播主号

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getLiveName() {
        return LiveName;
    }

    public void setLiveName(String liveName) {
        LiveName = liveName;
    }

    public String getClassID() {
        return ClassID;
    }

    public void setClassID(String classID) {
        ClassID = classID;
    }

    public String getChannelID() {
        return ChannelID;
    }

    public void setChannelID(String channelID) {
        ChannelID = channelID;
    }

    public String getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(String statusCode) {
        StatusCode = statusCode;
    }

    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String statusName) {
        StatusName = statusName;
    }

    public String getBrandID() {
        return BrandID;
    }

    public void setBrandID(String brandID) {
        BrandID = brandID;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public String getLiveType() {
        return LiveType;
    }

    public void setLiveType(String liveType) {
        LiveType = liveType;
    }

    public String getLivePix() {
        return LivePix;
    }

    public void setLivePix(String livePix) {
        LivePix = livePix;
    }

    public String getLivePixSer() {
        return LivePixSer;
    }

    public void setLivePixSer(String livePixSer) {
        LivePixSer = livePixSer;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getPreStartTime() {
        return PreStartTime;
    }

    public void setPreStartTime(String preStartTime) {
        PreStartTime = preStartTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getLiveInfo() {
        return LiveInfo;
    }

    public void setLiveInfo(String liveInfo) {
        LiveInfo = liveInfo;
    }

    public String getHitCount() {
        return HitCount;
    }

    public void setHitCount(String hitCount) {
        HitCount = hitCount;
    }

    public String getCommentCount() {
        return CommentCount;
    }

    public void setCommentCount(String commentCount) {
        CommentCount = commentCount;
    }

    public String getIsHot() {
        return IsHot;
    }

    public void setIsHot(String isHot) {
        IsHot = isHot;
    }

    public String getMasterUserID() {
        return MasterUserID;
    }

    public void setMasterUserID(String masterUserID) {
        MasterUserID = masterUserID;
    }

    @Override
    public String toString() {
        return "VedioBean{" +
                "ID='" + ID + '\'' +
                ", LiveName='" + LiveName + '\'' +
                ", ClassID='" + ClassID + '\'' +
                ", ChannelID='" + ChannelID + '\'' +
                ", StatusCode='" + StatusCode + '\'' +
                ", StatusName='" + StatusName + '\'' +
                ", BrandID='" + BrandID + '\'' +
                ", BrandName='" + BrandName + '\'' +
                ", LiveType='" + LiveType + '\'' +
                ", LivePix='" + LivePix + '\'' +
                ", LivePixSer='" + LivePixSer + '\'' +
                ", StartTime='" + StartTime + '\'' +
                ", PreStartTime='" + PreStartTime + '\'' +
                ", EndTime='" + EndTime + '\'' +
                ", ClassName='" + ClassName + '\'' +
                ", URL='" + URL + '\'' +
                ", LiveInfo='" + LiveInfo + '\'' +
                ", HitCount='" + HitCount + '\'' +
                ", CommentCount='" + CommentCount + '\'' +
                ", IsHot='" + IsHot + '\'' +
                ", MasterUserID='" + MasterUserID + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ID);
        dest.writeString(this.LiveName);
        dest.writeString(this.ClassID);
        dest.writeString(this.ChannelID);
        dest.writeString(this.StatusCode);
        dest.writeString(this.StatusName);
        dest.writeString(this.BrandID);
        dest.writeString(this.BrandName);
        dest.writeString(this.LiveType);
        dest.writeString(this.LivePix);
        dest.writeString(this.LivePixSer);
        dest.writeString(this.StartTime);
        dest.writeString(this.PreStartTime);
        dest.writeString(this.EndTime);
        dest.writeString(this.ClassName);
        dest.writeString(this.URL);
        dest.writeString(this.LiveInfo);
        dest.writeString(this.HitCount);
        dest.writeString(this.CommentCount);
        dest.writeString(this.IsHot);
        dest.writeString(this.MasterUserID);
    }

    public VedioBean() {
    }

    protected VedioBean(Parcel in) {
        this.ID = in.readString();
        this.LiveName = in.readString();
        this.ClassID = in.readString();
        this.ChannelID = in.readString();
        this.StatusCode = in.readString();
        this.StatusName = in.readString();
        this.BrandID = in.readString();
        this.BrandName = in.readString();
        this.LiveType = in.readString();
        this.LivePix = in.readString();
        this.LivePixSer = in.readString();
        this.StartTime = in.readString();
        this.PreStartTime = in.readString();
        this.EndTime = in.readString();
        this.ClassName = in.readString();
        this.URL = in.readString();
        this.LiveInfo = in.readString();
        this.HitCount = in.readString();
        this.CommentCount = in.readString();
        this.IsHot = in.readString();
        this.MasterUserID = in.readString();
    }

    public static final Parcelable.Creator<VedioBean> CREATOR = new Parcelable.Creator<VedioBean>() {
        @Override
        public VedioBean createFromParcel(Parcel source) {
            return new VedioBean(source);
        }

        @Override
        public VedioBean[] newArray(int size) {
            return new VedioBean[size];
        }
    };
}
