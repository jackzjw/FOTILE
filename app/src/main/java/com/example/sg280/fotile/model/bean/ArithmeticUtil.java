package com.example.sg280.fotile.model.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 加、减、乘计算
 * Created by Tian on 2016/8/15.
 */
public class ArithmeticUtil {

    private static ArithmeticClass arithmeticClass = null;

    /**
     * 获得两个数运算的结果
     *
     * @param ari 计算的type +,-,*
     * @param a   参数a
     * @param b   参数b
     */
    public static int getResultWithTwoParam(String ari, int a, int b) {

        switch (ari) {
            case "+":
                arithmeticClass = new AddTwoArithmetic(a, b);
                break;
            case "-":
                arithmeticClass = new SubtractArithmetic(a, b);
                break;
            case "*":
                arithmeticClass = new MultiplicationArithmetic(a, b);
                break;
        }

        return arithmeticClass.arithmetic();

    }

    /**
     * 获取多个数相加的结果
     *
     * @param list 所有要加的数的list集合
     * @return list所有数的和
     */
    public static int getResultWithMoreAdd(List<Double> list) {

        arithmeticClass = new AddMoreArithmetic(list);

        return arithmeticClass.arithmetic();
    }


    /**
     * 根据传过来购物车内产品的集合返回这些产品的价钱
     * @param list GoodsShoppingCartBean购物车商品集合
     * @return 购物车内该品牌下所有已选产品的价钱
     */
    public static int getResultWithBeansAdd(List<ShoppingCartGoodsBean> list) {
        List<Double> intList = new ArrayList<>();
        if(null == list || list.size() == 0){
            return 0;
        }

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).isChecked()){
                intList.add(Integer.valueOf(list.get(i).getProductQuantity()) *
                        Double.valueOf(list.get(i).getPricePromo()));
            }
        }
        return new AddMoreArithmetic(intList).arithmetic();
    }


}
