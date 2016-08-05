package com.example.sg280.fotile.app;

import android.app.Application;

import com.example.sg280.fotile.presents.InitAppPresent;
import com.example.sg280.fotile.utils.LogUtil;
import com.example.sg280.fotile.utils.ToastUtil;
import com.squareup.leakcanary.LeakCanary;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by sg280 on 2016-07-14.
 */
public class FTApplication extends Application{
 private static FTApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        //配置是否显示log
        LogUtil.isDebug = true;

        //配置时候显示toast
        ToastUtil.isShow = true;
            //APP初始化
        InitAppPresent.initApp(getApplicationContext());
        LeakCanary.install(this);
    }
    public static OkHttpClient defaultOkHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .build();
        return client;
    }
    public static FTApplication getInstance(){
        return instance;
    }
}
