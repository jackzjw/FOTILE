package com.example.sg280.fotile.model.bean;

/**
 * 我的收藏中直播点播的实体类
 * Created by Tian on 2016/8/10.
 */
public class CollectLiveBean {

    private String ID;//直播ID,
    private String LiveName;// "直播名称",
    private String ClassID;// 直播类型ID,
    private String ChannelID;// "腾讯云直播频道号",
    private String AppID;//云直播的APPID
    private String StatusCode;// 直播状态码,
    private String StatusName;// "直播状态名称",		1：正在直播；2：即将开始；3：直播完毕；4
    private String BrandID;// 品牌ID,
    private String FavID;// 收藏ID,
    private String BrandName;//"品牌名称",
    private String LivePix;// "直播文件名",
    private String LivePixSer;// "/UploadFiles/201606131642292861.jpg",封面图片地址
    private String StartTime;// "开始时间",
    private String PreStartTime;// "预热开始时间",
    private String EndTime;//"结束时间",
    private String BrandLogo;//"品牌Logo",
    private String BrandIntro;//"品牌介绍",
    private String ClassName;// "视频名称",
    private String ClassType;//"视频类型",
    private String SdkAppID;//
    private String AccountType;//"账号类型",
    private String AvChatRoomID;//"房间号",
    private String Identifier;//"腾讯云用户标识",
    private String userSig;//"腾讯云用户签名",
    private String IsFav;//"是否收藏",
    private String ProductLinkType;//"产品外链类型",
    private String URL;// "视频URL",
    private String HitCount;//点击次数,
    private String CommentCount;// 评论次数,
    private String IsHot;// 是否热门,
    private String MasterUserID;// 直播主号

    public String getMasterUserID() {
        return MasterUserID;
    }

    public void setMasterUserID(String masterUserID) {
        MasterUserID = masterUserID;
    }

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

    public String getAppID() {
        return AppID;
    }

    public void setAppID(String appID) {
        AppID = appID;
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

    public String getFavID() {
        return FavID;
    }

    public void setFavID(String favID) {
        FavID = favID;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
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

    public String getBrandLogo() {
        return BrandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        BrandLogo = brandLogo;
    }

    public String getBrandIntro() {
        return BrandIntro;
    }

    public void setBrandIntro(String brandIntro) {
        BrandIntro = brandIntro;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getClassType() {
        return ClassType;
    }

    public void setClassType(String classType) {
        ClassType = classType;
    }

    public String getSdkAppID() {
        return SdkAppID;
    }

    public void setSdkAppID(String sdkAppID) {
        SdkAppID = sdkAppID;
    }

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String accountType) {
        AccountType = accountType;
    }

    public String getAvChatRoomID() {
        return AvChatRoomID;
    }

    public void setAvChatRoomID(String avChatRoomID) {
        AvChatRoomID = avChatRoomID;
    }

    public String getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(String identifier) {
        Identifier = identifier;
    }

    public String getUserSig() {
        return userSig;
    }

    public void setUserSig(String userSig) {
        this.userSig = userSig;
    }

    public String getIsFav() {
        return IsFav;
    }

    public void setIsFav(String isFav) {
        IsFav = isFav;
    }

    public String getProductLinkType() {
        return ProductLinkType;
    }

    public void setProductLinkType(String productLinkType) {
        ProductLinkType = productLinkType;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
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

    @Override
    public String toString() {
        return "CollectLiveBean{" +
                "ID='" + ID + '\'' +
                ", LiveName='" + LiveName + '\'' +
                ", ClassID='" + ClassID + '\'' +
                ", ChannelID='" + ChannelID + '\'' +
                ", AppID='" + AppID + '\'' +
                ", StatusCode='" + StatusCode + '\'' +
                ", StatusName='" + StatusName + '\'' +
                ", BrandID='" + BrandID + '\'' +
                ", FavID='" + FavID + '\'' +
                ", BrandName='" + BrandName + '\'' +
                ", LivePix='" + LivePix + '\'' +
                ", LivePixSer='" + LivePixSer + '\'' +
                ", StartTime='" + StartTime + '\'' +
                ", PreStartTime='" + PreStartTime + '\'' +
                ", EndTime='" + EndTime + '\'' +
                ", BrandLogo='" + BrandLogo + '\'' +
                ", BrandIntro='" + BrandIntro + '\'' +
                ", ClassName='" + ClassName + '\'' +
                ", ClassType='" + ClassType + '\'' +
                ", SdkAppID='" + SdkAppID + '\'' +
                ", AccountType='" + AccountType + '\'' +
                ", AvChatRoomID='" + AvChatRoomID + '\'' +
                ", Identifier='" + Identifier + '\'' +
                ", userSig='" + userSig + '\'' +
                ", IsFav='" + IsFav + '\'' +
                ", ProductLinkType='" + ProductLinkType + '\'' +
                ", URL='" + URL + '\'' +
                ", HitCount='" + HitCount + '\'' +
                ", CommentCount='" + CommentCount + '\'' +
                ", IsHot='" + IsHot + '\'' +
                ", MasterUserID='" + MasterUserID + '\'' +
                '}';
    }
}
