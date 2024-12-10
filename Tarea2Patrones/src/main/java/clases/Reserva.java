/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import claseVehiculo.*;
import claseVuelo.*;
import enums.EstadoReserva;
import java.util.Date;

import Notificaciones.Notificacion;

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

    public Reserva(int idReserva, EstadoReserva estadoReserva, Date fechaReserva, Vehiculo vehiculo, Vuelo vuelo, Pago pago, Cliente cliente) {
        this.idReserva = idReserva;
        this.estadoReserva = estadoReserva;
        this.fechaReserva = fechaReserva;
        this.vehiculo = vehiculo;
        this.vuelo = vuelo;
        this.pago = pago;
        this.cliente = cliente;
    }

    public void asociarCliente(Cliente cliente ){
        this.cliente = cliente;
    }

    public int getIdReserva() {
        return idReserva;
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
    
    public void confirmarReserva(){
    
    }
    
    public void cancelarReserva(){
    
    }
    
    public void modificarReserva(String mensaje){


    for (Notificacion notificacion :cliente.getNotificaciones()) {
        notificacion.notificar(mensaje, cliente);
    }
    }
}
