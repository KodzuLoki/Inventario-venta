package com.miempresa.miproyecto.model;

public class Trabajador {
    private int id;
    private String nombre;
    private String rol;
    private String puesto;
    private String numeroCelular;
    private String correo;

    // Constructor con parámetros
    public Trabajador(int id, String nombre, String rol, String puesto, String numeroCelular, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
        this.puesto = puesto;
        this.numeroCelular = numeroCelular;
        this.correo = correo;
    }

    // Constructor sin parámetros
    public Trabajador() {
        this.id = 0;  // Valor predeterminado para id
        this.nombre = "";
        this.rol = "";
        this.puesto = "";
        this.numeroCelular = "";
        this.correo = "";
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
