package com.example.didaktikappartzikulturapp;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class Actividad3 extends AppCompatActivity {

    private String texto = "Herri polit honetako biztanleek, alegia, zuek, produktuak Erdi Aroan nola saltzen eta trukatzen zituzten urtero irudikatzen duzue. Dakizuenez, azoka bitxi hau udaren amaieran ospatzen da, baina badakizue zergatik momentu horretan? Uda amaiera, uzta biltzeko garaia izan ohi zen, hortaz, hainbat zonaldetako produktuen elkartrukea egiten zuten Artziniegan bertan.\n" +
            "Artziniegako kale guztiak apaintzen dituzte, iraganera bost mende bidaiatzeko. Gainera, kaleetan zehar artisautza eta gastronomiako erakustoki desberdinak daude, Erdi Arora hegaz eramaten gaituztenak. Horrez gain, kaleetatik pertsonaia desberdinak topa daitezke: zaldunak, nobleak, bufoiak, malabaristak, artisauak, elizgizonak, titiriteroak, trobadoreak, dontzeilak, perkusionistak, zingaroak, juglareak, eta abar. Zuei zein pertsonaietaz mozorratzea gustatzen zaizue? \n";
    private String[] textoSplit = texto.split("");
    private Button btnVolver, btnContinuar;
    private ImageButton btnAudio;
    private TextView textView;
    private MediaPlayer mp;
    private MediaObserver observer;
    private SeekBar progress;
    private Timer timer;
    private TimerTask taskEverySplitSecond;
    private Handler handler;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad3);
        getSupportActionBar().hide();

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        btnContinuar = (Button) findViewById(R.id.btnContinuar);
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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


        mp = MediaPlayer.create(Actividad3.this, R.raw.audio3);
        btnAudio = (ImageButton) findViewById(R.id.btnAudio);
        observer = new MediaObserver();
        new Thread(observer).start();
        btnAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mp.isPlaying()){
                    btnAudio.setImageResource(android.R.drawable.ic_media_play);
                    textView.setText("");
                    setText(texto);
                    mp.start();
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mpa) {
                            btnAudio.setImageResource(android.R.drawable.ic_popup_sync);
                            btnContinuar.setEnabled(true);
                        }
                    });
                }
            }
        });

        progress = (SeekBar) findViewById(R.id.progress);
        progress.setMax(mp.getDuration());

        textView = (TextView) findViewById(R.id.textView3);
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
    protected void onResume() {
        super.onResume();
        btnAudio.setImageResource(android.R.drawable.ic_media_play);
        mp = MediaPlayer.create(Actividad3.this, R.raw.audio3);
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
                    }catch (Exception e){ }
                }
            }
        }
    }

    public void setText(final String s) {
        final int[] i = new int[1];
        i[0] = 0;
        final int length = s.length();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                char c= s.charAt(i[0]);
                textView.append(String.valueOf(c));
                i[0]++;
            }
        };

        timer = new Timer();
        taskEverySplitSecond = new TimerTask() {
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
}