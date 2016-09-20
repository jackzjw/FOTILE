package com.example.sg280.fotile.presents;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.app.Constants;
import com.example.sg280.fotile.http.FotileRetrofit;
import com.example.sg280.fotile.model.bean.HttpResult;
import com.example.sg280.fotile.model.bean.MySelfInfo;
import com.example.sg280.fotile.model.bean.VedioCategoryBean;
import com.example.sg280.fotile.model.source.HttpService;
import com.example.sg280.fotile.utils.LogUtil;
import com.example.sg280.fotile.utils.NetUtil;
import com.example.sg280.fotile.utils.ToastUtil;
import com.tencent.TIMCallBack;
import com.tencent.TIMManager;
import com.tencent.TIMUser;
import com.tencent.TIMUserStatusListener;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import tencent.tls.platform.TLSErrInfo;
import tencent.tls.platform.TLSLoginHelper;
import tencent.tls.platform.TLSRefreshUserSigListener;
import tencent.tls.platform.TLSUserInfo;

/**
 * Created by sg280 on 2016-07-25.
 */
public class InitAppPresent {
    private static String appVer = "1.0";
    private static TLSLoginHelper mLoginHelper;
  //  private static TLSAccountHelper mAccountHelper;
    private static String TAG="initAppPresent";
    public static TLSLoginHelper getmLoginHelper() {
        return mLoginHelper;
    }

    /*public static TLSAccountHelper getmAccountHelper() {
        return mAccountHelper;
    }*/
    /**
     * 初始化App
     */
    public static void initApp(final Context context) {

        TIMManager.getInstance().disableBeaconReport();
        MySelfInfo.getInstance().getCache(context);

        TIMManager.getInstance().init(context);

        TIMManager.getInstance().setUserStatusListener(new TIMUserStatusListener() {
            @Override
            public void onForceOffline() {

                Toast.makeText(context, context.getString(R.string.tip_force_offline), Toast.LENGTH_SHORT).show();
                context.sendBroadcast(new Intent(Constants.BD_EXIT_APP));
            }

            @Override
            public void onUserSigExpired() {
                //   SxbLog.w(TAG, "onUserSigExpired->entered!");
                refreshSig();
            }
        });

        //QAL初始化
        //初始化TLS
        initTls(context);
        if (MySelfInfo.getInstance().islogin()) {
            reLoginIM(MySelfInfo.getInstance().getIdentifier(),MySelfInfo.getInstance().getUserSig());
        }
        //初始化CrashReport系统
        //配置程序异常退出处理
     //   Thread.setDefaultUncaughtExceptionHandler(new LocalFileHandler(context));
       //获取点播、直播分类接口
      //  getVedioClassCategory(context);
    }
    /**
     * 初始化TLS登录模块
     *
     * @param context
     */
    public static void initTls(Context context) {
        mLoginHelper = TLSLoginHelper.getInstance().init(context, Constants.SDK_APPID, Constants.ACCOUNT_TYPE, appVer);
        mLoginHelper.setTimeOut(5000);
      /*  mAccountHelper = TLSAccountHelper.getInstance().init(context, Constants.SDK_APPID, Constants.ACCOUNT_TYPE, appVer);
        mAccountHelper.setTimeOut(5000);*/
//      MySelfInfo.getInstance().setId(id);
 ///     MySelfInfo.getInstance().setUserSig(TLSService.getInstance().getUserSig(id));
    }
    /**
     * 重新登陆IM
     * @param identify
     * @param userSig
     */
    private static void reLoginIM(String identify, String userSig){
        TIMUser user = new TIMUser();
        user.setAccountType(String.valueOf(Constants.ACCOUNT_TYPE));
        user.setAppIdAt3rd(String.valueOf(Constants.SDK_APPID));
        user.setIdentifier(identify);
        //发起登录请求
        TIMManager.getInstance().login(
                Constants.SDK_APPID,
                user,
                userSig, //用户帐号签名，由私钥加密获得，具体请参考文档
                new TIMCallBack() {
                    @Override
                    public void onError(int i, String s) {
                        LogUtil.e(TAG, "reLoginIM fail ：" + i + "|" + s);
                    }

                    @Override
                    public void onSuccess() {
                        LogUtil.i(TAG, "reLoginIM IMLogin succ !");
                    }
                });
    }

    /**
     * 更新票据
     */
    private static void refreshSig(){
        final String userId = MySelfInfo.getInstance().getId();
        if (TextUtils.isEmpty(userId)){
         //   SxbLog.w(TAG, "refreshSig->with empty identifier");
            return;
        }

        // 更新票据
        mLoginHelper.TLSRefreshUserSig(MySelfInfo.getInstance().getId(), new TLSRefreshUserSigListener() {
            @Override
            public void OnRefreshUserSigSuccess(TLSUserInfo tlsUserInfo) {
                reLoginIM(userId, mLoginHelper.getUserSig(userId));
            }

            @Override
            public void OnRefreshUserSigFail(TLSErrInfo tlsErrInfo) {
                LogUtil.i(TAG, "OnRefreshUserSigFail->" + tlsErrInfo.ErrCode + "|" + tlsErrInfo.Msg);
            }

            @Override
            public void OnRefreshUserSigTimeout(TLSErrInfo tlsErrInfo) {
                LogUtil.i(TAG, "OnRefreshUserSigTimeout->" + tlsErrInfo.ErrCode + "|" + tlsErrInfo.Msg);
            }
        });
    }
    //获取直播、点播分类id
   public static void getVedioClassCategory( Context context){
        FotileRetrofit.getInstance().getRetrofit().create(HttpService.class).getVedioCategory().subscribeOn(Schedulers.io()
        ).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<HttpResult<List<VedioCategoryBean>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                NetUtil.onError(context,e);
            }

            @Override
            public void onNext(HttpResult<List<VedioCategoryBean>> listHttpResult) {
                    if(listHttpResult.getSuccess().equals("0")){
                        ToastUtil.showLong(context,listHttpResult.getErrorMessage());
                    }else {
                             List<String> live=new ArrayList<String>();
                        List<String> vedio=new ArrayList<String>();
                        for(VedioCategoryBean bean:listHttpResult.getRows()){
                            if(bean.getClassType().equals("3")){
                               live.add(bean.getID());
                            }else if(bean.getClassType().equals("2")){
                               vedio.add(bean.getID());
                            }
                        }
                        MySelfInfo.getInstance().setLiveCategory(live);
                        MySelfInfo.getInstance().setVedioCategory(vedio);
                    }
            }
        });
    }
}
