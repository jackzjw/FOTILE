package com.example.sg280.fotile.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;

import com.example.sg280.fotile.R;

/**
 * Created by sg280 on 2016-08-10.
 */
public class CommentSettingPopupWin extends PopupWindow {
    private Context mcontext;
    private  RadioButton rb_total;
    private RadioButton rb_goven;

    public CommentSettingPopupWin(Context context,View.OnClickListener listener){
        this.mcontext=context;
   final  View   view= LayoutInflater.from(context).inflate(R.layout.popupwindow_live_msg_setting,null);
        rb_total=(RadioButton)view.findViewById(R.id.rb1);
        rb_goven=(RadioButton)view.findViewById(R.id.rb2);
        rb_goven.setOnClickListener(listener);
        rb_total.setOnClickListener(listener);
        rb_total.setChecked(true);
        setOutsideTouchable(true);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = view.findViewById(R.id.popup_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
        setContentView(view);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);

        setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);
       setAnimationStyle(R.style.live_setting_anim);
    }

}
