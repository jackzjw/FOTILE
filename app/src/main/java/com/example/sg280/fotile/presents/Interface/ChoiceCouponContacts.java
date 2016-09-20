package com.example.sg280.fotile.presents.Interface;

import com.example.sg280.fotile.presents.BasePresenter;
import com.example.sg280.fotile.ui.BaseView;

/**
 * Created by Tian on 2016/9/6.
 */
public interface ChoiceCouponContacts {

    interface View extends BaseView{

        /**
         * 没有数据，显示没有数据的状态
         */
        void noData();

        /**
         * 有数据，显示有数据的状态
         */
        void hadData();
    }

    interface Present extends BasePresenter {

        /**
         * 获取目前可用的优惠券
         */
        void getNowUsableCoupons(String ShopCarId);


        /**
         * 获取目前可用的优惠券(立即购买)
         */
        void getNowUsableCouponsAtOnce(String productid, String skuid, String productnum);
    }
}
