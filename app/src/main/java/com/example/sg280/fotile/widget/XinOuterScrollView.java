package com.example.sg280.fotile.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * 滚屏ScrollView，注意该XinOuterScrollView的子ViewGroup最多只能有2个childView，超出将无法滑动到超出的View
 *
 * @author wuguangxin
 * @date 16/7/1 上午10:34
 */
public class XinOuterScrollView extends ScrollView {
    private int max_len = 150; // 垂直滑动多长时切换到下一屏
    private int mScreenHeight;
    private int currentItem = 0;
    private boolean isSetted = false;
    private boolean isPageOne = true;

    public XinOuterScrollView(Context context) {
        this(context, null);
    }

    public XinOuterScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XinOuterScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 这里去掉状态栏高度，还要根据特殊情况处理
        mScreenHeight = getScreenHeight(context) - getStatusBarHeight(context);
        max_len = mScreenHeight / 8; // 滑动一定距离时切换到下一屏
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
        case MotionEvent.ACTION_UP:
            // 当抬起手指时判断，如果在第一屏
            if (isPageOne) {
                if (getScrollY() <= max_len) {
                    // 当滑动的距离不足指定值时，还原到第一屏
                   smoothScrollTo(0, 0);
                } else {
                    // 否则滚动到第二屏
                    smoothScrollTo(0, mScreenHeight);
                    setFocusable(false);
                    isPageOne = false;
                }
            }
            // 如果抬起手指时在第二屏
            else {
                if (mScreenHeight - getScrollY() >= max_len) {
                    // 垂直方向滑动超过指定长度，则滚动到第一屏
                    smoothScrollTo(0, 0);
                    isPageOne = true;
                } else {
                   // 否则还原到第二屏
                   smoothScrollTo(0, mScreenHeight);
              }
            }

/*
            // 当抬起手指时判断，如果在第一屏
            if (getScrollY() <= max_len) {
                // 当滑动的距离不足指定值时，还原到第一屏
                smoothScrollTo(0, currentItem * mScreenHeight);
                currentItem++;
            } else {
                // 否则滚动到第二屏
                smoothScrollTo(0, mScreenHeight);
                setFocusable(false);
                isPageOne = false;
            }

            // 如果抬起手指时在第二屏
            if (mScreenHeight - getScrollY() >= max_len) {
                // 垂直方向滑动超过指定长度，则滚动到第一屏
                smoothScrollTo(0, 0);
                isPageOne = true;
            } else {
                // 否则还原到第二屏
                smoothScrollTo(0, mScreenHeight);
            }*/
            return true;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isSetted) {
            // 把所有child的高度设置为屏幕的高度（注意该高度已经减去状态栏高度，如果不减去，将被状态栏遮挡顶部）
            // 该高度是固定的，所以只需设置一次即可
            final LinearLayout rootView = (LinearLayout) getChildAt(0);
            for (int i = 0; i < rootView.getChildCount(); i++) {
                View view = rootView.getChildAt(i);
                view.getLayoutParams().height = mScreenHeight;
            }
            isSetted = true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            this.scrollTo(0, 0);
        }
    }

    /**
     * 得到屏幕高度
     *
     * @return 单位:px
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        java.lang.reflect.Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
    }
}


