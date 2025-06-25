package com.example.activityimc;

import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment3 extends Fragment {

    private RecyclerView recyclerView;
    private NoticiaAdapter noticiaAdapter;
    private List<Noticia> noticias;
    private RatingBar ratingBar;
    private CalendarView calendarView;

    private String selectedDate = "";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment3() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment3.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment3 newInstance(String param1, String param2) {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, container, false);

        // Inicializamos el RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewNoticias);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ratingBar = view.findViewById(R.id.ratingBar);

        // Inicializamos el CalendarView
        calendarView = view.findViewById(R.id.calendarView);

        // Establecemos un listener para el CalendarView
        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            // Guardamos la fecha seleccionada
            selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
        });


        final MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), R.raw.ranking);

        ratingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            // Reproducimos el sonido cuando cambie la calificación
            if (mediaPlayer != null) {
                mediaPlayer.start();
            }
            // Mostramos un mensaje con la calificación y la fecha seleccionada
            if (!selectedDate.isEmpty()) {
                String message = "Fecha seleccionada: " + selectedDate + "\nCalificación: " + rating;
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Por favor, selecciona una fecha primero.", Toast.LENGTH_SHORT).show();
            }
        });

        // Cargamos las noticias
        cargarNoticias();

        // Configuramos el adaptador
        noticiaAdapter = new NoticiaAdapter(getContext(), noticias);
        recyclerView.setAdapter(noticiaAdapter);

        return view;
    }


    private void cargarNoticias() {
        noticias = new ArrayList<>();

        // Añadir 5 noticias con imágenes y enlaces
        noticias.add(new Noticia("NOTICIA 1: Alimentos para PERDER PESO 10 alimentos BARATOS", R.drawable.perderpeso, "https://www.youtube.com/watch?v=HcxLajHRkpY"));
        noticias.add(new Noticia("NOTICIA 2: Rutina de ejercicios en casa", R.drawable.rutina, "https://www.finisher.es/blog/rutina-casa-ejercicios-deporte/"));
        noticias.add(new Noticia("NOTICIA 3: Beneficios de llevar un estilo de vida activo y saludable", R.drawable.salud, "https://cobee.io/mx/blog/beneficios-de-llevar-un-estilo-de-vida-activo-y-saludable/"));
        noticias.add(new Noticia("NOTICIA 4: Rutina de gimnasio para ganar masa muscular", R.drawable.masa, "https://www.myprotein.es/thezone/entrenamiento/rutina-masa-muscular/"));
        noticias.add(new Noticia("NOTICIA 5: Diez claves para hacer ejercicio y no abandonarlo al poco tiempo", R.drawable.ejercicios, "https://abcblogs.abc.es/fitness-que-la-fuerza-te-acompane/entrenamiento/diez-claves-para-hacer-ejercicio-y-no-abandonarlo-al-poco-tiempo.html"));
    }
}