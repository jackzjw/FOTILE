package com.example.sg280.fotile.model.bean;

/**
 * Created by sg280 on 2016/9/5.
 */
public class ProductCategoryBean {

   private String ID;// 9,
   private String PID;// 0,
   private String ClassName;// "热卖套餐",
   private String ClassCode;//: "CM",
   private String OrderBy;// 1

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

    @Override
    public String toString() {
        return "ProductCategoryBean{" +
                "ID='" + ID + '\'' +
                ", PID='" + PID + '\'' +
                ", ClassName='" + ClassName + '\'' +
                ", ClassCode='" + ClassCode + '\'' +
                ", OrderBy='" + OrderBy + '\'' +
                '}';
    }
}
