package com.example.sg280.fotile.presents.Interface;

import com.example.sg280.fotile.presents.BasePresenter;
import com.example.sg280.fotile.ui.BaseView;

/**
 * Created by sg280 on 2016-07-25.
 */
public interface ILoginContacts {
    interface View extends BaseView{
        void loginSucc();
        void loginFailed();
    }
    interface Present extends BasePresenter {
        void ftLogin(String id,String pwd);
        void imLogin(String indentify,String usersig);

    }
}
