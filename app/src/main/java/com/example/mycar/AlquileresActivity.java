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

        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM alquileres", null);

        while(cursor.moveToNext()) {

            String cliente = cursor.getString(1);

            String vehiculo = cursor.getString(2);

            int dias = cursor.getInt(3);

            double total = cursor.getDouble(4);

            listaAlquileres.add(new Alquiler(cliente, vehiculo, dias, total));
        }

        cursor.close();

        adapter = new AlquilerAdapter(listaAlquileres);

        recyclerAlquileres.setAdapter(adapter);

        recyclerAlquileres.setLayoutManager(new LinearLayoutManager(this));
    }

}
