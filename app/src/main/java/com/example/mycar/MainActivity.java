package com.example.mycar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //Declaracion de las variables globales de los botones y editText
    private Button btnInfo;
    private Button btnAutos;
    private Button btnAlquiler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencia a los elementos de las interfaces
        btnInfo = (Button) findViewById(R.id.informacion_app);
        btnAutos = (Button) findViewById(R.id.listado_autos);
        btnAlquiler = (Button) findViewById(R.id.alquileres_registrados);

        //Evento onCLick Listado de Autos
        btnAutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ListActivity.class);

                startActivity(intent);
            }
        });

        //Evento onCLick alquileres registrados
        btnAlquiler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, FormularioAlquilerActivity.class);

                startActivity(intent);
            }
        });

        //Evento onCLick Info
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, InfoActivity.class);

                startActivity(intent);
            }
        });
    }
}