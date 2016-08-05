package com.example.sg280.fotile.presents;

import com.example.sg280.fotile.ui.BaseView;

/**
 * Created by Tian on 2016/8/4.
 */
public interface ModifyUserInfoContacts {
    interface View extends BaseView {

        void modifySucc();//修改成功
    }
    interface Present extends BasePresenter{

        void overModify();//完成修改

    }
}
