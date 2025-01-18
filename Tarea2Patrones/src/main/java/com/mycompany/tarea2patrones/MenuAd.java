package com.mycompany.tarea2patrones;

import clases.Reserva;
import claseVehiculo.Vehiculo;
import claseVuelo.Vuelo;

import java.util.List;
import java.util.Scanner;

public class MenuAd {

    public static void menuAdministracion(List<Reserva> reservas, List<Vuelo> vuelos, List<Vehiculo> vehiculos) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        while (!salir) {
            System.out.println("=== Menú Administración ===");
            System.out.println("1. Gestionar reservas");
            System.out.println("2. Gestionar vuelos");
            System.out.println("3. Gestionar vehículos");
            System.out.println("4. Configuración");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> gestionarReservas(scanner, reservas);
                case 2 -> gestionarVuelos(scanner, vuelos);
                case 3 -> gestionarVehiculos(scanner, vehiculos);
                case 4 -> System.out.println("Accediendo a configuración...");
                case 5 -> {
                    System.out.println("Saliendo del menú administración...");
                    salir = true;
                }
                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }

    private static void gestionarReservas(Scanner scanner, List<Reserva> reservas) {
        System.out.println("=== Gestionar Reservas ===");
        System.out.println("1. Cancelar una reserva");
        System.out.println("2. Volver al menú principal");
        System.out.print("Selecciona una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1 -> {
                for (Reserva reserva : reservas) {
                    System.out.println(reserva+"\n");
                }
                System.out.print("Introduce el ID de la reserva a cancelar: ");
                int idReserva = scanner.nextInt();
                scanner.nextLine();

                Reserva reservaCancelar = buscarReserva(idReserva, reservas);
                if (reservaCancelar != null) {
                    reservaCancelar.cancelarReserva("Administracion ha cancelado su reserva");
                    
                    System.out.println("Reserva cancelada exitosamente.");
                } else {
                    System.out.println("Reserva no encontrada.");
                }
            }
            case 2 -> System.out.println("Volviendo al menú principal...");
            default -> System.out.println("Opción no válida. Intenta de nuevo.");
        }
    }

    private static void gestionarVuelos(Scanner scanner, List<Vuelo> vuelos) {
        System.out.println("=== Gestionar Vuelos ===");
        System.out.println("1. Cancelar un vuelo");
        System.out.println("2. Volver al menú principal");
        System.out.print("Selecciona una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1 -> {
                for (Vuelo vuelo : vuelos) {
                    System.out.println(vuelo);
                }
                System.out.print("Introduce el ID del vuelo a cancelar: ");
                int idVuelo = scanner.nextInt();
                scanner.nextLine();

                Vuelo vueloCancelar = buscarVuelo(idVuelo, vuelos);
                if (vueloCancelar != null) {
                    vueloCancelar.cancelarVuelo("Adminstracion ha cancelado su vuelo");
                    
                    System.out.println("Vuelo cancelado exitosamente.");
                } else {
                    System.out.println("Vuelo no encontrado.");
                }
            }
            case 2 -> System.out.println("Volviendo al menú principal...");
            default -> System.out.println("Opción no válida. Intenta de nuevo.");
        }
    }

    private static void gestionarVehiculos(Scanner scanner, List<Vehiculo> vehiculos) {
        System.out.println("=== Gestionar Vehículos ===");
        System.out.println("1. Cancelar un vehículo");
        System.out.println("2. Volver al menú principal");
        System.out.print("Selecciona una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1 -> {
                for (Vehiculo vehiculo : vehiculos) {
                    System.out.println(vehiculo);
                }
                System.out.print("Introduce el ID del vehículo a cancelar: ");
                int idVehiculo = scanner.nextInt();
                scanner.nextLine();

                Vehiculo vehiculoCancelar = buscarVehiculo(idVehiculo, vehiculos);
                if (vehiculoCancelar != null) {
                vehiculoCancelar.cancelarVehiculo("Administracion ha cancelado su reserva de vehiculo");
                    
                    System.out.println("Vehículo cancelado exitosamente.");
                } else {
                    System.out.println("Vehículo no encontrado.");
                }
            }
            case 2 -> System.out.println("Volviendo al menú principal...");
            default -> System.out.println("Opción no válida. Intenta de nuevo.");
        }
    }

    private static Reserva buscarReserva(int idReserva, List<Reserva> reservas) {
        for (Reserva reserva : reservas) {
            if (reserva.getIdReserva() == idReserva) {
                return reserva;
            }
        }
        return null;
    }

    private static Vuelo buscarVuelo(int idVuelo, List<Vuelo> vuelos) {
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getIdVuelo() == idVuelo) {
                return vuelo;
            }
        }
        return null;
    }

    private static Vehiculo buscarVehiculo(int idVehiculo, List<Vehiculo> vehiculos) {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getIdVehiculo() == idVehiculo) {
                return vehiculo;
            }
        }
        return null;
    }
}
