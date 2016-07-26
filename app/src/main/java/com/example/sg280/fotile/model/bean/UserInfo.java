package com.example.sg280.fotile.model.bean;

/**
 * Created by sg280 on 2016-07-25.
 */
public class UserInfo {


    private String UserID;// "31AFDDE109A10656",
    private String Identifier;//"",
    private String UserTel;// "电话号码",
    private String UserName;//"用户名",
    private String UserNameType;
    private String PwdType;
    private String UserAvatar;// 用户头像,
    private String UserAvatarSer;
    private String TlsSig;// 头像地址

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(String identifier) {
        Identifier = identifier;
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

    public String getUserNameType() {
        return UserNameType;
    }

    public void setUserNameType(String userNameType) {
        UserNameType = userNameType;
    }

    public String getPwdType() {
        return PwdType;
    }

    public void setPwdType(String pwdType) {
        PwdType = pwdType;
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

    public String getTlsSig() {
        return TlsSig;
    }

    public void setTlsSig(String tlsSig) {
        TlsSig = tlsSig;
    }
}
