package com.example.sg280.fotile.model.bean;

/**
 * Created by sg280 on 2016-07-28.
 */
public class VedioCategoryBean {
    private String ID;// 分类ID,
    private String PID;// 0,
    private String ClassType;// 分类类型, 1直播  2 点播
    private String ClassName;//"分类名称",
    private String ClassCode;// "分类编码",
    private String OrderBy;//排序

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

    public String getOrderBy() {
        return OrderBy;
    }

    public void setOrderBy(String orderBy) {
        OrderBy = orderBy;
    }
}
