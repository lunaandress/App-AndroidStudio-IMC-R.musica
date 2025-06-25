package com.example.activityimc;

public class Noticia {
    private String titulo;
    private int imagenId;
    private String url;

    public Noticia(String titulo, int imagenId, String url) {
        this.titulo = titulo;
        this.imagenId = imagenId;
        this.url = url;
    }
    public String getTitulo() {
        return titulo;
    }
    public int getImagenId() {
        return imagenId;
    }
    public String getUrl() {
        return url;
    }
}


