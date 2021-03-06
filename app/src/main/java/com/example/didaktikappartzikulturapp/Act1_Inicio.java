package com.example.didaktikappartzikulturapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.atomic.AtomicBoolean;

public class Act1_Inicio extends AppCompatActivity {

    private Button btnVolver, btnContinuar;
    private ImageButton btnAudio;
    private MediaPlayer mp;
    private MediaObserver observer = null;
    private SeekBar progress;
    private ImageView imgArbol;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act1_inicio);
        getSupportActionBar().hide();

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        imgArbol = (ImageView) findViewById(R.id.imgArbol);
        imgArbol.setVisibility(View.INVISIBLE);

        btnContinuar = (Button) findViewById(R.id.btnContinuar);
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Act1_Inicio.this, Act1_Juego.class);
                startActivity(intent);
            }
        });

        // vuelve a la ventana principal
        btnVolver = (Button) findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(0,0);
            }
        });

        mp = MediaPlayer.create(Act1_Inicio.this, R.raw.audio1);
        btnAudio = (ImageButton) findViewById(R.id.btnAudio);
        observer = new MediaObserver();
        new Thread(observer).start();
        btnAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mp.isPlaying()){
                    mp.pause();
                    btnAudio.setImageResource(android.R.drawable.ic_media_play);
                }else{
                    mp.start();
                    btnAudio.setImageResource(android.R.drawable.ic_media_pause);
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mpa)
                        {
                            btnAudio.setImageResource(android.R.drawable.ic_popup_sync);
                            btnContinuar.setEnabled(true);
                            imgArbol.setVisibility(View.VISIBLE);
                        }
                    });
                }
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

    @Override
    protected void onPause() {
        super.onPause();
        mp.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();;
        btnAudio.setImageResource(android.R.drawable.ic_media_play);
        mp = MediaPlayer.create(Act1_Inicio.this, R.raw.audio1);
    }

    private class MediaObserver implements Runnable {
        private AtomicBoolean stop = new AtomicBoolean(false);

        public void stop() {
            stop.set(true);
        }

        @Override
        public void run() {
            while (!stop.get()) {
                if(mp.isPlaying()){
                    progress.setProgress(mp.getCurrentPosition());
                    try{
                        Thread.sleep(200);
                    }catch (Exception e){

                    }
                }
            }
        }
    }
}