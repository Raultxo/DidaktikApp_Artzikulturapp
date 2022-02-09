package com.example.didaktikappartzikulturapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

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

        String path = "android.resource://" + getPackageName() + "/" + R.raw.act5_video;
        video = findViewById(R.id.videoAct5);
        video.setVideoURI(Uri.parse(path));

        MediaController mc= new MediaController(this);
        video.setMediaController(mc);
        mc.setAnchorView(video);
        video.start();

        btnContinuar = findViewById(R.id.btnContinuar2);
        //Cuando termina el video se cierra la actividad
        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                btnContinuar.setEnabled(true);
                setResult(RESULT_OK);

            }
        });
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();
    }
}