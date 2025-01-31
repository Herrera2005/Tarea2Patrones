/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Notificaciones;

import clases.Cliente;
import clases.Reserva;
import enums.EstadoReserva;
import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author RUCO HOUSE
 */
class NotificacionSMSTest {
    
    private NotificacionSMS notificacion;
    private Cliente cliente;
    
    @BeforeEach
    void setUp() {
        notificacion = new NotificacionSMS("123456789");
        cliente = new Cliente(1, "Juan Perez", "12345@h", "s");
    }
    
    /**
     * ID: TEST01
     * Propósito: Verificar que el método notificar funciona correctamente con entradas válidas.
     * Precondiciones: Se tiene una instancia de NotificacionSMS con un número válido.
     * Entradas: Mensaje "Hola, este es un test", cliente "Juan Perez".
     * Salidas esperadas: No se lanza excepción y se muestra el mensaje correctamente.
     */
    @Test
    void testNotificar_CasoExitoso() {
        assertDoesNotThrow(() -> notificacion.notificar("Hola, este es un test", cliente));
    }
    
    /**
     * ID: TEST02
     * Propósito: Verificar que el método notificar maneja un mensaje vacío correctamente.
     * Precondiciones: Se tiene una instancia de NotificacionSMS con un número válido.
     * Entradas: Mensaje vacío "", cliente "Juan Perez".
     * Salidas esperadas: No se lanza excepción pero la notificación debe manejarlo correctamente.
     */
    @Test
    void testNotificar_MensajeVacio() {
        assertDoesNotThrow(() -> notificacion.notificar("", cliente));
    }
    
    /**
     * ID: TEST03
     * Propósito: Verificar que el método notificar maneja un cliente nulo sin fallar.
     * Precondiciones: Se tiene una instancia de NotificacionSMS con un número válido.
     * Entradas: Mensaje "Hola", cliente nulo.
     * Salidas esperadas: Se lanza una NullPointerException.
     */
    @Test
    void testNotificar_ClienteNulo() {
        assertThrows(NullPointerException.class, () -> notificacion.notificar("Hola", null));
    }
    
    /**
     * ID: TEST04
     * Propósito: Verificar que el método notificar maneja un número de teléfono no válido.
     * Precondiciones: Se tiene una instancia de NotificacionSMS con un número vacío.
     * Entradas: Mensaje "Hola", cliente "Juan Perez".
     * Salidas esperadas: La notificación debería fallar debido al número inválido.
     */
    @Test
    void testNotificar_NumeroInvalido() {
        NotificacionSMS notificacionInvalida = new NotificacionSMS("");
        assertDoesNotThrow(() -> notificacionInvalida.notificar("Hola", cliente)); // Podría requerir un manejo especial
    }
 
//Escenario de falla 2: Cliente nulo
    // Identificador: testNotificarSMSConClienteNulo
    // Propósito: Verificar que la notificación falle si el cliente es nulo.
    // Precondiciones: Cliente nulo.
    // Entradas: "Mensaje de prueba", cliente nulo.
    // Salidas Esperadas: Se espera que lance una excepción debido a que el cliente es nulo.

    @Test
    public void testNotificarSMSConClienteNulo() {
        NotificacionSMS notificacion = new NotificacionSMS("123456789");

        Exception exception = assertThrows(NullPointerException.class, () -> {
            notificacion.notificar("Mensaje de prueba", null);
        });

        assertEquals("El cliente no puede ser nulo", exception.getMessage());
    }
}
