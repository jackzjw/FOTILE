package com.example.sg280.fotile.presents.Interface;

import com.example.sg280.fotile.model.bean.ShippingAddressBean;
import com.example.sg280.fotile.presents.BasePresenter;
import com.example.sg280.fotile.ui.BaseView;

/**
 * Created by Tian on 2016/8/24.
 */
public interface OrderSureContacts{

    interface View extends BaseView{
        //设置默认地址
        void setDefault(ShippingAddressBean shippingAddressBean);

        //显示没有默认地址布局
        void noDefault();


        /**
         * 显示可用的积分数量
         */
        void setPoint(String point);


        /**
         * 显示可用优惠券数量
         */
        void setCouponsNum(String couponsNum);

        /**
         * 提交订单成功
         */
        void commitSuc();
    }

    interface present extends BasePresenter{

        void getDefaultAddress();//获取默认地址

        //提交订单
        void commitOrder(String shopCarId, String userId,
                         String couponcardid, String pcr, String address, String tel,
                         String postcode, String recipients, String paytype, String remark,
                         String invoicestatus, String integral, String isliji);

        //提交订单(立即购买)
        void commitOrderAtOnce(String productid, String skuid,
                               String productnum, String userId,
                               String couponcardid, String pcr, String address, String tel,
                               String postcode, String recipients, String paytype, String remark,
                               String invoicestatus, String integral, String isliji);

        //获取当前可用的优惠券
        void getCouponsNum(String action, String shopCarId);

        //获取当前可用的优惠券
        void getCouponsNumAtOnce(String action, String productid, String skuid,
                                 String productnum);

        //获取可用积分
        void getPoint();

    }
}
