package com.example.mycar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class VehiculoPagerAdapter extends FragmentStateAdapter {
    ArrayList<Vehiculo> listaVehiculos;

    public VehiculoPagerAdapter(
            @NonNull FragmentActivity fragmentActivity,
            ArrayList<Vehiculo> listaVehiculos
    ) {

        super(fragmentActivity);

        this.listaVehiculos = listaVehiculos;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Vehiculo v = listaVehiculos.get(position);
        return DetalleVehiculoFragment.newInstance(
                v.getId(),
                v.getNombre(),
                v.getPrecio(),
                v.getDescripcion(),
                v.getImagen(),
                v.getAnio(),
                v.getPlazas(),
                v.getMotor(),
                v.getKilometraje()
        );
    }

    @Override
    public int getItemCount() {
        return listaVehiculos.size();
    }
}
