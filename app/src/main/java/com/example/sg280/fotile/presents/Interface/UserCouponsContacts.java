package com.example.sg280.fotile.presents.Interface;

import com.example.sg280.fotile.presents.BasePresenter;
import com.example.sg280.fotile.ui.BaseView;

/**
 * Created by Tian on 2016/9/18.
 */
public interface UserCouponsContacts {

    interface View extends BaseView{

    }

    interface Present extends BasePresenter{
        /**
         * 获取优惠券
         * @param action 可用或者不可用
         */
        void getCoupons(String action);
    }

}
