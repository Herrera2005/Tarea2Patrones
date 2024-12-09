/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Observer;

import clases.Reserva;

/**
 *
 * @author usuario
 */
public class ObservadorCambioReserva implements Observador{
    private Reserva reserva;

    public ObservadorCambioReserva(Reserva reserva) {
        this.reserva = reserva;
    }
    
    @Override
    public void actualizar(Reserva reserva) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
