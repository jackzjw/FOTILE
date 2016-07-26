package com.example.sg280.fotile.presents;

import com.example.sg280.fotile.ui.BaseView;

/**
 * Created by sg280 on 2016-07-26.
 */
public interface RegisterContracts {
    interface View extends BaseView{
        void RegisterSucc();
        void failed(String msg);

    }
    interface Present extends BasePresenter{
        void getDycode(String tel);
        void userValid(String tel,String dycode,String pwd);
        void register(String tel, String code, String pwd);
    }

}
