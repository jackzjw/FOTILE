package com.example.sg280.fotile.presents.Interface;

import android.app.Activity;

import com.example.sg280.fotile.presents.BasePresenter;
import com.example.sg280.fotile.ui.BaseView;

import retrofit2.http.Field;

/**
 * Created by Tian on 2016/8/23.
 */
public interface MyAddressContacts {

    interface View extends BaseView {
        Activity getActivity();//获取当前activity


    }

    interface Present extends BasePresenter {

        //获取收货地址
        void getAddress();

        //修改收货地址
        void modifyAddress(String action, String userId, String pcr, String address, String tel,
                           String postcode, String recipients, String isDefault, String addressId);


    }

}
