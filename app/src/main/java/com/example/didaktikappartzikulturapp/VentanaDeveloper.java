package com.example.didaktikappartzikulturapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class VentanaDeveloper extends AppCompatActivity {

    private Button btnAct1, btnAct2, btnAct3, btnAct4, btnAct5, btnAct6, btnAct7, btnAct8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_developer);

        Objects.requireNonNull(getSupportActionBar()).hide();

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        // vuelve a la ventana principal
        //botones
        Button btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(view -> {
            finish();
            overridePendingTransition(0,0);
        });

        // Botones actividades
        btnAct1 = findViewById(R.id.actividad1);
        btnAct2 = findViewById(R.id.actividad2);
        btnAct3 = findViewById(R.id.actividad3);
        btnAct4 = findViewById(R.id.actividad4);
        btnAct5 = findViewById(R.id.actividad5);
        btnAct6 = findViewById(R.id.actividad6);
        btnAct7 = findViewById(R.id.actividad7);
        btnAct8 = findViewById(R.id.actividad8);

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
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
    }

    public void abrirActividad(View v){
        if(v.equals(btnAct1)){
            Intent intent = new Intent(VentanaDeveloper.this, Actividad1.class);
            startActivity(intent);
        }

        if(v.equals(btnAct2)){
            Intent intent = new Intent(VentanaDeveloper.this, Actividad2.class);
            startActivity(intent);
        }

        if(v.equals(btnAct3)){
            Intent intent = new Intent(VentanaDeveloper.this, Actividad3.class);
            startActivity(intent);
        }

        if(v.equals(btnAct4)){
            System.out.println("Click en actividad 4");
            //Intent intent = new Intent(VentanaDeveloper.this, Actividad4.class);
            //startActivity(intent);
        }

        if(v.equals(btnAct5)){
            System.out.println("Click en actividad 5");
            //Intent intent = new Intent(VentanaDeveloper.this, Actividad5.class);
            //startActivity(intent);
        }

        if(v.equals(btnAct6)){
            System.out.println("Click en actividad 6");
            //Intent intent = new Intent(VentanaDeveloper.this, Actividad6.class);
            //startActivity(intent);
        }

        if(v.equals(btnAct7)){
            System.out.println("Click en actividad 7");
            //Intent intent = new Intent(VentanaDeveloper.this, Actividad7.class);
            //startActivity(intent);
        }

        if(v.equals(btnAct8)){
            System.out.println("Click en actividad 8");
            //Intent intent = new Intent(VentanaDeveloper.this, Actividad8.class);
            //startActivity(intent);
        }
    }
}
