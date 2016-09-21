package com.example.sg280.fotile.presents.Interface;

import com.example.sg280.fotile.model.bean.LiveProductBean;
import com.example.sg280.fotile.ui.BaseView;

import java.util.List;

/**
 * Created by sg280 on 2016-08-11.
 */
public interface ISeeBuyContacts {
    interface View extends BaseView{
        void fetchSuccess(List<LiveProductBean> data);
    }
    interface Present{
      void  loadData(String action,String liveid,int page);
    }
}
