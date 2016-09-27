package com.example.sg280.fotile.app;
import android.app.Application;
import android.content.Context;

import com.example.sg280.fotile.presents.InitAppPresent;
import com.example.sg280.fotile.utils.LogUtil;
import com.example.sg280.fotile.utils.NetUtil;
import com.example.sg280.fotile.utils.ToastUtil;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



/**
 * Created by sg280 on 2016-07-14.
 */
public class FTApplication extends Application {
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
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        File cacheFile = new File(Constants.PATH_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 20);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetUtil.isNetworkConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                    LogUtil.e("no network");
                }
                Response response = chain.proceed(request);
                if (NetUtil.isNetworkConnected()) {
                    int maxAge = 0*60;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                    response.body().close();
                }
                LogUtil.e("response="+response.cacheResponse());
                return response;
            }
        };
      //  builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);
        //错误重连
        builder.retryOnConnectionFailure(true);
        OkHttpClient client = builder.connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
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
