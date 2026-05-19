package com.example.mycar;

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

                // Guardar en la BD
                BDHelper admin = new BDHelper(FormularioAlquilerActivity.this);
                boolean ok = admin.registrarAlquiler(
                        vehiculoId, apellido, nombre, formaPago, dias, precioDia
                );

                if (ok) {
                    Toast.makeText(FormularioAlquilerActivity.this,
                            "Alquiler registrado correctamente",
                            Toast.LENGTH_SHORT).show();
                    finish(); // cierra y vuelve a la pantalla anterior
                } else {
                    Toast.makeText(FormularioAlquilerActivity.this,
                            "Error al registrar el alquiler",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }); // ← cierra setOnClickListener

    } // ← cierra onCreate

}