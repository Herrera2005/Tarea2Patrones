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
import enums.EstadoPago;
import enums.EstadoReserva;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author usuario
 */

public class ReservaTest {
//Prueba Exitosa
    // Identificador: testConfirmarReservaExitosa
    // Propósito: Verificar que una reserva se confirme correctamente.
    // Precondiciones: El cliente, vuelo y pago deben estar correctamente inicializados.
    // Entradas: Reserva con estado PENDIENTE, cliente, vuelo, pago.
    // Salidas Esperadas: El estado de la reserva debe cambiar a CONFIRMADO.

    @Test
    public void testConfirmarReservaExitosa() {
        Cliente cliente = new Cliente(123456, "Juan Pérez", "juan@email.com", "password");
        Vehiculo vehiculo = new VehiculoEconomico(1, "Economico", "Proveedor A", true);
        List<Asiento> asientos = new ArrayList<>();
        Vuelo vuelo = new Vuelo(1, "Aerolínea A", new Date(), new Date(), 10, Collections.singletonList(asientos));
        Pago pago = new Pago(200.0, MetodoPago.PAYPAL, EstadoPago.PENDIENTE);
        Reserva reserva = new Reserva(1, EstadoReserva.PENDIENTE, new Date(), vehiculo, vuelo, pago, cliente);

        reserva.confirmarReserva();
        
        assertEquals(EstadoReserva.CONFIRMADO, reserva.getEstadoReserva());
    }
    
//Escenario de falla 1: Reserva ya confirmada
    // Identificador: testConfirmarReservaYaConfirmada
    // Propósito: Verificar que no se pueda confirmar una reserva que ya ha sido confirmada.
    // Precondiciones: La reserva debe estar en estado CONFIRMADO.
    // Entradas: Reserva con estado CONFIRMADO, cliente, vuelo, pago.
    // Salidas Esperadas: El sistema no debe permitir cambiar el estado a CONFIRMADO nuevamente.

    @Test
    public void testConfirmarReservaYaConfirmada() {
        Cliente cliente = new Cliente(123456, "Juan Pérez", "juan@email.com", "password");
        Vehiculo vehiculo = new VehiculoEconomico(1, "Economico", "Proveedor A", true);
        List<Asiento> asientos = new ArrayList<>();
        Vuelo vuelo = new Vuelo(1, "Aerolínea A", new Date(), new Date(), 10, Collections.singletonList(asientos));
        Pago pago = new Pago(200.0, MetodoPago.PAYPAL, EstadoPago.PENDIENTE);
        Reserva reserva = new Reserva(1, EstadoReserva.CONFIRMADO, new Date(), vehiculo, vuelo, pago, cliente);

        reserva.confirmarReserva();

        assertEquals(EstadoReserva.CONFIRMADO, reserva.getEstadoReserva());  // Debe seguir confirmado, no cambia
    }
    
//Escenario de falla 2: Falta de pago para confirmar
    // Identificador: testConfirmarReservaSinPago
    // Propósito: Verificar que no se pueda confirmar una reserva si no se ha realizado el pago.
    // Precondiciones: El pago debe estar en estado PENDIENTE o no completado.
    // Entradas: Reserva con estado PENDIENTE, pero sin pago.
    // Salidas Esperadas: El sistema debe lanzar una excepción indicando que el pago es necesario.

    @Test
    public void testConfirmarReservaSinPago() {
        Cliente cliente = new Cliente(123456, "Juan Pérez", "juan@email.com", "password");
        Vehiculo vehiculo = new VehiculoEconomico(1, "Economico", "Proveedor A", true);
        List<Asiento> asientos = new ArrayList<>();
        Vuelo vuelo = new Vuelo(1, "Aerolínea A", new Date(), new Date(), 10, Collections.singletonList(asientos));
        Pago pago = new Pago(0.0, MetodoPago.PAYPAL, EstadoPago.PENDIENTE);  // Pago no realizado
        Reserva reserva = new Reserva(1, EstadoReserva.PENDIENTE, new Date(), vehiculo, vuelo, pago, cliente);

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            reserva.confirmarReserva();
        });

        assertEquals("El pago debe estar confirmado antes de la reserva", exception.getMessage());
    }
    
/*Prueba exitosa*/
    // Identificador: testGetIdReservaExitosa
    // Propósito: Verificar que el método getIdReserva() devuelve correctamente el ID de la reserva.
    // Precondiciones: La reserva debe estar correctamente inicializada.
    // Entradas: ID de la reserva 1.
    // Salidas Esperadas: Se espera que devuelva el ID de la reserva correctamente.

    @Test
    public void testGetIdReservaExitosa() {
        Cliente cliente = new Cliente(123456, "Juan Pérez", "juan@email.com", "password");
        Vehiculo vehiculo = new VehiculoEconomico(1, "Economico", "Proveedor A", true);
        List<Asiento> asientos = new ArrayList<>();
        Vuelo vuelo = new Vuelo(1, "Aerolínea A", new Date(), new Date(), 10, Collections.singletonList(asientos));
        Pago pago = new Pago(0.0, MetodoPago.PAYPAL, EstadoPago.PENDIENTE);  // Pago no realizado
        Reserva reserva = new Reserva(1, EstadoReserva.PENDIENTE, new Date(), vehiculo, vuelo, pago, cliente);
        assertEquals(1, reserva.getIdReserva());
    }
    
//Escenario de falla: ID de reserva negativo
    // Identificador: testGetIdReservaConIdNegativo
    // Propósito: Verificar que el método getIdReserva() lance una excepción si el ID es negativo.
    // Precondiciones: La reserva debe estar correctamente inicializada con un ID negativo.
    // Entradas: ID de la reserva -1.
    // Salidas Esperadas: Se espera que lance una excepción debido al ID inválido.

    @Test
    public void testGetIdReservaConIdNegativo() {
        Cliente cliente = new Cliente(123456, "Juan Pérez", "juan@email.com", "password");
        Vehiculo vehiculo = new VehiculoEconomico(1, "Economico", "Proveedor A", true);
        List<Asiento> asientos = new ArrayList<>();
        Vuelo vuelo = new Vuelo(1, "Aerolínea A", new Date(), new Date(), 10, Collections.singletonList(asientos));
        Pago pago = new Pago(0.0,MetodoPago.PAYPAL, EstadoPago.PENDIENTE);  // Pago no realizado
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Reserva reserva = new Reserva(-1, EstadoReserva.PENDIENTE, new Date(), vehiculo, vuelo, pago, cliente);
        });
        assertEquals("El ID de la reserva no puede ser negativo", exception.getMessage());
    }
    
//Prueba exitosa
    // Identificador: testModificarReservaExitosa
    // Propósito: Verificar que el método modificarReserva() modifica correctamente una reserva.
    // Precondiciones: La reserva debe estar correctamente inicializada.
    // Entradas: Modificación con texto "Cambio en la reserva".
    // Salidas Esperadas: La reserva debe ser modificada correctamente.

    @Test
    public void testModificarReservaExitosa() {
        Cliente cliente = new Cliente(123456, "Juan Pérez", "juan@email.com", "password");
        Vehiculo vehiculo = new VehiculoEconomico(1, "Economico", "Proveedor A", true);
        List<Asiento> asientos = new ArrayList<>();
        Vuelo vuelo = new Vuelo(1, "Aerolínea A", new Date(), new Date(), 10, Collections.singletonList(asientos));
        Pago pago = new Pago(0.0, MetodoPago.PAYPAL, EstadoPago.PENDIENTE);  // Pago no realizado
        Reserva reserva = new Reserva(1, EstadoReserva.PENDIENTE, new Date(), vehiculo, vuelo, pago, cliente);
        reserva.modificarReserva("Cambio en la reserva");
        assertEquals("Cambio en la reserva", reserva.getEstadoReserva().toString());
    }
    
//Escenario de falla: Modificación con texto vacío
    // Identificador: testModificarReservaConTextoVacio
    // Propósito: Verificar que modificarReserva() falle si se intenta modificar con un texto vacío.
    // Precondiciones: La reserva debe estar correctamente inicializada.
    // Entradas: Modificación con texto vacío.
    // Salidas Esperadas: Se espera que lance una excepción debido al texto vacío.

    @Test
    public void testModificarReservaConTextoVacio() {
        Cliente cliente = new Cliente(123456, "Juan Pérez", "juan@email.com", "password");
        Vehiculo vehiculo = new VehiculoEconomico(1, "Economico", "Proveedor A", true);
        List<Asiento> asientos = new ArrayList<>();
        Vuelo vuelo = new Vuelo(1, "Aerolínea A", new Date(), new Date(), 10, Collections.singletonList(asientos));
        Pago pago = new Pago(0.0, MetodoPago.PAYPAL, EstadoPago.PENDIENTE);  // Pago no realizado
        Reserva reserva = new Reserva(1, EstadoReserva.PENDIENTE, new Date(), vehiculo, vuelo, pago, cliente);
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reserva.modificarReserva("");
        });

        assertEquals("El texto de modificación no puede estar vacío", exception.getMessage());
    }
    
//Prueba exitosa
    // Identificador: testCancelarReservaExitosa
    // Propósito: Verificar que el método cancelarReserva() cancele la reserva correctamente.
    // Precondiciones: Reserva debe estar correctamente inicializada.
    // Entradas: Reserva con estado PENDIENTE.
    // Salidas Esperadas: Se espera que la reserva se cancele y el estado se cambie.

    @Test
    public void testCancelarReservaExitosa() {
        Cliente cliente = new Cliente(123456, "Juan Pérez", "juan@email.com", "password");
        Vehiculo vehiculo = new VehiculoEconomico(1, "Economico", "Proveedor A", true);
        List<Asiento> asientos = new ArrayList<>();
        Vuelo vuelo = new Vuelo(1, "Aerolínea A", new Date(), new Date(), 10, Collections.singletonList(asientos));
        Pago pago = new Pago(0.0, MetodoPago.PAYPAL, EstadoPago.PENDIENTE);  // Pago no realizado
        Reserva reserva = new Reserva(1, EstadoReserva.PENDIENTE, new Date(), vehiculo, vuelo, pago, cliente);
        reserva.cancelarReserva("");
        assertEquals(EstadoReserva.CANCELADA, reserva.getEstadoReserva());
    }
    
//Escenario de falla: Intento de cancelar reserva ya cancelada
    // Identificador: testCancelarReservaYaCancelada
    // Propósito: Verificar que el método cancelarReserva() falle si la reserva ya está cancelada.
    // Precondiciones: Reserva debe estar en estado CANCELADA.
    // Entradas: Reserva con estado CANCELADA.
    // Salidas Esperadas: Se espera que lance una excepción debido al estado ya cancelado.

    @Test
    public void testCancelarReservaYaCancelada() {
        Cliente cliente = new Cliente(123456, "Juan Pérez", "juan@email.com", "password");
        Vehiculo vehiculo = new VehiculoEconomico(1, "Economico", "Proveedor A", true);
        List<Asiento> asientos = new ArrayList<>();
        Vuelo vuelo = new Vuelo(1, "Aerolínea A", new Date(), new Date(), 10, Collections.singletonList(asientos));
        Pago pago = new Pago(0.0, MetodoPago.PAYPAL, EstadoPago.PENDIENTE);  // Pago no realizado
        Reserva reserva = new Reserva(1, EstadoReserva.CANCELADA, new Date(), vehiculo, vuelo, pago, cliente);

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            reserva.cancelarReserva("");
        });

        assertEquals("La reserva ya ha sido cancelada", exception.getMessage());
    }
    
}