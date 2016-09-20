package com.example.sg280.fotile.model.bean;

import java.util.List;

/**
 * Created by sg280 on 2016/9/2.
 */
public class HomeRequest {
    private HttpResult<List<HomeAdBean>> ad;
    private HttpResult<List<HomeLiveList>> data;
    private HttpResult<List<HomeHotProBean>> proinfo;

    public HttpResult<List<HomeHotProBean>> getProinfo() {
        return proinfo;
    }

    public void setProinfo(HttpResult<List<HomeHotProBean>> proinfo) {
        this.proinfo = proinfo;
    }

    public HttpResult<List<HomeAdBean>> getAd() {
        return ad;
    }

    public void setAd(HttpResult<List<HomeAdBean>> ad) {
        this.ad = ad;
    }

    public HttpResult<List<HomeLiveList>> getData() {
        return data;
    }

    public void setData(HttpResult<List<HomeLiveList>> data) {
        this.data = data;
    }
}
