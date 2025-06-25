package com.example.activityimc;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.slider.RangeSlider;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    // INICIALIZO LOS COMPONENTES
    private CardView VistaF;
    private CardView VistaM;
    private RangeSlider VistaMedidor;
    private TextView numerosAltura;
    private FloatingActionButton borrarPesoMenos;
    private FloatingActionButton sumarPesoMas;
    private TextView pesoNumeros;
    private FloatingActionButton borrarEdadMenos;
    private FloatingActionButton sumarEdadMas;
    private TextView edadNumeros;
    private Button calcular;
    public static final String IMC_KEY = "IMC_RESULTADO";

    // VARIABLES
    private boolean selecHombre = true;
    private boolean selecMujer = false;
    private int pesoActual = 45;
    private int edadActual = 18;
    private int alturaActual = 120;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarComponentes();
        selecionarMoF();

        mediaPlayer = MediaPlayer.create(this, R.raw.inicio);
        mediaPlayer.start();

        mediaPlayer.setOnCompletionListener(mp -> mp.release());
    }

    // Método onStart: Usado para tareas que deben ejecutarse cuando la actividad se vuelve visible
    @Override
    protected void onStart() {
        super.onStart();
        VistaF.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
    }

    // Método onResume: Usado cuando la actividad entra en primer plano y empieza a interactuar con el usuario.
    @Override
    protected void onResume() {
        super.onResume();
        if (pesoActual != 45) {
            pesoNumeros.setText(String.valueOf(pesoActual));
        }
    }

    // Método onPause: Liberar recursos de mediaPlayer
    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    // Inicializa los componentes
    private void iniciarComponentes() {
        VistaF = findViewById(R.id.VistaF);
        VistaM = findViewById(R.id.VistaM);

        numerosAltura = findViewById(R.id.alturaNumeros);
        VistaMedidor = findViewById(R.id.medidor);

        borrarPesoMenos = findViewById(R.id.borrarPeso);
        sumarPesoMas = findViewById(R.id.sumarPeso);
        pesoNumeros = findViewById(R.id.pesoNumeros);

        borrarEdadMenos = findViewById(R.id.borrarEdad);
        sumarEdadMas = findViewById(R.id.sumarEdad);
        edadNumeros = findViewById(R.id.edadNumerosT);

        calcular = findViewById(R.id.calculadorId);
    }

    // Método para abrir la otra actividad y pasar datos
    private void navegadorResultado(double resultado) {
        Intent intent = new Intent(MainActivity.this, Activity2Fragments.class);
        intent.putExtra("RESULTADO_IMC", resultado);
        startActivity(intent);
    }

    private double calcularIMC() {
        double IMC = pesoActual / (Math.pow(alturaActual / 100.0, 2));
        return Double.parseDouble(String.format("%.2f", IMC));
    }

    private void selecionarMoF() {
        calcular.setOnClickListener(v -> {
            double resultado = calcularIMC();
            navegadorResultado(resultado);
        });

        sumarEdadMas.setOnClickListener(v -> {
            if (edadActual < 120) {  // Evitar que la edad supere los límites
                edadActual += 1;
                ajustarEdad();
            }
        });
        borrarEdadMenos.setOnClickListener(v -> {
            if (edadActual > 0) {  // Evitar que la edad sea negativa
                edadActual -= 1;
                ajustarEdad();
            }
        });

        sumarPesoMas.setOnClickListener(v -> {
            if (pesoActual < 200) {  // Evitar valores de peso muy grandes
                pesoActual += 1;
                ajustarPeso();
            }
        });
        borrarPesoMenos.setOnClickListener(v -> {
            if (pesoActual > 0) {  // Evitar que el peso sea negativo
                pesoActual -= 1;
                ajustarPeso();
            }
        });

        VistaMedidor.addOnChangeListener((slider, value, fromUser) -> {
            DecimalFormat df = new DecimalFormat("#.##");
            double formattedValue = Double.parseDouble(df.format(value));
            alturaActual = (int) formattedValue; // Convertir a int
            numerosAltura.setText(String.valueOf(alturaActual));
        });

        VistaF.setOnClickListener(v -> cambiarGenero());
        VistaM.setOnClickListener(v -> cambiarGenero());
    }

    private void ajustarPeso() {
        pesoNumeros.setText(String.valueOf(pesoActual));
    }

    private void ajustarEdad() {
        edadNumeros.setText(String.valueOf(edadActual));
    }

    private void cambiarGenero() {
        boolean nuevoSelecHombre = !selecHombre;
        boolean nuevoSelecMujer = !selecMujer;

        if (nuevoSelecHombre != selecHombre || nuevoSelecMujer != selecMujer) {
            selecHombre = nuevoSelecHombre;
            selecMujer = nuevoSelecMujer;
            cambiarColorCard();
        }
    }

    // Cambiar el color de VistaM según el género seleccionado
    private void cambiarColorCard() {
        VistaM.setCardBackgroundColor(obtenerColorFondo(selecHombre));
        VistaF.setCardBackgroundColor(obtenerColorFondo(selecMujer));
    }

    // Obtiene el color adecuado dependiendo de si el género está seleccionado o no
    private int obtenerColorFondo(boolean seleccionado) {
        int colorReference = seleccionado ? R.color.colorPrimary : R.color.rojo;
        return ContextCompat.getColor(this, colorReference);
    }
}
