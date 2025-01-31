/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import Notificaciones.Notificacion;
import claseVehiculo.Vehiculo;
import claseVuelo.Vuelo;
import com.mycompany.tarea2patrones.MenuC;
import enums.EstadoPago;
import enums.EstadoReserva;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * @author herreranc
 */
public class Cliente extends Usuario {

    private List<Reserva> reservas;
    private List<Notificacion> notificaiones;


    public Cliente(int idCedula, String nombre, String email, String contrasenia) {
        super(idCedula, nombre, email, contrasenia);
        reservas = new ArrayList<>();
    }

    // Método para obtener una copia de la lista de reservas (encapsulación)
    public List<Reserva> obtenerReservas() {
        return new ArrayList<>(reservas); // Devuelve una copia para evitar modificaciones externas
    }

    // Método para agregar una reserva
    public void agregarReserva(Reserva reserva) {
        if (reservas == null) {
            reservas = new ArrayList<>();
        }
        reservas.add(reserva);
    }

    // Método para eliminar una reserva por ID
    public void eliminarReserva(int idReserva) {
        reservas.removeIf(reserva -> reserva.getIdReserva() == idReserva);
    }

    // Método para mostrar las reservas del cliente
    public void mostrarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("No tienes reservas.");
            return;
        }
        System.out.println("=== Tus Reservas ===");
        for (Reserva reserva : reservas) {
            System.out.println(reserva.toString());
        }
    }

    public void cancelarReserva() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Cancelar Reserva ===");
        mostrarReservas();
        if (reservas.isEmpty()) {
            return;
        }

        System.out.print("Introduce el ID de la reserva que deseas cancelar: ");
        int idReserva = scanner.nextInt();
        scanner.nextLine();

        boolean reservaEncontrada = reservas.stream()
                .anyMatch(reserva -> reserva.getIdReserva() == idReserva);

        if (reservaEncontrada) {
            eliminarReserva(idReserva);
            System.out.println("Reserva cancelada exitosamente.");
        } else {
            System.out.println("No se encontró una reserva con ese ID.");
        }
    }


    public List<Reserva> getReservas() {
        return reservas;
    }

    public void registrarse() {

    }

    public void realizarReservaPorConsola(List<Vehiculo> vehiculos, List<Vuelo> vuelos) {
        Vehiculo vehiculoSeleccionado = vehiculos.get(0);
        if (vehiculoSeleccionado == null || vehiculoSeleccionado.verificarDisponibilidad() == false) {
            System.out.println("Reserva cancelada: vehículo no encontrado.");
            return;
        }

        Vuelo vueloSeleccionado = vuelos.get(0);
        if (vueloSeleccionado == null) {
            System.out.println("Reserva cancelada: vuelo no encontrado.");
            return;
        }

        double montoPago = 16411;
        Reserva reserva = crearReserva(vehiculoSeleccionado, vueloSeleccionado, montoPago);

        confirmarReserva(reserva, montoPago);
    }

    // Método para seleccionar un vehículo
    private Vehiculo seleccionarVehiculo(List<Vehiculo> vehiculos) {
        System.out.println("=== Selecciona un vehículo ===");
        for (Vehiculo v : vehiculos) {
            System.out.println(v.toString());
        }
        System.out.print("Introduce el ID del vehículo: ");
        Scanner scanner = new Scanner(System.in);
        int idVehiculo = scanner.nextInt();
        scanner.nextLine();

        for (Vehiculo v : vehiculos) {
            if (v.getIdVehiculo() == idVehiculo) {
                return v;
            }
        }
        return null; // Si no se encuentra el vehículo
    }

    // Método para seleccionar un vuelo
    private Vuelo seleccionarVuelo(List<Vuelo> vuelos) {
        System.out.println("=== Selecciona un vuelo ===");
        for (Vuelo v : vuelos) {
            System.out.println(v.toString());
        }
        System.out.print("Introduce el ID del vuelo: ");
        Scanner scanner = new Scanner(System.in);
        int idVuelo = scanner.nextInt();
        scanner.nextLine();

        for (Vuelo v : vuelos) {
            if (v.getIdVuelo() == idVuelo) {
                return v;
            }
        }
        return null; // Si no se encuentra el vuelo
    }

    // Método para realizar el pago
    private double realizarPago() {
        Scanner scanner = new Scanner(System.in); // Se crea el scanner dentro del método
        double montoPago = -1;

        System.out.println("=== Realizar pago ===");

        while (montoPago <= 0) {
            try {
                System.out.print("Introduce el monto a pagar (por ejemplo, 100): ");
                montoPago = Double.parseDouble(scanner.nextLine());
                System.out.println(montoPago);

                if (montoPago <= 0) {
                    System.out.println("Error: El monto debe ser mayor a 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debes ingresar un número válido.");
            }
        }

        System.out.println("Pago registrado con éxito: $" + montoPago);
        scanner.close(); // Cerramos el scanner antes de salir del método
        return montoPago;
    }
    

    // Método para crear una reserva
    private Reserva crearReserva(Vehiculo vehiculo, Vuelo vuelo, double montoPago) {
        Random rd = new Random();
        LocalDate localDate = LocalDate.now();
        Pago pago = new Pago(montoPago, MetodoPago.TRANSFERENCIA, EstadoPago.CONFIRMADO);

        Reserva reserva = new Reserva(rd.nextInt(Integer.MAX_VALUE), EstadoReserva.PENDIENTE, Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()), vehiculo, vuelo, pago, this);

        reservas.add(reserva);
        this.agregarReserva(reserva); // Agregar la reserva a la lista del cliente
        return reserva;
    }

    // Método para confirmar la reserva
    private void confirmarReserva(Reserva reserva, double montoPago) {
        System.out.println("Reserva realizada exitosamente.");
        System.out.println("ID Reserva: " + reserva.getIdReserva());
        System.out.println("Cliente: " + this.getNombre());
        System.out.println("Vehículo seleccionado: " + reserva.getVehiculo().toString());
        System.out.println("Vuelo seleccionado: " + reserva.getVuelo().toString());
        System.out.println("Monto pagado: " + montoPago);
    }

    public List<Notificacion> getNotificaciones() {
        return notificaiones;
    }

    public void cargarReservas(File archivoCliente, Map<Integer, Reserva> todasLasReservas) {
        this.reservas = new ArrayList<>(); // Inicializar la lista de reservas
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoCliente))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith("Reservas:")) {
                    // Extraer los identificadores de reserva
                    String ids = linea.substring(linea.indexOf("{") + 1, linea.indexOf("}"));
                    String[] idArray = ids.split(",");
                    for (String id : idArray) {
                        int idReserva = Integer.parseInt(id.trim());
                        if (todasLasReservas.containsKey(idReserva)) {
                            System.out.println(idReserva);
                            this.reservas.add(todasLasReservas.get(idReserva));
                        }
                    }
                    break; // No es necesario continuar leyendo
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer las reservas del cliente: " + e.getMessage());
        }
    }





    public void addNotificacion(Notificacion notificacion) {
        if (this.notificaiones == null) {
            this.notificaiones = new ArrayList<>();
        }
        this.notificaiones.add(notificacion);
    }

    public int getId() {
        return super.getIdCedula(); // Si 'idCedula' está en Usuario, se accede con un getter
    }

}
