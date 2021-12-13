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
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class Actividad3 extends AppCompatActivity {

    private String texto = "Herri polit honetako biztanleek, alegia, zuek, produktuak Erdi Aroan nola saltzen eta trukatzen zituzten urtero irudikatzen duzue. Dakizuenez, azoka bitxi hau udaren amaieran ospatzen da, baina badakizue zergatik momentu horretan? Uda amaiera, uzta biltzeko garaia izan ohi zen, hortaz, hainbat zonaldetako produktuen elkartrukea egiten zuten Artziniegan bertan.\n" +
            "Artziniegako kale guztiak apaintzen dituzte, iraganera bost mende bidaiatzeko. Gainera, kaleetan zehar artisautza eta gastronomiako erakustoki desberdinak daude, Erdi Arora hegaz eramaten gaituztenak. Horrez gain, kaleetatik pertsonaia desberdinak topa daitezke: zaldunak, nobleak, bufoiak, malabaristak, artisauak, elizgizonak, titiriteroak, trobadoreak, dontzeilak, perkusionistak, zingaroak, juglareak, eta abar. Zuei zein pertsonaietaz mozorratzea gustatzen zaizue? \n";
    private Button btnVolver;
    private ImageButton btnAudio;
    private TextView textView;

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

        // vuelve a la ventana principal
        btnVolver = (Button) findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(0,0);
            }
        });

        textView = (TextView) findViewById(R.id.textView3);
        btnAudio = (ImageButton) findViewById(R.id.btnAudio);
        btnAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reproducirAudio();
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

    public void reproducirAudio(){
        MediaPlayer mp = MediaPlayer.create(this, R.raw.audio3);
        mp.start();
        textView.setText("");
        setText(texto);

    }

    public void setText(final String s)
    {
        final int[] i = new int[1];
        i[0] = 0;
        final int length = s.length();

        final Handler handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                char c= s.charAt(i[0]);
                Log.d("Strange",""+c);
                textView.append(String.valueOf(c));
                i[0]++;
            }
        };

        final Timer timer = new Timer();
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

}