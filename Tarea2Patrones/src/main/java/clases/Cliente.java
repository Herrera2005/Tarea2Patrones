/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.util.ArrayList;
import java.util.List;

import Notificaciones.Notificacion;

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
    
    public void registrarse(){
    
    }
    
    public Reserva realizarReserva(){
        return null;
    }
    
    public void cancelarReserva(Reserva reserva){
    }

    public List<Notificacion> getNotificaciones(){
        return notificaiones;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public List<Notificacion> getNotificaiones() {
        return notificaiones;
    }

    
    
}
