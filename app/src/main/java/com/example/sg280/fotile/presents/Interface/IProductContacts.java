package com.example.sg280.fotile.presents.Interface;

import com.example.sg280.fotile.model.bean.ProductsBean;
import com.example.sg280.fotile.ui.BaseView;

import java.util.HashMap;

/**
 * Created by sg280 on 2016-08-23.
 */
public interface IProductContacts {
    interface View extends BaseView{
        void fetchData(ProductsBean bean);
        void addCartSuccess();
        void addCartFailed(String msg);
        void collectSuccess();
    }
    interface Present {
        void getProductDetails(String proid);
        void addCart(HashMap<String,Object> map);
        void isCollect(String userid,String itemid,int type);
    }
}
