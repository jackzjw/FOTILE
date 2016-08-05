package com.example.sg280.fotile.presents;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.sg280.fotile.http.FotileRetrofit;
import com.example.sg280.fotile.http.ProgressSubscriber;
import com.example.sg280.fotile.model.bean.MySelfInfo;
import com.example.sg280.fotile.model.bean.NoResponseResult;
import com.example.sg280.fotile.model.bean.UserInfo;
import com.example.sg280.fotile.model.source.HttpOnNextListener;
import com.example.sg280.fotile.model.source.HttpService;
import com.example.sg280.fotile.model.source.RegisterSubject;
import com.example.sg280.fotile.presents.Interface.IRegisterContracts;
import com.example.sg280.fotile.utils.MD5Util;
import com.example.sg280.fotile.utils.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sg280 on 2016-07-26.
 */
public class RegisterPresent  implements IRegisterContracts.Present{



    private IRegisterContracts.View mview;
   private Context context;
    public RegisterPresent(IRegisterContracts.View view,Context context){
     this.mview=view;
        this.context=context;

    }


    @Override
    public void ondestory() {

    }

    @Override
    public void getDycode(String tel) {
        FotileRetrofit.getInstance().getRetrofit().create(HttpService.class).
                getResponse(tel).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<NoResponseResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof SocketTimeoutException) {
                    Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
                } else if (e instanceof ConnectException) {
                    Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context,   e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.i("tag", "error----------->" + e.toString());
                }
            }

            @Override
            public void onNext(NoResponseResult noResponseResult) {
                if(noResponseResult.getSuccess().equals("1")){
               //     ToastUtil.showLong(context,"动态验证码已发出");
                    mview.refreshDycode();
                }else {
                      ToastUtil.showLong(context,noResponseResult.getErrorMessage());
                }
            }
        });

    }



    //验证动态验证码
    @Override
    public void userValid(final String tel, final String dycode) {
        FotileRetrofit.getInstance().getRetrofit().create(HttpService.class).
                conformDycode(tel, dycode,"1").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<NoResponseResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof SocketTimeoutException) {
                    Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
                } else if (e instanceof ConnectException) {
                    Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context,   e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.i("tag", "error----------->" + e.toString());
                }
            }

            @Override
            public void onNext(NoResponseResult result) {
               if(result.getSuccess().equals("1")){
                //   ToastUtil.showLong(context,"验证成功");
                  mview.verifySucc();
               }else{
                   ToastUtil.showLong(context, result.getErrorMessage());
               }
            }
        });

    }
    @Override
    public void register(String tel, String code, String pwd) {
        String MD5pwd= MD5Util.getStringMD5(pwd+"FOTILE");
    //    LogUtil.e("密码"+MD5pwd);
        RegisterSubject basepar = new RegisterSubject(new ProgressSubscriber(registerOnNext, context), tel, code, MD5pwd);
        FotileRetrofit.getInstance().doHttpDeal2(basepar);
    }


    HttpOnNextListener registerOnNext=new HttpOnNextListener<UserInfo>() {
        @Override
        public void onNext(UserInfo info) {
          //  LogUtil.e(info.toString());
            MySelfInfo.getInstance().setIdentifier(info.getIdentifier());
            MySelfInfo.getInstance().setUserSig(info.getTlsSig());
                   mview.RegisterSucc();
        }
    };
}
