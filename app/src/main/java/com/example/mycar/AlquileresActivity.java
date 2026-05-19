package com.example.mycar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AlquileresActivity extends Activity {
    private Button btnVolver;
    RecyclerView recyclerAlquileres;
    ArrayList<Alquiler> listaAlquileres;
    AlquilerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alquileres);

        recyclerAlquileres = findViewById(R.id.recyclerAlquileres);

        listaAlquileres = new ArrayList<>();

        BDHelper helper = new BDHelper(this);

        Cursor cursor = helper.getTodosAlquileres();

        while(cursor.moveToNext()) {

            String apellido = cursor.getString(2);

            String nombre = cursor.getString(3);

            int dias = cursor.getInt(5);

            double total = cursor.getDouble(7);

            String marca = cursor.getString(9);

            String modelo = cursor.getString(10);

            listaAlquileres.add(
                    new Alquiler(
                            apellido + " " + nombre,
                            marca + " " + modelo,
                            dias,
                            total
                    )
            );
        }

        cursor.close();

        adapter = new AlquilerAdapter(listaAlquileres);

        recyclerAlquileres.setAdapter(adapter);

        recyclerAlquileres.setLayoutManager(new LinearLayoutManager(this));
    }

}
