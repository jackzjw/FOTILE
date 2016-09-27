package com.example.sg280.fotile.model.bean;

/**
 * 加法运算
 * Created by Tian on 2016/8/15.
 */
public class AddTwoArithmetic extends ArithmeticClass {

    private int a;//被加数
    private int b;//加数

    public AddTwoArithmetic() {
    }

    /**
     *
     * @param a 被加数
     * @param b 加数
     */
    public AddTwoArithmetic(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    double arithmetic() {

        return a + b;
    }
}
