/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NotificacionesTest;

import Notificaciones.NotificacionSMS;
import clases.Cliente;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author usuario
 */
public class NotificacionSMSTest {
//Prueba Exitosa
    // Identificador: testNotificarSMSExitosa
    // Propósito: Verificar la notificación correcta mediante SMS.
    // Precondiciones: Cliente y número de teléfono deben estar inicializados correctamente.
    // Entradas: "Mensaje de prueba", cliente con nombre "Juan Pérez".
    // Salidas Esperadas: Se espera que la notificación sea enviada correctamente al número indicado.

    @Test
    public void testNotificarSMSExitosa() {
        Cliente cliente = new Cliente(123456, "Juan Pérez", "juan@email.com", "password");
        NotificacionSMS notificacion = new NotificacionSMS("123456789");

        notificacion.notificar("Mensaje de prueba", cliente);
        // La salida es un mensaje impreso, por lo que se verificará la impresión o un comportamiento similar
        assertDoesNotThrow(() -> notificacion.notificar("Mensaje de prueba", cliente));
    }
    
//Escenario de falla 1: Número vacío
    // Identificador: testNotificarSMSConNumeroVacio
    // Propósito: Verificar que la notificación falle si el número de teléfono es vacío.
    // Precondiciones: Cliente y número vacío.
    // Entradas: "Mensaje de prueba", cliente con nombre "Juan Pérez", número vacío.
    // Salidas Esperadas: Se espera que lance una excepción debido a que el número está vacío.

    @Test
    public void testNotificarSMSConNumeroVacio() {
        Cliente cliente = new Cliente(123456, "Juan Pérez", "juan@email.com", "password");
        NotificacionSMS notificacion = new NotificacionSMS("");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            notificacion.notificar("Mensaje de prueba", cliente);
        });

        assertEquals("El número no puede estar vacío", exception.getMessage());
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
