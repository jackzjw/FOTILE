package com.example.sg280.fotile.model.bean;

/**
 * Created by Tian on 2016/9/5.
 */
public class UserInfoBean {


    /**
     * UserID : 用户ID
     * Identifier :  腾讯云用户标识
     * IntegralTotal : 总积分
     * IntegralUsed : 已用积分
     * UserTel : 电话
     * UserName : 用户名
     * UserNameType : null  1 微信随机生成用户名 2 不能修改 3 修改后的用户名
     * PwdType : 1  0:空密码  1:可以修改密码
     * UserAvatar : 用户头像
     * UserAvatarSer : 用户头像地址
     * TlsSig : 腾讯云直播用户签名
     * SigCreateTime : 签名生成时间
     */

    private String UserID;
    private String Identifier;
    private String IntegralTotal;
    private String IntegralUsed;
    private String UserTel;
    private String UserName;
    private String UserNameType;
    private String PwdType;
    private String UserAvatar;
    private String UserAvatarSer;
    private String TlsSig;
    private String SigCreateTime;

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(String Identifier) {
        this.Identifier = Identifier;
    }

    public String getIntegralTotal() {
        return IntegralTotal;
    }

    public void setIntegralTotal(String IntegralTotal) {
        this.IntegralTotal = IntegralTotal;
    }

    public String getIntegralUsed() {
        return IntegralUsed;
    }

    public void setIntegralUsed(String IntegralUsed) {
        this.IntegralUsed = IntegralUsed;
    }

    public String getUserTel() {
        return UserTel;
    }

    public void setUserTel(String UserTel) {
        this.UserTel = UserTel;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getUserNameType() {
        return UserNameType;
    }

    public void setUserNameType(String UserNameType) {
        this.UserNameType = UserNameType;
    }

    public String getPwdType() {
        return PwdType;
    }

    public void setPwdType(String PwdType) {
        this.PwdType = PwdType;
    }

    public String getUserAvatar() {
        return UserAvatar;
    }

    public void setUserAvatar(String UserAvatar) {
        this.UserAvatar = UserAvatar;
    }

    public String getUserAvatarSer() {
        return UserAvatarSer;
    }

    public void setUserAvatarSer(String UserAvatarSer) {
        this.UserAvatarSer = UserAvatarSer;
    }

    public String getTlsSig() {
        return TlsSig;
    }

    public void setTlsSig(String TlsSig) {
        this.TlsSig = TlsSig;
    }

    public String getSigCreateTime() {
        return SigCreateTime;
    }

    public void setSigCreateTime(String SigCreateTime) {
        this.SigCreateTime = SigCreateTime;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "UserID='" + UserID + '\'' +
                ", Identifier='" + Identifier + '\'' +
                ", IntegralTotal=" + IntegralTotal +
                ", IntegralUsed=" + IntegralUsed +
                ", UserTel='" + UserTel + '\'' +
                ", UserName='" + UserName + '\'' +
                ", UserNameType=" + UserNameType +
                ", PwdType=" + PwdType +
                ", UserAvatar='" + UserAvatar + '\'' +
                ", UserAvatarSer='" + UserAvatarSer + '\'' +
                ", TlsSig='" + TlsSig + '\'' +
                ", SigCreateTime='" + SigCreateTime + '\'' +
                '}';
    }
}
