package com.example.mycar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class BDHelper extends SQLiteOpenHelper {

    //Constructor de la BD
    public BDHelper(Context context) {
        super(context, "car.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tabla vehículos
        db.execSQL("CREATE TABLE vehiculos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "categoria TEXT, " +
                "marca TEXT, " +
                "modelo TEXT, " +
                "anio INTEGER, " +
                "plazas INTEGER, " +
                "motor TEXT, " +
                "kilometraje INTEGER, " +
                "precio_dia REAL, " +
                "descripcion TEXT, " +
                "imagen TEXT)");

        // Crear tabla alquileres
        db.execSQL("CREATE TABLE alquileres (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "vehiculo_id INTEGER, " +
                "cliente_apellido TEXT, " +
                "cliente_nombre TEXT, " +
                "forma_pago TEXT, " +
                "cantidad_dias INTEGER, " +
                "precio_dia REAL, " +
                "monto_total REAL, " +
                "fecha TEXT)");

        // Cargar vehículos iniciales
        cargarVehiculos(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    // ─────────────────────────────────────
    //  INSERTAR VEHÍCULO (usado en onCreate)

    //Esto llena la BD de datos iniciales de autos, la info fue generada con IA ya que desconozco de autos
    // ─────────────────────────────────────
    private void cargarVehiculos(SQLiteDatabase db) {
        // Autos
        insertarVehiculo(db, "Auto", "Toyota", "Corolla", 2022, 5, "1.8L", 15000, 45.0, "Sedán confortable y económico", "auto_corolla");
        insertarVehiculo(db, "Auto", "Volkswagen", "Vento", 2023, 5, "1.4L Turbo", 8000, 50.0, "Sedán moderno con gran equipamiento", "auto_vento");
        insertarVehiculo(db, "Auto", "Renault", "Logan", 2021, 5, "1.6L", 22000, 38.0, "Económico y espacioso", "auto_logan");
        insertarVehiculo(db, "Auto", "Ford", "Focus", 2022, 5, "2.0L", 18000, 48.0, "Dinámico y versátil", "auto_focus");
        // SUV
        insertarVehiculo(db, "SUV", "Toyota", "RAV4", 2023, 5, "2.5L Hybrid", 5000, 85.0, "SUV híbrida con tecnología avanzada", "suv_rav4");
        insertarVehiculo(db, "SUV", "Ford", "Territory", 2022, 5, "1.5L Turbo", 12000, 75.0, "SUV urbana y cómoda", "suv_territory");
        insertarVehiculo(db, "SUV", "Jeep", "Compass", 2023, 5, "2.4L", 9000, 90.0, "Aventura y confort combinados", "suv_compass");
        insertarVehiculo(db, "SUV", "Chevrolet", "Tracker", 2022, 5, "1.2L Turbo", 14000, 70.0, "Compacta y eficiente", "suv_tracker");
        // Camionetas
        insertarVehiculo(db, "Camioneta", "Ford", "Ranger", 2022, 5, "3.2L Diesel", 20000, 95.0, "Potente y resistente", "cam_ranger");
        insertarVehiculo(db, "Camioneta", "Toyota", "Hilux", 2023, 5, "2.8L Diesel", 10000, 100.0, "La más vendida del segmento", "cam_hilux");
        insertarVehiculo(db, "Camioneta", "Volkswagen", "Amarok", 2022, 5, "3.0L V6", 15000, 110.0, "Potencia y lujo en una pickup", "cam_amarok");
        insertarVehiculo(db, "Camioneta", "Nissan", "Frontier", 2023, 5, "2.5L Diesel", 8000, 92.0, "Robusta y confiable", "cam_frontier");
        // Deportivos
        insertarVehiculo(db, "Deportivo", "Ford", "Mustang", 2023, 4, "5.0L V8", 3000, 150.0, "Ícono americano de alto rendimiento", "dep_mustang");
        insertarVehiculo(db, "Deportivo", "Chevrolet", "Camaro", 2022, 4, "6.2L V8", 5000, 145.0, "Pura potencia y estilo", "dep_camaro");
        insertarVehiculo(db, "Deportivo", "BMW", "M3", 2023, 5, "3.0L Turbo", 4000, 200.0, "Deportividad alemana al máximo", "dep_m3");
        insertarVehiculo(db, "Deportivo", "Audi", "TT", 2022, 4, "2.0L TFSI", 7000, 175.0, "Diseño y performance únicos", "dep_tt");
    }

    private void insertarVehiculo(SQLiteDatabase db, String categoria, String marca,
                                  String modelo, int anio, int plazas, String motor,
                                  int km, double precio, String desc, String imagen) {
        ContentValues v = new ContentValues();
        v.put("categoria", categoria);
        v.put("marca", marca);
        v.put("modelo", modelo);
        v.put("anio", anio);
        v.put("plazas", plazas);
        v.put("motor", motor);
        v.put("kilometraje", km);
        v.put("precio_dia", precio);
        v.put("descripcion", desc);
        v.put("imagen", imagen);
        db.insert("vehiculos", null, v);
    }

    // ─────────────────────────────────────
    //  CONSULTAS — VEHÍCULOS
    // ─────────────────────────────────────

    // Devuelve todas las categorías sin repetir
    public Cursor getCategorias() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT DISTINCT categoria FROM vehiculos", null);
    }

    // Devuelve todos los vehículos de una categoría
    public Cursor getVehiculosPorCategoria(String categoria) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT * FROM vehiculos WHERE categoria = '" + categoria + "'", null);
    }

    // Devuelve un vehículo por su ID
    public Cursor getVehiculoPorId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT * FROM vehiculos WHERE id = " + id, null);
    }

    // ─────────────────────────────────────
    //  ALQUILERES
    // ─────────────────────────────────────

    // Registra un alquiler nuevo
    public boolean registrarAlquiler(int vehiculoId, String apellido, String nombre,
                                     String formaPago, int dias, double precioDia) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put("vehiculo_id", vehiculoId);
        v.put("cliente_apellido", apellido);
        v.put("cliente_nombre", nombre);
        v.put("forma_pago", formaPago);
        v.put("cantidad_dias", dias);
        v.put("precio_dia", precioDia);
        v.put("monto_total", precioDia * dias);
        v.put("fecha", new java.text.SimpleDateFormat(
                "yyyy-MM-dd", java.util.Locale.getDefault())
                .format(new java.util.Date()));
        long resultado = db.insert("alquileres", null, v);
        return resultado != -1;
    }

    // Devuelve todos los alquileres con marca y modelo del vehículo
    public Cursor getTodosAlquileres() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT a.*, v.marca, v.modelo " +
                        "FROM alquileres a, vehiculos v " +
                        "WHERE a.vehiculo_id = v.id " +
                        "ORDER BY a.id DESC", null);
    }
}