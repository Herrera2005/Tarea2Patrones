/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesTest;
import clases.MetodoPago;
import clases.Pago;
import enums.EstadoPago;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author usuario
 */

public class PagoTest {
//Prueba exitosa
    // Identificador: testPagoCreacionExitosa
    // Propósito: Verificar la creación de un pago con valores válidos.
    // Precondiciones: La clase Pago debe estar implementada correctamente.
    // Entradas: 150.0 (monto), "Tarjeta de Crédito" (método de pago), EstadoPago.CONFIRMADO (estado).
    // Salidas Esperadas: El monto debe ser 150.0, el método de pago "Tarjeta de Crédito" y el estado debe ser "CONFIRMADO".

    @Test
    public void testPagoCreacionExitosa() {
        Pago pago = new Pago(150.0, MetodoPago.TARJETA, EstadoPago.CONFIRMADO);

        assertEquals(150.0, pago.getMonto());
        assertEquals("Tarjeta de Crédito", pago.getMetodoPago());
        assertEquals(EstadoPago.CONFIRMADO, pago.getEstadoPago());
    }
   
//Escenario de falla 1: Pago con monto negativo
    // Identificador: testPagoConMontoNegativo
    // Propósito: Verificar la creación de un pago con monto negativo, lo cual debe fallar.
    // Precondiciones: La clase Pago debe validar el monto antes de la creación.
    // Entradas: -50.0 (monto), "Paypal" (método de pago), EstadoPago.PENDIENTE (estado).
    // Salidas Esperadas: Se espera que el monto no pueda ser negativo, el sistema debe lanzar una excepción.

    @Test
    public void testPagoConMontoNegativo() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Pago(-50.0, MetodoPago.PAYPAL, EstadoPago.PENDIENTE);
        });

        assertEquals("El monto no puede ser negativo", exception.getMessage());
    }
    
//Escenario de falla 2: Método de pago no válido
    // Identificador: testPagoConMetodoInvalido
    // Propósito: Verificar la creación de un pago con un método de pago no válido.
    // Precondiciones: La clase Pago debe validar el método de pago.
    // Entradas: 100.0 (monto), "Método Invalido" (método de pago), EstadoPago.PENDIENTE (estado).
    // Salidas Esperadas: El sistema debe lanzar una excepción para métodos de pago no válidos.

    @Test
    public void testPagoConMetodoInvalido() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Pago(100.0, MetodoPago.EFECTIVO, EstadoPago.PENDIENTE);
        });

        assertEquals("Método de pago no válido", exception.getMessage());
    }
}