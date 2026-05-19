package com.example.mycar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FormularioAlquilerActivity extends Activity {

    TextView txtVehiculoFormulario;
    EditText edtNombre;
    EditText edtDias;
    Button btnConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_formulario_alquiler);

        txtVehiculoFormulario = findViewById(R.id.txtVehiculoFormulario);

        edtNombre = findViewById(R.id.edtNombre);

        edtDias = findViewById(R.id.edtDias);

        btnConfirmar = findViewById(R.id.btnConfirmar);

        String nombreVehiculo = getIntent().getStringExtra("nombre");

        txtVehiculoFormulario.setText(nombreVehiculo);

        btnConfirmar.setOnClickListener(v -> {
            String nombreCliente = edtNombre.getText().toString();

            String diasTexto = edtDias.getText().toString();

            if(nombreCliente.isEmpty() || diasTexto.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Alquiler registrado", Toast.LENGTH_SHORT).show();
        });
    }
}
