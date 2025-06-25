package com.example.activityimc;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Activity2Fragments extends AppCompatActivity {

    private EditText editTextNombre, editTextPrimerApellido, editTextSegundoApellido, editTextDireccion;
    private Button buttonGuardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2_fragments);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.layout_fragment, new Fragment1())
                    .commit();
        }
    }

    // Método que maneja los clics en las pestañas (tabs)
    public void clic_tab(View v) {
        int id = v.getId();

        // Crear un MediaPlayer para reproducir el sonido
        MediaPlayer mediaPlayer = null;

        // Identificar el fragmento a reemplazar y el sonido que se debe reproducir
        Fragment fragment = null;
        String fragmentTag = "";

        if (id == R.id.tab1) {
            Log.d("Activity2Fragments", "Reemplazando con Fragment1");
            fragment = new Fragment1();
            fragmentTag = "Fragment1";
            mediaPlayer = MediaPlayer.create(this, R.raw.tab);  // Sonido para tab1
        } else if (id == R.id.tab2) {
            Log.d("Activity2Fragments", "Reemplazando con Fragment2");
            fragment = new Fragment2();
            fragmentTag = "Fragment2";
            mediaPlayer = MediaPlayer.create(this, R.raw.tab);  // Sonido para tab2
        } else if (id == R.id.tab3) {
            Log.d("Activity2Fragments", "Reemplazando con Fragment3");
            fragment = new Fragment3();
            fragmentTag = "Fragment3";
            mediaPlayer = MediaPlayer.create(this, R.raw.tab);  // Sonido para tab3
        } else if (id == R.id.tab4) {
            Log.d("Activity2Fragments", "Reemplazando con Fragment4");
            fragment = new Fragment4();
            fragmentTag = "Fragment4";
            mediaPlayer = MediaPlayer.create(this, R.raw.tab);  // Sonido para tab4
        } else {
            Log.e("Activity2Fragments", "Error en clic_tab: ID desconocido");
        }

        // Reemplazar el fragmento si se encontró uno
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.layout_fragment, fragment, fragmentTag)
                    .addToBackStack(null)  // Esto permite la navegación hacia atrás
                    .commit();
        }

        // Reproducir el sonido
        if (mediaPlayer != null) {
            mediaPlayer.start();  // Iniciar el sonido
            mediaPlayer.setOnCompletionListener(mp -> mp.release());  // Liberar recursos cuando termine
        }

        // Actualizar un TextView si es necesario
        if (id == R.id.tab1) {
            TextView tuIMC = findViewById(R.id.tuIMC);
            String imcValue = "50.3";
            tuIMC.setText(imcValue);
        }
    }


    // Método que calcula o devuelve el valor del IMC
    public String calcular() {
        double imcValue = getIntent().getDoubleExtra("RESULTADO_IMC", 0.0);
        return String.valueOf(imcValue);
    }

}
