package com.example.sg280.fotile.app;

import android.app.Application;
import android.content.Context;

import com.example.sg280.fotile.presents.InitAppPresent;
import com.example.sg280.fotile.utils.LogUtil;
import com.example.sg280.fotile.utils.ToastUtil;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

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
        refWatcher=LeakCanary.install(this);
    }
    public static OkHttpClient defaultOkHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
        return client;
    }
    public static RefWatcher getRefWatcher(Context context) {
        FTApplication application = (FTApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;
    public static FTApplication getInstance(){
        return instance;
    }
}
