package com.mycompany.tarea2patrones;

import clases.Cliente;
import clases.Reserva;

import java.util.List;
import java.util.Scanner;

import claseVehiculo.Vehiculo;
import claseVuelo.Vuelo;

/**
 *
 * @author RUCO HOUSE
 */
public class Servicio {

    private List<Cliente> clientes;

    public Servicio(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void Iniciar(List<Reserva> reservas, List<Vuelo> vuelos, List<Vehiculo> vehiculos) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== Menú Principal ===");
            System.out.println("1. Ingresar como Cliente");
            System.out.println("2. Ingresar como Administración");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> iniciarSesionCliente(scanner,vuelos,vehiculos);
                case 2 -> iniciarSesionAdministracion(reservas, vuelos, vehiculos);
                case 3 -> {
                    System.out.println("¡Gracias por usar el sistema!");
                    return;
                }
                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }

    private void iniciarSesionAdministracion(List<Reserva> reservas, List<Vuelo> vuelos, List<Vehiculo> vehiculos) {
        System.out.println("=== Acceso a Administración ===");
        System.out.println("¡Acceso otorgado sin necesidad de usuario ni contraseña!");

        // Mostrar menú de administración
        MenuAd.menuAdministracion(reservas,vuelos,vehiculos);
    }

        

    private void iniciarSesionCliente(Scanner scanner, List<Vuelo> vuelos, List<Vehiculo> vehiculos) {
        System.out.println("=== Inicio de sesión para Cliente ===");
        System.out.println("Selecciona un cliente para iniciar sesión:");

        // Mostrar lista de todos los clientes disponibles
        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            System.out.println((i + 1) + ". " + cliente.getNombre() + " (ID: " + cliente.getIdCedula() + ")");
        }

        System.out.print("Selecciona el número del cliente: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        if (opcion < 1 || opcion > clientes.size()) {
            System.out.println("Opción no válida. Intenta de nuevo.");
            return;
        }

        Cliente clienteSeleccionado = clientes.get(opcion - 1);

        // Omitimos la contraseña y continuamos con el inicio de sesión directamente
        System.out.println("¡Inicio de sesión exitoso como Cliente!");

     
        // Mostrar el menú del cliente
        MenuC.menuCliente(clienteSeleccionado,vehiculos,vuelos);
    }
}
