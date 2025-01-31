/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesTest;

import claseVehiculo.Vehiculo;
import claseVehiculo.VehiculoEconomico;
import claseVuelo.Asiento;
import claseVuelo.Vuelo;
import clases.Cliente;
import clases.MetodoPago;
import clases.Pago;
import clases.Reserva;
import clases.SistemaReservas;
import enums.EstadoPago;
import enums.EstadoReserva;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author usuario
 */

public class SistemaReservasTest {
//Prueba exitosa
    // Identificador: testAgregarReservaExitosa
    // Propósito: Verificar que el método agregarReserva() agregue correctamente una reserva al sistema.
    // Precondiciones: El sistema debe estar correctamente inicializado.
    // Entradas: Reserva con ID 1.
    // Salidas Esperadas: Se espera que la reserva se agregue correctamente al sistema.

    @Test
    public void testAgregarReservaExitosa() {
        SistemaReservas sistema= new SistemaReservas();
        Cliente cliente = new Cliente(123456, "Juan Pérez", "juan@email.com", "password");
        Vehiculo vehiculo = new VehiculoEconomico(1, "Economico", "Proveedor A", true);
        List<Asiento> asientos = new ArrayList<>();
        Vuelo vuelo = new Vuelo(1, "Aerolínea A", new Date(), new Date(), 10, Collections.singletonList(asientos));
        Pago pago = new Pago(0.0, MetodoPago.PAYPAL, EstadoPago.PENDIENTE);  // Pago no realizado
        Reserva reserva = new Reserva(1, EstadoReserva.PENDIENTE, new Date(), vehiculo, vuelo, pago, cliente);
        sistema.addReserva(reserva);
        assertEquals(1, sistema.getReservas().size());
    }
    
//Escenario de falla: Reserva nula
    // Identificador: testAgregarReservaConReservaNula
    // Propósito: Verificar que agregarReserva() falle si la reserva es nula.
    // Precondiciones: Reserva nula.
    // Entradas: Reserva nula.
    // Salidas Esperadas: Se espera que lance una excepción debido a la reserva nula.

    @Test
    public void testAgregarReservaConReservaNula() {
        SistemaReservas sistema = new SistemaReservas();

        Exception exception = assertThrows(NullPointerException.class, () -> {
            sistema.addReserva(null);
        });

        assertEquals("La reserva no puede ser nula", exception.getMessage());
    }
}