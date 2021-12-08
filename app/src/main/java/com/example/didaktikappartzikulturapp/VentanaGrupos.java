package com.example.didaktikappartzikulturapp;

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

public class VentanaGrupos extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_grupos);

        // lista de grupos
        AdaptadorGrupos adaptador=new AdaptadorGrupos(this,datos);
        lista = (ListView) findViewById(R.id.lstListado);
        lista.setAdapter(adaptador);


        // botones
        // si hay grupo seleccionado inicia la animacion
        btnJugar = (Button) findViewById(R.id.btnJugar);
        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VentanaGrupos.this, PruebaAimacion.class);
                startActivity(intent);
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

}
