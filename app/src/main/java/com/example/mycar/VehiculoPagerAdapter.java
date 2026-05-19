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

        Vehiculo vehiculo = listaVehiculos.get(position);

        return DetalleVehiculoFragment.newInstance(
                vehiculo.getId(),
                vehiculo.getNombre(),
                vehiculo.getPrecio(),
                vehiculo.getDescripcion(),
                vehiculo.getImagen()
        );
    }

    @Override
    public int getItemCount() {
        return listaVehiculos.size();
    }
}
