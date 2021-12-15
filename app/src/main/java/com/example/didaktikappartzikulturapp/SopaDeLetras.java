package com.example.didaktikappartzikulturapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Objects;

public class SopaDeLetras extends AppCompatActivity {

    private GridView sopa;
    private String[] letras = new String[] {
            "Y","X","R","E","L","I","Z","G","I","Z","O","N","A","N",
            "T","I","R","Z","A","L","D","U","N","A","B","K","W","E",
            "F","J","J","U","G","L","A","R","E","A","C","P","W","P",
            "R","P","S","A","L","T","I","M","B","A","N","K","I","A",
            "P","T","I","T","I","R","I","T","E","R","O","A","E","Q",
            "A","R","T","I","S","A","U","A","N","O","B","L","E","A",
            "A","T","R","O","B","A","D","O","R","E","A","Z","R","K",
            "M","I","V","S","E","Z","I","N","G","A","R","O","A","R",
            "E","L","E","G","E","N","A","R","D","U","N","A","W","O",
            "A","M","E","C","M","A","G","O","A","J","G","Q","U","A",
            "L","M","A","L","A","B","A","R","I","S","T","A","P","M",
            "S","E","S","K","A","L","E","A","B","U","F","O","I","A",
            "H","Z","M","E","R","K","A","T","A","R","I","A","D","Y",
            "F","D","O","N","T","Z","E","I","L","A","Z","L","W","I"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sopa_de_letras);

        // Quitar la barra de titulo de actividad
        Objects.requireNonNull(getSupportActionBar()).hide();

        // Quitar la barra de notificaciones, bateria, hora, etc
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        sopa = findViewById(R.id.sopa);

        ArrayAdapter<String> listaAdapter = new ArrayAdapter<String>(this, R.layout.textview_sopa, letras);
        sopa.setAdapter(listaAdapter);

        sopa.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                System.out.println("UWU");
                return false;
            }
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