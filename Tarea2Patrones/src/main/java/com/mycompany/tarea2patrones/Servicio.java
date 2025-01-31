package com.mycompany.tarea2patrones;

import clases.Autenticacion;
import clases.Cliente;
import clases.GestorClientes;
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
    private final List<Cliente> clientes;
    private final Scanner scanner;

    public Servicio(List<Cliente> clientes, Scanner scanner) {
        this.clientes = clientes;
        this.scanner = scanner;
    }

    public void iniciar(List<Reserva> reservas, List<Vuelo> vuelos, List<Vehiculo> vehiculos) {
        while (true) {
            mostrarMenuPrincipal();
            int opcion = leerOpcion();

            switch (opcion) {
                case 1 -> iniciarSesionCliente(scanner, vuelos, vehiculos);
                case 2 -> iniciarSesionAdministracion(reservas, vuelos, vehiculos);
                case 3 -> {
                    System.out.println("¡Gracias por usar el sistema!");
                    return;
                }
                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }

    private void mostrarMenuPrincipal() {
        System.out.println("=== Menú Principal ===");
        System.out.println("1. Ingresar como Cliente");
        System.out.println("2. Ingresar como Administración");
        System.out.println("3. Salir");
        System.out.print("Selecciona una opción: ");
    }

    private int leerOpcion() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada no válida. Intenta de nuevo.");
            scanner.next();
        }
        int opcion = scanner.nextInt();
        scanner.nextLine();
        return opcion;
    }

    public void iniciarSesionAdministracion(List<Reserva> reservas, List<Vuelo> vuelos, List<Vehiculo> vehiculos) {
        System.out.println("=== Acceso a Administración ===");
        System.out.println("¡Acceso otorgado sin necesidad de usuario ni contraseña!");
        MenuAd menuAd = new MenuAd(scanner);
        menuAd.menuAdministracion(reservas, vuelos, vehiculos);
    }

    public void iniciarSesionCliente(Scanner scanner, List<Vuelo> vuelos, List<Vehiculo> vehiculos) {
        GestorClientes gestorClientes = new GestorClientes(clientes);
        Autenticacion autenticacion = new Autenticacion(gestorClientes);

        Cliente clienteSeleccionado = autenticacion.iniciarSesionCliente(scanner);
        if (clienteSeleccionado != null) {
            MenuC.menuCliente(clienteSeleccionado, vehiculos, vuelos,scanner);
        }
    }

    private void mostrarClientes() {
        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            System.out.println((i + 1) + ". " + cliente.getNombre() + " (ID: " + cliente.getIdCedula() + ")");
        }
        System.out.print("Selecciona el número del cliente: ");
    }
}