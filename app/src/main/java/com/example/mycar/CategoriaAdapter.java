package com.example.mycar;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.ViewHolder> {

    ArrayList<Categoria> listaCategorias;

    public CategoriaAdapter(ArrayList<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtCategoria;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCategoria = itemView.findViewById(R.id.txtCategoria);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categoria_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Categoria categoria = listaCategorias.get(position);

        holder.txtCategoria.setText(categoria.getNombre());

        holder.txtCategoria.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), VehiculosActivity.class);

            intent.putExtra("categoria", categoria.getNombre());

            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaCategorias.size();
    }
}