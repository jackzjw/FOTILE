package com.example.sg280.fotile.utils;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Tian on 2016/8/3.
 */

public class IntentUtil {
    private static Intent intent = null;

    /**
     * Activity无参跳转
     * @param context 上下文本
     * @param c 要跳转的Class
     */
    public static void jumpToActivity(Context context,Class c){
        intent = new Intent(context,c);
        context.startActivity(intent);
    }

    /**
     * activity带一个String类型的跳转
     * @param context 上下文本
     * @param c 要跳转的界面Class
     * @param name 传递String类型的名字
     * @param para 传递的参数
     */
    public static void jumpToActivity(Context context,Class c,String name,String para){
        intent = new Intent(context,c);
        intent.putExtra(name,para);
        context.startActivity(intent);
    }

}
