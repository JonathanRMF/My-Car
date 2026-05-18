package com.example.mycar;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListActivity extends Activity{

    RecyclerView recyclerCategorias;
    CategoriaAdapter adapter;
    ArrayList<Categoria> listaCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listado_layout);

        recyclerCategorias = findViewById(R.id.recyclerCategorias);

        listaCategorias = new ArrayList<>();

        listaCategorias.add(new Categoria("Camioneta"));
        listaCategorias.add(new Categoria("Deportivo"));
        listaCategorias.add(new Categoria("Utilitario"));


        adapter = new CategoriaAdapter(listaCategorias);

        recyclerCategorias.setAdapter(adapter);

        recyclerCategorias.setLayoutManager(new LinearLayoutManager(this));
    }
}
