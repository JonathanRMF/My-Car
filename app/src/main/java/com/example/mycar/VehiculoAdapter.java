package com.example.mycar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VehiculoAdapter extends RecyclerView.Adapter<VehiculoAdapter.ViewHolder> {

    ArrayList<Vehiculo> listaVehiculos;

    public VehiculoAdapter(ArrayList<Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombreVehiculo;
        TextView txtPrecioVehiculo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNombreVehiculo = itemView.findViewById(R.id.txtNombreVehiculo);
            txtPrecioVehiculo = itemView.findViewById(R.id.txtPrecioVehiculo);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vehiculo_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Vehiculo vehiculo = listaVehiculos.get(position);

        holder.txtNombreVehiculo.setText(vehiculo.getNombre());

        holder.txtPrecioVehiculo.setText(
                "$" + vehiculo.getPrecio()
        );

        holder.txtNombreVehiculo.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetalleVehiculoActivity.class);

            intent.putExtra("nombre", vehiculo.getNombre());
            intent.putExtra("precio", vehiculo.getPrecio());
            intent.putExtra("descripcion", vehiculo.getDescripcion());

            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaVehiculos.size();
    }
}