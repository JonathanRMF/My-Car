package com.example.mycar;

public class Vehiculo {

    String nombre;
    String descripcion;
    double precio;

    public Vehiculo(String nombre, double precio, String descripcion) {

        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }
}