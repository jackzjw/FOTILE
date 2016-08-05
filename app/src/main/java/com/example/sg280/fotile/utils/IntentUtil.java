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
}
