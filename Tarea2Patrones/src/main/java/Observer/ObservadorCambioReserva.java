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
public class ObservadorCambioReserva implements Observador {
    private Reserva reserva;

    public ObservadorCambioReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    @Override
    public void actualizar(Reserva reserva) {
        System.out.println("La reserva con ID " + reserva.getIdReserva() + " ha sido modificada.");
        // Aquí puedes añadir lógica adicional como guardar el cambio en un log
    }
}