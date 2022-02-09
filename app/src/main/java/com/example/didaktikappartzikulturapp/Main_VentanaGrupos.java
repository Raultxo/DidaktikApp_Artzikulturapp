package com.example.didaktikappartzikulturapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import java.util.ArrayList;
import java.util.Objects;

public class Main_VentanaGrupos extends AppCompatActivity implements Main_DialogoGrupoNuevo.OnDialogoConfirmacionListener{

    private SQLiteDatabase db;

    //dialogos
    private FragmentManager fragmentManager;
    private Main_DialogoGrupoNuevo dialogo;

    private Main_Grupo[] datos;

    //botones
    private Button btnJugar;

    public void onPossitiveButtonClick() {

        String nombre = dialogo.getTxtGrupo();
        String query = "insert into Grupo(nombre, actividad1, actividad2, actividad3, actividad5, actividad5, actividad6, actividad7, actividad8) values('" + nombre + "', 0,0,0,0,0,0,0,0)";
        db.execSQL(query);
        cargarDatos();
    }
    public void onNegativeButtonClick() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_listado_grupos);

        Main_BBDD main_bbdd = new Main_BBDD(this, "DB",null, 1);
        db = main_bbdd.getWritableDatabase();
        cargarDatos();

        Objects.requireNonNull(getSupportActionBar()).hide();

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        btnJugar = (Button) findViewById(R.id.btnJugar);
        btnJugar.setEnabled(false);

        // lista de grupos


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

    private void cargarDatos() {
        ArrayList<Main_Grupo> arrGrupos = new ArrayList<>();
        String query = "select * from Grupo";
        @SuppressLint("Recycle")Cursor c = db.rawQuery(query, null);
        while(c.moveToNext()) {
            String nombre = c.getString(1);
            int actividad1 = c.getInt(2);
            int actividad2 = c.getInt(3);
            int actividad3 = c.getInt(4);
            int actividad4 = c.getInt(5);
            int actividad5 = c.getInt(6);
            int actividad6 = c.getInt(7);
            int actividad7 = c.getInt(8);
            int actividad8 = c.getInt(9);

            Main_Grupo nuevo = new Main_Grupo();
            nuevo.setNombre(nombre);

            if(actividad1 == 0) {
                nuevo.setActividad1(false);
            }
            else {
                nuevo.setActividad1(true);
            }

            if(actividad2 == 0) {
                nuevo.setActividad2(false);
            }
            else {
                nuevo.setActividad2(true);
            }

            if(actividad3 == 0) {
                nuevo.setActividad3(false);
            }
            else {
                nuevo.setActividad3(true);
            }

            if(actividad4 == 0) {
                nuevo.setActividad4(false);
            }
            else {
                nuevo.setActividad4(true);
            }

            if(actividad5 == 0) {
                nuevo.setActividad5(false);
            }
            else {
                nuevo.setActividad5(true);
            }

            if(actividad6 == 0) {
                nuevo.setActividad6(false);
            }
            else {
                nuevo.setActividad6(true);
            }

            if(actividad7 == 0) {
                nuevo.setActividad7(false);
            }
            else {
                nuevo.setActividad7(true);
            }

            if(actividad8 == 0) {
                nuevo.setActividad8(false);
            }
            else {
                nuevo.setActividad8(true);
            }

            arrGrupos.add(nuevo);

        }

        datos = new Main_Grupo[arrGrupos.size()];
        datos = arrGrupos.toArray(datos);

        AdaptadorGrupos adaptador=new AdaptadorGrupos(this,datos);
        //lista
        ListView lista = (ListView) findViewById(R.id.lstListado);
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener((adapterView, view, i, l) -> btnJugar.setEnabled(true));
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

            Button btnInfo = item.findViewById(R.id.btnInfo);
            btnInfo.setOnClickListener(view -> {
                Intent intent = new Intent(Main_VentanaGrupos.this, Main_Datos_Grupo.class);
                intent.putExtra("nombre", datos[position].getNombre());
                intent.putExtra("actividad1", datos[position].isActividad1());
                intent.putExtra("actividad2", datos[position].isActividad2());
                intent.putExtra("actividad3", datos[position].isActividad3());
                intent.putExtra("actividad4", datos[position].isActividad4());
                intent.putExtra("actividad5", datos[position].isActividad5());
                intent.putExtra("actividad6", datos[position].isActividad6());
                intent.putExtra("actividad7", datos[position].isActividad7());
                intent.putExtra("actividad8", datos[position].isActividad8());

                startActivity(intent);
            });

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
