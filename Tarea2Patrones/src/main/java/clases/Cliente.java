/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author herreranc
 */
public class Cliente extends Usuario{
    
    private List<Reserva> reservas;

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
    
    @Override
    public void actualizar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
