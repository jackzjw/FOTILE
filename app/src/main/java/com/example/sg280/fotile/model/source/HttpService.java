package com.example.sg280.fotile.model.source;

import com.example.sg280.fotile.model.bean.HomeLiveList;
import com.example.sg280.fotile.model.bean.HttpDataResult;
import com.example.sg280.fotile.model.bean.HttpResult;
import com.example.sg280.fotile.model.bean.NoResponseResult;
import com.example.sg280.fotile.model.bean.UserInfo;

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
    Observable<HttpDataResult<UserInfo>> getUserInfo(@Field("tel") String tel, @Field("pwd") String pwd);

    //获取动态验证码
    @FormUrlEncoded
    @POST("getUserDyCode.ashx")
    Observable<NoResponseResult> getResponse(@Field("tel") String tel);

    //验证动态验证码
    @FormUrlEncoded
    @POST("userDycodeValid.ashx")
    Observable<NoResponseResult> conformDycode(@Field("tel") String tel, @Field("dycode") String dycode);

    //注册
    @FormUrlEncoded
    @POST("userRegister.ashx")
    Observable<HttpDataResult> register(@Field("tel") String tel, @Field("dycode") String code, @Field("pwd") String pwd);

    /**
     * 修改用户资料
     *
     * @param userid   用户id
     * @param username 用户昵称
     * @param usertel  用户电话
     * @param pwdold   用户的旧密码
     * @param pwdnew   用户的新密码
     * @return Observable
     */
    @FormUrlEncoded
    @POST("editMyInfo.ashx")
    Observable<NoResponseResult> modifyUserInfo(@Field("userid") String userid, @Field("username") String username,
                                                @Field("usertel") String usertel, @Field("pwdold") String pwdold, @Field("pwdnew") String pwdnew);


}
