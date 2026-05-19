package com.example.mycar;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleVehiculoFragment extends Fragment {

    private static final String ARG_ID     = "id";
    private static final String ARG_NOMBRE = "nombre";
    private static final String ARG_PRECIO = "precio";
    private static final String ARG_DESC   = "descripcion";
    private static final String ARG_IMG    = "imagen";

    public static DetalleVehiculoFragment newInstance(
            int id, String nombre, double precio, String descripcion, String imagen) {

        DetalleVehiculoFragment fragment = new DetalleVehiculoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        args.putString(ARG_NOMBRE, nombre);
        args.putDouble(ARG_PRECIO, precio);
        args.putString(ARG_DESC, descripcion);
        args.putString(ARG_IMG, imagen);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detalle_vehiculo, container, false);

        ImageView imgVehiculo  = view.findViewById(R.id.imgVehiculo);
        TextView txtNombre     = view.findViewById(R.id.txtNombre);
        TextView txtDescripcion= view.findViewById(R.id.txtDescripcion);
        TextView txtPrecio     = view.findViewById(R.id.txtPrecio);
        Button btnReservar     = view.findViewById(R.id.btnReservar);
        Button btnVolverLista  = view.findViewById(R.id.btnVolverLista);
        Button btnVolverCategorias = view.findViewById(R.id.btnVolverCategorias);
        Button btnVolverMain   = view.findViewById(R.id.btnVolverMain);

        int id           = getArguments().getInt(ARG_ID);
        String nombre    = getArguments().getString(ARG_NOMBRE);
        double precio    = getArguments().getDouble(ARG_PRECIO);
        String descripcion = getArguments().getString(ARG_DESC);
        String imagen    = getArguments().getString(ARG_IMG);

        txtNombre.setText(nombre);
        txtDescripcion.setText(descripcion);
        txtPrecio.setText("Precio por día: $" + precio);

        int imageResource = getResources().getIdentifier(
                imagen, "drawable", requireContext().getPackageName());
        imgVehiculo.setImageResource(imageResource);

        // Botón Reservar — ahora sí envía vehiculo_id
        btnReservar.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), FormularioAlquilerActivity.class);
            intent.putExtra("vehiculo_id", id);
            intent.putExtra("nombre", nombre);
            intent.putExtra("precio_dia", precio);
            startActivity(intent);
        });

        // Volver a lista de vehículos
        btnVolverLista.setOnClickListener(v -> {
            requireActivity().finish();
        });

        // Volver a categorías
        btnVolverCategorias.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        // Volver a pantalla principal
        btnVolverMain.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        return view;
    }
}