package com.example.mycar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DetalleVehiculoActivity extends Activity {
    TextView txtNombreDetalle;
    TextView txtPrecioDetalle;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detalle_vehiculo);

        txtNombreDetalle = findViewById(R.id.txtNombreDetalle);
        txtPrecioDetalle = findViewById(R.id.txtPrecioDetalle);

        String nombre = getIntent().getStringExtra("nombre");
        double precio = getIntent().getDoubleExtra("precio", 0);

        txtPrecioDetalle.setText(nombre);
        txtPrecioDetalle.setText("Precio por dia: $" + precio);
    }
}
