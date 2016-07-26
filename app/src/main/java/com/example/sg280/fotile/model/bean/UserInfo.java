package com.example.sg280.fotile.model.bean;

/**
 * Created by sg280 on 2016-07-25.
 */
public class UserInfo {

    private int ID;// 9,
    private String UserID;// "31AFDDE109A10656",
    private String OpenID;//"",
    private String UserTel;// "电话号码",
    private String UserName;//"用户名",
    private String UserAvatar;// 用户头像,
    private String UserAvatarSer;// 头像地址

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getOpenID() {
        return OpenID;
    }

    public void setOpenID(String openID) {
        OpenID = openID;
    }

    public String getUserTel() {
        return UserTel;
    }

    public void setUserTel(String userTel) {
        UserTel = userTel;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserAvatar() {
        return UserAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        UserAvatar = userAvatar;
    }

    public String getUserAvatarSer() {
        return UserAvatarSer;
    }

    public void setUserAvatarSer(String userAvatarSer) {
        UserAvatarSer = userAvatarSer;
    }
}
