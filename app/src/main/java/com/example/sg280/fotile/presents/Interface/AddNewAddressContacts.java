package com.example.sg280.fotile.presents.Interface;

import com.example.sg280.fotile.presents.BasePresenter;
import com.example.sg280.fotile.ui.BaseView;

import retrofit2.http.Field;

/**
 * Created by Tian on 2016/8/22.
 */
public interface AddNewAddressContacts {

    interface View extends BaseView {

        void addSuc();//添加成功

        void modifySuc();//修改成功
    }

    interface Present extends BasePresenter{

        //添加收货地址
        void addAddress(String type, String userid, String pcr, String address, String tel, String postcode, String recipients, String isdefault);

        //修改收货地址
        void modifyAddress(String action, String userId, String pcr, String address, String tel,
                           String postcode, String recipients, String isDefault, String addressId);
    }
}
