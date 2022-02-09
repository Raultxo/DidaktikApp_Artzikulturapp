package com.example.didaktikappartzikulturapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main_Datos_Grupo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_datos_grupo);

        TextView txtNombre = findViewById(R.id.txtNombre);

        TextView txtActividad1 = findViewById(R.id.txtActividad1);
        TextView txtActividad2 = findViewById(R.id.txtActividad2);
        TextView txtActividad3 = findViewById(R.id.txtActividad3);
        TextView txtActividad4 = findViewById(R.id.txtActividad4);
        TextView txtActividad5 = findViewById(R.id.txtActividad5);
        TextView txtActividad6 = findViewById(R.id.txtActividad6);
        TextView txtActividad7 = findViewById(R.id.txtActividad7);
        TextView txtActividad8 = findViewById(R.id.txtActividad8);

        txtNombre.setText(getIntent().getExtras().getString("nombre"));

        if(getIntent().getExtras().getBoolean("actividad1")) {
            txtActividad1.setText("Osatua");
        }else {
            txtActividad1.setText("Osatu Gabe");
        }

        if(getIntent().getExtras().getBoolean("actividad2")) {
            txtActividad2.setText("Osatua");
        }else {
            txtActividad2.setText("Osatu Gabe");
        }

        if(getIntent().getExtras().getBoolean("actividad3")) {
            txtActividad3.setText("Osatua");
        }else {
            txtActividad3.setText("Osatu Gabe");
        }

        if(getIntent().getExtras().getBoolean("actividad4")) {
            txtActividad4.setText("Osatua");
        }else {
            txtActividad4.setText("Osatu Gabe");
        }

        if(getIntent().getExtras().getBoolean("actividad5")) {
            txtActividad5.setText("Osatua");
        }else {
            txtActividad5.setText("Osatu Gabe");
        }

        if(getIntent().getExtras().getBoolean("actividad6")) {
            txtActividad6.setText("Osatua");
        }else {
            txtActividad6.setText("Osatu Gabe");
        }

        if(getIntent().getExtras().getBoolean("actividad7")) {
            txtActividad7.setText("Osatua");
        }else {
            txtActividad7.setText("Osatu Gabe");
        }

        if(getIntent().getExtras().getBoolean("actividad8")) {
            txtActividad8.setText("Osatua");
        }else {
            txtActividad8.setText("Osatu Gabe");
        }
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