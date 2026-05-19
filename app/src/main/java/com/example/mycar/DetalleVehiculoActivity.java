package com.example.mycar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Button;

public class DetalleVehiculoActivity extends Activity {
    TextView txtNombreDetalle;
    TextView txtPrecioDetalle;
    Button btnAlquilar;
    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detalle_vehiculo);

        txtNombreDetalle = findViewById(R.id.txtNombreDetalle);
        txtPrecioDetalle = findViewById(R.id.txtPrecioDetalle);
        btnAlquilar = findViewById(R.id.btnAlquilar);

        String nombre = getIntent().getStringExtra("nombre");
        double precio = getIntent().getDoubleExtra("precio", 0);

        txtNombreDetalle.setText(nombre);
        txtPrecioDetalle.setText("Precio por dia: $" + precio);

        btnAlquilar.setOnClickListener(v -> {
            Intent intent = new Intent(this, FormularioAlquilerActivity.class);
            intent.putExtra("nombre", nombre);

            startActivity(intent);

        });
    }
}
