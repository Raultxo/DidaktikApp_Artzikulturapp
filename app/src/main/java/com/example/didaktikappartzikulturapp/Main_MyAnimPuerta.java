package com.example.didaktikappartzikulturapp;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

public class Main_MyAnimPuerta extends Animation {

    private final LinearLayout view;
    private int valorIntDrawable;

    public Main_MyAnimPuerta(LinearLayout view, int valorIntDrawable) {
        this.view = view;
        this.valorIntDrawable = valorIntDrawable;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        view.setBackgroundResource(valorIntDrawable);
    }

    public void setFondo(int fondo) {
        this.valorIntDrawable = fondo;
    }
}
