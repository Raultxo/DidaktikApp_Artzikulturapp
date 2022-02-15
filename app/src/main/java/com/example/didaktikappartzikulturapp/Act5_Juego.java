package com.example.didaktikappartzikulturapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    private TextView txtH, txtV;

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
       // aniadirListener();

        txtH = findViewById(R.id.lblHorizontal);
        txtH.setText("HORIZONTAL \n" +
                "4. Mende honetan eraiki zen dorretxea \n" +
                "5. Nor zen Diego Ortiz de Molinillo Velasco jauna? \n" +
                "6. Fatxada nagusiko solairuak \n");

        txtV = findViewById(R.id.lblVertical);
        txtV.setText("VERTICAL \n" +
                "1. Plaza honetarako bidean dago dorretxa \n" +
                "2. Zer da gaur gun? \n" +
                "3. Zein da corretxearen jatorria?");

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
                ed.setText(resp);
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
        solucion.put(findViewById(R.id.resp4),"K");
        solucion.put(findViewById(R.id.resp5),"O");
        solucion.put(findViewById(R.id.resp6),"P");
        solucion.put(findViewById(R.id.resp7),"L");
        solucion.put(findViewById(R.id.resp8),"A");
        solucion.put(findViewById(R.id.resp9),"Z");
        solucion.put(findViewById(R.id.resp10),"A");

        // ALKATEA (usa la 'L' de plaza)
        solucion.put(findViewById(R.id.resp11),"A");
        solucion.put(findViewById(R.id.resp12),"K");
        solucion.put(findViewById(R.id.resp13),"A");
        solucion.put(findViewById(R.id.resp14),"T");
        solucion.put(findViewById(R.id.resp15),"E");
        solucion.put(findViewById(R.id.resp16),"A");

        // JATETXEA (usa la 'E' de alkatea)
        solucion.put(findViewById(R.id.resp17),"J");
        solucion.put(findViewById(R.id.resp18),"A");
        solucion.put(findViewById(R.id.resp19),"T");
        solucion.put(findViewById(R.id.resp20),"T");
        solucion.put(findViewById(R.id.resp21),"X");
        solucion.put(findViewById(R.id.resp22),"E");
        solucion.put(findViewById(R.id.resp23),"A");

        // LAU (usa la ultima 'A' de jatetxea)
        solucion.put(findViewById(R.id.resp24),"L");
        solucion.put(findViewById(R.id.resp25),"U");

        // HAMASEI (usa la primera 'A' de jatetxea)
        solucion.put(findViewById(R.id.resp26),"H");
        solucion.put(findViewById(R.id.resp27),"M");
        solucion.put(findViewById(R.id.resp28),"A");
        solucion.put(findViewById(R.id.resp29),"S");
        solucion.put(findViewById(R.id.resp30),"E");
        solucion.put(findViewById(R.id.resp31),"I");

        // MILITARRA (usa la 'I' de hamasei)
        solucion.put(findViewById(R.id.resp32),"M");
        solucion.put(findViewById(R.id.resp33),"L");
        solucion.put(findViewById(R.id.resp34),"I");
        solucion.put(findViewById(R.id.resp35),"T");
        solucion.put(findViewById(R.id.resp36),"A");
        solucion.put(findViewById(R.id.resp37),"R");
        solucion.put(findViewById(R.id.resp38),"R");
        solucion.put(findViewById(R.id.resp39),"A");

        ponerBonito();
    }


    public void ponerBonito()
    {
        Iterator<EditText> it = solucion.keySet().iterator();
        EditText aux=null;
        while(it.hasNext())
        {
            EditText ed = it.next();
            ed.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            ed.setBackgroundResource(R.color.itemLista);
            ed.setPadding(38,0,10,0);
            ed.setInputType(InputType.TYPE_CLASS_TEXT);
            if(aux!=null) // para evitar el primero
                ed.setNextFocusForwardId(aux.getId()); // tab order
            aux=ed;
        }
    }
}
