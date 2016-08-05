package com.example.sg280.fotile.presents.Interface;

import com.example.sg280.fotile.model.bean.HomeLiveList;
import com.example.sg280.fotile.presents.BasePresenter;
import com.example.sg280.fotile.ui.BaseView;

import java.util.List;

/**
 * Created by sg280 on 2016-07-19.
 */
public interface IHomeContacts {
    interface View extends BaseView{
     void load(List<HomeLiveList> list);
        void onError(String error);
        void showError();
        void showNormal();
    }
    interface Present extends BasePresenter {

    }
}
