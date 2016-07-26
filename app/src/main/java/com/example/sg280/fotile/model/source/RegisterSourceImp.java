package com.example.sg280.fotile.model.source;

import com.example.sg280.fotile.http.FotileRetrofit;
import com.example.sg280.fotile.model.bean.NoResponseResult;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by sg280 on 2016-07-26.
 */
public class RegisterSourceImp implements RegisterSouce{


    @Override
    public void getDycode(String tel, final DataListener listener) {
        FotileRetrofit.getInstance().getRetrofit().create(HttpService.class).
                getResponse(tel).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<NoResponseResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
              listener.noResponse();
            }

            @Override
            public void onNext(NoResponseResult noResponseResult) {
                          if(noResponseResult.getSuccess().equals("1")){
                            //  listener.success();
                          }else {
                              listener.failed(noResponseResult.getErrorMessage());
                          }
            }
        });
    }

    @Override
    public void userValid(String tel, String dycode, final DataListener listener) {
        FotileRetrofit.getInstance().getRetrofit().create(HttpService.class).
                conformDycode(tel,dycode).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<NoResponseResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                   listener.noResponse();
            }

            @Override
            public void onNext(NoResponseResult noResponseResult) {
                if(noResponseResult.getSuccess().equals("1")){
                     listener.success();
                }else {
                    listener.failed(noResponseResult.getErrorMessage());
                }
            }
        });
    }

    @Override
    public void register(String tel, String code, String pwd, DataListener listener) {

    }
}
