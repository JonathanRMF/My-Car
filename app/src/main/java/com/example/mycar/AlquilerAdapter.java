package com.example.mycar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AlquilerAdapter extends RecyclerView.Adapter<AlquilerAdapter.ViewHolder> {

    ArrayList<Alquiler> listaAlquileres;

    public AlquilerAdapter(ArrayList<Alquiler> listaAlquileres) {
        this.listaAlquileres = listaAlquileres;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtCliente;
        TextView txtVehiculo;
        TextView txtDias;
        TextView txtTotal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCliente = itemView.findViewById(R.id.txtCliente);
            txtVehiculo = itemView.findViewById(R.id.txtVehiculo);
            txtDias = itemView.findViewById(R.id.txtDias);
            txtTotal = itemView.findViewById(R.id.txtTotal);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.alquiler_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position
    ) {

        Alquiler alquiler = listaAlquileres.get(position);

        holder.txtCliente.setText(
                alquiler.getCliente()
        );

        holder.txtVehiculo.setText(
                alquiler.getVehiculo()
        );

        holder.txtDias.setText(
                alquiler.getDias() + " días"
        );

        holder.txtTotal.setText(
                "$" + alquiler.getTotal()
        );
    }

    @Override
    public int getItemCount() {
        return listaAlquileres.size();
    }
}