package com.example.sg280.fotile.presents.Interface;

import com.example.sg280.fotile.presents.BasePresenter;
import com.example.sg280.fotile.ui.BaseView;

/**
 * Created by Tian on 2016/9/19.
 */
public interface UserPointContacts {

    interface View extends BaseView{

        /**
         * 显示积分信息
         * @param totalPoint 总积分
         * @param usedPoint 已用积分
         */
        void setPoint(String totalPoint,String usedPoint);
    }

    interface Present extends BasePresenter{

        /**
         * 获取用户详情拿取积分
         */
        void getPoint();
    }
}
