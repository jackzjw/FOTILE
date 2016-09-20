package com.example.sg280.fotile.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 内部ScrollView，解决滑动内部ScrollView时，触发外部滚动问题
 *
 * @author wuguangxin
 * @date 16/7/1 上午10:34
 */
public class XinInnerScrollView extends ScrollView {
    private final String TAG = "XinInnerScrollView";
    private float childHeight = 0;
    private float downX, downY; // 按下时
    private float currX, currY; // 移动时
    private float moveY; // 从按下到移动的Y距离
    private float scrollViewHeight;
    private boolean isOnTop; // ScrollView是否处于屏幕顶端
    private boolean isOnBottom; // ScrollView是否处于屏幕底端
    private boolean debug = true;
    private Position position = Position.NONE;

    public XinInnerScrollView(Context context) {
        this(context, null);
    }

    public XinInnerScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XinInnerScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
        case MotionEvent.ACTION_DOWN:
            getParent().getParent().requestDisallowInterceptTouchEvent(true);
            downX = ev.getX();
            downY = ev.getY();
            childHeight = getChildAt(0).getMeasuredHeight();
            scrollViewHeight = getHeight();
            break;
        case MotionEvent.ACTION_MOVE:
            currX = ev.getX();
            currY = ev.getY();
            moveY = Math.abs(currY - downY);
            isOnTop = getScrollY() == 0;
            isOnBottom = (getScrollY() + scrollViewHeight) == childHeight;
            // 垂直滑动
            if (moveY > Math.abs(currX - downX)) {
                if (childHeight <= scrollViewHeight) {
                    printLog("onTouchEvent ACTION_MOVE 不能滚动 父处理");
                    getParent().getParent().requestDisallowInterceptTouchEvent(false);
                } else if (isOnTop) { // 当前处于ScrollView顶部
                    if (currY - downY > 0) {
                        printLog("onTouchEvent ACTION_MOVE 已到顶部 下滑 父处理");
                        getParent().getParent().requestDisallowInterceptTouchEvent(false);
                    } else {
                        printLog("onTouchEvent ACTION_MOVE 已到顶部 上滑 子处理");
                    }
                } else if (isOnBottom) {
                    // 当前处于ScrollView底部
                    if (currY - downY < 0) {
                        printLog("onTouchEvent ACTION_MOVE 已到底部 上滑 父处理");
                        getParent().getParent().requestDisallowInterceptTouchEvent(false);
                    } else {
                        printLog("onTouchEvent ACTION_MOVE 已到底部 下滑 子处理");
                    }
                } else {
                    // 当前处于ScrollView中间
                    printLog("onTouchEvent ACTION_MOVE 在中间 子处理");
                }
            }
            // 水平滚动
            else {
                if(position.equals(Position.TOP)){
                    printLog("onTouchEvent ACTION_MOVE 水平滚动 position=TOP 子处理");
                } else {
                    if(Math.abs(currX - downX) > 30){
                        printLog("onTouchEvent ACTION_MOVE 水平滚动 position!=TOP 横向滑动距离>30 父处理");
                        getParent().getParent().requestDisallowInterceptTouchEvent(false);
                    } else {
                        printLog("onTouchEvent ACTION_MOVE 水平滚动 position!=TOP 横向滑动距离<=30 子处理");
                    }
                }
            }
            break;
        case MotionEvent.ACTION_UP:
            printLog("onTouchEvent ACTION_UP ========================");
            getParent().getParent().requestDisallowInterceptTouchEvent(true);
            break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 为了更好的处理手势滑动事件，设置该组件所处的位置；
     * 比如只有上下两屏时，如果该View是在第一屏，那么设置为Position.TOP,如果在第二屏，则设置为Position.BOTTOM
     *
     * @param position
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    public static enum Position {
        /**
         * 顶部View，横向滑动时将不考虑将事件交给父View。（该设计只为第一屏为纯ScrollView考虑）
         */
        TOP,
        /**
         * 底部View, 横向滑动时，将把事件交给父View处理
         */
        BOTTOM,
        /**
         * 不设置，将自动判断(自动判断并不是很精准)
         */
        NONE
    }

    public void printLog(String msg) {
        if (debug) {
            Log.d(TAG, msg);
        }
    }
}

// ===================== 条件参考 =================================
//              // 垂直滑动
//            if (moveY > Math.abs(currX - downX)) {
//                if (childHeight <= scrollViewHeight) {
//                    printLog("onTouchEvent ACTION_MOVE 不能滚动 父处理");
//                    getParent().getParent().requestDisallowInterceptTouchEvent(false);
//                } else if (isOnTop) { // 当前处于ScrollView顶部
//                    if (currY - downY > 0) {
//                        printLog("onTouchEvent ACTION_MOVE 已到顶部 下滑 父处理");
//                        getParent().getParent().requestDisallowInterceptTouchEvent(false);
//                    } else {
//                        printLog("onTouchEvent ACTION_MOVE 已到顶部 上滑 子处理");
//                    }
//                } else if (isOnBottom) {
//                    // 当前处于ScrollView底部
//                    if (currY - downY < 0) {
//                        printLog("onTouchEvent ACTION_MOVE 已到底部 上滑 父处理");
//                        getParent().getParent().requestDisallowInterceptTouchEvent(false);
//                    } else {
//                        printLog("onTouchEvent ACTION_MOVE 已到底部 下滑 子处理");
//                    }
//                } else {
//                    // 当前处于ScrollView中间
//                    printLog("onTouchEvent ACTION_MOVE 在中间 子处理");
//                }
//            }
//            // 水平滚动
//            else {
//                if(!position.equals(Position.TOP)){
//                    printLog("onTouchEvent ACTION_MOVE 水平滚动 position=TOP 子处理");
//                } else {
//                    if(Math.abs(currX - downX) > 30){
//                        printLog("onTouchEvent ACTION_MOVE 水平滚动 position!=TOP 横向滑动距离>30 父处理");
//                        getParent().getParent().requestDisallowInterceptTouchEvent(false);
//                    } else {
//                        printLog("onTouchEvent ACTION_MOVE 水平滚动 position!=TOP 横向滑动距离<=30 子处理");
//                    }
//                }
//            }