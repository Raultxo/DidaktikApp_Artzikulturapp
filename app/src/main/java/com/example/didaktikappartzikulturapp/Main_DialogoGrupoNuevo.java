package com.example.didaktikappartzikulturapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public class Main_DialogoGrupoNuevo extends DialogFragment {

    private EditText txtGrupo;
    Main_DialogoGrupoNuevo.OnDialogoConfirmacionListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //Creamos Dialog
        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
        @SuppressLint("UseRequireInsteadOfGet") LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.main_dialogo_grupo_nuevo, null);
        builder.setView(dialogView)
                .setPositiveButton("Aceptar", (dialog, id) -> {
                    listener.onPossitiveButtonClick();
                    dialog.cancel();
                })
                .setNegativeButton("Cancelar", (dialog, id) -> {
                    listener.onNegativeButtonClick();
                    dialog.cancel();
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (Main_DialogoGrupoNuevo.OnDialogoConfirmacionListener) context;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +" no Implemento OnDialogoConfirmacionListener");
        }
    }

    public EditText getTxtGrupo() { return txtGrupo; }
}
