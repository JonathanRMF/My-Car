package com.example.mycar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class FormularioAlquilerActivity extends AppCompatActivity {

    TextView txtVehiculoFormulario;
    EditText edtApellido, edtNombre, edtDias;
    Spinner spinnerPago;
    Button btnConfirmar;

    int vehiculoId;
    double precioDia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_alquiler);

        // Vincular vistas
        txtVehiculoFormulario = findViewById(R.id.txtVehiculoFormulario);
        edtApellido  = findViewById(R.id.edtApellido);
        edtNombre    = findViewById(R.id.edtNombre);
        edtDias      = findViewById(R.id.edtDias);
        spinnerPago  = findViewById(R.id.spinner);
        btnConfirmar = findViewById(R.id.btnConfirmar);

        // Cargar opciones del Spinner
        String[] formasDePago = {"Efectivo", "Débito", "Crédito"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                formasDePago
        );
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPago.setAdapter(adaptador);

        // Recibir datos de la Activity anterior
        vehiculoId = getIntent().getIntExtra("vehiculo_id", 0);
        precioDia  = getIntent().getDoubleExtra("precio_dia", 0.0);
        String nombreVehiculo = getIntent().getStringExtra("nombre");
        txtVehiculoFormulario.setText(nombreVehiculo);
        TextView txtPrecioDia = findViewById(R.id.txtPrecioDiaFormulario);
        txtPrecioDia.setText("Precio por día: $" + precioDia);

        // Botón confirmar
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String apellido  = edtApellido.getText().toString();
                String nombre    = edtNombre.getText().toString();
                String diasTexto = edtDias.getText().toString();
                String formaPago = spinnerPago.getSelectedItem().toString();

                // Validar campos vacíos
                if (apellido.isEmpty() || nombre.isEmpty() || diasTexto.isEmpty()) {
                    Toast.makeText(FormularioAlquilerActivity.this,
                            "Complete todos los campos",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                int dias          = Integer.parseInt(diasTexto);
                double montoTotal = precioDia * dias;

                // Mandar todo a ResumenAlquilerActivity SIN guardar en BD
                Intent intent = new Intent(
                        FormularioAlquilerActivity.this,
                        ResumenAlquilerActivity.class
                );
                intent.putExtra("vehiculo_id",  vehiculoId);
                intent.putExtra("apellido",     apellido);
                intent.putExtra("nombre",       nombre);
                intent.putExtra("forma_pago",   formaPago);
                intent.putExtra("dias",         dias);
                intent.putExtra("precio_dia",   precioDia);
                intent.putExtra("monto_total",  montoTotal);
                intent.putExtra("nombre_vehiculo", txtVehiculoFormulario.getText().toString());
                startActivity(intent);
            }
        });

    } // ← cierra onCreate

} // ← cierra la clase