package com.example.mycar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InfoActivity extends Activity {

    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_layout);

        //Localizacion de controles
        btnVolver = (Button) findViewById(R.id.regresar_info);

        //Evento onCLick Info
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(InfoActivity.this, MainActivity.class);

                startActivity(intent);

                finish();
            }
        });

    }
}
