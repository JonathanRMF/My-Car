package com.example.mycar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ResumenAlquilerActivity extends AppCompatActivity {

    TextView txtResumenVehiculo, txtResumenCliente, txtResumenFormaPago,
            txtResumenDias, txtResumenPrecioDia, txtResumenTotal;
    Button btnConfirmarResumen, btnCancelarResumen;

    // datos recibidos del formulario
    int vehiculoId;
    String apellido, nombre, formaPago, nombreVehiculo;
    int dias;
    double precioDia, montoTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_alquiler);

        // Vincular vistas
        txtResumenVehiculo  = findViewById(R.id.txtResumenVehiculo);
        txtResumenCliente   = findViewById(R.id.txtResumenCliente);
        txtResumenFormaPago = findViewById(R.id.txtResumenFormaPago);
        txtResumenDias      = findViewById(R.id.txtResumenDias);
        txtResumenPrecioDia = findViewById(R.id.txtResumenPrecioDia);
        txtResumenTotal     = findViewById(R.id.txtResumenTotal);
        btnConfirmarResumen = findViewById(R.id.btnConfirmarResumen);
        btnCancelarResumen  = findViewById(R.id.btnCancelarResumen);

        // Recibir datos del Intent
        vehiculoId     = getIntent().getIntExtra("vehiculo_id", 0);
        apellido       = getIntent().getStringExtra("apellido");
        nombre         = getIntent().getStringExtra("nombre");
        formaPago      = getIntent().getStringExtra("forma_pago");
        dias           = getIntent().getIntExtra("dias", 0);
        precioDia      = getIntent().getDoubleExtra("precio_dia", 0.0);
        montoTotal     = getIntent().getDoubleExtra("monto_total", 0.0);
        nombreVehiculo = getIntent().getStringExtra("nombre_vehiculo");

        // Mostrar los datos en pantalla
        txtResumenVehiculo.setText(nombreVehiculo);
        txtResumenCliente.setText("Cliente: " + apellido + ", " + nombre);
        txtResumenFormaPago.setText("Forma de pago: " + formaPago);
        txtResumenDias.setText("Días: " + dias);
        txtResumenPrecioDia.setText("Precio por día: $" + precioDia);
        txtResumenTotal.setText("Total: $" + montoTotal);

        // Confirmar — acá sí guarda en la BD
        btnConfirmarResumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BDHelper admin = new BDHelper(ResumenAlquilerActivity.this);
                boolean ok = admin.registrarAlquiler(
                        vehiculoId, apellido, nombre, formaPago, dias, precioDia
                );

                if (ok) {
                    Toast.makeText(ResumenAlquilerActivity.this,
                            "¡Alquiler confirmado!",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ResumenAlquilerActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish(); // vuelve al detalle del vehículo
                } else {
                    Toast.makeText(ResumenAlquilerActivity.this,
                            "Error al guardar el alquiler",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Cancelar — cierra sin guardar nada
        btnCancelarResumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // vuelve al formulario sin guardar
            }
        });

    } // ← cierra onCreate

} // ← cierra la clase