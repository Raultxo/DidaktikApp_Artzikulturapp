package com.example.didaktikappartzikulturapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class Act3_PruebaLinea extends View {
    private final Paint mPaint;
    private float startX;
    private float startY;
    private float endX;
    private float endY;

    public Act3_PruebaLinea(Context context) {
        this(context, null);
    }

    public Act3_PruebaLinea(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(20);
        int color = ContextCompat.getColor(context, R.color.purple_500);
        mPaint.setColor(color);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//
//        int desiredWidth = getSuggestedMinimumWidth() + getPaddingLeft() + getPaddingRight();
//        int desiredHeight = getSuggestedMinimumHeight() + getPaddingTop() + getPaddingBottom();
//
//        setMeasuredDimension(measureDimension(desiredWidth, widthMeasureSpec),
//                measureDimension(desiredHeight, heightMeasureSpec));
//    }
//
//    private int measureDimension(int desiredSize, int measureSpec) {
//        int result;
//        int specMode = MeasureSpec.getMode(measureSpec);
//        int specSize = MeasureSpec.getSize(measureSpec);
//
//        if (specMode == MeasureSpec.EXACTLY) {
//            result = specSize;
//        } else {
//            result = desiredSize;
//            if (specMode == MeasureSpec.AT_MOST) {
//                result = Math.min(result, specSize);
//            }
//        }
//
//        return result;
//    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(startX, startY, endX, endY, mPaint);
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                // Set the end to prevent initial jump (like on the demo recording)
                endX = event.getX();
                endY = event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                endX = event.getX();
                endY = event.getY();
                invalidate();
                break;
        }
        return true;
    }
}
