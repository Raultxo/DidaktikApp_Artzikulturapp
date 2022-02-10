package com.example.didaktikappartzikulturapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;

public class Act5_Juego extends AppCompatActivity {

    private Button btnCorregir;
    private HashMap<EditText,String> solucion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act5_juego);
        Objects.requireNonNull(getSupportActionBar()).hide();

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        // vuelve a la ventana principal
        Button btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(view -> {
            finish();
            overridePendingTransition(0, 0);
        });

        llenarSolucion();

        btnCorregir = findViewById(R.id.btnCorregir);
        btnCorregir.setOnClickListener(view -> {
            if(corregir())
            {
                Intent intento = new Intent(Act5_Juego.this, Act5_Fin.class);
                startActivity(intento);
            }
        });
    }


    public boolean corregir()
    {
        boolean seguir = true;
        Iterator<EditText> it = solucion.keySet().iterator();
        while(it.hasNext())
        {
            EditText ed = it.next();
            String resp = ed.getText().toString().toUpperCase(); // letra introducida en mayusculas
            String solu = solucion.get(ed);
            if(solu.equals(resp))  // respuesta es correcta
            {
                ed.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.correcto));
                ed.setEnabled(false);
            }
            else
            {
                seguir = false;
                ed.setText("");
            }
        }
        return seguir;
    }


    public void llenarSolucion()
    {
        solucion= new HashMap<EditText,String>();
        // GOIKO PLAZA
        solucion.put(findViewById(R.id.resp1),"G");
        solucion.put(findViewById(R.id.resp2),"O");
        solucion.put(findViewById(R.id.resp3),"I");
        solucion.put(findViewById(R.id.resp1),"K");
        solucion.put(findViewById(R.id.resp1),"O");
        solucion.put(findViewById(R.id.resp1),"P");
        solucion.put(findViewById(R.id.resp1),"L");
        solucion.put(findViewById(R.id.resp1),"A");
        solucion.put(findViewById(R.id.resp1),"Z");
        solucion.put(findViewById(R.id.resp1),"A");

        // ALKATEA (usa la 'L' de plaza)
        solucion.put(findViewById(R.id.resp1),"A");
        solucion.put(findViewById(R.id.resp1),"K");
        solucion.put(findViewById(R.id.resp1),"A");
        solucion.put(findViewById(R.id.resp1),"T");
        solucion.put(findViewById(R.id.resp1),"E");
        solucion.put(findViewById(R.id.resp1),"A");

        // JATETXEA (usa la 'E' de alkatea)
        solucion.put(findViewById(R.id.resp1),"J");
        solucion.put(findViewById(R.id.resp1),"A");
        solucion.put(findViewById(R.id.resp1),"T");
        solucion.put(findViewById(R.id.resp1),"T");
        solucion.put(findViewById(R.id.resp1),"X");
        solucion.put(findViewById(R.id.resp1),"E");
        solucion.put(findViewById(R.id.resp1),"A");

        // LAU (usa la ultima 'A' de jatetxea)
        solucion.put(findViewById(R.id.resp1),"L");
        solucion.put(findViewById(R.id.resp1),"U");

        // HAMASEI (usa la primera 'A' de jatetxea)
        solucion.put(findViewById(R.id.resp1),"H");
        solucion.put(findViewById(R.id.resp1),"M");
        solucion.put(findViewById(R.id.resp1),"A");
        solucion.put(findViewById(R.id.resp1),"S");
        solucion.put(findViewById(R.id.resp1),"E");
        solucion.put(findViewById(R.id.resp1),"I");

        // MILITARRA (usa la 'I' de hamasei)
        solucion.put(findViewById(R.id.resp1),"M");
        solucion.put(findViewById(R.id.resp1),"L");
        solucion.put(findViewById(R.id.resp1),"I");
        solucion.put(findViewById(R.id.resp1),"T");
        solucion.put(findViewById(R.id.resp1),"A");
        solucion.put(findViewById(R.id.resp1),"R");
        solucion.put(findViewById(R.id.resp1),"R");
        solucion.put(findViewById(R.id.resp1),"A");

        // BARROKOA (usa la ultima 'A' de militarra)
        solucion.put(findViewById(R.id.resp1),"B");
        solucion.put(findViewById(R.id.resp1),"R");
        solucion.put(findViewById(R.id.resp1),"R");
        solucion.put(findViewById(R.id.resp1),"O");
        solucion.put(findViewById(R.id.resp1),"K");
        solucion.put(findViewById(R.id.resp1),"O");
        solucion.put(findViewById(R.id.resp1),"A");

        // AIARA DORREA (usa la 'R' de barrokoa)
        solucion.put(findViewById(R.id.resp47),"A");
        solucion.put(findViewById(R.id.resp48),"I");
        solucion.put(findViewById(R.id.resp49),"A");
        solucion.put(findViewById(R.id.resp50),"A");
        solucion.put(findViewById(R.id.resp51),"D");
        solucion.put(findViewById(R.id.resp52),"O");
        solucion.put(findViewById(R.id.resp53),"R");
        solucion.put(findViewById(R.id.resp54),"R");
        solucion.put(findViewById(R.id.resp55),"E");
        solucion.put(findViewById(R.id.resp56),"A");
    }

}
