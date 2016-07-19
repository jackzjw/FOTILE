package com.example.sg280.fotile.model.source;

import com.example.sg280.fotile.model.bean.HomeLiveList;

import retrofit2.http.GET;
import rx.Observable;

/**
 * 获取首页视频直播列表
 * Created by sg280 on 2016-07-18.
 */
public interface HomeDataSource {
    @GET("getHotLive.ashx")
    Observable<HomeLiveList> getHomeLiveList();
}
