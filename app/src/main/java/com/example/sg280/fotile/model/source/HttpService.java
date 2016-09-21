package com.example.sg280.fotile.model.source;

import com.example.sg280.fotile.model.bean.BrandBean;
import com.example.sg280.fotile.model.bean.CollectLiveBean;
import com.example.sg280.fotile.model.bean.CollectProductBean;
import com.example.sg280.fotile.model.bean.CommitOrderBackMsgBean;
import com.example.sg280.fotile.model.bean.CouponsBean;
import com.example.sg280.fotile.model.bean.CouponsNumBean;
import com.example.sg280.fotile.model.bean.HomeLiveList;
import com.example.sg280.fotile.model.bean.HttpDataResult;
import com.example.sg280.fotile.model.bean.HttpResult;
import com.example.sg280.fotile.model.bean.NoResponseResult;
import com.example.sg280.fotile.model.bean.OrderBean;
import com.example.sg280.fotile.model.bean.ShippingAddressBean;
import com.example.sg280.fotile.model.bean.UserInfo;
import com.example.sg280.fotile.model.bean.UserInfoBean;
import com.example.sg280.fotile.model.bean.UserOrderInfoBean;
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



    /**
     * 添加收货地址
     * @param type action类型
     * @param userid 用户id
     * @param pcr 省市区
     * @param address 详细地址
     * @param tel 电话
     * @param postcode 邮政
     * @param recipients 收货人
     * @param isdefault 是否默认
     * @return
     */
    @FormUrlEncoded
    @POST("editAddress.ashx")
    Observable<NoResponseResult> addAddress(@Field("action") String type, @Field("userid") String userid,
                                                @Field("pcr") String pcr, @Field("address") String address, @Field("tel") String tel,
                                            @Field("postcode") String postcode,@Field("recipients") String recipients,@Field("isdefault") String isdefault);


    /**
     * 获取默认地址
     * @param action
     * @param userId
     * @param isDefault
     * @return
     */
    @FormUrlEncoded
    @POST("editAddress.ashx")
    Observable<HttpDataResult<ShippingAddressBean>> getDefaultAddress(@Field("action") String action, @Field("userId") String userId,@Field("isDefault") String isDefault);

    /**
     * 修改地址信息
     * @param action
     * @param userId
     * @param pcr
     * @param address
     * @param tel
     * @param postcode
     * @param recipients
     * @param isDefault
     * @param addressId 地址id
     * @return
     */
    @FormUrlEncoded
    @POST("editAddress.ashx")
    Observable<NoResponseResult> modifyAddress(@Field("action") String action, @Field("userId") String userId,
                                            @Field("pcr") String pcr, @Field("address") String address, @Field("tel") String tel,
                                            @Field("postcode") String postcode,@Field("recipients") String recipients,@Field("isDefault") String isDefault,@Field("addressId") String addressId);

    /**
     * 获取收货地址列表
     * @param userid
     * @param limit
     * @param start
     * @return
     */
    @FormUrlEncoded
    @POST("getMyAddressList.ashx")
    Observable<HttpResult<List<ShippingAddressBean>>> getAddressList(@Field("userid") String userid,
                                                                     @Field("limit") String limit,@Field("start") int start);


    /**
     * 获取我的收藏列表
     * @param userid
     * @param itemtype
     * @param limit
     * @param start
     * @return
     */
    @FormUrlEncoded
    @POST("getMyFav.ashx")
    Observable<HttpResult<List<CollectProductBean>>>
    getCollectProList(@Field("userid") String userid, @Field("itemtype") int itemtype,
                      @Field("limit") String limit, @Field("start") int start);

    @FormUrlEncoded
    @POST("getMyFav.ashx")
    Observable<HttpResult<List<CollectLiveBean>>> getCollectLiveList
            (@Field("userid") String userid, @Field("itemtype") int itemtype,
             @Field("limit") String limit, @Field("start") int start);


    /**
     * 获取订单列表
     * @param userid
     * @param limit
     * @param start
     * @return
     */
    @FormUrlEncoded
    @POST("getMyOrderList.ashx")
    Observable<HttpResult<List<OrderBean>>> getOrderList
            (@Field("userid") String userid, @Field("limit") String limit, @Field("start") int start,@Field("orderstatus") String orderstatus);


    /**
     * 获取购物车内商品列表
     * @param userid
     * @return
     */
    @FormUrlEncoded
    @POST("getShopCartListForApp.ashx")
    Observable<HttpResult<List<BrandBean>>> getShoppingCartGoods
            (@Field("userid") String userid);


    /**
     * 添加，减少，删除购物车内产品
     * @param action addnum：添加 subnum：减少 delpro：删除（可批量删除）
     * @param shopCarId 购物车ID
     * @param userId 用户ID
     * @return
     */
    @FormUrlEncoded
    @POST("editShopCar.ashx")
    Observable<NoResponseResult> modifyGoodsNum
            (@Field("action") String action,@Field("shopCarId") String shopCarId,@Field("userId") String userId);


    /**
     * 提交订单
     * @param shopCarId 购物车ID
     * @param userId 用户ID
     * @param couponcardid 优惠券ID
     * @param pcr 省市区
     * @param address 详细地址
     * @param tel 联系电话
     * @param postcode 邮政编码
     * @param recipients 收货人
     * @param paytype 支付方式 1：微信支付 2：货到付款
     * @param remark 留言
     * @param invoicestatus 是否开纸质发票 1：是
     * @param integral 积分e
     * utmsource:这个参数app端不用传utmsource 来源
     * @param isliji 是否立即购买 1：是 2：不是
     * @return
     */
    @FormUrlEncoded
    @POST("addOrderInfo.ashx")
    Observable<HttpDataResult<CommitOrderBackMsgBean>> commitOrder
            (@Field("shopCarId") String shopCarId,@Field("userId") String userId,
             @Field("couponcardid") String couponcardid,
             @Field("pcr") String pcr,@Field("address") String address,
             @Field("tel") String tel,@Field("postcode") String postcode,
             @Field("recipients") String recipients, @Field("paytype") String paytype,
             @Field("remark") String remark,@Field("invoicestatus") String invoicestatus,
             @Field("integral") String integral,@Field("isliji") String isliji);

    /**
     * 立即购买的提交订单
     * @param productid 产品ID
     * @param skuid 规格ID
     * @param productnum 产品数量
     * @param userId 用户ID
     * @param couponcardid 优惠券ID
     * @param pcr 省市区
     * @param address 详细地址
     * @param tel 联系电话
     * @param postcode 邮政编码
     * @param recipients 收货人
     * @param paytype 支付方式 1：微信支付 2：货到付款
     * @param remark 留言
     * @param invoicestatus 是否开纸质发票 1：是
     * @param integral 积分e
     * utmsource:这个参数app端不用传utmsource 来源
     * @param isliji 是否立即购买 1：是 2：不是
     * @return
     */
    @FormUrlEncoded
    @POST("addOrderInfo.ashx")
    Observable<HttpDataResult<CommitOrderBackMsgBean>> commitOrderAtOnce
            (@Field("productid") String productid,@Field("skuid") String skuid,
             @Field("productnum") String productnum,@Field("userId") String userId,
             @Field("couponcardid") String couponcardid,
             @Field("pcr") String pcr,@Field("address") String address,
             @Field("tel") String tel,@Field("postcode") String postcode,
             @Field("recipients") String recipients, @Field("paytype") String paytype,
             @Field("remark") String remark,@Field("invoicestatus") String invoicestatus,
             @Field("integral") String integral,@Field("isliji") String isliji);


    /**
     * 获取用户详细信息
     * @param userId 用户ID
     * @return
     */
    @FormUrlEncoded
    @POST("getMyInfo.ashx")
    Observable<HttpDataResult<UserInfoBean>> userInfo
            (@Field("userId") String userId);

    /**
     * 获取当前可用的优惠券数量
     * @param action
     * @param userId 用户ID
     * @param shopCarId 购物车ID
     * @return
     */
    @FormUrlEncoded
    @POST("getMyCoupon.ashx")
    Observable<HttpDataResult<CouponsNumBean>> getCouponsNum
            (@Field("action") String action,@Field("userId") String userId,@Field("shopCarId") String shopCarId);

    /**
     * 获取当前可用的优惠券数量(立即购买)
     * @param action
     * @param userId 用户ID
     * @param productid 产品ID
     * @param skuid 规格ID
     * @param productnum 产品数量
     * @return
     */
    @FormUrlEncoded
    @POST("getMyCoupon.ashx")
    Observable<HttpDataResult<CouponsNumBean>> getCouponsNumAtOnce
            (@Field("action") String action,@Field("userId") String userId,@Field("productid") String productid,@Field("skuid") String skuid,
             @Field("productnum") String productnum);


    /**
     * 获取当前可用的优惠券
     * @param action
     * @param userId 用户ID
     * @param shopCarId 购物车ID
     * @return
     */
    @FormUrlEncoded
    @POST("getMyCoupon.ashx")
    Observable<HttpResult<List<CouponsBean>>> getNowUsableCoupons
            (@Field("action") String action,@Field("userId") String userId,@Field("shopCarId") String shopCarId);


    /**
     * 获取当前可用的优惠券(立即购买)
     * @param action
     * @param userId
     * @param productid
     * @param skuid
     * @param productnum
     * @return
     */
    @FormUrlEncoded
    @POST("getMyCoupon.ashx")
    Observable<HttpResult<List<CouponsBean>>> getNowUsableCouponsAtOnce
            (@Field("action") String action,@Field("userId") String userId,@Field("productid") String productid,@Field("skuid") String skuid,
             @Field("productnum") String productnum);

    /**
     * 获取所有可用不可用的优惠券
     * @param action
     * @param userId
     * @return
     */
    @FormUrlEncoded
    @POST("getMyCoupon.ashx")
    Observable<HttpResult<List<CouponsBean>>> getUsableCoupons
            (@Field("action") String action,@Field("userId") String userId);


    /**
     * 获取用户订单详情
     * @param userId 用户Id
     * @param orderId 订单Id
     * @return
     */
    @FormUrlEncoded
    @POST("getOrderInfo.ashx")
    Observable<HttpDataResult<UserOrderInfoBean>> getUserOrderInfo
            (@Field("userId") String userId,@Field("orderId") String orderId);
}
