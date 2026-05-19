package com.example.mycar;

public class Vehiculo {

    String nombre;

    String descripcion;

    double precio;

    String imagen;

    public Vehiculo(
            String nombre,
            double precio,
            String descripcion,
            String imagen
    ) {

        this.nombre = nombre;

        this.precio = precio;

        this.descripcion = descripcion;

        this.imagen = imagen;
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

    public String getImagen() {
        return imagen;
    }
}