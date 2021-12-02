package com.example.didaktikappartzikulturapp;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class PruebaAimacion extends AppCompatActivity {

    private LinearLayout layout;
    private MyAnim backgroundAnim;
    private static final int[] FONDOS = new int[] {
            R.drawable.fondo,
            R.drawable.fondo1,
            R.drawable.fondo2,
            R.drawable.fondo3,
            R.drawable.fondo4,
            R.drawable.fondo5,
            R.drawable.fondo6,
            R.drawable.fondo7,
            R.drawable.fondo8
    };
    private int posFondo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prueba_animacion);
        getSupportActionBar().hide();

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        layout = (LinearLayout) findViewById(R.id.animacion);

        backgroundAnim = new MyAnim(layout, FONDOS[posFondo]);
        backgroundAnim.setDuration(125);
        layout.startAnimation(backgroundAnim);
        backgroundAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                if(posFondo == FONDOS.length - 1) {
                    finish();
                } else {
                    posFondo++;
                }
                backgroundAnim.setFondo(FONDOS[posFondo]);
                layout.startAnimation(backgroundAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }
}