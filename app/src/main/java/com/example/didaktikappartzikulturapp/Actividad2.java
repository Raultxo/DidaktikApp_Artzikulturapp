package com.example.didaktikappartzikulturapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Iterator;

public class Actividad2 extends AppCompatActivity {

    private HashMap<EditText,String> respuestas;
    private Button btnCorregir;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad2);

        getSupportActionBar().hide();

        llenarDiccionario();

        btnCorregir = (Button) findViewById(R.id.btnCorregir);
        btnCorregir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                corregir();
            }
        });
    }

    private void llenarDiccionario()
    {
        respuestas=new HashMap<EditText,String>();
        EditText aux=(EditText) findViewById(R.id.resp1);
        respuestas.put(aux,"santutegi");
        aux=(EditText) findViewById(R.id.resp2);
        respuestas.put(aux,"alboan");
        aux=(EditText) findViewById(R.id.resp3);
        respuestas.put(aux,"artzain");
        aux=(EditText) findViewById(R.id.resp4);
        respuestas.put(aux,"Birjina");
        aux=(EditText) findViewById(R.id.resp5);
        respuestas.put(aux,"tenpluaren");
        aux=(EditText) findViewById(R.id.resp6);
        respuestas.put(aux,"mende");
        aux=(EditText) findViewById(R.id.resp7);
        respuestas.put(aux,"eraikin");
        aux=(EditText) findViewById(R.id.resp8);
        respuestas.put(aux,"aterpe");
        aux=(EditText) findViewById(R.id.resp9);
        respuestas.put(aux,"lau");
        aux=(EditText) findViewById(R.id.resp10);
        respuestas.put(aux,"eskultura");
    }


    public void corregir()
    {
        Iterator<EditText> it = respuestas.keySet().iterator();

        while(it.hasNext()){
            EditText edit = it.next();
            String resp = respuestas.get(edit);
            String texto = edit.getText().toString();

            if(texto.equals(resp))
            {
                edit.setTextColor(getResources().getColor(R.color.correcto));  //FF48B54D --> verde
                edit.setEnabled(false);
            }
            else
            {
                edit.setText("");
            }
        }
    }
}
