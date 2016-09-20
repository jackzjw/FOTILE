package com.example.sg280.fotile.presents;

import android.app.Activity;
import android.widget.Toast;

import com.example.sg280.fotile.app.Constants;
import com.example.sg280.fotile.http.FotileRetrofit;
import com.example.sg280.fotile.http.ProgressSubscriber;
import com.example.sg280.fotile.model.bean.MySelfInfo;
import com.example.sg280.fotile.model.bean.UserInfo;
import com.example.sg280.fotile.model.source.HttpOnNextListener;
import com.example.sg280.fotile.model.source.LoginSubject;
import com.example.sg280.fotile.presents.Interface.ILoginContacts;
import com.example.sg280.fotile.utils.LogUtil;
import com.example.sg280.fotile.utils.MD5Util;
import com.tencent.TIMCallBack;
import com.tencent.TIMManager;
import com.tencent.TIMUser;

/**
 * Created by sg280 on 2016-07-25.
 */
public class LoginPresent implements ILoginContacts.Present{

    private ILoginContacts.View mview;
    private Activity activity;
    private ProgressSubscriber progressSuscriber;
    private static final String TAG = "LoginPresent";
    public LoginPresent(ILoginContacts.View view,Activity activity){
    this.mview=view;
    this.activity=activity;
}

    @Override
    public void ftLogin(String id, String pwd) {
       String MD5Pwd= MD5Util.getStringMD5(pwd + "FOTILE");
     //   LogUtil.e("密码" + MD5Pwd);
        progressSuscriber=new ProgressSubscriber(loginOnNextListener,activity);
        LoginSubject source=new LoginSubject(progressSuscriber,id,MD5Pwd);
        FotileRetrofit.getInstance().doHttpDeal2(source);
    }
    //   回调一一对应
    HttpOnNextListener loginOnNextListener = new HttpOnNextListener<UserInfo>() {
        @Override
        public void onNext(UserInfo subjects) {
            LogUtil.e(subjects.toString());
            MySelfInfo.getInstance().setId(subjects.getUserID());
            MySelfInfo.getInstance().setIslogin(true);
            MySelfInfo.getInstance().setIdentifier(subjects.getIdentifier());
            MySelfInfo.getInstance().setUserSig(subjects.getTlsSig());
            MySelfInfo.getInstance().setPhone(subjects.getUserTel());
            MySelfInfo.getInstance().writeToCache(activity);
            imLogin(subjects.getIdentifier(),subjects.getTlsSig());
        }
    };
    @Override
    public void imLogin(String identify, String userSig) {
        TIMUser user = new TIMUser();
        user.setAccountType(String.valueOf(Constants.ACCOUNT_TYPE));
        user.setAppIdAt3rd(String.valueOf(Constants.SDK_APPID));
        user.setIdentifier(identify);
        //发起登录请求
        TIMManager.getInstance().login(
                Constants.SDK_APPID,
                user,
                userSig,                    //用户帐号签名，由私钥加密获得，具体请参考文档
                new TIMCallBack() {
                    @Override
                    public void onError(int i, String s) {
                        LogUtil.e(TAG, "IMLogin fail ：" + i + " msg " + s);
                        Toast.makeText(activity, "IMLogin fail ：" + i + " msg " + s, Toast.LENGTH_SHORT).show();
                        if (mview != null) {
                            mview.loginFailed();
                        }
                    }

                    @Override
                    public void onSuccess() {
                        LogUtil.i(TAG, "keypath IMLogin succ !");
                        mview.loginSucc();
//                        Toast.makeText(mContext, "IMLogin succ !", Toast.LENGTH_SHORT).show();

                    }
                });
    }



    @Override
    public void ondestory() {
           progressSuscriber.onCancelProgress();


    }
}
