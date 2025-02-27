/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import claseVehiculo.*;
import claseVuelo.*;
import enums.EstadoReserva;
import java.util.Date;
import java.util.List;

import Notificaciones.Notificacion;
import Notificaciones.NotificacionEmail;
import claseIncidente.Incidente;

/**
 *
 * @author herreranc
 */
public class Reserva {
    private int idReserva;
    private EstadoReserva estadoReserva;
    private Date fechaReserva;
    private Vehiculo vehiculo;
    private Pago pago;
    private Vuelo vuelo;
    private Cliente cliente;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Reserva(int idReserva, EstadoReserva estadoReserva, Date fechaReserva, Vehiculo vehiculo, Vuelo vuelo,
            Pago pago, Cliente cliente) {
        this.idReserva = idReserva;
        this.estadoReserva = estadoReserva;
        this.fechaReserva = fechaReserva;
        this.vehiculo = vehiculo;
        this.vuelo = vuelo;
        this.pago = pago;
        this.cliente = cliente;
    }

    public Reserva() {
        // TODO Auto-generated constructor stub
    }

    public Cliente getCliente() {
        return cliente;
    }

    // para administracion
    public void notificarCliente(String mensaje) {
        List<Notificacion> notificaciones = cliente.getNotificaciones();
        for (Notificacion notificacion : notificaciones) {
            notificacion.notificar(mensaje, cliente);
        }
    }

    public void asociarCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void quitarVehiculo(String mensaje) {
        vehiculo = null;
        notificarCliente(mensaje);
    }

    public EstadoReserva getEstadoReserva() {
        return estadoReserva;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Pago getPago() {
        return pago;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public void setEstadoReserva(EstadoReserva estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public void confirmarReserva() {
        estadoReserva =EstadoReserva.CONFIRMADO;
    }

    public void cancelarReserva(String mensaje) {
        estadoReserva = EstadoReserva.CANCELADO;
        vehiculo.cancelarVehiculo(mensaje);
    }

    public void crearIncidente() {
        new Incidente();
    }

    public void modificarReserva(String mensaje) {
        Notificacion notificacion = new NotificacionEmail("mensaje");
        cliente.addNotificacion(notificacion);
        cliente.addNotificacion(notificacion);
        for (Notificacion notificacio : cliente.getNotificaciones()) {
            notificacio.notificar(mensaje, cliente);
        }
    }

    @Override
public String toString() {
    return "Reserva ID: " + idReserva + "\n" +
           "Estado: " + estadoReserva + "\n" +
           "Fecha de Reserva: " + fechaReserva + "\n" +
           "Cliente: " + cliente.getNombre() + "\n" +
           "Vehículo: " + vehiculo.getTipoVehiculo() + " - " + vehiculo.getProveedor() + "\n" +
           "Vuelo: " + vuelo.getIdVuelo() + " - Aerolínea: " + vuelo.getAerolinea() + "\n" +
           "Pago: " + pago.getMonto() + " - Estado: " + pago.getEstadoPago() + "\n";
}

    
}
