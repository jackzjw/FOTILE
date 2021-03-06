package com.example.sg280.fotile.model.bean;

import com.example.sg280.fotile.app.exception.BaseException;
import com.example.sg280.fotile.model.source.HttpService;
import com.example.sg280.fotile.utils.LogUtil;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;



/**
 * 请求数据统一封装类
 * Created by WZG on 2016/7/16.
 */
public abstract class BaseEntity<T> implements Func1<HttpResult<T>, T> {
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
    public T call(HttpResult<T> httpResult) {

        LogUtil.i("返回的数据",httpResult.toString());
        if (httpResult.getSuccess().equals("0")) {
            throw new BaseException(httpResult.getErrorMessage());
        }
        return httpResult.getRows();
    }
}
