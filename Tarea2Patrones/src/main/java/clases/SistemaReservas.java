package clases;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import claseVehiculo.Vehiculo;
import claseVuelo.Vuelo;
import enums.EstadoPago;
import enums.EstadoReserva;

public class SistemaReservas {
    List<Reserva> reservas;

    public SistemaReservas(List<Vehiculo> vehiculos, List<Vuelo> vuelos, List<Cliente> clientes) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        // Fecha actual (para la reserva)
        Date fechaActual = new Date(); // Fecha de hoy
        reservas = new ArrayList<>();
        reservas.add(new Reserva(1,
                EstadoReserva.PENDIENTE,
                fechaActual,
                vehiculos.get(0),
                vuelos.get(0),
                new Pago(100, "Paypal", EstadoPago.PENDIENTE),
                clientes.get(0)));
        vehiculos.get(0).reservarVehiculo(reservas.get(0));
        vuelos.get(0).anadirPasajero(clientes.get(0));

        reservas.add(new Reserva(2,
                EstadoReserva.PENDIENTE,
                fechaActual,
                vehiculos.get(1),
                vuelos.get(1),
                new Pago(120, "Tarjeta de Crédito", EstadoPago.PENDIENTE),
                clientes.get(0)));
        vehiculos.get(1).reservarVehiculo(reservas.get(1));
        vuelos.get(1).anadirPasajero(clientes.get(0));

        reservas.add(new Reserva(3,
                EstadoReserva.PENDIENTE,
                fechaActual,
                vehiculos.get(2),
                vuelos.get(0),
                new Pago(150, "Paypal", EstadoPago.PENDIENTE),
                clientes.get(1)));
        vehiculos.get(2).reservarVehiculo(reservas.get(2));
        vuelos.get(0).anadirPasajero(clientes.get(1));
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public void mostrarReservas() {
        for (Reserva reserva : reservas) {
            System.out.println(reserva.toString());
        }
    }
    
}
