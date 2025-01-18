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
    
    public static void menuCliente(Cliente cliente,List<Vehiculo> vehiculos, List<Vuelo> vuelos) {
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
                case 2 -> cliente.realizarReservaPorConsola(vehiculos, vuelos);
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


            System.out.println("Reserva eliminada exitosamente.");
        } else {
            System.out.println("No se encontró una reserva con ese ID.");
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
