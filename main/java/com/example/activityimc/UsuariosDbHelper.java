package com.example.activityimc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsuariosDbHelper extends SQLiteOpenHelper {

    // Nombre de la base de datos y versión
    private static final String DATABASE_NAME = "usuarios.db";
    private static final int DATABASE_VERSION = 1;




    // Nombre de la tabla y sus columnas
    public static final String TABLE_USUARIOS = "usuarios";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_PRIMER_APELLIDO = "primer_apellido";
    public static final String COLUMN_SEGUNDO_APELLIDO = "segundo_apellido";
    public static final String COLUMN_DIRECCION = "direccion";




    // SQL para crear la tabla
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_USUARIOS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOMBRE + " TEXT, " +
                    COLUMN_PRIMER_APELLIDO + " TEXT, " +
                    COLUMN_SEGUNDO_APELLIDO + " TEXT, " +
                    COLUMN_DIRECCION + " TEXT)";

    // Constructor
    public UsuariosDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creo la tabla en la base de datos
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        onCreate(db);
    }
}
