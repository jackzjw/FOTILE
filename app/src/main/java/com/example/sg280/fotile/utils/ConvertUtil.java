package com.example.sg280.fotile.utils;

import java.util.List;

/**
 * Created by sg027 on 2016/8/30.
 */
public class ConvertUtil {

    public static String getOrderStatus(String str) {
        String string = null;
        switch (str) {
            case "0":
                string = "取消订单";
                break;
            case "1":
                string = "待付款";
                break;
            case "2":
                string = "待发货";
                break;
            case "3":
                string = "待收货";
                break;
            case "4":
                string = "交易完成";
                break;
            default:
                break;
        }
        return string;
    }

    /**
     * 多个ID之间拼接","
     *
     * @param list
     * @return
     */
    public static String jointComma(List<String> list) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i) + ",");
        }

        return sb.substring(0, sb.length() - 1);
    }

}
