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
        super(context, "car.db", null, 2);
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
        db.execSQL("DROP TABLE IF EXISTS vehiculos");
        db.execSQL("DROP TABLE IF EXISTS alquileres");
        onCreate(db);
    }

    // ─────────────────────────────────────
    //  INSERTAR VEHÍCULO (usado en onCreate)

    //Esto llena la BD de datos iniciales de autos, la info fue generada con IA ya que desconozco de autos
    // ─────────────────────────────────────
    private void cargarVehiculos(SQLiteDatabase db) {
        // Autos
        insertarVehiculo(db, "Auto", "Toyota", "Corolla", 2022, 5, "1.8L", 15000, 45.0, "Sedán confortable y económico", "auto_toyota_corolla_2022");
        insertarVehiculo(db, "Auto", "Volkswagen", "Vento GLI", 2023, 5, "1.4L Turbo", 8000, 50.0, "Sedán moderno con gran equipamiento", "auto_volkswagen_vento_gli");
        insertarVehiculo(db, "Auto", "Renault", "Logan", 2021, 5, "1.6L", 22000, 38.0, "Económico y espacioso", "auto_renaut_logan");
        insertarVehiculo(db, "Auto", "Ford", "Focus", 2022, 5, "2.0L", 18000, 48.0, "Dinámico y versátil", "auto_ford_focus");
        // Utilitarios
        insertarVehiculo(db, "Utilitario", "Citroën", "Berlingo", 2022, 5, "1.6L HDi", 25000, 60.0, "Versátil y espacioso furgón urbano", "uti_citroen_berlingo");
        insertarVehiculo(db, "Utilitario", "Mercedes-Benz", "Sprinter", 2023, 9, "2.2L CDi", 18000, 90.0, "Furgón grande ideal para carga y pasajeros", "uti_mercedes_benz_sprinter");
        insertarVehiculo(db, "Utilitario", "Peugeot", "Partner", 2022, 5, "1.6L HDi", 20000, 55.0, "Compacto y eficiente para trabajo urbano", "uti_peugeot_partner");
        insertarVehiculo(db, "Utilitario", "Renault", "Kangoo", 2021, 5, "1.6L SCe", 22000, 50.0, "Práctico y económico para el día a día", "uti_renaut_kangoo");
        // Camionetas
        insertarVehiculo(db, "Camioneta", "Ford", "Ranger", 2022, 5, "3.2L Diesel", 20000, 95.0, "Potente y resistente", "cam_ford_ranger");
        insertarVehiculo(db, "Camioneta", "Toyota", "Hilux", 2023, 5, "2.8L Diesel", 10000, 100.0, "La más vendida del segmento", "cam_toyota_hilux");
        insertarVehiculo(db, "Camioneta", "Volkswagen", "Amarok", 2022, 5, "3.0L V6", 15000, 110.0, "Potencia y lujo en una pickup", "cam_volkswagen_amarok_2022");
        insertarVehiculo(db, "Camioneta", "Nissan", "Frontier", 2023, 5, "2.5L Diesel", 8000, 92.0, "Robusta y confiable", "cam_nissan_frontier_023");
        // Deportivos
        insertarVehiculo(db, "Deportivo", "Chevrolet", "Camaro", 2022, 4, "6.2L V8", 5000, 145.0, "Pura potencia y estilo", "dep_chevrolet_camaro");
        insertarVehiculo(db, "Deportivo", "BMW", "M3", 2023, 5, "3.0L Turbo", 4000, 200.0, "Deportividad alemana al máximo", "dep_bmw_m3");
        insertarVehiculo(db, "Deportivo", "Audi", "TT", 2022, 4, "2.0L TFSI", 7000, 175.0, "Diseño y performance únicos", "dep_audi_tt");
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
        return db.rawQuery("SELECT * FROM vehiculos WHERE categoria = ?", new String[]{categoria});
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