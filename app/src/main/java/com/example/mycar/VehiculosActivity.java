package com.example.mycar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VehiculosActivity extends Activity {
    TextView txtCategoria;
    RecyclerView recyclerVehiculos;
    VehiculoAdapter adapter;
    ArrayList<Vehiculo> listaVehiculos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vehiculos);

        txtCategoria = findViewById(R.id.txtCategoriaSeleccionada);

        String categoria = getIntent().getStringExtra("categoria");

        txtCategoria.setText(categoria);

        listaVehiculos = new ArrayList<>();

        listaVehiculos.add(new Vehiculo("Toyota Corolla", 25000));
        listaVehiculos.add(new Vehiculo("Honda Civic", 22000));
        listaVehiculos.add(new Vehiculo("Ford Focus", 27000));
        listaVehiculos.add(new Vehiculo("Chevrolet Cruze", 24000));

        adapter = new VehiculoAdapter(listaVehiculos);

        recyclerVehiculos.setAdapter(adapter);

        recyclerVehiculos.setLayoutManager(new LinearLayoutManager(this));


    }
}
