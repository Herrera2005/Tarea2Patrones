/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import claseVehiculo.Vehiculo;
import claseVuelo.Vuelo;
import enums.EstadoReserva;
import java.util.Date;

/**
 *
 * @author RUCO HOUSE
 */
public class ReservaBuilder {
    private int idReserva;
    private EstadoReserva estadoReserva;
    private Date fechaReserva;
    private Vehiculo vehiculo;
    private Pago pago;
    private Vuelo vuelo;
    private Cliente cliente;

    public ReservaBuilder setIdReserva(int idReserva) {
        this.idReserva = idReserva;
        return this;
    }

    public ReservaBuilder setEstadoReserva(EstadoReserva estadoReserva) {
        this.estadoReserva = estadoReserva;
        return this;
    }

    public ReservaBuilder setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
        return this;
    }

    public ReservaBuilder setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        return this;
    }

    public ReservaBuilder setPago(Pago pago) {
        this.pago = pago;
        return this;
    }

    public ReservaBuilder setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
        return this;
    }

    public ReservaBuilder setCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public Reserva build() {
        Reserva reserva = new Reserva(); // Llama al constructor con acceso de paquete
        reserva.setIdReserva(this.idReserva); // Asigna los valores usando setters
        reserva.setEstadoReserva(this.estadoReserva);
        reserva.setFechaReserva(this.fechaReserva);
        reserva.setVehiculo(this.vehiculo);
        reserva.setPago(this.pago);
        reserva.setVuelo(this.vuelo);
        reserva.setCliente(this.cliente);
        return reserva;
    }
}