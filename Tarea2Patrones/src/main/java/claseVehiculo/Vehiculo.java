/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package claseVehiculo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author herreranc
 */
public abstract class Vehiculo {
    private int idVehiculo;
    private String TipoVehiculo;
    private String Proveedor;
    private boolean disponibilidad;

    public Vehiculo(int idVehiculo, String TipoVehiculo, String Proveedor, boolean disponibilidad) {
        this.idVehiculo = idVehiculo;
        this.TipoVehiculo = TipoVehiculo;
        this.Proveedor = Proveedor;
        this.disponibilidad = disponibilidad;
    }

    public boolean verificarDisponibilidad(){
        return true;
    }

    public abstract void confirmarVehiculo();

    public static List<Vehiculo> getVehiculos(String archivo) {
        List<Vehiculo> vehiculos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                int idVehiculo = Integer.parseInt(datos[0]);
                String tipoVehiculo = datos[1];
                String proveedor = datos[2];
                boolean disponibilidad = datos[3].equals("1");

                Vehiculo vehiculo;
                switch (tipoVehiculo) {
                    case "DeLujo" -> vehiculo = new VehiculoDeLujo(idVehiculo, tipoVehiculo, proveedor, disponibilidad);
                    case "Economico" -> vehiculo = new VehiculoEconomico(idVehiculo, tipoVehiculo, proveedor, disponibilidad);
                    case "Ejecutivo" -> vehiculo = new VehiculoEjecutivo(idVehiculo, tipoVehiculo, proveedor, disponibilidad);
                    default -> throw new IllegalArgumentException("Tipo de vehículo desconocido: " + tipoVehiculo);
                }
                vehiculos.add(vehiculo);
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return vehiculos;
    }
}
