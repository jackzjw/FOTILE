package com.example.sg280.fotile.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * 内部WebView, 该View只适合放在最后一屏
 * 
 * @author wuguangxin
 * @date 16/7/1 上午10:34
 */
public class XinInnerWebView extends WebView {
    private final String TAG = "XinInnerScrollView";
    private boolean debug = true;
    private float downX, downY; // 按下时
    private float currX, currY; // 移动时
    private float moveX; // 移动长度-横向

    public XinInnerWebView(Context context) {
        super(context);
    }

    public XinInnerWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XinInnerWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
        case MotionEvent.ACTION_DOWN:
            getParent().getParent().requestDisallowInterceptTouchEvent(true);
            printLog("onTouchEvent ACTION_DOWN");
            downX = ev.getX();
            downY = ev.getY();
            break;
        case MotionEvent.ACTION_MOVE:
            currX = ev.getX();
            currY = ev.getY();
            moveX = Math.abs(currX - downX);
            printLog("onTouchEvent ACTION_MOVE getScrollX()="+getScrollX() + "  getScrollY()="+getScrollY());
            // 垂直滑动
            if (Math.abs(currY - downY) > moveX) {
                // 处于顶部或者无法滚动，并且继续下滑，交出事件（currY-downY  >0是下滑, <0则是上滑）
                if (getScrollY() == 0 && currY - downY > 0) {
                    printLog("onTouchEvent ACTION_MOVE 在顶部 下滑 父处理");
                    getParent().getParent().requestDisallowInterceptTouchEvent(false);
                }
                // 已到底部且继续上滑时，把事件交出去
                else if(getContentHeight()*getScale() - (getHeight() + getScrollY()) <= 1 && currY - downY < 0){
                    printLog("onTouchEvent ACTION_MOVE 在底部 上滑 父处理");
                    getParent().getParent().requestDisallowInterceptTouchEvent(false);
                }
            }
            // 水平滚动,横向滑动长度大于20像素时再交出去，不然都当做是垂直滑动。
            else if(moveX > 20){
                // 横向滑动事不直接交出去，是因为可能页面出现水平滚动条，就是网页宽度比屏幕还宽的情况下就需要判断滑到左边和滑到右边的情况。
                // printLog("onTouchEvent ACTION_MOVE 横向滑动 父处理");
                // getParent().getParent().requestDisallowInterceptTouchEvent(false);

                // 已在左边且继续右滑时，把事件交出去（currX - downX  >0是右滑, <0则是左滑）
                if (getScrollX() == 0 && currX - downX > 0) {
                    printLog("onTouchEvent ACTION_MOVE 在左边 右滑 父处理");
                    getParent().getParent().requestDisallowInterceptTouchEvent(false);
                }
                // 已在右边且继续左滑时，把事件交出去
                else if(getRight()*getScale() - (getWidth() + getScrollX()) <= 1 && currX - downX < 0){
                    printLog("onTouchEvent ACTION_MOVE 在右边 左滑 父处理");
                    getParent().getParent().requestDisallowInterceptTouchEvent(false);
                }
            }
            break;
        case MotionEvent.ACTION_CANCEL:
        case MotionEvent.ACTION_UP:
            printLog("onTouchEvent ACTION_UP");
            getParent().getParent().requestDisallowInterceptTouchEvent(true);
            break;
        }
        return super.onTouchEvent(ev);
    }

    public void printLog(String msg) {
        if (debug) {
            Log.d(TAG, msg);
        }
    }
}
