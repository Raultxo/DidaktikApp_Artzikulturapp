package com.example.didaktikappartzikulturapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class Main_VentanaDeveloper extends AppCompatActivity {

    private Button btnAct1, btnAct2, btnAct3, btnAct4, btnAct5, btnAct6, btnAct7, btnAct8, btnMapa, btnPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_ventana_developer);

        Objects.requireNonNull(getSupportActionBar()).hide();

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        // vuelve a la ventana principal
        //botones
        Button btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(view -> {
            Intent intent = new Intent(Main_VentanaDeveloper.this, Main_Activity.class);
            startActivity(intent);
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
        btnMapa = findViewById(R.id.btnMapa);
        btnPrincipal = findViewById(R.id.btnPrincipal);
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
            Intent intent = new Intent(Main_VentanaDeveloper.this, Act1_Inicio.class);
            startActivity(intent);
        }

        if(v.equals(btnAct2)){
            Intent intent = new Intent(Main_VentanaDeveloper.this, Act2_Inicio.class);
            startActivity(intent);
        }

        if(v.equals(btnAct3)){
            Intent intent = new Intent(Main_VentanaDeveloper.this, Act3_Inicio.class);
            startActivity(intent);
        }

        if(v.equals(btnAct4)){
             Intent intent = new Intent(Main_VentanaDeveloper.this, Act4_Inicio.class);
             startActivity(intent);
            System.out.println("Click en actividad 4");
        }

        if(v.equals(btnAct5)){
            System.out.println("Click en actividad 5");
            // Intent intent = new Intent(Main_VentanaDeveloper.this, Actividad5.class);
            // startActivity(intent);
        }

        if(v.equals(btnAct6)){
            System.out.println("Click en actividad 6");
            //Intent intent = new Intent(Main_VentanaDeveloper.this, Actividad6.class);
            //startActivity(intent);
        }

        if(v.equals(btnAct7)){
            System.out.println("Click en actividad 7");
            //Intent intent = new Intent(Main_VentanaDeveloper.this, Actividad7.class);
            //startActivity(intent);
        }

        if(v.equals(btnAct8)){
            System.out.println("Click en actividad 8");
            Intent intent = new Intent(Main_VentanaDeveloper.this, Act3_PruebaDibujo.class);
            startActivity(intent);
        }

        if(v.equals(btnMapa)){
            System.out.println("Click en Mapa");
            Intent intent = new Intent(Main_VentanaDeveloper.this, Mapa.class);
            startActivity(intent);
        }

        if(v.equals(btnPrincipal)) {
            System.out.println("Click en ventana principal");
            Intent intent = new Intent(Main_VentanaDeveloper.this, Main_VentanaGrupos.class);
            startActivity(intent);
        }
    }
}
