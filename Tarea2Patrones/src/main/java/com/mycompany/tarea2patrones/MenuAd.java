package com.mycompany.tarea2patrones;

import clases.Reserva;
import claseVehiculo.Vehiculo;
import claseVuelo.Vuelo;
import java.util.InputMismatchException;

import java.util.List;
import java.util.Scanner;

public class MenuAd {

    private final Scanner scanner;

    public MenuAd(Scanner scanner) {
        this.scanner = scanner;
    }

    public void menuAdministracion(List<Reserva> reservas, List<Vuelo> vuelos, List<Vehiculo> vehiculos) {
        boolean salir = false;
        while (!salir) {
            System.out.println("=== Menú Administración ===");
            System.out.println("1. Gestionar reservas");
            System.out.println("2. Gestionar vuelos");
            System.out.println("3. Gestionar vehículos");
            System.out.println("4. Configuración");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");

            int opcion = leerOpcionNumerica();

            switch (opcion) {
                case 1 -> gestionarReservas(reservas);
                case 2 -> gestionarVuelos(vuelos);
                case 3 -> gestionarVehiculos(vehiculos);
                case 4 -> System.out.println("Accediendo a configuración...");
                case 5 -> {
                    System.out.println("Saliendo del menú administración...");
                    salir = true;
                }
                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }

    private void gestionarReservas(List<Reserva> reservas) {
        System.out.println("=== Gestionar Reservas ===");
        System.out.println("1. Cancelar una reserva");
        System.out.println("2. Volver al menú principal");
        System.out.print("Selecciona una opción: ");

        int opcion = leerOpcionNumerica();

        switch (opcion) {
            case 1 -> {
                if (reservas.isEmpty()) {
                    System.out.println("No hay reservas disponibles.");
                    return;
                }
                reservas.forEach(System.out::println);
                System.out.print("Introduce el ID de la reserva a cancelar: ");
                int idReserva = leerOpcionNumerica();

                Reserva reservaCancelar = buscarReserva(idReserva, reservas);
                if (reservaCancelar != null) {
                    reservaCancelar.cancelarReserva("Administración ha cancelado su reserva");
                    System.out.println("Reserva cancelada exitosamente.");
                } else {
                    System.out.println("Reserva no encontrada.");
                }
            }
            case 2 -> System.out.println("Volviendo al menú principal...");
            default -> System.out.println("Opción no válida. Intenta de nuevo.");
        }
    }

    private void gestionarVuelos(List<Vuelo> vuelos) {
        System.out.println("=== Gestionar Vuelos ===");
        System.out.println("1. Cancelar un vuelo");
        System.out.println("2. Volver al menú principal");
        System.out.print("Selecciona una opción: ");

        int opcion = leerOpcionNumerica();

        switch (opcion) {
            case 1 -> {
                if (vuelos.isEmpty()) {
                    System.out.println("No hay vuelos disponibles.");
                    return;
                }
                vuelos.forEach(System.out::println);
                System.out.print("Introduce el ID del vuelo a cancelar: ");
                int idVuelo = leerOpcionNumerica();

                Vuelo vueloCancelar = buscarVuelo(idVuelo, vuelos);
                if (vueloCancelar != null) {
                    vueloCancelar.cancelarVuelo("Administración ha cancelado su vuelo");
                    System.out.println("Vuelo cancelado exitosamente.");
                } else {
                    System.out.println("Vuelo no encontrado.");
                }
            }
            case 2 -> System.out.println("Volviendo al menú principal...");
            default -> System.out.println("Opción no válida. Intenta de nuevo.");
        }
    }

    private void gestionarVehiculos(List<Vehiculo> vehiculos) {
        System.out.println("=== Gestionar Vehículos ===");
        System.out.println("1. Cancelar un vehículo");
        System.out.println("2. Volver al menú principal");
        System.out.print("Selecciona una opción: ");

        int opcion = leerOpcionNumerica();

        switch (opcion) {
            case 1 -> {
                if (vehiculos.isEmpty()) {
                    System.out.println("No hay vehículos disponibles.");
                    return;
                }
                vehiculos.forEach(System.out::println);
                System.out.print("Introduce el ID del vehículo a cancelar: ");
                int idVehiculo = leerOpcionNumerica();

                Vehiculo vehiculoCancelar = buscarVehiculo(idVehiculo, vehiculos);
                if (vehiculoCancelar != null) {
                    vehiculoCancelar.cancelarVehiculo("Administración ha cancelado su reserva de vehículo");
                    System.out.println("Vehículo cancelado exitosamente.");
                } else {
                    System.out.println("Vehículo no encontrado.");
                }
            }
            case 2 -> System.out.println("Volviendo al menú principal...");
            default -> System.out.println("Opción no válida. Intenta de nuevo.");
        }
    }

    private Reserva buscarReserva(int idReserva, List<Reserva> reservas) {
        return reservas.stream().filter(reserva -> reserva.getIdReserva() == idReserva).findFirst().orElse(null);
    }

    private Vuelo buscarVuelo(int idVuelo, List<Vuelo> vuelos) {
        return vuelos.stream().filter(vuelo -> vuelo.getIdVuelo() == idVuelo).findFirst().orElse(null);
    }

    private Vehiculo buscarVehiculo(int idVehiculo, List<Vehiculo> vehiculos) {
        return vehiculos.stream().filter(vehiculo -> vehiculo.getIdVehiculo() == idVehiculo).findFirst().orElse(null);
    }

    private int leerOpcionNumerica() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Entrada inválida. Introduce un número: ");
                scanner.nextLine();
            }
        }
    }
} 
