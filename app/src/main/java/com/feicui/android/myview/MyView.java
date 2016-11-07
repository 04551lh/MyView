package com.feicui.android.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/11/7.
 *
 */
public class MyView extends View {

    int index;
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setAntiAlias(true);
        RectF f = new RectF(200, 200, 500, 500);
        try {
            update();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        canvas.drawArc(f, -90, index, true, paint);
        paint.setColor(Color.RED);
        RectF minf = new RectF(210, 210, 490, 490);
        canvas.drawArc(minf, 0, 360, true, paint);

    }

    public void update() throws InterruptedException {
        if(index == 270){
            return;
        }
        index += 10;
        Thread.sleep(100);
        postInvalidate();
    }
}
