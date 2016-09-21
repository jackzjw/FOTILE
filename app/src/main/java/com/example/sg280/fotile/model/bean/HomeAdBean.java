package com.example.sg280.fotile.model.bean;

/**
 * Created by sg280 on 2016/9/2.
 */
public class HomeAdBean {
 private String  Id;//:"广告ID"
 private String  PosID;// 父ID,
 private String  ADName;//"劲魔方上市",
 private String  IsLimited;// 有无时间限制,   1  限制  0 不限制
 private String  OnlineTime;//上线时间,
 private String  OfflineTime;//下线时间,
 private String  FileName;// "http://zhiboimg01.efotile.net/UploadFiles/banner/kv_pro.jpg",图片名称
 private String  LinkTarget;// "链接目标",  _self 当前窗口       _blank  新窗口
 private String  LinkURL;// "链接地址",
 private String  LinkTitle;// "显示文字",
 private String  OrderBy;// 排序,
 private String  HitCount;//点击量,
 private String  AdStatus;// 状态    1 上线   0 下线

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPosID() {
        return PosID;
    }

    public void setPosID(String posID) {
        PosID = posID;
    }

    public String getADName() {
        return ADName;
    }

    public void setADName(String ADName) {
        this.ADName = ADName;
    }

    public String getIsLimited() {
        return IsLimited;
    }

    public void setIsLimited(String isLimited) {
        IsLimited = isLimited;
    }

    public String getOnlineTime() {
        return OnlineTime;
    }

    public void setOnlineTime(String onlineTime) {
        OnlineTime = onlineTime;
    }

    public String getOfflineTime() {
        return OfflineTime;
    }

    public void setOfflineTime(String offlineTime) {
        OfflineTime = offlineTime;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getLinkTarget() {
        return LinkTarget;
    }

    public void setLinkTarget(String linkTarget) {
        LinkTarget = linkTarget;
    }

    public String getLinkURL() {
        return LinkURL;
    }

    public void setLinkURL(String linkURL) {
        LinkURL = linkURL;
    }

    public String getLinkTitle() {
        return LinkTitle;
    }

    public void setLinkTitle(String linkTitle) {
        LinkTitle = linkTitle;
    }

    public String getOrderBy() {
        return OrderBy;
    }

    public void setOrderBy(String orderBy) {
        OrderBy = orderBy;
    }

    public String getHitCount() {
        return HitCount;
    }

    public void setHitCount(String hitCount) {
        HitCount = hitCount;
    }

    public String getAdStatus() {
        return AdStatus;
    }

    public void setAdStatus(String adStatus) {
        AdStatus = adStatus;
    }

    @Override
    public String toString() {
        return "HomeAdBean{" +
                "Id='" + Id + '\'' +
                ", PosID='" + PosID + '\'' +
                ", ADName='" + ADName + '\'' +
                ", IsLimited='" + IsLimited + '\'' +
                ", OnlineTime='" + OnlineTime + '\'' +
                ", OfflineTime='" + OfflineTime + '\'' +
                ", FileName='" + FileName + '\'' +
                ", LinkTarget='" + LinkTarget + '\'' +
                ", LinkURL='" + LinkURL + '\'' +
                ", LinkTitle='" + LinkTitle + '\'' +
                ", OrderBy='" + OrderBy + '\'' +
                ", HitCount='" + HitCount + '\'' +
                ", AdStatus='" + AdStatus + '\'' +
                '}';
    }
}
