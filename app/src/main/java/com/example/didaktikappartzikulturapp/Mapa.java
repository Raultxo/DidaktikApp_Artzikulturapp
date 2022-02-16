package com.example.didaktikappartzikulturapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mapbox.geojson.Feature;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import java.util.List;
import java.util.Objects;

public class Mapa extends AppCompatActivity {

    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapa);

        // Quitar la barra de titulo de actividad
        Objects.requireNonNull(getSupportActionBar()).hide();

        // Quitar la barra de notificaciones, bateria, hora, etc
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        // establecer token de mapbox
        Mapbox.getInstance(getApplicationContext(), getString(R.string.mapbox_access_token));

// Obtenga el contenedor utilizado para almacenar el mapa en la plantilla
        mapView = (MapView)findViewById(R.id.mapview);
// Nuevo mapa
        mapView.onCreate(savedInstanceState);
// Establece la función de devolución de llamada del mapa, similar a map.on ()
        mapView.getMapAsync(new OnMapReadyCallback() {
            @SuppressLint("WrongConstant")
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
// Establece el estilo del mapa, puedes usar tu propio style.json
                mapboxMap.setStyle("mapbox://styles/staticvoid/ckznxa7uk000i14leyspm339w");
                mapboxMap.setCameraPosition(new CameraPosition.Builder()
                        .target(new LatLng(43.121353, -3.134898))
                        .zoom(15)
                        .build());

                mapboxMap.addOnMapClickListener(new MapboxMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng point) {
                        PointF finalPoint = mapboxMap.getProjection().toScreenLocation(point);
                        List<Feature> features = mapboxMap.queryRenderedFeatures(finalPoint);
                        for (Feature feature : features) {
                            String title = null;
                            if (feature.getProperty("title") != null) {
                                title = feature.getProperty("title").toString();
                            }

                            if (title != null) {
                                Intent intent = null;
                                if (title.contains("1.")) {
                                    intent = new Intent(Mapa.this, Act1_Inicio.class);
                                } else if (title.contains("2.")) {
                                    intent = new Intent(Mapa.this, Act2_Inicio.class);
                                } else if (title.contains("3.")) {
                                    intent = new Intent(Mapa.this, Act3_Inicio.class);
                                } else if (title.contains("4.")) {
                                    intent = new Intent(Mapa.this, Act4_Inicio.class);
                                } else if (title.contains("5.")) {
                                    intent = new Intent(Mapa.this, Act5_Inicio.class);
                                } else if (title.contains("6.")) {
                                    //intent = new Intent(Mapa.this, Act6_Inicio.class);
                                } else if (title.contains("7.")) {
                                    //intent = new Intent(Mapa.this, Act7_Inicio.class);
                                }
                                if (intent != null) startActivity(intent);
                            }
                        }
                        ;
                    }
                });
            }
        });



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
}