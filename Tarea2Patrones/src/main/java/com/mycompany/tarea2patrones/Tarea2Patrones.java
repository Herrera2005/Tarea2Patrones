/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
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
    private static final String RESERVAS_TXT = "reservas.txt";

    public static void main(String[] args) throws IOException {
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
                case 1 -> iniciarSesionCliente(scanner, CLIENTE_TXT);
                case 2 -> iniciarSesion(scanner, ADMIN_TXT, "Administración", Tarea2Patrones::menuAdministracion);
                case 3 -> iniciarSesion(scanner, SOPORTE_TXT, "Soporte", Tarea2Patrones::menuSoporte);
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
        crearArchivoSiNoExiste(archivo);

        System.out.print("Usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        if (validarCredenciales(usuario, contraseña, archivo)) {
            System.out.println("¡Inicio de sesión exitoso como " + tipoUsuario + "!");
            menuUsuario.run();
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

                File archivoCliente = new File("cliente_" + id + ".txt");
                if (!archivoCliente.exists()) {
                    System.out.println("Archivo del cliente no encontrado. Creando archivo...");
                    crearArchivoCliente(archivoCliente, cliente);
                } else {
                    System.out.println("Archivo del cliente encontrado. Leyendo datos...");
                    Map<Integer, Reserva> todasLasReservas = cargarArchivoReservas(RESERVAS_TXT, cliente);
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
            writer.write("Reservas:{}");
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al crear el archivo del cliente: " + e.getMessage());
        }
    }

    private static boolean validarCredenciales(String usuario, String contraseña, String archivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length == 2 && partes[0].equals(usuario) && partes[1].equals(contraseña)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void crearArchivoSiNoExiste(String archivo) throws IOException {
        File file = new File(archivo);
        if (!file.exists()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
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

    private static void menuCliente(Cliente cliente) {
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
                case 2 -> crearReserva(cliente);
                case 3 -> Cliente.cancelarReserva(cliente);
                case 4 -> {
                    System.out.println("Saliendo del menú cliente...");
                    salir = true;
                }
                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }
   public static void eliminarReservaDeArchivoGeneral(int idReserva) {
        File archivoReservas = new File(RESERVAS_TXT);
        List<String> lineasReservasActualizadas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(archivoReservas))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Verifica si la línea NO corresponde al ID de la reserva a eliminar
                if (!linea.startsWith(idReserva + ":")) {
                    lineasReservasActualizadas.add(linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de reservas: " + e.getMessage());
        }

        // Escribe las líneas actualizadas nuevamente en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoReservas))) {
            for (String linea : lineasReservasActualizadas) {
                writer.write(linea);
                writer.newLine();
            }
            System.out.println("Archivo de reservas actualizado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al actualizar el archivo de reservas: " + e.getMessage());
        }
    }
   public static void actualizarReservasCliente(Cliente cliente, int idReserva) {
        File archivoCliente = new File("cliente_" + cliente.getIdCedula() + ".txt");
        List<String> lineasActualizadas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(archivoCliente))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith("Reservas:{")) {
                    // Extraer la lista de IDs y eliminar el ID específico
                    String reservasExistentes = linea.substring(9, linea.length() - 1).trim(); // Extraer contenido entre {}
                    List<String> idsReservas = new ArrayList<>(Arrays.asList(reservasExistentes.split(", ")));
                    idsReservas.remove(String.valueOf(idReserva)); // Eliminar el ID de la reserva
                    lineasActualizadas.add("Reservas:{" + String.join(", ", idsReservas) + "}");
                } else {
                    lineasActualizadas.add(linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo del cliente: " + e.getMessage());
        }

        // Escribir las líneas actualizadas nuevamente en el archivo del cliente
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoCliente))) {
            for (String linea : lineasActualizadas) {
                writer.write(linea);
                writer.newLine();
            }
            System.out.println("Archivo del cliente actualizado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al actualizar el archivo del cliente: " + e.getMessage());
        }
    }
    private static void crearReserva(Cliente cliente) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce el ID de la reserva: ");
        int idReserva = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Introduce la fecha de la reserva (YYYY-MM-DD): ");
        Date fechaReserva = Date.valueOf(scanner.nextLine());

        System.out.println("Selecciona un vehículo (ID): ");
        int idVehiculo = scanner.nextInt();
        scanner.nextLine();
        Vehiculo vehiculo = new VehiculoEconomico(idVehiculo, "MarcaX", "ModeloY", true);

        System.out.println("Introduce los detalles del vuelo: ");
        Vuelo vuelo = new Vuelo(1, "AerolineaX", fechaReserva, fechaReserva, 100, new ArrayList<>());

        System.out.println("Introduce el monto del pago: ");
        double monto = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Introduce el método de pago: ");
        String metodoPago = scanner.nextLine();
        Pago pago = new Pago(monto, metodoPago, EstadoPago.PENDIENTE);

        Reserva nuevaReserva = new ReservaBuilder()
                .setIdReserva(idReserva)
                .setEstadoReserva(EstadoReserva.PENDIENTE)
                .setFechaReserva(fechaReserva)
                .setVehiculo(vehiculo)
                .setVuelo(vuelo)
                .setPago(pago)
                .setCliente(cliente)
                .build();

        cliente.agregarReserva(nuevaReserva); // Método para agregar la reserva al cliente
        actualizarArchivosReservas(nuevaReserva,cliente);
        System.out.println("Reserva creada exitosamente con ID: " + idReserva);
    }
    
    private static void actualizarArchivosReservas(Reserva reserva, Cliente cliente) {
        try {
            // Actualizar el archivo general de reservas
            File reservasFile = new File(RESERVAS_TXT);
            if (!reservasFile.exists()) {
                reservasFile.createNewFile();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(reservasFile, true))) {
                writer.write(reserva.toString());
                writer.newLine(); // Agrega un salto de línea
            }

            // Actualizar el archivo del cliente
            File clienteFile = new File("cliente_" + cliente.getIdCedula() + ".txt");
            if (clienteFile.exists()) {
                List<String> lineasArchivo = new ArrayList<>();
                boolean reservasEncontradas = false;

                try (BufferedReader reader = new BufferedReader(new FileReader(clienteFile))) {
                    String linea;
                    while ((linea = reader.readLine()) != null) {
                        if (linea.startsWith("Reservas:{")) {
                            reservasEncontradas = true;
                            String reservasExistentes = linea.substring(9, linea.length() - 1).trim(); // Extraer contenido entre {}
                            if (!reservasExistentes.isEmpty()) {
                                reservasExistentes += ", " + reserva.getIdReserva();
                            } else {
                                reservasExistentes = String.valueOf(reserva.getIdReserva());
                            }
                            lineasArchivo.add("Reservas:" + reservasExistentes + "}"); // Línea corregida
                        } else {
                            lineasArchivo.add(linea);
                        }
                    }
                }

                // Si no se encontró la línea de reservas, agregarla al final
                if (!reservasEncontradas) {
                    lineasArchivo.add("Reservas:{" + reserva.getIdReserva() + "}");
                }

                // Escribir el archivo actualizado
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(clienteFile))) {
                    for (String linea : lineasArchivo) {
                        writer.write(linea);
                        writer.newLine();
                    }
                }
            } else {
                System.out.println("Archivo del cliente no encontrado. No se pudo actualizar.");
            }
        } catch (IOException e) {
            System.out.println("Error al actualizar los archivos: " + e.getMessage());
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
            scanner.nextLine();

            switch (opcion) {
                case 1 -> System.out.println("Gestionando usuarios...");
                case 2 -> System.out.println("Accediendo a configuración...");
                case 3 -> {
                    System.out.println("Saliendo del menú administración...");
                    salir = true;
                }
                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }

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
            scanner.nextLine();

            switch (opcion) {
                case 1 -> System.out.println("Mostrando incidentes...");
                case 2 -> System.out.println("Escalando incidente...");
                case 3 -> {
                    System.out.println("Saliendo del menú soporte...");
                    salir = true;
                }
                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }
    private static Map<Integer, Reserva> cargarArchivoReservas(String archivoReservas, Cliente cliente) {
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

                    // Datos de Vehículo
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

                    // Crear y almacenar Reserva usando el Builder
                    Reserva reserva = new ReservaBuilder()
                            .setIdReserva(idReserva)
                            .setEstadoReserva(estado)
                            .setFechaReserva(fechaReserva)
                            .setVehiculo(vehiculo)
                            .setVuelo(vuelo)
                            .setPago(pago)
                            .setCliente(cliente)
                            .build();

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
