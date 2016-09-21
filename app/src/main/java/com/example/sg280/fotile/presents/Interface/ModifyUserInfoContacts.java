package com.example.sg280.fotile.presents.Interface;

import com.example.sg280.fotile.presents.BasePresenter;
import com.example.sg280.fotile.ui.BaseView;

/**
 * Created by Tian on 2016/8/4.
 */
public interface ModifyUserInfoContacts {
    interface View extends BaseView {

        void modifySuc();//修改成功
    }
    interface Present extends BasePresenter {
        //完成修改
        void overModify(String userId, String userName, String userTel, String oldPwd,String pwdNew,String againNewPwd);

    }
}
