package com.example.sg280.fotile.model.bean;

/**
 * 乘法运算
 * Created by Tian on 2016/8/15.
 */
public class MultiplicationArithmetic extends ArithmeticClass {

    private int a;
    private int b;

    public MultiplicationArithmetic() {
    }

    /**
     *
     * @param a 被乘数
     * @param b 乘数
     */
    public MultiplicationArithmetic(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    double arithmetic() {
        return a * b;
    }
}
