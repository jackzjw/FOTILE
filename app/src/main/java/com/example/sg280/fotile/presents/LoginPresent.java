package com.example.sg280.fotile.presents;

import android.app.Activity;

import com.example.sg280.fotile.http.FotileRetrofit;
import com.example.sg280.fotile.http.ProgressSubscriber;
import com.example.sg280.fotile.model.bean.MySelfInfo;
import com.example.sg280.fotile.model.bean.UserInfo;
import com.example.sg280.fotile.model.source.HttpOnNextListener;
import com.example.sg280.fotile.model.source.LoginSubject;
import com.example.sg280.fotile.presents.Interface.ILoginContacts;
import com.example.sg280.fotile.utils.LogUtil;
import com.example.sg280.fotile.utils.MD5Util;
import com.example.sg280.fotile.utils.SharedPreferencesUtil;

/**
 * Created by sg280 on 2016-07-25.
 */
public class LoginPresent implements ILoginContacts.Present{

    private ILoginContacts.View mview;
    private Activity activity;
public LoginPresent(ILoginContacts.View view,Activity activity){
    this.mview=view;
    this.activity=activity;
}

    @Override
    public void ftLogin(String id, String pwd) {
       String MD5Pwd= MD5Util.getStringMD5(pwd + "FOTILE");
     //   LogUtil.e("密码" + MD5Pwd);
        LoginSubject source=new LoginSubject(new ProgressSubscriber(loginOnNextListener,activity),id,MD5Pwd);
        FotileRetrofit.getInstance().doHttpDeal2(source);
    }
    //   回调一一对应
    HttpOnNextListener loginOnNextListener = new HttpOnNextListener<UserInfo>() {
        @Override
        public void onNext(UserInfo subjects) {
            LogUtil.e(subjects.toString());
            MySelfInfo.getInstance().setId(subjects.getUserID());
            MySelfInfo.getInstance().setNickName(subjects.getUserName());
            MySelfInfo.getInstance().writeToCache(activity);
            SharedPreferencesUtil.setPhone(activity,subjects.getUserTel());
            mview.loginSucc();
        }

    };
    @Override
    public void imLogin(String indentify, String usersig) {

    }


    @Override
    public void ondestory() {

    }
}
