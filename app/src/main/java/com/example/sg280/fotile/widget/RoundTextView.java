package com.example.sg280.fotile.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.sg280.fotile.R;

/**
 * Created by sg280 on 2016-08-10.
 */
public class RoundTextView extends TextView {
    private int mBgColor;
    private int mCornerSize;
    private int mEdgeColor;
    public RoundTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttrs(context,attrs);
    }

    private void getAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.round_textview);
        mBgColor = ta.getColor(R.styleable.round_textview_round_tv_color, Color.WHITE);
        mCornerSize = (int)ta.getDimension(R.styleable.round_textview_round_tv_corner_size, 8);
        mEdgeColor=ta.getColor(R.styleable.round_textview_round_tv_edge_color,context.getResources().getColor(R.color.hui_bg));
        ta.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundRounded(getMeasuredWidth(),getMeasuredHeight(),this);

    }
    private void setBackgroundRounded(int w, int h,View v)
    {
        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
        RectF rec = new RectF(0, 0, w, h);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(mBgColor);
//        paint.setColor(mContext.getResources().getColor(R.color.blue));
        c.drawRoundRect(rec, mCornerSize, mCornerSize, paint);
        paint.setStyle(Paint.Style.STROKE);//设置填充样式为描边
        paint.setColor(mEdgeColor);//设置颜色为黑色
        paint.setStrokeWidth(2);//设置笔触宽度为2像素
        c.drawRoundRect(rec, mCornerSize, mCornerSize, paint);//绘制一个描边的圆角矩形
        v.setBackgroundDrawable(new BitmapDrawable(getResources(), bmp));
    }
    public void setRoundColor(int color){
        this.mBgColor=color;
        invalidate();
    }
}
