package com.example.didaktikappartzikulturapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Iterator;

public class Actividad2 extends AppCompatActivity {

    private HashMap<EditText,String> respuestas;
    private Button btnCorregir, btnVolver;
    private ImageView imgFin;
    private TextView lblFin;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad2);

        getSupportActionBar().hide();


        llenarDiccionario();

        //para el fin de la actividad
        imgFin = (ImageView) findViewById(R.id.imgFin);
        lblFin = (TextView) findViewById(R.id.lblFin);
        imgFin.setVisibility(View.GONE);
        lblFin.setVisibility(View.GONE);

        //audio

        //corregir
        btnCorregir = (Button) findViewById(R.id.btnCorregir);
        btnCorregir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                corregir();
            }
        });

        //volver
        btnVolver = (Button) findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(0,0);   //cambiar a ir al mapa
            }
        });
    }

    private void llenarDiccionario()
    {
        respuestas=new HashMap<EditText,String>();
        EditText aux=(EditText) findViewById(R.id.resp1);
        respuestas.put(aux,"santutegi");
        aux.setText("santutegi");

        aux=(EditText) findViewById(R.id.resp2);
        respuestas.put(aux,"alboan");
        aux.setText("alboan");

        aux=(EditText) findViewById(R.id.resp3);
        respuestas.put(aux,"artzain");
        aux.setText("artzain");

        aux=(EditText) findViewById(R.id.resp4);
        respuestas.put(aux,"birjina");
        aux.setText("birjina");

        aux=(EditText) findViewById(R.id.resp5);
        respuestas.put(aux,"tenpluaren");
        aux.setText("tenpluaren");

        aux=(EditText) findViewById(R.id.resp6);
        respuestas.put(aux,"mende");
        aux.setText("mende");

        aux=(EditText) findViewById(R.id.resp7);
        respuestas.put(aux,"eraikin");
        aux.setText("eraikin");

        aux=(EditText) findViewById(R.id.resp8);
        respuestas.put(aux,"aterpe");
        aux.setText("aterpe");

        aux=(EditText) findViewById(R.id.resp9);
        respuestas.put(aux,"lau");
        aux.setText("lau");

        aux=(EditText) findViewById(R.id.resp10);
        respuestas.put(aux,"eskultura");
        aux.setText("eskultura");
    }


    public void corregir()
    {
        Iterator<EditText> it = respuestas.keySet().iterator();
        int correctas=0;
        while(it.hasNext()){
            EditText edit = it.next();
            String resp = respuestas.get(edit);
            String texto = edit.getText().toString().toLowerCase();

            if(texto.equals(resp))
            {
                edit.setTextColor(getResources().getColor(R.color.correcto));  //FF48B54D --> verde
                edit.setEnabled(false);
                correctas++;
            }
            else
            {
                edit.setText("");
            }
        }

        if(correctas==10)
        {
            //ocultar texto y botones
            RelativeLayout rel=(RelativeLayout) findViewById(R.id.textoLayout);
            rel.setVisibility(View.GONE);

            Button btn=(Button) findViewById(R.id.btnCorregir);
            btn.setVisibility(View.GONE);
            btn=(Button) findViewById(R.id.btnAudio);
            btn.setVisibility(View.GONE);

            TableLayout tabla=(TableLayout) findViewById(R.id.tabla);
            tabla.setVisibility(View.GONE);

            //marcar la actividad como hecha en la info del grupo

            //felicidades por haber terminado, etc, etc
                imgFin.setImageResource(R.drawable.dontzeila);
                lblFin.setText("oso ondo egin duzu, pasa zaitezke hurrengo jarduerara." +
                                "Horretarako, Artziniegako Museo Etnografikora joan zaitez.");
                imgFin.setVisibility(View.VISIBLE);
                lblFin.setVisibility(View.VISIBLE);
        }
    }
}
