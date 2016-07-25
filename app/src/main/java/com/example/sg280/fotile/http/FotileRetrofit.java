package com.example.sg280.fotile.http;

import com.example.sg280.fotile.app.Constants;
import com.example.sg280.fotile.app.FTApplication;
import com.example.sg280.fotile.model.bean.BaseEntity;
import com.example.sg280.fotile.model.source.HttpService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 */
public class FotileRetrofit {

    private final HttpService httpservice;
    private  Retrofit retrofit;
    private volatile static FotileRetrofit INSTANCE;
    public  FotileRetrofit() {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(Constants.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(FTApplication.defaultOkHttpClient())
                            .build();
        httpservice=retrofit.create(HttpService.class);
    }

    //获取单例
    public static FotileRetrofit getInstance() {
        if (INSTANCE == null) {
            synchronized (FotileRetrofit.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FotileRetrofit();
                }
            }
        }
        return INSTANCE;
    }
    /**
     * 处理http请求
     *
     * @param basePar 封装的请求数据
     */
    public void doHttpDeal(BaseEntity basePar) {
        Observable observable = basePar.getObservable(httpservice)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(basePar);
        observable.subscribe(basePar.getSubscirber());
    }

}
