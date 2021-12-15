package com.example.didaktikappartzikulturapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private String codigo = "";

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        Button btnEmpezar = findViewById(R.id.btnEmpezar);
        btnEmpezar.setOnClickListener(view -> {
            System.out.println(codigo);
            if(codigo.equals("aabbcdcd")) {
                Toast.makeText(MainActivity.this, "MODO DEVELOPER ACTIVADO", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(MainActivity.this, VentanaDeveloper.class);
                Intent intent = new Intent(MainActivity.this, VentanaGrupos.class);
                startActivity(intent);
            }
            else {
                //Intent intent = new Intent(MainActivity.this, VentanaGrupos.class);
                Intent intent = new Intent(MainActivity.this, VentanaDeveloper.class);
                startActivity(intent);
            }
            codigo = "";
        });
        ConstraintLayout principal = findViewById(R.id.principal);
        principal.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {

            @Override
            public void onSwipeTop() {
                codigo += "a";
            }

            @Override
            public void onSwipeBottom() {
                codigo += "b";
            }

            @Override
            public void onSwipeLeft() {
                codigo += "c";
            }

            @Override
            public void onSwipeRight() {
                codigo += "d";
            }
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