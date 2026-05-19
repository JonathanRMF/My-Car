package com.example.mycar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.database.Cursor;

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
        recyclerVehiculos = findViewById(R.id.recyclerVehiculos);

        String categoria = getIntent().getStringExtra("categoria");

        txtCategoria.setText(categoria);

        listaVehiculos = new ArrayList<>();

        BDHelper helper = new BDHelper(this);

        Cursor cursor = helper.getVehiculosPorCategoria(categoria);

        while(cursor.moveToNext()) {

            String marca = cursor.getString(2);

            String modelo = cursor.getString(3);

            double precio = cursor.getDouble(8);

            String descripcion = cursor.getString(9);

            listaVehiculos.add(new Vehiculo(marca + " " + modelo, precio, descripcion));
        }

        cursor.close();

        adapter = new VehiculoAdapter(listaVehiculos);
        recyclerVehiculos.setAdapter(adapter);
        recyclerVehiculos.setLayoutManager(new LinearLayoutManager(this));


    }
}
