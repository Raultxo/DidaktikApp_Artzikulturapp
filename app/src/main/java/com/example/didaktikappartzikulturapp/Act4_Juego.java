package com.example.didaktikappartzikulturapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class Act4_Juego extends AppCompatActivity {

    //audio
    private ImageButton btnAudio;
    private MediaPlayer mp;
    private SeekBar progress;

    //juego
    private ImageView img1, img2, img3,img4;
    private Button btnComprobar;
    private EditText resp1,resp2,resp3,resp4;
    private HashMap<EditText ,String> solucion ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act4_juego);
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

        //audio
        mp = MediaPlayer.create(Act4_Juego.this, R.raw.audio4_juego);
        btnAudio = (ImageButton) findViewById(R.id.btnAudio);
        Act4_Juego.MediaObserver observer = new Act4_Juego.MediaObserver();
        new Thread(observer).start();
        btnAudio.setOnClickListener(view -> {
            if(mp.isPlaying()){
                mp.pause();
                btnAudio.setImageResource(android.R.drawable.ic_media_play);
            }else{
                mp.start();
                btnAudio.setImageResource(android.R.drawable.ic_media_pause);
            }
        });

        progress = (SeekBar) findViewById(R.id.progress);
        progress.setMax(mp.getDuration());
        progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mp.seekTo(progress.getProgress());
            }
        });


        ///// juego
        colocarImagenes();
        llenarSoluciones();

        btnComprobar = findViewById(R.id.btnComprobar);
        btnComprobar.setOnClickListener(view -> {
            if(comprobar())
            {
                Intent intento = new Intent(Act4_Juego.this, Act4_Fin.class);
                startActivity(intento);
            }
        });
    }

    private void colocarImagenes()
    {
        // imagenes juego
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);

        img1.setImageResource(R.drawable.juego4_3);
        img2.setImageResource(R.drawable.juego4_2);
        img3.setImageResource(R.drawable.juego4_4);
        img4.setImageResource(R.drawable.juego4_1);
    }

    public void llenarSoluciones()
    {
        solucion = new HashMap<EditText,String>();

        resp1 = findViewById(R.id.resp1);
        resp2 = findViewById(R.id.resp2);
        resp3 = findViewById(R.id.resp3);
        resp4 = findViewById(R.id.resp4);

        solucion.put(resp1,"c");
        solucion.put(resp2,"b");
        solucion.put(resp3,"d");
        solucion.put(resp4,"a");

    }

    public boolean comprobar()
    {
        boolean seguir = true;
        Iterator<EditText> it = solucion.keySet().iterator();
        while(it.hasNext())
        {
            EditText ed = it.next();
            String resp = ed.getText().toString();
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

/// audio
    @Override
    protected void onPause() {
        super.onPause();
        mp.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnAudio.setImageResource(android.R.drawable.ic_media_play);
        mp = MediaPlayer.create(Act4_Juego.this, R.raw.audio4_juego);
    }

    private class MediaObserver implements Runnable {
        private final AtomicBoolean stop = new AtomicBoolean(false);

        @Override
        public void run() {
            while (!stop.get()) {
                if(mp.isPlaying()){
                    progress.setProgress(mp.getCurrentPosition());
                    sleep();
                }
            }
        }
    }

    private void sleep() {
        try{
            Thread.sleep(200);
        }catch (Exception e){
            Log.e("Sleep", "Error en el sleep");
        }
    }

}
