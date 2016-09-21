package com.example.sg280.fotile.presents.Interface;

import com.example.sg280.fotile.model.bean.HomeAdBean;
import com.example.sg280.fotile.model.bean.HomeHotProBean;
import com.example.sg280.fotile.model.bean.ProductCategoryBean;

import java.util.List;

/**
 * Created by sg280 on 2016/9/5.
 */
public interface IProductsContacts {
    interface View{
        void loadAdInfo(List<HomeAdBean> adBeans);
        void loadCategoryList(List<ProductCategoryBean> productCategoryBeans);
        void loadProList(List<HomeHotProBean> homeHotProBeans);
    }
    interface Present{
        void  getAdInfo();
        void getProCategory();
        void getCategoryPro(String classid,int start,int limit);

    }
}
