/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tarea2patrones;

import claseVehiculo.Vehiculo;
import claseVehiculo.VehiculoEconomico;
import claseVuelo.Vuelo;
import clases.Cliente;
import clases.Pago;
import clases.Reserva;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                case 1 -> iniciarSesionCliente(scanner, CLIENTE_TXT);
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
    private static void iniciarSesionCliente(Scanner scanner, String archivoClientes) {
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

            // Verificar si existe un archivo específico del cliente
            File archivoCliente = new File("cliente_" + id + ".txt");
            if (!archivoCliente.exists()) {
                System.out.println("Archivo del cliente no encontrado. Creando archivo...");
                crearArchivoCliente(archivoCliente, cliente);
            } else {
                System.out.println("Archivo del cliente encontrado. Leyendo datos...");
                Map<Integer, Reserva> todasLasReservas = cargarArchivoReservas("reservas.txt");
                cliente.cargarReservas(archivoCliente, todasLasReservas);
            }

            System.out.println("¡Inicio de sesión exitoso como Cliente!");
            menuCliente(cliente);
        } else {
            System.out.println("Archivo de clientes no encontrado.");
        }
    } catch (IOException e) {
        System.out.println("Error al manejar el archivo: " + e.getMessage());
    }
}
        private static void crearArchivoCliente(File archivoCliente, Cliente cliente) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoCliente))) {
            writer.write("ID: " + cliente.getIdCedula());
            writer.newLine();
            writer.write("Nombre: " + cliente.getNombre());
            writer.newLine();
            writer.write("Email: " + cliente.getEmail());
            writer.newLine();
            writer.write("Contrasena: " + cliente.getContrasenia());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al crear el archivo del cliente: " + e.getMessage());
        }
    }
        private static Cliente leerArchivoCliente(File archivoCliente, Cliente cliente) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivoCliente))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    if (linea.startsWith("ID:")) {
                        cliente.setIdCedula(Integer.parseInt(linea.split(":")[1].trim()));
                    } else if (linea.startsWith("Nombre:")) {
                        cliente.setNombre(linea.split(":")[1].trim());
                    } else if (linea.startsWith("Email:")) {
                        cliente.setEmail(linea.split(":")[1].trim());
                    } else if (linea.startsWith("Contrasena:")) {
                        cliente.setContrasenia(linea.split(":")[1].trim());
                    }
                }
            } catch (IOException e) {
                System.out.println("Error al leer el archivo del cliente: " + e.getMessage());
            }

            return cliente;
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
    private static void menuCliente(Cliente cliente) {
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
                case 1 -> mostrarReservas(cliente); // Mostrar reservas del cliente
                case 2 -> System.out.println("Realizando nueva reserva..."); // Implementar lógica para realizar reserva
                case 3 -> {
                    System.out.println("Saliendo del menú cliente...");
                    salir = true; // Salir del bucle y regresar al menú principal
                }
                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }
    private static void mostrarReservas(Cliente cliente) {
        Scanner scanner = new Scanner(System.in);
        List<Reserva> reservas = cliente.getReservas();

        System.out.println("=== Tus Reservas ===");
        if (reservas.isEmpty()) {
            System.out.println("No tienes reservas registradas.");
            return;
        }

        for (int i = 0; i < reservas.size(); i++) {
            Reserva reserva = reservas.get(i);
            System.out.println((i + 1) + ". ID Reserva: " + reserva.getIdReserva());
            System.out.println("   Estado: " + reserva.getEstadoReserva());
            System.out.println("   Fecha: " + reserva.getFechaReserva());
            System.out.println("   Vehículo: " + reserva.getVehiculo().getClass().getSimpleName() + " - ID: " + reserva.getVehiculo().getIdVehiculo());
            System.out.println("   Vuelo: " + reserva.getVuelo().getAerolinea() + " - ID Vuelo: " + reserva.getVuelo().getIdVuelo());
            System.out.println("   Pago: " + reserva.getPago().getMonto() + " (" + reserva.getPago().getMetodoPago() + ")");
            System.out.println("--------------------");
        }

        System.out.print("Selecciona una reserva para gestionar (0 para salir): ");
        int seleccion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        if (seleccion > 0 && seleccion <= reservas.size()) {
            Reserva reservaSeleccionada = reservas.get(seleccion - 1);
            System.out.println("=== Gestión de Reserva ID: " + reservaSeleccionada.getIdReserva() + " ===");
            System.out.println("1. Confirmar");
            System.out.println("2. Cancelar");
            System.out.println("3. Modificar");
            System.out.println("4. Regresar");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> {
                    reservaSeleccionada.confirmarReserva();
                    System.out.println("Reserva confirmada.");
                }
                case 2 -> {
                    reservaSeleccionada.cancelarReserva();
                    System.out.println("Reserva cancelada.");
                }
                case 3 -> {
                    reservaSeleccionada.modificarReserva();
                    System.out.println("Reserva modificada.");
                }
                case 4 -> System.out.println("Regresando al menú anterior.");
                default -> System.out.println("Opción no válida.");
            }
        } else if (seleccion != 0) {
            System.out.println("Selección inválida.");
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
    private static Map<Integer, Reserva> cargarArchivoReservas(String archivoReservas) {
        Map<Integer, Reserva> reservas = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(archivoReservas))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length == 6) {
                    // Datos de Reserva
                    int idReserva = Integer.parseInt(partes[0].trim());
                    EstadoReserva estado = EstadoReserva.valueOf(partes[1].trim());
                    Date fechaReserva = Date.valueOf(partes[2].trim());

                    // Datos de Vehiculo
                    String[] datosVehiculo = partes[3].split(",");
                    Vehiculo vehiculo = new VehiculoEconomico(
                            Integer.parseInt(datosVehiculo[0].trim()),
                            datosVehiculo[1].trim(),
                            datosVehiculo[2].trim(),
                            Boolean.parseBoolean(datosVehiculo[3].trim())
                    );

                    // Datos de Vuelo
                    String[] datosVuelo = partes[4].split(",");
                    Vuelo vuelo = new Vuelo(
                            Integer.parseInt(datosVuelo[0].trim()),
                            datosVuelo[1].trim(),
                            Date.valueOf(datosVuelo[2].trim()),
                            Date.valueOf(datosVuelo[3].trim()),
                            Integer.parseInt(datosVuelo[4].trim()),
                            new ArrayList<>()
                    );

                    // Datos de Pago
                    String[] datosPago = partes[5].split(",");
                    EstadoPago estadoPago = EstadoPago.valueOf(datosPago[2].trim()); // Validar estado del pago
                    Pago pago = new Pago(
                            Double.parseDouble(datosPago[0].trim()),
                            datosPago[1].trim(),
                            estadoPago
                    );

                    // Crear y almacenar Reserva
                    Reserva reserva = new Reserva(idReserva, estado, fechaReserva, vehiculo, vuelo, pago);
                    reservas.put(idReserva, reserva);
                }
            }

        } catch (IOException e) {
            System.out.println("Error al cargar el archivo de reservas: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error en el formato de las reservas o valores no válidos: " + e.getMessage());
        }

        return reservas;
    }
}
