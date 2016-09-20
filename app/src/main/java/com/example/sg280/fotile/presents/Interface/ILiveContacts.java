package com.example.sg280.fotile.presents.Interface;

import com.example.sg280.fotile.model.bean.VedioDetailsBean;

/**
 * Created by sg280 on 2016-08-02.
 */
public interface ILiveContacts {
    interface View{
       void  getLiveViewSucc(VedioDetailsBean data);
    }
    interface Present{
        void getLiveView(String type,String liveid);

    }
}
