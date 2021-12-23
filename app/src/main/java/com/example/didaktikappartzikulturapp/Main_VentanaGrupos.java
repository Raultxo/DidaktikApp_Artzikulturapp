package com.example.didaktikappartzikulturapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.Objects;

public class Main_VentanaGrupos extends AppCompatActivity implements Main_DialogoGrupoNuevo.OnDialogoConfirmacionListener{

    //dialogos
    private FragmentManager fragmentManager;
    private Main_DialogoGrupoNuevo dialogo;

    final Main_Grupo[] datos = new Main_Grupo[] {
            new Main_Grupo("Main_Grupo 1",1),
            new Main_Grupo("Main_Grupo 2",2),
            new Main_Grupo("Main_Grupo 3",3)
    };

    //botones
    private Button btnJugar;

    public void onPossitiveButtonClick() {
    }
    public void onNegativeButtonClick() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_listado_grupos);

        Objects.requireNonNull(getSupportActionBar()).hide();

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        btnJugar = (Button) findViewById(R.id.btnJugar);
        btnJugar.setEnabled(false);

        // lista de grupos
        AdaptadorGrupos adaptador=new AdaptadorGrupos(this,datos);
        //lista
        ListView lista = (ListView) findViewById(R.id.lstListado);
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener((adapterView, view, i, l) -> btnJugar.setEnabled(true));

        btnJugar.setOnClickListener(view -> {
            Intent intent = new Intent(Main_VentanaGrupos.this, Main_AnimacionPuerta.class);
            startActivity(intent);
            overridePendingTransition(0,0);
        });

        // abre el dialogo para crear un grupo
        Button btnNuevoGrup = (Button) findViewById(R.id.btnCrear);
        btnNuevoGrup.setOnClickListener(view -> {
            fragmentManager = getSupportFragmentManager();
            dialogo = new Main_DialogoGrupoNuevo();
            dialogo.show(fragmentManager,"Dialogo");
        });

        // vuelve a la ventana principal
        Button btnVolver = (Button) findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(view -> {
            finish();
            overridePendingTransition(0,0);
        });
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    class AdaptadorGrupos extends ArrayAdapter <Main_Grupo> {
        public AdaptadorGrupos(@NonNull Context context, Main_Grupo[] datos) {
            super(context, R.layout.main_listitem_grupo, datos);
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
             View item = inflater.inflate(R.layout.main_listitem_grupo, null);
            TextView lblNombre = (TextView)item.findViewById(R.id.lblNomGrupo);
            lblNombre.setText(datos[position].getNombre());
            return (item);
        }
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
}
