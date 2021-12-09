package com.example.didaktikappartzikulturapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogoGrupoNuevo extends DialogFragment {

    private EditText txtGrupo;
    DialogoGrupoNuevo.OnDialogoConfirmacionListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //Creamos Dialog
        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialogo_grupo_nuevo, null);
        builder.setView(dialogView)
               .setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onPossitiveButtonClick();
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Cancelar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onNegativeButtonClick();
                        dialog.cancel();
                    }
                });


        txtGrupo = dialogView.findViewById(R.id.txtGrupo);

        Dialog dialog =  builder.create();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    //Interfaz para los botones Aceptar y Cancelar
    public interface OnDialogoConfirmacionListener {
        void onPossitiveButtonClick(); //Eventos Botón Positivos
        void onNegativeButtonClick();  //Eventos Botón Negativo
    }

    //interfaz para evitar el error
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (DialogoGrupoNuevo.OnDialogoConfirmacionListener) context;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +" no Implemento OnDialogoConfirmacionListener");
        }
    }

    public EditText getTxtGrupo() { return txtGrupo; }
}
