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

        if (cursor != null && cursor.moveToFirst()) {
            // Hay registros — los recorremos
            do {
                // Usamos getColumnIndex por seguridad en lugar de índices fijos
                String apellido = cursor.getString(cursor.getColumnIndexOrThrow("cliente_apellido"));
                String nombre   = cursor.getString(cursor.getColumnIndexOrThrow("cliente_nombre"));
                int dias        = cursor.getInt(cursor.getColumnIndexOrThrow("cantidad_dias"));
                double total    = cursor.getDouble(cursor.getColumnIndexOrThrow("monto_total"));
                String marca    = cursor.getString(cursor.getColumnIndexOrThrow("marca"));
                String modelo   = cursor.getString(cursor.getColumnIndexOrThrow("modelo"));

                listaAlquileres.add(new Alquiler(
                        apellido + " " + nombre,
                        marca + " " + modelo,
                        dias,
                        total
                ));
            } while (cursor.moveToNext());
            cursor.close();

            // Configurar RecyclerView y asegurar visibilidad
            adapter = new AlquilerAdapter(listaAlquileres);
            recyclerAlquileres.setAdapter(adapter);
            recyclerAlquileres.setLayoutManager(new LinearLayoutManager(this));
            
            recyclerAlquileres.setVisibility(View.VISIBLE);
            txtSinAlquileres.setVisibility(View.GONE);

        } else {
            // Lista vacía o error
            if (cursor != null) cursor.close();
            recyclerAlquileres.setVisibility(View.GONE);
            txtSinAlquileres.setVisibility(View.VISIBLE);
        }
    }
}