package com.example.sg280.fotile.presents.Interface;

import com.example.sg280.fotile.adapter.LiveOrDemandAdapter;
import com.example.sg280.fotile.presents.BasePresenter;
import com.example.sg280.fotile.ui.BaseView;

/**
 * Created by Tian on 2016/8/26.
 */
public interface MyCollectContacts {

    interface View extends BaseView{

    }

    interface present extends BasePresenter{

        //获取收藏列表
        void getMyCollectList(int itemType);
    }
}
