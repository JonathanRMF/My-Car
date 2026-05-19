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

    private static final String ARG_ID    = "id";
    private static final String ARG_NOMBRE = "nombre";
    private static final String ARG_PRECIO = "precio";
    private static final String ARG_DESC  = "descripcion";
    private static final String ARG_IMG   = "imagen";
    private static final String ARG_ANIO  = "anio";
    private static final String ARG_PLAZAS = "plazas";
    private static final String ARG_MOTOR = "motor";
    private static final String ARG_KM    = "kilometraje";

    public static DetalleVehiculoFragment newInstance(
            int id, String nombre, double precio, String descripcion, String imagen,
            int anio, int plazas, String motor, int kilometraje) {

        DetalleVehiculoFragment fragment = new DetalleVehiculoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        args.putString(ARG_NOMBRE, nombre);
        args.putDouble(ARG_PRECIO, precio);
        args.putString(ARG_DESC, descripcion);
        args.putString(ARG_IMG, imagen);
        args.putInt(ARG_ANIO, anio);
        args.putInt(ARG_PLAZAS, plazas);
        args.putString(ARG_MOTOR, motor);
        args.putInt(ARG_KM, kilometraje);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detalle_vehiculo, container, false);

        ImageView imgVehiculo       = view.findViewById(R.id.imgVehiculo);
        TextView txtNombre          = view.findViewById(R.id.txtNombre);
        TextView txtDescripcion     = view.findViewById(R.id.txtDescripcion);
        TextView txtFicha           = view.findViewById(R.id.txtFicha);
        TextView txtPrecio          = view.findViewById(R.id.txtPrecio);
        Button btnReservar          = view.findViewById(R.id.btnReservar);
        Button btnVolverLista       = view.findViewById(R.id.btnVolverLista);
        Button btnVolverCategorias  = view.findViewById(R.id.btnVolverCategorias);
        Button btnVolverMain        = view.findViewById(R.id.btnVolverMain);

        int id           = getArguments().getInt(ARG_ID);
        String nombre    = getArguments().getString(ARG_NOMBRE);
        double precio    = getArguments().getDouble(ARG_PRECIO);
        String descripcion = getArguments().getString(ARG_DESC);
        String imagen    = getArguments().getString(ARG_IMG);
        int anio         = getArguments().getInt(ARG_ANIO);
        int plazas       = getArguments().getInt(ARG_PLAZAS);
        String motor     = getArguments().getString(ARG_MOTOR);
        int kilometraje  = getArguments().getInt(ARG_KM);

        txtNombre.setText(nombre);
        txtDescripcion.setText(descripcion);
        txtPrecio.setText("Precio por día: $" + precio);

        // Ficha técnica con todos los datos del enunciado
        String ficha = "Año: " + anio + "\n"
                + "Plazas: " + plazas + "\n"
                + "Motor: " + motor + "\n"
                + "Kilometraje: " + kilometraje + " km";
        txtFicha.setText(ficha);

        int imageResource = getResources().getIdentifier(
                imagen, "drawable", requireContext().getPackageName());
        imgVehiculo.setImageResource(imageResource);

        btnReservar.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), FormularioAlquilerActivity.class);
            intent.putExtra("vehiculo_id", id);
            intent.putExtra("nombre", nombre);
            intent.putExtra("precio_dia", precio);
            startActivity(intent);
        });

        btnVolverLista.setOnClickListener(v -> requireActivity().finish());

        btnVolverCategorias.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        btnVolverMain.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        return view;
    }
}