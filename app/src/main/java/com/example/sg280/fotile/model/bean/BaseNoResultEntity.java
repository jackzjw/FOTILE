package com.example.sg280.fotile.model.bean;

import android.util.Log;

import com.example.sg280.fotile.model.source.HttpService;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by sg027 on 2016/8/31.
 */
public abstract class BaseNoResultEntity implements Func1<NoResponseResult,String> {

    /**
     * 设置参数
     *
     * @param methods
     * @return
     */
    public abstract Observable getObservable(HttpService methods);

    /**
     * 设置回调sub
     *
     * @return
     */
    public abstract Subscriber getSubscirber();

    @Override
    public String call(NoResponseResult noResponseResult) {
        Log.i("返回的NoResponseResult",noResponseResult.toString());
        return noResponseResult.getSuccess();
    }
}
