package com.example.sg280.fotile.model.bean;

import java.util.List;

/**
 * Created by Tian on 2016/8/16.
 */
public abstract class ShoppingLogicClass {

    //品牌的选择框是否显示
    public abstract boolean brandIsShow(List<Boolean> List);

    public abstract boolean brandIsShow(int a,int b);

    //选中的商品的总价格
    public abstract int getTotalPrice();
}
