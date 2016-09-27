package com.example.sg280.fotile.presents.Interface;

import com.example.sg280.fotile.model.bean.UserOrderInfoBean;
import com.example.sg280.fotile.presents.BasePresenter;
import com.example.sg280.fotile.ui.BaseView;

/**
 * Created by Tian on 2016/9/20.
 */
public interface UserOrderInfoContacts {

    interface View extends BaseView{

        /**
         * 将获取到的订单详情显示到界面上
         * @param userOrderInfoBean
         */
        void setInfo(UserOrderInfoBean userOrderInfoBean);
    }

    interface Present extends BasePresenter{

        /**
         * 获取订单详情
         * @param orderId 订单Id
         */
        void getUserOrderInfo(String orderId);

    }

}
