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
public class ObservadorIncumplimiento implements Observador {
    private Reserva reserva;

    public ObservadorIncumplimiento(Reserva reserva) {
        this.reserva = reserva;
    }

    @Override
    public void actualizar(Reserva reserva) {
        System.out.println("Se detectó un posible incumplimiento en la reserva con ID " + reserva.getIdReserva());
        // Lógica para registrar o procesar el incumplimiento
    }
}