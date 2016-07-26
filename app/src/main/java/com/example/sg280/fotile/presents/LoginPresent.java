package com.example.sg280.fotile.presents;

import android.app.Activity;

import com.example.sg280.fotile.http.FotileRetrofit;
import com.example.sg280.fotile.http.ProgressSubscriber;
import com.example.sg280.fotile.model.bean.UserInfo;
import com.example.sg280.fotile.model.source.HttpOnNextListener;
import com.example.sg280.fotile.model.source.LoginDataSource;

/**
 * Created by sg280 on 2016-07-25.
 */
public class LoginPresent implements LoginContacts.Present{

    private LoginContacts.View mview;
    private Activity activity;
public LoginPresent(LoginContacts.View view,Activity activity){
    this.mview=view;
    this.activity=activity;
}

    @Override
    public void ftLogin(String id, String pwd) {
     //   String MD5Pwd= MD5Util.getStringMD5("FOTILE"+pwd);
        LoginDataSource source=new LoginDataSource(new ProgressSubscriber(loginOnNextListener,activity),id,pwd);
        FotileRetrofit.getInstance().doHttpDeal2(source);
    }
    //   回调一一对应
    HttpOnNextListener loginOnNextListener = new HttpOnNextListener<UserInfo>() {
        @Override
        public void onNext(UserInfo subjects) {
             mview.loginSucc();
        }
    };
    @Override
    public void imLogin(String indentify, String usersig) {

    }

    @Override
    public void start() {

    }

    @Override
    public void ondestory() {

    }
}
