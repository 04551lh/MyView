package com.feicui.android.myview;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Administrator on 2016/11/7.
 *
 */
public class MyCustum extends View {
    private  String mTitleText ;
    private int mTitleTextColor;
    private int mTitleTextSize;

    private Rect mBound;
    private Paint mPaint;
    public MyCustum(Context context) {
        this(context, null);
    }

    public MyCustum(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCustum(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyCustum, defStyleAttr, 0);
        int n = array.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = array.getIndex(i);
            switch (attr){
                case R.styleable.MyCustum_titleText:
                    mTitleText = array.getString(attr);
                    break;
                case R.styleable.MyCustum_titleTextColor:
                    mTitleTextColor = array.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.MyCustum_titleTextSize:
                    mTitleTextSize = array.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
            }
        }
        array.recycle();
        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        mBound = new Rect();
        if(mTitleText != null){
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
        }
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTitleText = randomText();
                postInvalidate();
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int withMode = MeasureSpec.getMode(widthMeasureSpec);
        int withSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if(withMode == MeasureSpec.EXACTLY){
            width = withSize;
        }
        else{
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
            float textWidth = mBound.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }
        if(heightMode == MeasureSpec.EXACTLY){
            height = heightSize;
        }
        else{
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
            float textHeight = mBound.height();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = desired;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        mPaint.setColor(mTitleTextColor);
        if(mTitleText != null){
            canvas.drawText(mTitleText, getWidth()/2 - mBound.width()/2, getHeight()/2 + mBound.height()/2, mPaint);
        }
    }

    private String randomText(){
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4){
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (Integer i: set) {
            stringBuffer.append("" + i);
        }

        return stringBuffer.toString();
    }
}
