package com.example.mycar;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VehiculosActivity extends Activity {

    TextView txtCategoria;
    RecyclerView recyclerVehiculos;
    VehiculoAdapter adapter;
    ArrayList<Vehiculo> listaVehiculos;
    public static ArrayList<Vehiculo> listaVehiculosGlobal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiculos);

        Button btnVolver      = findViewById(R.id.btnVolverVehiculos);
        txtCategoria          = findViewById(R.id.txtCategoriaSeleccionada);
        recyclerVehiculos     = findViewById(R.id.recyclerVehiculos);

        // Botón volver — simplemente cierra esta Activity
        btnVolver.setOnClickListener(v -> finish());

        String categoria = getIntent().getStringExtra("categoria");
        txtCategoria.setText(categoria);

        listaVehiculos = new ArrayList<>();
        BDHelper helper = new BDHelper(this);
        Cursor cursor = helper.getVehiculosPorCategoria(categoria);

        while (cursor.moveToNext()) {
            int id             = cursor.getInt(0);
            String marca       = cursor.getString(2);
            String modelo      = cursor.getString(3);
            double precio      = cursor.getDouble(8);
            String descripcion = cursor.getString(9);
            String imagen      = cursor.getString(10);

            listaVehiculos.add(new Vehiculo(id, marca + " " + modelo, precio, descripcion, imagen));
        }
        cursor.close();

        listaVehiculosGlobal = listaVehiculos;
        adapter = new VehiculoAdapter(listaVehiculos);
        recyclerVehiculos.setAdapter(adapter);
        recyclerVehiculos.setLayoutManager(new LinearLayoutManager(this));
    }
}