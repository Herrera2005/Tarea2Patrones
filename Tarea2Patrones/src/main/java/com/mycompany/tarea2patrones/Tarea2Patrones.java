/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tarea2patrones;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author herreranc
 */
public class Tarea2Patrones {
    private static final String CLIENTE_TXT = "clientes.txt";
    private static final String ADMIN_TXT = "admin.txt";
    private static final String SOPORTE_TXT = "soporte.txt";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Menú Principal
            System.out.println("=== Menú Principal ===");
            System.out.println("1. Ingresar como Cliente");
            System.out.println("2. Ingresar como Administración");
            System.out.println("3. Ingresar como Soporte");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> iniciarSesion(scanner, CLIENTE_TXT, "Cliente", () -> menuCliente());
                case 2 -> iniciarSesion(scanner, ADMIN_TXT, "Administración", () -> menuAdministracion());
                case 3 -> iniciarSesion(scanner, SOPORTE_TXT, "Soporte", () -> menuSoporte());
                case 4 -> {
                    System.out.println("¡Gracias por usar el sistema!");
                    return; // Salir del programa
                }
                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }

    // Método genérico para inicio de sesión
    private static void iniciarSesion(Scanner scanner, String archivo, String tipoUsuario, Runnable menuUsuario) throws IOException {
        System.out.println("=== Inicio de sesión para " + tipoUsuario + " ===");
        crearArchivoSiNoExiste(archivo); // Crear archivo si no existe

        System.out.print("Usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        if (validarCredenciales(usuario, contraseña, archivo)) {
            System.out.println("¡Inicio de sesión exitoso como " + tipoUsuario + "!");
            menuUsuario.run(); // Ejecutar el menú correspondiente al usuario
        } else {
            System.out.println("Credenciales incorrectas. Intenta de nuevo.");
        }
    }

    // Validar credenciales contra el archivo
    private static boolean validarCredenciales(String usuario, String contraseña, String archivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":"); // Formato esperado: usuario:contraseña
                if (partes.length == 2 && partes[0].equals(usuario) && partes[1].equals(contraseña)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Crear archivo si no existe
    private static void crearArchivoSiNoExiste(String archivo) throws IOException {
        File file = new File(archivo);
        if (!file.exists()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
                // Agregar usuarios predeterminados
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

    // Menú Cliente
    private static void menuCliente() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        while (!salir) {
            System.out.println("=== Menú Cliente ===");
            System.out.println("1. Ver reservas");
            System.out.println("2. Realizar nueva reserva");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> System.out.println("Mostrando reservas...");
                case 2 -> System.out.println("Realizando nueva reserva...");
                case 3 -> {
                    System.out.println("Saliendo del menú cliente...");
                    salir = true; // Salir del bucle y regresar al menú principal
                }
                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }

    // Menú Administración
    private static void menuAdministracion() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        while (!salir) {
            System.out.println("=== Menú Administración ===");
            System.out.println("1. Gestionar usuarios");
            System.out.println("2. Configuración");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> System.out.println("Gestionando usuarios...");
                case 2 -> System.out.println("Accediendo a configuración...");
                case 3 -> {
                    System.out.println("Saliendo del menú administración...");
                    salir = true; // Salir del bucle y regresar al menú principal
                }
                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }

    // Menú Soporte
    private static void menuSoporte() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        while (!salir) {
            System.out.println("=== Menú Soporte ===");
            System.out.println("1. Ver incidentes");
            System.out.println("2. Escalar incidente");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> System.out.println("Mostrando incidentes...");
                case 2 -> System.out.println("Escalando incidente...");
                case 3 -> {
                    System.out.println("Saliendo del menú soporte...");
                    salir = true; // Salir del bucle y regresar al menú principal
                }
                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }
}
