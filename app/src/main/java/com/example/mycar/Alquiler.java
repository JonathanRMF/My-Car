package com.example.mycar;

public class Alquiler {
    String cliente;
    String vehiculo;
    int dias;
    double total;

    public Alquiler(String cliente, String vehiculo, int dias, double total) {

        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.dias = dias;
        this.total = total;
    }

    public String getCliente() {
        return cliente;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public int getDias() {
        return dias;
    }

    public double getTotal() {
        return total;
    }
}