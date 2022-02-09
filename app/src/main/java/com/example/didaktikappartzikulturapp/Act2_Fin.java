package com.example.didaktikappartzikulturapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class Act2_Fin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act2_fin);

        // Quitar la barra de titulo de actividad
        Objects.requireNonNull(getSupportActionBar()).hide();

        // Quitar la barra de notificaciones, bateria, hora, etc
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);


        // Boton para continuar e ir a la sopa de letras
        Button btnContinuar = findViewById(R.id.btnContinuar);
        btnContinuar.setOnClickListener(view -> {
            Intent intent = new Intent(Act2_Fin.this, Main_VentanaDeveloper.class);
            startActivity(intent);
        });
    }

    // Metodo para que quite la barra de navegacion, notificaciones, etc cuando se cambia el focus
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