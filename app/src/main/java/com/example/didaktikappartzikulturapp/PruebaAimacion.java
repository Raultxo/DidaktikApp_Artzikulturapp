package com.example.didaktikappartzikulturapp;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class PruebaAimacion extends AppCompatActivity {

    private LinearLayout layout;
    private MyAnim backgroundAnim;
    private final int[] fondos = new int[] {
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
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prueba_animacion);
        getSupportActionBar().hide();

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        layout = (LinearLayout) findViewById(R.id.animacion);

        backgroundAnim = new MyAnim(layout, fondos[i]);
        backgroundAnim.setDuration(125);
        layout.startAnimation(backgroundAnim);
        backgroundAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                if(i == fondos.length - 1) {
                    finish();
                } else {
                    i++;
                }
                backgroundAnim.setBack(fondos[i]);
                layout.startAnimation(backgroundAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }
}