/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea2patrones;

import claseVehiculo.Vehiculo;
import claseVehiculo.VehiculoEconomico;
import claseVuelo.Vuelo;
import clases.Cliente;
import clases.Pago;
import clases.Reserva;
import clases.ReservaBuilder;
import enums.EstadoPago;
import enums.EstadoReserva;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author RUCO HOUSE
 */
public class Validaciones {
    private static final String CLIENTE_TXT = "clientes.txt";
    private static final String ADMIN_TXT = "admin.txt";
    private static final String SOPORTE_TXT = "soporte.txt";
    private static final String RESERVAS_TXT = "reservas.txt";
    public static boolean validarCredenciales(String usuario, String contraseña, String archivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length == 2 && partes[0].equals(usuario) && partes[1].equals(contraseña)) {
                    return true;
                }
            }
        }
        return false;
    }
    public static void crearArchivoSiNoExiste(String archivo) throws IOException {
        File file = new File(archivo);
        if (!file.exists()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
                if (archivo.equals(CLIENTE_TXT)) {
                    bw.write("cliente:client\n");
                } else if (archivo.equals(ADMIN_TXT)) {
                    bw.write("admin:admin\n");
                } else if (archivo.equals(SOPORTE_TXT)) {
                    bw.write("soporte:support\n");
                }
            }
        }
    }
    public static void crearArchivoCliente(File archivoCliente, Cliente cliente) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoCliente))) {
            writer.write("ID: " + cliente.getIdCedula());
            writer.newLine();
            writer.write("Nombre: " + cliente.getNombre());
            writer.newLine();
            writer.write("Email: " + cliente.getEmail());
            writer.newLine();
            writer.write("Contrasena: " + cliente.getContrasenia());
            writer.newLine();
            writer.write("Reservas:{}");
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al crear el archivo del cliente: " + e.getMessage());
        }
    }
    public static Map<Integer, Reserva> cargarArchivoReservas(String archivoReservas, Cliente cliente) {
        Map<Integer, Reserva> reservas = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(archivoReservas))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length == 6) {
                    // Datos de Reserva
                    int idReserva = Integer.parseInt(partes[0].trim());
                    EstadoReserva estado = EstadoReserva.valueOf(partes[1].trim());
                    Date fechaReserva = Date.valueOf(partes[2].trim());

                    // Datos de Vehículo
                    String[] datosVehiculo = partes[3].split(",");
                    Vehiculo vehiculo = new VehiculoEconomico(
                            Integer.parseInt(datosVehiculo[0].trim()),
                            datosVehiculo[1].trim(),
                            datosVehiculo[2].trim(),
                            Boolean.parseBoolean(datosVehiculo[3].trim())
                    );

                    // Datos de Vuelo
                    String[] datosVuelo = partes[4].split(",");
                    Vuelo vuelo = new Vuelo(
                            Integer.parseInt(datosVuelo[0].trim()),
                            datosVuelo[1].trim(),
                            Date.valueOf(datosVuelo[2].trim()),
                            Date.valueOf(datosVuelo[3].trim()),
                            Integer.parseInt(datosVuelo[4].trim()),
                            new ArrayList<>()
                    );

                    // Datos de Pago
                    String[] datosPago = partes[5].split(",");
                    EstadoPago estadoPago = EstadoPago.valueOf(datosPago[2].trim()); // Validar estado del pago
                    Pago pago = new Pago(
                            Double.parseDouble(datosPago[0].trim()),
                            datosPago[1].trim(),
                            estadoPago
                    );

                    // Crear y almacenar Reserva usando el Builder
                    Reserva reserva = new ReservaBuilder()
                            .setIdReserva(idReserva)
                            .setEstadoReserva(estado)
                            .setFechaReserva(fechaReserva)
                            .setVehiculo(vehiculo)
                            .setVuelo(vuelo)
                            .setPago(pago)
                            .setCliente(cliente)
                            .build();

                    reservas.put(idReserva, reserva);
                }
            }

        } catch (IOException e) {
            System.out.println("Error al cargar el archivo de reservas: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error en el formato de las reservas o valores no válidos: " + e.getMessage());
        }

        return reservas;
    }

}
