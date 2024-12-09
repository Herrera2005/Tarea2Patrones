/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tarea2patrones;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author herreranc
 */
public class Tarea2Patrones {
    private static final String CLIENTE_FILE = "cliente.txt";
    private static final String ADMIN_FILE = "administracion.txt";
    private static final String SOPORTE_FILE = "soporte.txt";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        inicializarArchivos();

        while (true) {
            System.out.println("=== Menú Principal ===");
            System.out.println("1. Ingresar como Cliente");
            System.out.println("2. Ingresar como Administración");
            System.out.println("3. Ingresar como Soporte");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    manejarLogin(CLIENTE_FILE, "Cliente");
                    break;
                case 2:
                    manejarLogin(ADMIN_FILE, "Administración");
                    break;
                case 3:
                    manejarLogin(SOPORTE_FILE, "Soporte");
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    return;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
            }
        }
    }

    private static void inicializarArchivos() throws IOException {
        crearArchivoSiNoExiste(CLIENTE_FILE);
        crearArchivoSiNoExiste(ADMIN_FILE);
        crearArchivoSiNoExiste(SOPORTE_FILE);
    }

    private static void crearArchivoSiNoExiste(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Creando archivo: " + fileName);
            file.createNewFile();
        }
    }

    private static void manejarLogin(String fileName, String tipoUsuario) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Inicio de sesión para " + tipoUsuario + " ===");
        boolean autenticado = false;

        while (!autenticado) {
            System.out.print("Usuario: ");
            String usuario = scanner.nextLine();

            System.out.print("Contraseña: ");
            String contrasena = scanner.nextLine();

            if (validarCredenciales(fileName, usuario, contrasena)) {
                System.out.println("¡Inicio de sesión exitoso como " + tipoUsuario + "!");
                mostrarMenuPorTipo(tipoUsuario);
                autenticado = true;
            } else {
                System.out.println("Credenciales incorrectas. Intenta de nuevo.");
            }
        }
    }

    private static boolean validarCredenciales(String fileName, String usuario, String contrasena) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length == 2 && partes[0].equals(usuario) && partes[1].equals(contrasena)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + fileName);
        }
        return false;
    }

    private static void mostrarMenuPorTipo(String tipoUsuario) {
        switch (tipoUsuario) {
            case "Cliente":
                menuCliente();
                break;
            case "Administración":
                menuAdministracion();
                break;
            case "Soporte":
                menuSoporte();
                break;
        }
    }

    private static void menuCliente() {
        System.out.println("=== Menú Cliente ===");
        System.out.println("1. Ver reservas");
        System.out.println("2. Realizar nueva reserva");
        System.out.println("3. Salir");
        // Implementar las opciones del menú cliente
    }

    private static void menuAdministracion() {
        System.out.println("=== Menú Administración ===");
        System.out.println("1. Gestionar usuarios");
        System.out.println("2. Gestionar vuelos o vehículos");
        System.out.println("3. Salir");
        // Implementar las opciones del menú administración
    }

    private static void menuSoporte() {
        System.out.println("=== Menú Soporte ===");
        System.out.println("1. Ver incidentes");
        System.out.println("2. Escalar incidentes");
        System.out.println("3. Salir");
        // Implementar las opciones del menú soporte
    }


}
