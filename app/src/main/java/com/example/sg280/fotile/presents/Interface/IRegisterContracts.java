package com.example.sg280.fotile.presents.Interface;

import com.example.sg280.fotile.presents.BasePresenter;
import com.example.sg280.fotile.ui.BaseView;

/**
 * Created by sg280 on 2016-07-26.
 */
public interface IRegisterContracts {
    interface View extends BaseView{
        void RegisterSucc();

        void refreshDycode();
        void verifySucc();

    }
    interface Present extends BasePresenter {
        void getDycode(String tel);
        void userValid(String tel,String dycode);
        void register(String tel,String dycode,String pwd);

    }

}
