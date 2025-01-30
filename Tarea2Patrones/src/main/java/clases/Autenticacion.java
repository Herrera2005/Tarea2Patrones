package clases;

import java.util.Scanner;

public class Autenticacion {
    private GestorClientes gestorClientes;

    public Autenticacion(GestorClientes gestorClientes) {
        this.gestorClientes = gestorClientes;
    }

    public Cliente iniciarSesionCliente(Scanner scanner) {
        System.out.println("=== Inicio de sesión para Cliente ===");
        System.out.println("Selecciona un cliente para iniciar sesión:");

        gestorClientes.mostrarClientes();

        System.out.print("Selecciona el número del cliente: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        try {
            Cliente clienteSeleccionado = gestorClientes.obtenerClientePorIndice(opcion - 1);
            System.out.println("¡Inicio de sesión exitoso como Cliente!");
            return clienteSeleccionado;
        } catch (IllegalArgumentException e) {
            System.out.println("Opción no válida. Intenta de nuevo.");
            return null;
        }
    }
}