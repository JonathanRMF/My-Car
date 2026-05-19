package com.example.mycar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InfoActivity extends Activity {

    private Button btnVolver, btnAutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_layout);

        //Localizacion de controles vuncula los elemtos del xml con variables en java
        btnVolver = (Button) findViewById(R.id.regresar_info);
        btnAutos = (Button) findViewById(R.id.listado_info);


        //Evento onCLick Info aqui se hacen los cambios de pantalla
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(InfoActivity.this, MainActivity.class);

                startActivity(intent);

                finish();
            }
        });

        btnAutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(InfoActivity.this, ListActivity.class);

                startActivity(intent);

                finish();
            }
        });

    }
}
