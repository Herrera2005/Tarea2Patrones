/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import com.mycompany.tarea2patrones.Tarea2Patrones;

import Notificaciones.Notificacion;
import Observer.GestorReservas;
import Observer.ObservadorCambioReserva;
import Observer.ObservadorIncumplimiento;
import claseVehiculo.Vehiculo;
import claseVehiculo.VehiculoEconomico;
import claseVuelo.Vuelo;
import com.mycompany.tarea2patrones.MenuC;

import enums.EstadoPago;
import enums.EstadoReserva;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author herreranc
 */
public class Cliente extends Usuario{
    
    private List<Reserva> reservas;
    private List<Notificacion> notificaiones;


    public Cliente(int idCedula, String nombre, String email, String contrasenia) {
        super(idCedula, nombre, email, contrasenia);
        reservas = new ArrayList<>();
    }

    public List<Reserva> getReservas() {
        return reservas;
    }
    
    public void registrarse(){
    
    }
    
    public void realizarReservaPorConsola(List<Vehiculo> vehiculos,List<Vuelo> vuelos) {
        Random rd = new Random();
        Scanner scanner = new Scanner(System.in);
        // Selección de vehículo
        System.out.println("=== Selecciona un vehículo ===");
        for (Vehiculo v : vehiculos) {
            System.out.println(v.toString());
        }
        System.out.print("Introduce el ID del vehículo: ");
        int idVehiculo = scanner.nextInt();
        scanner.nextLine();

        Vehiculo vehiculoSeleccionado = null;
        for (Vehiculo v : vehiculos) {
            if (v.getIdVehiculo() == idVehiculo) {
                vehiculoSeleccionado = v;
                break;
            }
        }

        if (vehiculoSeleccionado == null) {
            System.out.println("Vehículo no encontrado.");
            return;
        }

        // Selección de vuelo
        System.out.println("=== Selecciona un vuelo ===");
        for (Vuelo v : vuelos) {
            System.out.println(v.toString());
        }
        System.out.print("Introduce el ID del vuelo: ");
        int idVuelo = scanner.nextInt();
        scanner.nextLine();

        Vuelo vueloSeleccionado = null;
        for (Vuelo v : vuelos) {
            if (v.getIdVuelo() == idVuelo) {
                vueloSeleccionado = v;
                break;
            }
        }

        if (vueloSeleccionado == null) {
            System.out.println("Vuelo no encontrado.");
            return;
        }

        // Realizar pago
        System.out.println("=== Realizar pago ===");
        System.out.print("Introduce el monto a pagar (por ejemplo, 100): ");
        double montoPago = scanner.nextDouble();
        scanner.nextLine();
        Pago pago = new Pago(montoPago, "Método de pago", EstadoPago.CONFIRMADO); // El pago es exitoso por ahora

        // Crear la reserva
        LocalDate localDate = LocalDate.now();
        Reserva reserva = new Reserva(
            rd.nextInt(Integer.MAX_VALUE), EstadoReserva.PENDIENTE, 
            Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()), 
            vehiculoSeleccionado, vueloSeleccionado, pago, this);  // Asociar el cliente con la reserva
        
        // Agregar la reserva a la lista
        reservas.add(reserva);
        this.agregarReserva(reserva);  // Agregar la reserva a la lista del cliente

        // Confirmación
        System.out.println("Reserva realizada exitosamente.");
        System.out.println("ID Reserva: " + reserva.getIdReserva());
        System.out.println("Cliente: " + this.getNombre());
        System.out.println("Vehículo seleccionado: " + vehiculoSeleccionado.toString());
        System.out.println("Vuelo seleccionado: " + vueloSeleccionado.toString());
        System.out.println("Monto pagado: " + montoPago);
    }

    public List<Notificacion> getNotificaciones(){
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
    public void agregarReserva(Reserva reserva) {
        if (reservas == null) {
            reservas = new ArrayList<>();
        }

        // Agregar la reserva a la lista del cliente
        reservas.add(reserva);

       
        
    }
   public void eliminarReserva(int idReserva) {
        reservas.removeIf(reserva -> reserva.getIdReserva() == idReserva);
    }

public static void cancelarReserva(Cliente cliente) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("=== Eliminar Reserva ===");
    MenuC.mostrarReservas(cliente);
    if (cliente.getReservas().isEmpty()) {
        return;
    }

    System.out.print("Introduce el ID de la reserva que deseas eliminar: ");
    int idReserva = scanner.nextInt();
    scanner.nextLine();

    Reserva reservaAEliminar = null;
    for (Reserva reserva : cliente.getReservas()) {
        if (reserva.getIdReserva() == idReserva) {
            reservaAEliminar = reserva;
            break;
        }
    }

    if (reservaAEliminar != null) {
        cliente.eliminarReserva(idReserva);
        System.out.println("Reserva eliminada exitosamente.");
    } else {
        System.out.println("No se encontró una reserva con ese ID.");
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
