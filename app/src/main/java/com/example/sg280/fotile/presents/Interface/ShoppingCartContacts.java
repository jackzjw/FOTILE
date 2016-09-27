package com.example.sg280.fotile.presents.Interface;

import com.example.sg280.fotile.presents.BasePresenter;
import com.example.sg280.fotile.ui.BaseView;

/**
 * Created by sg027 on 2016/8/31.
 */
public interface ShoppingCartContacts {

    interface View extends BaseView {

        void setBrandLogo(String logoUrl);

        void noData();

        void hadData();

    }

    interface Present extends BasePresenter {

        void getShoppingCartGoods();//获取购物车所有产品

        void modifyGoodsNum(String action, int position);


    }

}
