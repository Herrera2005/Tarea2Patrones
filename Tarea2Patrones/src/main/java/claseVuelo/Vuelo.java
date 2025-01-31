/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package claseVuelo;

import clases.Cliente;
import clases.Reserva;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Notificaciones.Notificacion;

/**
 *
 * @author herreranc
 */
public class Vuelo {
    private List<Cliente> pasajeros;
    private int idVuelo;
    private String aerolinea;
    private Date horaSalida;
    private Date horaLlegada;
    private int asientosDisponibles;
    private List<Object> clases;

    // para administracion
    public void cancelarVuelo(String mensaje){
        for (Cliente pasajero : pasajeros) {
            List<Reserva> reservas = pasajero.getReservas();
            for (Reserva reserva : reservas) {
                if(reserva.getVuelo().getIdVuelo() ==idVuelo){
                    reserva.cancelarReserva(mensaje);
                }
            }    
            // notificar a los pasajeros
            enviarNotificacionesPasajeros(mensaje, pasajero);
        }
    }

    private void enviarNotificacionesPasajeros(String mensaje, Cliente pasajero) {
        List<Notificacion> notificaciones = pasajero.getNotificaciones();
        for (Notificacion notificacion : notificaciones) {
            notificacion.notificar(mensaje, pasajero);                
        }
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public String getAerolinea() {
        return aerolinea;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public Date getHoraLlegada() {
        return horaLlegada;
    }

    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }

    public List<Object> getClases() {
        return clases;
    }

    public void anadirPasajero(Cliente pasajero){
        pasajeros.add(pasajero);
        asientosDisponibles -=1;
    }

    public Vuelo(int idVuelo, String aerolinea, Date horaSalida, Date horaLlegada, int asientosDisponibles, List<Object> clases) {
        this.idVuelo = idVuelo;
        this.aerolinea = aerolinea;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.asientosDisponibles = asientosDisponibles;
        this.clases = clases;
        this.pasajeros = new ArrayList<>();
        llenarAsientos();
    }

    public void llenarAsientos() {
        int idClase = 1;

        // Llenamos con asientos económicos
        for (int i = 0; i < asientosDisponibles / 2; i++) {
            clases.add(new AsientoEconomico(idClase++, 100)); // 100 es el precio del asiento económico
        }

        // Llenamos con asientos ejecutivos
        for (int i = 0; i < asientosDisponibles / 4; i++) {
            clases.add(new AsientoEjecutivo(idClase++, 250)); // 250 es el precio del asiento ejecutivo
        }

        // Llenamos con asientos de primera clase
        for (int i = 0; i < asientosDisponibles / 4; i++) {
            clases.add(new AsientoPrimeraClase(idClase++, 500)); // 500 es el precio del asiento de primera clase
        }
    }
    
    public boolean verificarDisponibilidad(){
        return asientosDisponibles>1;
    
    }
    
    public void bloquearAsientos(){
    
    }
    
    public void confirmarVuelo(){
    
    }

    @Override
    public String toString() {
        return idVuelo + "," +
               aerolinea + "," +
               horaSalida + "," +
               horaLlegada + "," +
               asientosDisponibles;
    }
}
