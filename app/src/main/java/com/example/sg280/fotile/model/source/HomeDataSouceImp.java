package com.example.sg280.fotile.model.source;

import com.example.sg280.fotile.http.FotileRetrofit;
import com.example.sg280.fotile.model.bean.HomeLiveList;
import com.example.sg280.fotile.model.bean.HttpResult;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sg280 on 2016-07-19.
 */
public class HomeDataSouceImp implements HomeDataSource {
    @Override
    public void getHomeData(final CallBack callBack) {
        FotileRetrofit.getRetrofit().create(HomeService.class).getHotLiveList().
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<HttpResult<List<HomeLiveList>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                    callBack.onDataNotAvaiable();
             //   callBack.onError(e.getMessage());
            }

            @Override
            public void onNext(HttpResult<List<HomeLiveList>> result) {

                          if(result.getSuccess()==1){
                              callBack.onHomeLoad(result.getRows());
                          }else {
                              callBack.onError(result.getErrorMessage());
                          }
            }
        });
    }
}
