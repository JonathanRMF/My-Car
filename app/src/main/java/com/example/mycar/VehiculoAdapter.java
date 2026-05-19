package com.example.mycar;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VehiculoAdapter extends RecyclerView.Adapter<VehiculoAdapter.ViewHolder> {

    ArrayList<Vehiculo> listaVehiculos;

    public VehiculoAdapter(ArrayList<Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgMiniatura;
        TextView txtNombreVehiculo;
        TextView txtPrecioVehiculo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMiniatura       = itemView.findViewById(R.id.imgMiniatura);
            txtNombreVehiculo  = itemView.findViewById(R.id.txtNombreVehiculo);
            txtPrecioVehiculo  = itemView.findViewById(R.id.txtPrecioVehiculo);
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
        holder.txtPrecioVehiculo.setText("$" + vehiculo.getPrecio() + " / día");

        // Cargar imagen miniatura
        int resId = holder.imgMiniatura.getContext().getResources().getIdentifier(
                vehiculo.getImagen(), "drawable",
                holder.imgMiniatura.getContext().getPackageName());
        holder.imgMiniatura.setImageResource(resId);

        // Click en cualquier parte del item abre el carrusel
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), CarruselVehiculosActivity.class);
            intent.putExtra("posicion", position);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaVehiculos.size();
    }
}