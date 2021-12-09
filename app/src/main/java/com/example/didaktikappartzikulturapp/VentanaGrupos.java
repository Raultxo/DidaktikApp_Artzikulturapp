package com.example.didaktikappartzikulturapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class VentanaGrupos extends AppCompatActivity implements DialogoGrupoNuevo.OnDialogoConfirmacionListener{

    //dialogos
    private FragmentManager fragmentManager;
    private DialogoGrupoNuevo dialogo;

    //lista
    private ListView lista;
    final Grupo[] datos = new Grupo[] { new Grupo("Grupo 1",1),
                                        new Grupo("Grupo 2",2),
                                        new Grupo("Grupo 3",3)};

    //botones
    private Button btnJugar,btnVolver,btnNuevoGrup;

    /////  Metodos del dialogo
    public void onPossitiveButtonClick()
    {
        String nombre = dialogo.getTxtGrupo().toString();
        // se aniadira un nuevo grupo
    }
    public void onNegativeButtonClick()
    {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_grupos);

        getSupportActionBar().hide();

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        btnJugar = (Button) findViewById(R.id.btnJugar);
        btnJugar.setEnabled(false);

        // lista de grupos
        AdaptadorGrupos adaptador=new AdaptadorGrupos(this,datos);
        lista = (ListView) findViewById(R.id.lstListado);
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                btnJugar.setEnabled(true);
            }
        });

        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VentanaGrupos.this, PruebaAimacion.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

        // abre el dialogo para crear un grupo
        btnNuevoGrup = (Button) findViewById(R.id.btnCrear);
        btnNuevoGrup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager = getSupportFragmentManager();
                //Dialogo
                dialogo = new DialogoGrupoNuevo();
                dialogo.show(fragmentManager,"Dialogo");
            }
        });

        // vuelve a la ventana principal
        btnVolver = (Button) findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VentanaGrupos.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
    }

    class AdaptadorGrupos extends ArrayAdapter <Grupo>
    {
        public AdaptadorGrupos(@NonNull Context context, Grupo[] datos)
        {
            super(context, R.layout.listitem_grupo, datos);
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listitem_grupo, null);
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
