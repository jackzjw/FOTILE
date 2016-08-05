package com.example.sg280.fotile.ui.activity;

import com.example.sg280.fotile.R;

/**
 * Created by SJ on 2016/7/28.
 */
public class StandardChoiceActivity extends BaseActivity {
    @Override
    protected int getLayoutResource() {
        //设置点击空白是否消失true为消失
        setFinishOnTouchOutside(true);
        return R.layout.activity_pay_successful;
    }

    @Override
    protected void onInitView() {
    // 设置宽度为屏宽、靠近屏幕底部。

//        Window window = getWindow();
//        WindowManager.LayoutParams wlp = window.getAttributes();
//        wlp.gravity = Gravity.BOTTOM;
//        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        window.setAttributes(wlp);
    }
}
