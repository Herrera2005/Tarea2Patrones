/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea2patrones;

import clases.Cliente;
import clases.Reserva;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author RUCO HOUSE
 */
public class Servicio {
    public void Iniciar(String CLIENTE_TXT,String ADMIN_TXT, String SOPORTE_TXT, String RESERVAS_TXT) throws IOException{
        Scanner scanner = new Scanner(System.in);

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
                case 1 -> iniciarSesionCliente(scanner, CLIENTE_TXT,RESERVAS_TXT);
                case 2 -> iniciarSesion(scanner, ADMIN_TXT, "Administración", MenuAd::menuAdministracion);
                case 3 -> iniciarSesion(scanner, SOPORTE_TXT, "Soporte", MenuSo::menuSoporte);
                case 4 -> {
                    System.out.println("¡Gracias por usar el sistema!");
                    return;
                }
                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }

    private static void iniciarSesion(Scanner scanner, String archivo, String tipoUsuario, Runnable menuUsuario) throws IOException {
        System.out.println("=== Inicio de sesión para " + tipoUsuario + " ===");
        Validaciones.crearArchivoSiNoExiste(archivo);

        System.out.print("Usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        if (Validaciones.validarCredenciales(usuario, contraseña, archivo)) {
            System.out.println("¡Inicio de sesión exitoso como " + tipoUsuario + "!");
            menuUsuario.run();
        } else {
            System.out.println("Credenciales incorrectas. Intenta de nuevo.");
        }
    }
    private static void iniciarSesionCliente(Scanner scanner, String archivoClientes,String RESERVAS_TXT) {
        System.out.println("=== Inicio de sesión para Cliente ===");
        System.out.print("Usuario (ID): ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Contraseña: ");
        String contrasenia = scanner.nextLine();

        Cliente cliente = null;
        File archivo = new File(archivoClientes);

        try {
            if (archivo.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                    String linea;
                    while ((linea = reader.readLine()) != null) {
                        String[] partes = linea.split(":");
                        if (partes.length == 4 && Integer.parseInt(partes[0]) == id && partes[3].equals(contrasenia)) {
                            cliente = new Cliente(id, partes[1], partes[2], partes[3]);
                            break;
                        }
                    }
                }

                if (cliente == null) {
                    System.out.println("ID o contraseña incorrectos. Intenta de nuevo.");
                    return;
                }

                File archivoCliente = new File("cliente_" + id + ".txt");
                if (!archivoCliente.exists()) {
                    System.out.println("Archivo del cliente no encontrado. Creando archivo...");
                    Validaciones.crearArchivoCliente(archivoCliente, cliente);
                } else {
                    System.out.println("Archivo del cliente encontrado. Leyendo datos...");
                    Map<Integer, Reserva> todasLasReservas = Validaciones.cargarArchivoReservas(RESERVAS_TXT, cliente);
                    cliente.cargarReservas(archivoCliente, todasLasReservas);
                }

                System.out.println("¡Inicio de sesión exitoso como Cliente!");
                MenuC.menuCliente(cliente);
            } else {
                System.out.println("Archivo de clientes no encontrado.");
            }
        } catch (IOException e) {
            System.out.println("Error al manejar el archivo: " + e.getMessage());
        }
    }

    

    
}
