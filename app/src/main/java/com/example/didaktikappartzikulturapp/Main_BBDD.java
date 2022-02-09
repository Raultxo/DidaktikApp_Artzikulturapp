package com.example.didaktikappartzikulturapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Main_BBDD extends SQLiteOpenHelper {

    String crearTabla = "CREATE TABLE Grupo (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "nombre TEXT," +
                        "actividad1 INTEGER,"+
                        "actividad2 INTEGER,"+
                        "actividad3 INTEGER,"+
                        "actividad4 INTEGER,"+
                        "actividad5 INTEGER,"+
                        "actividad6 INTEGER,"+
                        "actividad7 INTEGER,"+
                        "actividad8 INTEGER)";

    public Main_BBDD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(crearTabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
