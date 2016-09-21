package com.example.sg280.fotile.model.bean;

import java.util.List;

/**
 * Created by Tian on 2016/8/16.
 */
public class ShoppingLogicSon extends ShoppingLogicClass {

    private List<Integer> priceList;
    private List<Boolean> list;

    private int a;//购物车内该品牌下所有不同产品的数目
    private int b;//购物车内该品牌下所选择不同产品的数目

    public ShoppingLogicSon() {
    }



    @Override
    public boolean brandIsShow(List<Boolean> list) {

        if(null == list || list.size() == 0){
            return false;
        }

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i) == false){
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean brandIsShow(int a, int b) {
        if(a > b){
            return false;
        }else if(a == b){
            return true;
        }
        return false;
    }

    @Override
    public int getTotalPrice() {
        return 0;
    }

    public List<Integer> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<Integer> priceList) {
        this.priceList = priceList;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
