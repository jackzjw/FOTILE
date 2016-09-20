package com.example.sg280.fotile.presents.Interface;

import com.example.sg280.fotile.presents.BasePresenter;
import com.tencent.TIMMessage;

/**
 * Created by sg280 on 2016-08-16.
 */
public interface IIMContacts {
    interface View{
       void refreshText(String msg,String name);
    }
    interface Present extends BasePresenter{
        void sendGroupText(TIMMessage msg);
        void perpareQuitRoom(boolean ispause);
    }
}
