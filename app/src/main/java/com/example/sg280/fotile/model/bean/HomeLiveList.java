package com.example.sg280.fotile.model.bean;

import java.util.List;

/**
 * Created by sg280 on 2016-07-18.
 */
public class HomeLiveList {


    private  String ID; //分类ID,
    private String  PID; //上级分类ID,
    private String ClassType;// 视频类型,    1：直播；2点播
    private String ClassName;//"分类名称",
    private  String ClassCode;//"英文代码",
    private List<VedioBean> LiveList; //视频列表

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getClassType() {
        return ClassType;
    }

    public void setClassType(String classType) {
        ClassType = classType;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getClassCode() {
        return ClassCode;
    }

    public void setClassCode(String classCode) {
        ClassCode = classCode;
    }

    public List<VedioBean> getLiveList() {
        return LiveList;
    }

    public void setLiveList(List<VedioBean> liveList) {
        LiveList = liveList;
    }

    @Override
    public String toString() {
        return "HomeLiveList{" +
                "ID='" + ID + '\'' +
                ", PID='" + PID + '\'' +
                ", ClassType='" + ClassType + '\'' +
                ", ClassName='" + ClassName + '\'' +
                ", ClassCode='" + ClassCode + '\'' +
                ", LiveList=" + LiveList +
                '}';
    }
}
