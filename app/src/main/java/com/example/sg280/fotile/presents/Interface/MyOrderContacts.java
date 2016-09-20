package com.example.sg280.fotile.presents.Interface;

import com.example.sg280.fotile.presents.BasePresenter;
import com.example.sg280.fotile.ui.BaseView;

/**
 * Created by sg027 on 2016/8/30.
 */
public interface MyOrderContacts {

    interface View extends BaseView{

    }

    interface Present extends BasePresenter{

        void getMyOrder(String orderstatus);

    }

}
