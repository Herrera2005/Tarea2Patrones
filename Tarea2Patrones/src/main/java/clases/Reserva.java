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
