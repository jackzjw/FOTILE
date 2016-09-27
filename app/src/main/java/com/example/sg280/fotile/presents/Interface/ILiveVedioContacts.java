package com.example.sg280.fotile.presents.Interface;

import com.example.sg280.fotile.model.bean.VedioDetailsBean;
import com.example.sg280.fotile.presents.BasePresenter;
import com.example.sg280.fotile.ui.BaseView;

import java.util.List;

/**
 * Created by sg280 on 2016-07-29.
 */
public interface ILiveVedioContacts {
    interface View extends BaseView {
        void refresh(List<VedioDetailsBean> data);
        void fetchSucc(List<VedioDetailsBean> data);


    }
    interface Present extends BasePresenter {

        void getVedioResource(int start,int limit,boolean isRefresh);
    }
}
