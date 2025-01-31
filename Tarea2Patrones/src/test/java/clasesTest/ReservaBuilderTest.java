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
import clases.ReservaBuilder;
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

public class ReservaBuilderTest {
//Prueba exitosa
    // Identificador: testBuildReservaExitosa
    // Propósito: Verificar la correcta construcción de una reserva.
    // Precondiciones: Debe haberse configurado correctamente el builder con valores válidos.
    // Entradas: Se configura el builder con un cliente, vuelo, vehículo, etc.
    // Salidas Esperadas: Se espera que el builder cree una reserva con los datos asignados.

    @Test
    public void testBuildReservaExitosa() {
        Cliente cliente = new Cliente(123456, "Juan Pérez", "juan@email.com", "password");
        Vehiculo vehiculo = new VehiculoEconomico(1, "Economico", "Proveedor A", true);
        List<Asiento> asientos = new ArrayList<>();
        Vuelo vuelo = new Vuelo(1, "Aerolínea A", new Date(), new Date(), 10, Collections.singletonList(asientos));
        Pago pago = new Pago(200.0, MetodoPago.PAYPAL, EstadoPago.CONFIRMADO);

        Reserva reserva = new ReservaBuilder()
                .setIdReserva(1)
                .setEstadoReserva(EstadoReserva.CONFIRMADO)
                .setFechaReserva(new Date())
                .setVehiculo(vehiculo)
                .setPago(pago)
                .setVuelo(vuelo)
                .setCliente(cliente)
                .build();

        assertEquals(1, reserva.getIdReserva());
        assertEquals(EstadoReserva.CONFIRMADO, reserva.getEstadoReserva());
    }
    
// Escenario de falla 1: Faltan datos obligatorios
    // Identificador: testBuildReservaFaltandoDatos
    // Propósito: Verificar que el builder falle si no se configuran todos los datos requeridos.
    // Precondiciones: El builder debe configurarse con valores incompletos.
    // Entradas: El builder sin configurar algunos campos (p.ej., sin cliente).
    // Salidas Esperadas: Se espera que lance una excepción debido a que faltan datos importantes.

    @Test
    public void testBuildReservaFaltandoDatos() {
        Cliente cliente = new Cliente(123456, "Juan Pérez", "juan@email.com", "password");
        Vehiculo vehiculo = new VehiculoEconomico(1, "Economico", "Proveedor A", true);
        List<Asiento> asientos = new ArrayList<>();
        Vuelo vuelo = new Vuelo(1, "Aerolínea A", new Date(), new Date(), 10, Collections.singletonList(asientos));
        Pago pago = new Pago(200.0, MetodoPago.PAYPAL, EstadoPago.CONFIRMADO);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            new ReservaBuilder()
                .setIdReserva(1)
                .setEstadoReserva(EstadoReserva.CONFIRMADO)
                .setFechaReserva(new Date())
                .setVehiculo(vehiculo)
                .setPago(pago)
                .setVuelo(vuelo)
                .build();
        });

        assertEquals("El cliente es obligatorio", exception.getMessage());
    }
}