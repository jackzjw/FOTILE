package com.example.sg280.fotile.presents;

import com.example.sg280.fotile.model.bean.HomeLiveList;
import com.example.sg280.fotile.ui.BaseView;

import java.util.List;

/**
 * Created by sg280 on 2016-07-19.
 */
public interface HomeContacts {
    interface View extends BaseView{
     void load(List<HomeLiveList> list);
        void onError(String error);
        void showError();
        void showNormal();
    }
    interface Present extends BasePresenter{

    }
}
