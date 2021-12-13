package com.example.didaktikappartzikulturapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class VentanaDeveloper extends AppCompatActivity {

    //botones
    private Button btnVolver;
    private Button btnAct1, btnAct2, btnAct3, btnAct4, btnAct5, btnAct6, btnAct7, btnAct8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_developer);

        getSupportActionBar().hide();

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        // vuelve a la ventana principal
        btnVolver = (Button) findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(0,0);
            }
        });

        // Botones actividades
        btnAct1 = (Button) findViewById(R.id.actividad1);
        btnAct2 = (Button) findViewById(R.id.actividad2);
        btnAct3 = (Button) findViewById(R.id.actividad3);
        btnAct4 = (Button) findViewById(R.id.actividad4);
        btnAct5 = (Button) findViewById(R.id.actividad5);
        btnAct6 = (Button) findViewById(R.id.actividad6);
        btnAct7 = (Button) findViewById(R.id.actividad7);
        btnAct8 = (Button) findViewById(R.id.actividad8);

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
            //Intent intent = new Intent(VentanaDeveloper.this, Actividad2.class);
            //startActivity(intent);
        }

        if(v.equals(btnAct3)){
            Intent intent = new Intent(VentanaDeveloper.this, Actividad3.class);
            startActivity(intent);
        }

        if(v.equals(btnAct4)){
            //Intent intent = new Intent(VentanaDeveloper.this, Actividad4.class);
            //startActivity(intent);
        }

        if(v.equals(btnAct5)){
            //Intent intent = new Intent(VentanaDeveloper.this, Actividad5.class);
            //startActivity(intent);
        }

        if(v.equals(btnAct6)){
            //Intent intent = new Intent(VentanaDeveloper.this, Actividad6.class);
            //startActivity(intent);
        }

        if(v.equals(btnAct7)){
            //Intent intent = new Intent(VentanaDeveloper.this, Actividad7.class);
            //startActivity(intent);
        }

        if(v.equals(btnAct8)){
            //Intent intent = new Intent(VentanaDeveloper.this, Actividad8.class);
            //startActivity(intent);
        }
    }
}
