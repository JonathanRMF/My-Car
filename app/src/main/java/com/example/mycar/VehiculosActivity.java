package com.example.mycar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class VehiculosActivity extends Activity {
    TextView txtCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vehiculos);

        txtCategoria = findViewById(R.id.txtCategoriaSeleccionada);

        String categoria = getIntent().getStringExtra("categoria");

        txtCategoria.setText(categoria);
    }
}
