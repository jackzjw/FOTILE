package com.example.sg280.fotile.model.source;

/**
 * Created by sg280 on 2016-07-26.
 */
public interface RegisterSouce {
    interface DataListener{
        void success();
        void failed(String msg);
        void noResponse();
    }
    void getDycode(String tel,DataListener listener);
    void userValid(String tel,String dycode,DataListener listener);
    void register(String tel,String code,String pwd,DataListener listener);
}
