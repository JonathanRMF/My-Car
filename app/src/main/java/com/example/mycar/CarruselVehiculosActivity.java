package com.example.mycar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class CarruselVehiculosActivity
        extends AppCompatActivity {

    ViewPager2 viewPager;

    VehiculoPagerAdapter adapter;

    ArrayList<Vehiculo> listaVehiculos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_carrusel);

        viewPager = findViewById(R.id.viewPager);

        listaVehiculos =
                VehiculosActivity.listaVehiculosGlobal;

        adapter = new VehiculoPagerAdapter(
                this,
                listaVehiculos
        );

        viewPager.setAdapter(adapter);

        int posicion =
                getIntent().getIntExtra(
                        "posicion",
                        0
                );

        viewPager.setCurrentItem(posicion, false);
    }
}