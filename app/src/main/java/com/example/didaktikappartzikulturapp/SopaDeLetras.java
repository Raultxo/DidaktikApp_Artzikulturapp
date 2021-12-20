package com.example.didaktikappartzikulturapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.Objects;

public class SopaDeLetras extends AppCompatActivity {

    private GridView sopa;
    private final String[] letras = new String[] {
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

    @SuppressLint("ClickableViewAccessibility")
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

        TextView resultado = findViewById(R.id.resultado);

        sopa = findViewById(R.id.sopa);

        ArrayAdapter<String> listaAdapter = new ArrayAdapter<>(this, R.layout.textview_sopa, letras);
        sopa.setAdapter(listaAdapter);

        final String[] anterior = {""};
        sopa.setOnTouchListener((view, motionEvent) -> {
            System.out.println("Empieza");
            for(int i = 0; i < sopa.getChildCount(); i++) {
                View v = sopa.getChildAt(i);
                Rect outRecto = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                if(outRecto.contains((int)motionEvent.getX(), (int)motionEvent.getY())) {
                    if(!((TextView) v).getText().equals(anterior[0])) {
                        resultado.append(((TextView) v).getText());
                        anterior[0] = (String) ((TextView) v).getText();
                    }
                }
            }
            System.out.println("Termina");
            return false;
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