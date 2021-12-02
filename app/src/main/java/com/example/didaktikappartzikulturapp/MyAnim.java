package com.example.didaktikappartzikulturapp;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

public class MyAnim extends Animation {

    private final LinearLayout view;
    private int targetBackGround;


    public MyAnim(LinearLayout view, int tagetBackGroundColor) {
        this.view = view;
        this.targetBackGround = tagetBackGroundColor;
    }
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        view.setBackgroundResource(targetBackGround);
    }

    public void setBack(int back) {
        this.targetBackGround = back;
    }
}
