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
import clases.SistemaReservasVehiculo;
import clases.SistemaReservasVuelo;
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
    
    public static void menuCliente(Cliente cliente, List<Vehiculo> vehiculos, List<Vuelo> vuelos) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== Menú Cliente ===");
            System.out.println("1. Ver reservas");
            System.out.println("2. Realizar nueva reserva");
            System.out.println("3. Eliminar reserva");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción: ");

            // Verificar si la entrada es numérica
            if (!scanner.hasNextInt()) {
                System.out.println("Error: Debes ingresar un número válido.");
                scanner.next(); // Descartar entrada incorrecta
                continue;
            }

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            System.out.println("Debug: Opción ingresada -> " + opcion); // Mensaje de depuración

            switch (opcion) {
                case 1:
                    System.out.println("Debug: Mostrando reservas...");
                    mostrarReservas(cliente);
                    break;
                case 2:
                    System.out.println("Debug: Realizando nueva reserva...");
                    cliente.realizarReservaPorConsola(vehiculos, vuelos);
                    break;
                case 3:
                    System.out.println("Debug: Eliminando reserva...");
                    eliminarReserva(cliente);
                    break;
                case 4:
                    System.out.println("Saliendo del menú cliente...");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }

    private static void eliminarReserva(Cliente cliente) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Eliminar Reserva ===");
        mostrarReservas(cliente);

        if (cliente.getReservas().isEmpty()) {
            System.out.println("No tienes reservas para eliminar.");
            return;
        }

        System.out.print("Introduce el ID de la reserva que deseas eliminar: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Error: Debes ingresar un número válido.");
            scanner.next(); // Descartar entrada incorrecta
        }

        int idReserva = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Reserva reservaAEliminar = null;
        for (Reserva reserva : cliente.getReservas()) {
            if (reserva.getIdReserva() == idReserva) {
                reservaAEliminar = reserva;
                break;
            }
        }

        if (reservaAEliminar != null) {
            cliente.eliminarReserva(idReserva);
            System.out.println("Reserva eliminada exitosamente.");
        } else {
            System.out.println("No se encontró una reserva con ese ID.");
        }
    }

    public static void mostrarReservas(Cliente cliente) {
        System.out.println("\n=== Tus Reservas ===");

        List<Reserva> reservas = cliente.getReservas();
        if (reservas == null || reservas.isEmpty()) {
            System.out.println("No tienes reservas registradas.");
            return;
        }

        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }
    }
}