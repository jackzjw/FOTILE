package com.example.sg280.fotile.model.bean;

import java.util.List;

/**
 * 多个数相加
 * Created by Tian on 2016/8/17.
 */
public class AddMoreArithmetic extends ArithmeticClass {


    private List<Double> list;
    private int result = 0;

    public AddMoreArithmetic(List<Double> list) {
        this.list = list;
    }

    @Override
    double arithmetic() {

        if(null == list || list.size() == 0){
            return 0;
        }

        for (int i = 0; i < list.size(); i++) {
            result += list.get(i);
        }

        return result;
    }
}
