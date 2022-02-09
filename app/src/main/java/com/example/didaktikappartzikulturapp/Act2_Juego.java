package com.example.didaktikappartzikulturapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

public class Act2_Juego extends AppCompatActivity {

    private HashMap<EditText,String> respuestas;
    private ImageView  imgB;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act2_juego);

        Objects.requireNonNull(getSupportActionBar()).hide();

        // Quitar la barra de notificaciones, bateria, hora, etc
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        llenarDiccionario();

        //audio
        Button btnAudio = (Button) findViewById(R.id.btnAudio);
        btnAudio.setOnClickListener(view -> audio());

        //corregir
        Button btnCorregir = (Button) findViewById(R.id.btnCorregir);
        btnCorregir.setOnClickListener(view -> corregir());

        //volver
        Button btnVolver = (Button) findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(view -> {
            finish();
            overridePendingTransition(0,0);   //cambiar a ir al mapa
        });
    }

    @SuppressLint("SetTextI18n")
    private void llenarDiccionario()
    {
        respuestas= new HashMap<>();
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

    public void audio()
    {
        Intent intento=new Intent(Act2_Juego.this, Act2_Inicio.class);
        startActivity(intento);
    }

    @SuppressLint("SetTextI18n")
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
                edit.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.correcto));
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

            Intent intent = new Intent(Act2_Juego.this, Act2_Fin.class);
            startActivity(intent);
         /*   //ocultar texto y botones
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
                lblFin.setText("Oso ondo egin duzu, pasa zaitezke hurrengo jarduerara." +
                                "Horretarako, Artziniegako Museo Etnografikora joan zaitez.");
                imgFin.setVisibility(View.VISIBLE);
                imgB.setVisibility(View.GONE);
                lblFin.setVisibility(View.VISIBLE);

                */

        }
    }
}
