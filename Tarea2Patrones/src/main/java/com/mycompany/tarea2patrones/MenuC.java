/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea2patrones;

import Observer.GestorReservas;
import Observer.ObservadorCambioReserva;
import Observer.ObservadorIncumplimiento;
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
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author RUCO HOUSE
 */
public class MenuC {
    private static final String RESERVAS_TXT = "reservas.txt";
    public static void menuCliente(Cliente cliente) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        while (!salir) {
            System.out.println("=== Menú Cliente ===");
            System.out.println("1. Ver reservas");
            System.out.println("2. Realizar nueva reserva");
            System.out.println("3. Eliminar reserva");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> mostrarReservas(cliente);
                case 2 -> crearReserva(cliente);
                case 3 -> Cliente.cancelarReserva(cliente);
                case 4 -> {
                    System.out.println("Saliendo del menú cliente...");
                    salir = true;
                }
                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }

   private static void eliminarReserva(Cliente cliente) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Eliminar Reserva ===");
        mostrarReservas(cliente);
        if (cliente.getReservas().isEmpty()) {
            return;
        }

        System.out.print("Introduce el ID de la reserva que deseas eliminar: ");
        int idReserva = scanner.nextInt();
        scanner.nextLine();

        Reserva reservaAEliminar = null;
        for (Reserva reserva : cliente.getReservas()) {
            if (reserva.getIdReserva() == idReserva) {
                reservaAEliminar = reserva;
                break;
            }
        }

        if (reservaAEliminar != null) {
            cliente.eliminarReserva(idReserva);
             // Notificar a los observadores
            GestorReservas gestor = new GestorReservas();
            gestor.addObserver(new ObservadorCambioReserva(reservaAEliminar));
            gestor.addObserver(new ObservadorIncumplimiento(reservaAEliminar));
            gestor.notifyObservers(reservaAEliminar);
            // Actualizar los archivos
            eliminarReservaDeArchivoGeneral(idReserva);
            actualizarReservasCliente(cliente, idReserva);

            System.out.println("Reserva eliminada exitosamente.");
        } else {
            System.out.println("No se encontró una reserva con ese ID.");
        }
    }

   public static void eliminarReservaDeArchivoGeneral(int idReserva) {
        File archivoReservas = new File(RESERVAS_TXT);
        List<String> lineasReservasActualizadas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(archivoReservas))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Verifica si la línea NO corresponde al ID de la reserva a eliminar
                if (!linea.startsWith(idReserva + ":")) {
                    lineasReservasActualizadas.add(linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de reservas: " + e.getMessage());
        }

        // Escribe las líneas actualizadas nuevamente en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoReservas))) {
            for (String linea : lineasReservasActualizadas) {
                writer.write(linea);
                writer.newLine();
            }
            System.out.println("Archivo de reservas actualizado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al actualizar el archivo de reservas: " + e.getMessage());
        }
    }
   public static void actualizarReservasCliente(Cliente cliente, int idReserva) {
        File archivoCliente = new File("cliente_" + cliente.getIdCedula() + ".txt");
        List<String> lineasActualizadas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(archivoCliente))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith("Reservas:{")) {
                    // Extraer la lista de IDs y eliminar el ID específico
                    String reservasExistentes = linea.substring(9, linea.length() - 1).trim(); // Extraer contenido entre {}
                    List<String> idsReservas = new ArrayList<>(Arrays.asList(reservasExistentes.split(", ")));
                    idsReservas.remove(String.valueOf(idReserva)); // Eliminar el ID de la reserva
                    lineasActualizadas.add("Reservas:" + String.join(", ", idsReservas) + "}");
                } else {
                    lineasActualizadas.add(linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo del cliente: " + e.getMessage());
        }

        // Escribir las líneas actualizadas nuevamente en el archivo del cliente
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoCliente))) {
            for (String linea : lineasActualizadas) {
                writer.write(linea);
                writer.newLine();
            }
            System.out.println("Archivo del cliente actualizado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al actualizar el archivo del cliente: " + e.getMessage());
        }
    }
    private static void crearReserva(Cliente cliente) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce el ID de la reserva: ");
        int idReserva = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Introduce la fecha de la reserva (YYYY-MM-DD): ");
        Date fechaReserva = Date.valueOf(scanner.nextLine());

        System.out.println("Selecciona un vehículo (ID): ");
        int idVehiculo = scanner.nextInt();
        scanner.nextLine();
        Vehiculo vehiculo = new VehiculoEconomico(idVehiculo, "MarcaX", "ModeloY", true);

        System.out.println("Introduce los detalles del vuelo: ");
        Vuelo vuelo = new Vuelo(1, "AerolineaX", fechaReserva, fechaReserva, 100, new ArrayList<>());

        System.out.println("Introduce el monto del pago: ");
        double monto = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Introduce el método de pago: ");
        String metodoPago = scanner.nextLine();
        Pago pago = new Pago(monto, metodoPago, EstadoPago.PENDIENTE);

        Reserva nuevaReserva = new ReservaBuilder()
                .setIdReserva(idReserva)
                .setEstadoReserva(EstadoReserva.PENDIENTE)
                .setFechaReserva(fechaReserva)
                .setVehiculo(vehiculo)
                .setVuelo(vuelo)
                .setPago(pago)
                .setCliente(cliente)
                .build();

        cliente.agregarReserva(nuevaReserva); // Método para agregar la reserva al cliente
        actualizarArchivosReservas(nuevaReserva,cliente);
        System.out.println("Reserva creada exitosamente con ID: " + idReserva);
    }
    
    private static void actualizarArchivosReservas(Reserva reserva, Cliente cliente) {
        try {
            // Actualizar el archivo general de reservas
            File reservasFile = new File(RESERVAS_TXT);
            if (!reservasFile.exists()) {
                reservasFile.createNewFile();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(reservasFile, true))) {
                writer.write(reserva.toString());
                writer.newLine(); // Agrega un salto de línea
            }

            // Actualizar el archivo del cliente
            File clienteFile = new File("cliente_" + cliente.getIdCedula() + ".txt");
            if (clienteFile.exists()) {
                List<String> lineasArchivo = new ArrayList<>();
                boolean reservasEncontradas = false;

                try (BufferedReader reader = new BufferedReader(new FileReader(clienteFile))) {
                    String linea;
                    while ((linea = reader.readLine()) != null) {
                        if (linea.startsWith("Reservas:{")) {
                            reservasEncontradas = true;
                            String reservasExistentes = linea.substring(9, linea.length() - 1).trim(); // Extraer contenido entre {}
                            if (!reservasExistentes.isEmpty()) {
                                reservasExistentes += ", " + reserva.getIdReserva();
                            } else {
                                reservasExistentes = String.valueOf(reserva.getIdReserva());
                            }
                            lineasArchivo.add("Reservas:" + reservasExistentes + "}"); // Línea corregida
                        } else {
                            lineasArchivo.add(linea);
                        }
                    }
                }

                // Si no se encontró la línea de reservas, agregarla al final
                if (!reservasEncontradas) {
                    lineasArchivo.add("Reservas:{" + reserva.getIdReserva() + "}");
                }

                // Escribir el archivo actualizado
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(clienteFile))) {
                    for (String linea : lineasArchivo) {
                        writer.write(linea);
                        writer.newLine();
                    }
                }
            } else {
                System.out.println("Archivo del cliente no encontrado. No se pudo actualizar.");
            }
        } catch (IOException e) {
            System.out.println("Error al actualizar los archivos: " + e.getMessage());
        }
    }
    

    public static void mostrarReservas(Cliente cliente) {
        List<Reserva> reservas = cliente.getReservas();
        System.out.println("=== Tus Reservas ===");
        if (reservas.isEmpty()) {
            System.out.println("No tienes reservas registradas.");
            return;
        }

        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }
    }
}
