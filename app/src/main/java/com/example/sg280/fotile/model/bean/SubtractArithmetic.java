package com.example.sg280.fotile.model.bean;

/**
 * 减法运算
 * Created by Tian on 2016/8/15.
 */
public class SubtractArithmetic extends ArithmeticClass {

    private int a;
    private int b;

    public SubtractArithmetic() {
    }

    /**
     *
     * @param a 被减数
     * @param b 减数
     */
    public SubtractArithmetic(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    int arithmetic() {
        return a - b;
    }
}
