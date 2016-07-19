package com.example.sg280.fotile.model.bean;

/**
 * Created by sg280 on 2016-07-18.
 */
public class VedioBean {
    private  String ID;
    //直播ID,
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

}
