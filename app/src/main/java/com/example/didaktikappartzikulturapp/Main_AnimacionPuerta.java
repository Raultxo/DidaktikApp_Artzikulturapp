package com.example.didaktikappartzikulturapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class Main_AnimacionPuerta extends AppCompatActivity {

    private LinearLayout layout;
    private Main_MyAnimPuerta backgroundAnim;
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
        setContentView(R.layout.main_animacion_puerta);
        Objects.requireNonNull(getSupportActionBar()).hide();

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
        layout = (LinearLayout) findViewById(R.id.animacion);

        backgroundAnim = new Main_MyAnimPuerta(layout, FONDOS[posFondo]);
        backgroundAnim.setDuration(125);
        layout.startAnimation(backgroundAnim);
        backgroundAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                if(posFondo == FONDOS.length - 1) {
                    Intent intent = new Intent(Main_AnimacionPuerta.this, Mapa.class);
                    startActivity(intent);
                    overridePendingTransition(0,0);
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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}