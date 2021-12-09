package com.example.didaktikappartzikulturapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnEmpezar;
    private ImageView img;
    private String codigo = "";
    private ConstraintLayout principal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        btnEmpezar = (Button) findViewById(R.id.btnEmpezar);
        btnEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(codigo.equals("aabbcdcd")) {
                    Toast.makeText(MainActivity.this, "UWU", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this, VentanaGrupos.class);
                    startActivity(intent);
                }
             //   overridePendingTransition(0,0);
            }
        });
        principal = (ConstraintLayout) findViewById(R.id.principal);
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