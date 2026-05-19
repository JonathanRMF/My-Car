package com.example.mycar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.database.Cursor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AlquileresActivity extends AppCompatActivity {

    Button btnVolver;
    RecyclerView recyclerAlquileres;
    TextView txtSinAlquileres;
    ArrayList<Alquiler> listaAlquileres;
    AlquilerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alquileres);

        // Vincular vistas
        recyclerAlquileres  = findViewById(R.id.recyclerAlquileres);
        txtSinAlquileres    = findViewById(R.id.txtSinAlquileres);
        btnVolver           = findViewById(R.id.btnVolver);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mostrarAlquileres();
    }

    public void mostrarAlquileres() {
        BDHelper helper = new BDHelper(this);
        Cursor cursor = helper.getTodosAlquileres();

        listaAlquileres = new ArrayList<>();

        if (cursor.moveToFirst()) {
            // hay registros — los recorremos
            do {
                String apellido = cursor.getString(2);
                String nombre   = cursor.getString(3);
                int dias        = cursor.getInt(5);
                double total    = cursor.getDouble(7);
                String marca    = cursor.getString(9);
                String modelo   = cursor.getString(10);

                listaAlquileres.add(new Alquiler(
                        apellido + " " + nombre,
                        marca + " " + modelo,
                        dias,
                        total
                ));
            } while (cursor.moveToNext());

            cursor.close();

            // conectar la lista al RecyclerView
            adapter = new AlquilerAdapter(listaAlquileres);
            recyclerAlquileres.setAdapter(adapter);
            recyclerAlquileres.setLayoutManager(new LinearLayoutManager(this));

        } else {
            // lista vacía — mostrar mensaje y ocultar el RecyclerView
            cursor.close();
            recyclerAlquileres.setVisibility(View.GONE);
            txtSinAlquileres.setVisibility(View.VISIBLE);
        }
    }
}