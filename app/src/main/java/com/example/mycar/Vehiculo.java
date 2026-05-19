package com.example.mycar;

public class Vehiculo {
    private int id;
    private String nombre;
    private double precio;
    private String descripcion;
    private String imagen;
    private int anio;
    private int plazas;
    private String motor;
    private int kilometraje;

    public Vehiculo(int id, String nombre, double precio, String descripcion,
                    String imagen, int anio, int plazas, String motor, int kilometraje) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.anio = anio;
        this.plazas = plazas;
        this.motor = motor;
        this.kilometraje = kilometraje;
    }

    public int getId()           { return id; }
    public String getNombre()    { return nombre; }
    public double getPrecio()    { return precio; }
    public String getDescripcion() { return descripcion; }
    public String getImagen()    { return imagen; }
    public int getAnio()         { return anio; }
    public int getPlazas()       { return plazas; }
    public String getMotor()     { return motor; }
    public int getKilometraje()  { return kilometraje; }
}