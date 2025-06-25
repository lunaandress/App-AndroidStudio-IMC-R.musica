package com.example.activityimc;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Fragment2 extends Fragment {

    private EditText editTextNombre, editTextPrimerApellido, editTextSegundoApellido, editTextDireccion;
    private Button buttonGuardar, buttonVerUsuarios;
    private RecyclerView recyclerViewUsuarios;
    private UsuariosDbHelper dbHelper;
    private UsuarioAdapter usuarioAdapter;
    private List<Usuario> usuariosList;

    public Fragment2() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);

        // Inicializamos los campos y los botones
        editTextNombre = view.findViewById(R.id.editTextNombre);
        editTextPrimerApellido = view.findViewById(R.id.editTextPrimerApellido);
        editTextSegundoApellido = view.findViewById(R.id.editTextSegundoApellido);
        editTextDireccion = view.findViewById(R.id.editTextDireccion);
        buttonGuardar = view.findViewById(R.id.buttonGuardar);
        buttonVerUsuarios = view.findViewById(R.id.verUsuarios);
        recyclerViewUsuarios = view.findViewById(R.id.recyclerViewUsuarios);

        // Inicializamos la base de datos y la lista de usuarios
        dbHelper = new UsuariosDbHelper(getContext());
        usuariosList = new ArrayList<>();

        // Configuramos el RecyclerView
        recyclerViewUsuarios.setLayoutManager(new LinearLayoutManager(getContext()));
        usuarioAdapter = new UsuarioAdapter(usuariosList);
        recyclerViewUsuarios.setAdapter(usuarioAdapter);

        // Configuramos el botón para guardar el usuario
        buttonGuardar.setOnClickListener(v -> {
            String nombre = editTextNombre.getText().toString();
            String primerApellido = editTextPrimerApellido.getText().toString();
            String segundoApellido = editTextSegundoApellido.getText().toString();
            String direccion = editTextDireccion.getText().toString();

            if (insertarUsuario(nombre, primerApellido, segundoApellido, direccion)) {
                Toast.makeText(getContext(), "Usuario guardado en la base de datos", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Error al guardar el usuario", Toast.LENGTH_SHORT).show();
            }
        });

        buttonVerUsuarios.setOnClickListener(v -> leerUsuarios());

        return view;
    }

    private boolean insertarUsuario(String nombre, String primerApellido, String segundoApellido, String direccion) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UsuariosDbHelper.COLUMN_NOMBRE, nombre);
        values.put(UsuariosDbHelper.COLUMN_PRIMER_APELLIDO, primerApellido);
        values.put(UsuariosDbHelper.COLUMN_SEGUNDO_APELLIDO, segundoApellido);
        values.put(UsuariosDbHelper.COLUMN_DIRECCION, direccion);

        long newRowId = db.insert(UsuariosDbHelper.TABLE_USUARIOS, null, values);
        return newRowId != -1;
    }

    @SuppressLint("NotifyDataSetChanged")
    private void leerUsuarios() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                UsuariosDbHelper.COLUMN_ID,
                UsuariosDbHelper.COLUMN_NOMBRE,
                UsuariosDbHelper.COLUMN_PRIMER_APELLIDO,
                UsuariosDbHelper.COLUMN_SEGUNDO_APELLIDO,
                UsuariosDbHelper.COLUMN_DIRECCION
        };

        Cursor cursor = db.query(
                UsuariosDbHelper.TABLE_USUARIOS,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        usuariosList.clear();  // Limpiamos la lista antes de cargar los nuevos usuarios

        while (cursor.moveToNext()) {
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow(UsuariosDbHelper.COLUMN_NOMBRE));
            String primerApellido = cursor.getString(cursor.getColumnIndexOrThrow(UsuariosDbHelper.COLUMN_PRIMER_APELLIDO));
            String segundoApellido = cursor.getString(cursor.getColumnIndexOrThrow(UsuariosDbHelper.COLUMN_SEGUNDO_APELLIDO));
            String direccion = cursor.getString(cursor.getColumnIndexOrThrow(UsuariosDbHelper.COLUMN_DIRECCION));

            usuariosList.add(new Usuario(nombre, primerApellido, segundoApellido, direccion));
        }

        cursor.close();

        // Notificamos al adaptador que actualice la vista
        usuarioAdapter.notifyDataSetChanged();

        // Mostramos el RecyclerView
        recyclerViewUsuarios.setVisibility(View.VISIBLE);
    }


    private void borrarTodosUsuarios() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(UsuariosDbHelper.TABLE_USUARIOS, null, null);
        Toast.makeText(getContext(), "Todos los usuarios han sido eliminados", Toast.LENGTH_SHORT).show();

        usuariosList.clear();
        usuarioAdapter.notifyDataSetChanged();

        recyclerViewUsuarios.setVisibility(View.GONE);
    }


}
