package com.example.didaktikappartzikulturapp;

public class Main_Grupo {

    private String nombre;
    private int actividad;

    public Main_Grupo(String nom, int acti) {
        nombre=nom;
        actividad=acti;
    }

    public String getNombre(){ return nombre; }
    public void setNombre(String nom){ nombre=nom; }

    public int getActividad(){ return actividad; }
    public void setActividad(int acti){ actividad=acti; }

}
