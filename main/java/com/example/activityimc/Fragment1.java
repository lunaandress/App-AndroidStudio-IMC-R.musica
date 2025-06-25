package com.example.activityimc;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {

    private TextView tuIMC, resultadoSalud, descripcion;
    private Button calculadorIdRe;

    public Fragment1() {

    }

    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        // Inicializamos los TextViews
        tuIMC = view.findViewById(R.id.tuIMC);
        resultadoSalud = view.findViewById(R.id.saludId);
        descripcion = view.findViewById(R.id.desc);
        calculadorIdRe = view.findViewById(R.id.calculadorIdRe);

        // Configurar el OnClickListener para el botón
        calculadorIdRe.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);




        });



        // Obtener el valor del IMC desde la actividad principal
        String imcValue = getActivity() != null ? ((Activity2Fragments) getActivity()).calcular() : "0.0";


        try {
            float result = Float.parseFloat(imcValue);
            tuIMC.setText(imcValue);

            // Dependiendo del valor del IMC, se actualizan los otros TextViews
            if (result >= 0.00 && result <= 18.50) {
                resultadoSalud.setText("Peso Bajo");
                descripcion.setText("Bajo peso, es recomendable ganar peso de manera saludable.");
            } else if (result >= 18.51 && result <= 24.99) {
                resultadoSalud.setText("Peso Normal");
                descripcion.setText("Tienes un peso saludable. ¡Sigue así!");
            } else if (result >= 25.00 && result <= 29.99) {
                resultadoSalud.setText("Sobrepeso");
                descripcion.setText("Tienes sobrepeso, es recomendable llevar un estilo de vida más saludable.");
            } else if (result >= 30.00 && result <= 99.00) {
                resultadoSalud.setText("Obesidad");
                descripcion.setText("Tienes obesidad, se recomienda consultar a un profesional de salud.");
            } else {
                resultadoSalud.setText("Error");
                descripcion.setText("Error: El valor del IMC no es válido.");
            }
        } catch (NumberFormatException e) {
            // Si no se puede convertir el valor de IMC, mostramos un error
            tuIMC.setText("Error");
            resultadoSalud.setText("Error");
            descripcion.setText("El valor del IMC no es válido.");
        }

        return view;
    }
}
