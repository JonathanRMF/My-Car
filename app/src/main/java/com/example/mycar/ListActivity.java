package com.example.mycar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListActivity extends Activity{

    private Button btnVolver;
    Button btnAuto;
    Button btnCamioneta;
    Button btnDeportivo;
    Button btnUtilitario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listado_layout);

        btnAuto = findViewById(R.id.auto_list);
        btnCamioneta = findViewById(R.id.camionetas_list);
        btnUtilitario = findViewById(R.id.utl_list);
        btnDeportivo = findViewById(R.id.depor_list);
        btnVolver = findViewById(R.id.listado_volver);

        //Localizacion de controles
        btnVolver = (Button) findViewById(R.id.listado_volver);

        //Evento onCLick Info
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ListActivity.this, MainActivity.class);

                startActivity(intent);

                finish();
            }
        });

        btnAuto.setOnClickListener(v -> {
            Intent intent = new Intent(
                    ListActivity.this,
                    VehiculosActivity.class
            );

            intent.putExtra(
                    "categoria",
                    "Auto"
            );

            startActivity(intent);
        });

        btnCamioneta.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ListActivity.this,
                    VehiculosActivity.class
            );

            intent.putExtra(
                    "categoria",
                    "Camioneta"
            );

            startActivity(intent);
        });

        btnUtilitario.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ListActivity.this,
                    VehiculosActivity.class
            );

            intent.putExtra(
                    "categoria",
                    "Utilitario"
            );

            startActivity(intent);
        });

        btnDeportivo.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ListActivity.this,
                    VehiculosActivity.class
            );

            intent.putExtra(
                    "categoria",
                    "Deportivo"
            );

            startActivity(intent);
        });

    }
}
