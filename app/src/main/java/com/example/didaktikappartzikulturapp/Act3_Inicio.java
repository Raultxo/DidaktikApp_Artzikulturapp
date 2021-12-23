package com.example.didaktikappartzikulturapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class Act3_Inicio extends AppCompatActivity {

    private final String texto = "Herri polit honetako biztanleek, alegia, zuek, produktuak Erdi " +
            "Aroan nola saltzen eta trukatzen zituzten urtero irudikatzen duzue. Dakizuenez, azoka " +
            "bitxi hau udaren amaieran ospatzen da, baina badakizue zergatik momentu horretan? Uda " +
            "amaiera, uzta biltzeko garaia izan ohi zen, hortaz, hainbat zonaldetako produktuen " +
            "elkartrukea egiten zuten Artziniegan bertan.\n" +
            "Artziniegako kale guztiak apaintzen dituzte, iraganera bost mende bidaiatzeko. Gainera, " +
            "kaleetan zehar artisautza eta gastronomiako erakustoki desberdinak daude, Erdi Arora " +
            "hegaz eramaten gaituztenak. Horrez gain, kaleetatik pertsonaia desberdinak topa " +
            "daitezke: zaldunak, nobleak, bufoiak, malabaristak, artisauak, elizgizonak, " +
            "titiriteroak, trobadoreak, dontzeilak, perkusionistak, zingaroak, juglareak, eta " +
            "abar. Zuei zein pertsonaietaz mozorratzea gustatzen zaizue? \n";
    private Button btnContinuar;
    private ImageButton btnAudio;
    private TextView textView;
    private MediaPlayer mp;
    private SeekBar progress;
    private Timer timer;
    private Handler handler;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act3_inicio);

        // Quitar la barra de titulo de actividad
        Objects.requireNonNull(getSupportActionBar()).hide();

        // Quitar la barra de notificaciones, bateria, hora, etc
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        // Boton para continuar e ir a la sopa de letras
        btnContinuar = findViewById(R.id.btnContinuar);
        btnContinuar.setOnClickListener(view -> {
            Intent intent = new Intent(Act3_Inicio.this, Act3_Juego.class);
            startActivity(intent);
        });

        // Boton para volver a la ventana principal
        Button btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(view -> {
            finish();
            overridePendingTransition(0,0);
        });

        // Boton para reproducir el audio y que la barra de progreso cambie en un Thread distinto
        mp = MediaPlayer.create(Act3_Inicio.this, R.raw.audio3);
        btnAudio = findViewById(R.id.btnAudio);
        MediaObserver observer = new MediaObserver();
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
        });

        // Barra de progreso
        progress = findViewById(R.id.progress);
        progress.setMax(mp.getDuration());

        // TextView en el que va a aparecer el texto
        textView = findViewById(R.id.textView3);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.stop();
        finish();
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

    // Clase interna que cambia la barra de progreso
    private class MediaObserver implements Runnable {
        private final AtomicBoolean stop = new AtomicBoolean(false);

        @Override
        public void run() {
            while (!stop.get()) {
                if(mp.isPlaying()){
                    progress.setProgress(mp.getCurrentPosition());
                    try{
                        sleep();
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }
}