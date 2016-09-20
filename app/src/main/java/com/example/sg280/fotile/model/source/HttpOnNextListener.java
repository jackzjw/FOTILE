package com.example.sg280.fotile.model.source;

/**
 * 成功回调处理
 * Created by WZG on 2016/7/16.
 */
public interface HttpOnNextListener<T> {
    void onNext(T t);
}
