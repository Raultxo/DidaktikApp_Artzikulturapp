package com.example.didaktikappartzikulturapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.HashMap;

public class Act5_Inicio extends AppCompatActivity {

    private VideoView video;
    private Button btnContinuar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act5_inicio);

        getSupportActionBar().hide();

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        // vuelver
        Button btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(view -> {
            finish();
            overridePendingTransition(0, 0);
        });

        ////  video
        String path = "android.resource://" + getPackageName() + "/" + R.raw.act5_video;
        video = findViewById(R.id.videoAct5);
        video.setVideoURI(Uri.parse(path));

        MediaController mc= new MediaController(this);
        video.setMediaController(mc);
        mc.setAnchorView(video);
        video.start();

        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                btnContinuar.setEnabled(true);
                setResult(RESULT_OK);

            }
        });


        // continuar al juego
        btnContinuar = findViewById(R.id.btnContinuar2);
        btnContinuar.setOnClickListener(view -> {
            Intent intento = new Intent(Act5_Inicio.this, Act5_Juego.class);
            startActivity(intento);
        });

    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();
    }
}