package com.example.sg280.fotile.model.bean;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sg280.fotile.app.Constants;
import com.example.sg280.fotile.utils.LogUtil;

import java.util.List;

/**
 * Created by sg280 on 2016-07-25.
 *
 */
public class MySelfInfo {

    private static final String TAG = MySelfInfo.class.getSimpleName();
    private String id;
    private String userSig;
    private String nickName;    // 呢称
    private String avatar;      // 头像
    private String sign;      // 签名
    private String CosSig;
    private int id_status;
    private String identifier;
    private List<String> liveCategory;

    private boolean islogin;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean islogin() {
        return islogin;
    }

    public void setIslogin(boolean islogin) {
        this.islogin = islogin;
    }

    public List<String> getLiveCategory() {
        return liveCategory;
    }

    public void setLiveCategory(List<String> liveCategory) {
        this.liveCategory = liveCategory;
    }

    public List<String> getVedioCategory() {
        return vedioCategory;
    }

    public void setVedioCategory(List<String> vedioCategory) {
        this.vedioCategory = vedioCategory;
    }

    private List<String> vedioCategory;
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    private int myRoomNum = -1;

    private static MySelfInfo ourInstance = new MySelfInfo();

    public static MySelfInfo getInstance() {

        return ourInstance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserSig() {
        return userSig;
    }

    public void setUserSig(String userSig) {
        this.userSig = userSig;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getCosSig() {
        return CosSig;
    }

    public void setCosSig(String cosSig) {
        CosSig = cosSig;
    }

    public int getId_status() {
        return id_status;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

    public int getMyRoomNum() {
        return myRoomNum;
    }

    public void setMyRoomNum(int myRoomNum) {
        this.myRoomNum = myRoomNum;
    }

    //将个人信息写进数据库
    public void writeToCache(Context context) {
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_INFO,
                0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Constants.USER_ID, id);
        editor.putString(Constants.USER_SIG, userSig);
        editor.putString(Constants.USER_NICK, nickName);
        editor.putString(Constants.USER_AVATAR, avatar);
        editor.putString(Constants.USER_SIGN, sign);
        editor.putBoolean(Constants.ISLOGIN, islogin);
        editor.putString(Constants.USER_IDENTIFY, identifier);
        editor.putInt(Constants.USER_ROOM_NUM, myRoomNum);
        editor.putString(Constants.USER_PHONE,phone);
        editor.commit();
    }

    public void clearCache(Context context) {
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_INFO,
                0);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
    }

    public void getCache(Context context) {
        SharedPreferences sharedata = context.getSharedPreferences(Constants.USER_INFO,
                0);
        id = sharedata.getString(Constants.USER_ID, null);
        userSig = sharedata.getString(Constants.USER_SIG, null);
        myRoomNum = sharedata.getInt(Constants.USER_ROOM_NUM, -1);
        nickName = sharedata.getString(Constants.USER_NICK, null);
        avatar = sharedata.getString(Constants.USER_AVATAR, null);
        sign = sharedata.getString(Constants.USER_SIGN, null);
        identifier=sharedata.getString(Constants.USER_IDENTIFY,null);
        islogin=sharedata.getBoolean(Constants.ISLOGIN, false);
        phone=sharedata.getString(Constants.USER_PHONE, null);
        LogUtil.i(TAG, " getCache id: " + id);
//sdfsefs
    }


}
