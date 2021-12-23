package com.example.didaktikappartzikulturapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class Act2_Inicio extends AppCompatActivity {

    private ImageButton btnAudio;
    private MediaPlayer mp;
    private SeekBar progress;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act2_inicio);
        Objects.requireNonNull(getSupportActionBar()).hide();

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);


        Button btnContinuar = (Button) findViewById(R.id.btnContinuar);
        btnContinuar.setOnClickListener(view -> {
            Intent intent = new Intent(Act2_Inicio.this, Act2_Juego.class);
            startActivity(intent);
        });

        // vuelve a la ventana principal
        Button btnVolver = (Button) findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(view -> {
            finish();
            overridePendingTransition(0,0);
        });

        //audio
        mp = MediaPlayer.create(Act2_Inicio.this, R.raw.audio2);
        btnAudio = (ImageButton) findViewById(R.id.btnAudio);
        MediaObserver observer = new MediaObserver();
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
        super.onResume();
        btnAudio.setImageResource(android.R.drawable.ic_media_play);
        mp = MediaPlayer.create(Act2_Inicio.this, R.raw.audio2);
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
