package com.example.sg280.fotile.model.source;

import com.example.sg280.fotile.model.bean.HomeLiveList;

import java.util.List;

/**
 * Created by sg280 on 2016-07-19.
 */
public interface HomeDataSource {
    interface CallBack{
        void onHomeLoad(List<HomeLiveList> data);
        void onDataNotAvaiable();
        void onError(String msg);
    }
  void getHomeData(CallBack callBack);
}
