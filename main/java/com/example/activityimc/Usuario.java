package com.example.activityimc;

public class Usuario {

    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String direccion;

    private String fecha;



    public Usuario(String nombre, String primerApellido, String segundoApellido, String direccion) {
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.direccion = direccion;
        this.fecha=fecha;
    }
    public String getNombre() {
        return nombre;
    }
    public String getPrimerApellido() {
        return primerApellido;
    }
    public String getSegundoApellido() {
        return segundoApellido;
    }
    public String getDireccion() {
        return direccion;
    }
}
