package com.example.activityimc;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Fragment4 extends Fragment {

    private ImageView albumCover;
    private TextView songInfo, currentTime, totalTime, songTime, song1, song2, song3, song4;
    private Button playBtn, pauseBtn, stopBtn, nextBtn, prevBtn;
    private SeekBar seekBar;
    private MediaPlayer mediaPlayer;
    private int currentSongIndex = 0;
    private ArrayList<Integer> songResources;
    private ArrayList<String> songNames;
    private ArrayList<Integer> albumCovers;
    private Handler handler = new Handler();
    private Runnable updateSeekBarTask;

    // Inicializa las canciones, sus recursos y carátulas
    private void initializeSongs() {
        songResources = new ArrayList<>();
        songNames = new ArrayList<>();
        albumCovers = new ArrayList<>();

        // Cargar canciones con sus recursos y nombres
        songResources.add(R.raw.song1);
        songNames.add("Canción 1 - Artista");
        albumCovers.add(R.drawable.album1);

        songResources.add(R.raw.song2);
        songNames.add("Canción 2 - Artista");
        albumCovers.add(R.drawable.album2);

        songResources.add(R.raw.song3);
        songNames.add("Canción 3 - Artista");
        albumCovers.add(R.drawable.album3);

        songResources.add(R.raw.song4);
        songNames.add("Canción 4 - Artista");
        albumCovers.add(R.drawable.album4);
    }

    // Configura los listeners para los botones y la barra de búsqueda
    private void setupListeners() {
        playBtn.setOnClickListener(v -> playMusic());
        pauseBtn.setOnClickListener(v -> pauseMusic());
        stopBtn.setOnClickListener(v -> stopMusic());
        nextBtn.setOnClickListener(v -> nextSong());
        prevBtn.setOnClickListener(v -> prevSong());

        // Listener para el SeekBar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && mediaPlayer != null) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }

    // Método para iniciar la reproducción de la canción
    private void playMusic() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(getActivity(), songResources.get(currentSongIndex));
            mediaPlayer.setOnCompletionListener(mp -> nextSong());
        }
        mediaPlayer.start();

        // Actualiza la UI con la información de la canción actual
        songInfo.setText(songNames.get(currentSongIndex));
        albumCover.setImageResource(albumCovers.get(currentSongIndex));

        updateSeekBar();
    }

    // Método para pausar la música
    private void pauseMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    // Método para detener la música
    private void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            seekBar.setProgress(0);
            handler.removeCallbacks(updateSeekBarTask);
        }
    }

    // Cambia a la siguiente canción en la lista
    private void nextSong() {
        stopMusic();
        currentSongIndex = (currentSongIndex + 1) % songResources.size();
        playMusic();
    }

    // Reproduce la canción anterior
    private void prevSong() {
        stopMusic();
        currentSongIndex = (currentSongIndex - 1 + songResources.size()) % songResources.size();
        playMusic();
    }

    // Actualiza el SeekBar y el tiempo actual
    private void updateSeekBar() {
        if (mediaPlayer != null) {
            seekBar.setMax(mediaPlayer.getDuration());
            updateSeekBarTask = new Runnable() {
                @Override
                public void run() {
                    int currentPos = mediaPlayer.getCurrentPosition();
                    seekBar.setProgress(currentPos);
                    updateTimeDisplay(currentPos);
                    handler.postDelayed(this, 1000);
                }
            };
            handler.post(updateSeekBarTask);
        }
    }

    // Actualiza los textos de tiempo en la UI
    private void updateTimeDisplay(int currentPosition) {
        String currentTimeStr = String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(currentPosition),
                TimeUnit.MILLISECONDS.toSeconds(currentPosition) % 60);
        currentTime.setText(currentTimeStr);

        String totalTimeStr = String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(mediaPlayer.getDuration()),
                TimeUnit.MILLISECONDS.toSeconds(mediaPlayer.getDuration()) % 60);
        totalTime.setText(totalTimeStr);
    }

    // Método para llenar el ScrollView dinámicamente
    private void llenarScroll(View view) {
        LinearLayout scrollLinearLayout = view.findViewById(R.id.scrollLinearLayout);
        scrollLinearLayout.removeAllViews(); // Limpiar antes de agregar

        for (int i = 0; i < songNames.size(); i++) { // Iterar por cada canción en songNames
            // Inflar la fila para cada canción
            View itemView = LayoutInflater.from(getContext()).inflate(R.layout.row_item, scrollLinearLayout, false);

            // Obtener el TextView dentro de la fila inflada
            TextView titulo = itemView.findViewById(R.id.song_title);
            titulo.setText(songNames.get(i));

            // Configurar el clic en el item
            int finalI = i;
            itemView.setOnClickListener(v -> {
                // Detener cualquier canción que se esté reproduciendo
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }

                // Establecer el índice de la canción seleccionada
                currentSongIndex = finalI;

                // Reproducir la canción seleccionada
                playMusic();
            });

            // Agregar la vista inflada al LinearLayout
            scrollLinearLayout.addView(itemView);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_4, container, false);

        // Inicializar vistas
        albumCover = rootView.findViewById(R.id.albumCover);
        songInfo = rootView.findViewById(R.id.songInfo);
        currentTime = rootView.findViewById(R.id.currentTime);
        totalTime = rootView.findViewById(R.id.totalTime);
        songTime = rootView.findViewById(R.id.songTime);
        playBtn = rootView.findViewById(R.id.playButton);
        pauseBtn = rootView.findViewById(R.id.pauseButton);
        stopBtn = rootView.findViewById(R.id.stopButton);
        nextBtn = rootView.findViewById(R.id.nextButton);
        prevBtn = rootView.findViewById(R.id.prevButton);
        seekBar = rootView.findViewById(R.id.seekBar);

        // Inicializar canciones
        initializeSongs();

        // Llenar el ScrollView
        llenarScroll(rootView);

        // Configurar los listeners
        setupListeners();

        return rootView;
    }
}
