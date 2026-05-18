package com.example.mycar;

public class Vehiculo {
    String nombre;
    double precio;

    public Vehiculo(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}
