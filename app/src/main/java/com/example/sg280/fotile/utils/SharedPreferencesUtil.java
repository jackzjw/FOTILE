package com.example.sg280.fotile.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sg280.fotile.app.Constants;

/**
 * Created by Tian on 2016/8/5.
 */
public class SharedPreferencesUtil {

    private static void setParam(Context context, String param1,String param2) {
        SharedPreferences  sp = context.getSharedPreferences(Constants.USER_INFO, 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(param1, param2);
        editor.commit();
    }

    /**
     * 获取用户的id
     * @param context
     * @return 用户id
     */
    public static String getId(Context context){
        SharedPreferences sp = context.getSharedPreferences(Constants.USER_INFO, 0);
        return sp.getString(Constants.USER_ID,"");
    }


    /**
     * 储存用户密码
     * @param context
     * @param pwd 密码
     */
    public static void setPwd(Context context,String pwd){
        setParam(context,Constants.USER_PWD,pwd);
    }

    /**
     * 获取用户的密码
     * @param context
     * @return 用户的密码
     */
    public static String getPwd(Context context){
        SharedPreferences sp = context.getSharedPreferences(Constants.USER_INFO, 0);
        return sp.getString(Constants.USER_PWD,"");
    }

    /**
     * 储存用户电话
     * @param context
     * @param phone 电话
     */
    public static void setPhone(Context context,String phone){
        setParam(context,Constants.USER_PHONE,phone);
    }

    /**
     * 获取用户的电话
     * @param context
     * @return 用户的电话
     */
    public static String getPhone(Context context){
        SharedPreferences sp = context.getSharedPreferences(Constants.USER_INFO, 0);
        return sp.getString(Constants.USER_PHONE,"");
    }

    /**
     * 储存用户昵称
     * @param context
     * @param nickName 昵称
     */
    public static void setNickName(Context context,String nickName){
        setParam(context,Constants.USER_NICK,nickName);
    }

    /**
     * 获取用户的昵称
     * @param context
     * @return 用户的昵称
     */
    public static String getNickName(Context context){
        SharedPreferences sp = context.getSharedPreferences(Constants.USER_INFO, 0);
        return sp.getString(Constants.USER_NICK,"");
    }


}
