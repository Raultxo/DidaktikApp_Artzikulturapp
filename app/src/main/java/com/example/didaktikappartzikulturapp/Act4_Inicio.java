package com.example.didaktikappartzikulturapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class Act4_Inicio extends AppCompatActivity {

    private ImageButton btnAudio;
    private MediaPlayer mp;
    private SeekBar progress;
    private TextView textView;
    private final String texto = "Artziniegara heldu baino lehen, errepidetik gertu gaztandegi " +
            "txiki bat dago. Gaztandegi honetan, gaztagileak bere onddo-andui propioak hazten ditu," +
            " benetan emaitza harrigarriak lortu arte. Gazta bikainak dira, eta horien artean" +
            " ahuntz-gazta urdina topatu dezakegu. Gaztagileak, gazta ezberdinak garatzeko " +
            "ahaleginean eta grinan, ahuntz-esneari Penicilium roqueforti onddoa eranstea pentsatu " +
            "zuen. Emaitza, aditu askoren arabera, munduko ahuntz-gazta urdinarik hoberena da. " +
            "Eta zuei… gazta gustatzen zaizue? Ahuntz-gazta urdin goxo hau probatuko zenukete? " +
            "Ñam, ñam! Gonbidatuta zaudete!";

    private ImageView imgCambio;
    private TextView txtImg;
    private Timer timer;
    private Handler handler;

    private Button btnContinuar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act4_inicio);
        Objects.requireNonNull(getSupportActionBar()).hide();

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        // vuelve a la ventana principal
        Button btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(view -> {
            finish();
            overridePendingTransition(0,0);
        });

        // Boton para continuar
        btnContinuar = findViewById(R.id.btnContinuar);
   /*    btnContinuar.setOnClickListener(view -> {
            Intent intent = new Intent(Act4_Inicio.this, Act4_Juego.class);
            startActivity(intent);
        });
    */
        // imagenes y texto
        textView = findViewById(R.id.texto);
        imgCambio = findViewById(R.id.imgCambio);
        txtImg = findViewById(R.id.textoImagen);

        // Boton para reproducir el audio y que la barra de progreso cambie en un Thread distinto
        mp = MediaPlayer.create(Act4_Inicio.this, R.raw.audio4_explicacion);
        btnAudio = findViewById(R.id.btnAudio);
        Act4_Inicio.MediaObserver observer = new Act4_Inicio.MediaObserver();
        new Thread(observer).start();
        btnAudio.setOnClickListener(view -> {
            if(!mp.isPlaying()){
                btnAudio.setImageResource(android.R.drawable.ic_media_play);
                textView.setText("");
                setText(texto);
                mp.start();
                mp.setOnCompletionListener(mpa -> {
                    btnAudio.setImageResource(android.R.drawable.ic_popup_sync);
                    btnContinuar.setEnabled(true);
                });
            }

            //a los 10 segundo cambiar imagen y pie de foto  (cambio en run de la clase ContadorTask)
            new CountDownTimer(10000, 1000)
            {
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {  // al terminar la cuenta atras (10s)
                    imgCambio.setImageResource(R.drawable.quesero);
                    txtImg.setText("Alfonso Zamora (hombre de los quesos)");
                }

            }.start();
        });

        // Barra de progreso
        progress = findViewById(R.id.progress);
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

    // metodos del audio
    @Override
    protected void onPause() {
        super.onPause();
        mp.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnAudio.setImageResource(android.R.drawable.ic_media_play);
        mp = MediaPlayer.create(Act4_Inicio.this, R.raw.audio4_explicacion);
    }

    // Metodo para esperar 200ms
    private void sleep() {
        try{
            Thread.sleep(200);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // Metodo para que aparezca el texto letra a letra
    @SuppressLint("HandlerLeak")
    public void setText(final String s) {
        final int[] i = new int[1];
        final int length = s.length();

        handler = new Handler(Looper.getMainLooper())  {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                char c= s.charAt(i[0]);
                textView.append(String.valueOf(c));
                i[0]++;
            }
        };

        timer = new Timer();
        TimerTask taskEverySplitSecond = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
                if (i[0] == length - 1) {
                    timer.cancel();
                }
            }

        };
        timer.schedule(taskEverySplitSecond, 1, 65);
    }

    // para cambiar la barra de progreso
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




    /////explicacion
// audio con explicacion  de quesos
// texto del audio
// imagen de 10 segundos
// imagen de 10 segundos


    /////juego
// audio elaboracion de queso
// ordenar imagenes



}
