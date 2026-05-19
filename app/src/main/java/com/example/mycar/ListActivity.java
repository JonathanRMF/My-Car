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

    RecyclerView recyclerCategorias;
    CategoriaAdapter adapter;
    ArrayList<Categoria> listaCategorias;

    private Button btnVolver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listado_layout);

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



        /*
        recyclerCategorias = findViewById(R.id.recyclerCategorias);

        listaCategorias = new ArrayList<>();

        listaCategorias.add(new Categoria("Camioneta"));
        listaCategorias.add(new Categoria("Deportivo"));
        listaCategorias.add(new Categoria("Utilitario"));


        adapter = new CategoriaAdapter(listaCategorias);

        recyclerCategorias.setAdapter(adapter);

        recyclerCategorias.setLayoutManager(new LinearLayoutManager(this));*/
    }
}
