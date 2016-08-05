package com.example.sg280.fotile.model.source;

import com.example.sg280.fotile.model.bean.HomeLiveList;
import com.example.sg280.fotile.model.bean.HttpDataResult;
import com.example.sg280.fotile.model.bean.HttpResult;
import com.example.sg280.fotile.model.bean.NoResponseResult;
import com.example.sg280.fotile.model.bean.UserInfo;
import com.example.sg280.fotile.model.bean.VedioCategoryBean;
import com.example.sg280.fotile.model.bean.VedioDetailsBean;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * app接口列表
 * Created by sg280 on 2016-07-18.
 */
public interface HttpService {
    //获取首页列表
    @GET("getHotLive.ashx")
    Observable<HttpResult<List<HomeLiveList>>> getHotLiveList();
    //登录接口
    @FormUrlEncoded
    @POST("UserLogin.ashx")
    Observable<HttpDataResult<UserInfo>> getUserInfo(@Field("tel") String tel,@Field("pwd") String pwd);
    //获取动态验证码
    @FormUrlEncoded
    @POST("getUserDyCode.ashx")
    Observable<NoResponseResult> getResponse(@Field("tel") String tel);
    //验证动态验证码
    @FormUrlEncoded
    @POST("userDycodeValid.ashx")
    Observable<NoResponseResult> conformDycode(@Field("tel") String tel,@Field("dycode") String dycode,@Field("type") String type);
    //注册
    @FormUrlEncoded
    @POST("userRegister.ashx")
    Observable<HttpDataResult<UserInfo>> register(@Field("tel") String tel,@Field("dycode") String code,@Field("pwd") String pwd);
    //忘记密码
    @FormUrlEncoded
    @POST("userPwdFoget.ashx")
    Observable<NoResponseResult> findPwd(@Field("userid") String userid,@Field("pwdnew") String pwd);
    //视频分类
    @GET("getLiveCategory.ashx")
    Observable<HttpResult<List<VedioCategoryBean>>> getVedioCategory();
    //直播/点播视频列表
    @FormUrlEncoded
    @POST("getLiveList.ashx")
    Observable<HttpResult<List<VedioDetailsBean>>> getVedioList(@Field("start") int start, @Field("limit") int limit,@Field("classid") String classid);
    //获取视频直播内容
    @FormUrlEncoded
    @POST("getLiveView.ashx")
    Observable<HttpDataResult<VedioDetailsBean>> getLiveView(@Field("Action") String type,@Field("liveid") String liveid);

}
