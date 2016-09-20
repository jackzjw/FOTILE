package com.example.sg280.fotile.presents.Interface;

import com.example.sg280.fotile.ui.BaseView;

/**
 * Created by sg280 on 2016-07-28.
 */
public interface IFindPwdContacts {
    interface View extends BaseView{
        void findPwdSucc();
    }
    interface Present{
        void findPwd(String userid,String newpwd);
    }
}
