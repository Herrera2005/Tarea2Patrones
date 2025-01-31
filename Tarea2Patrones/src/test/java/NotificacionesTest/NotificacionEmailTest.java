/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NotificacionesTest;

import Notificaciones.NotificacionEmail;
import clases.Cliente;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
/**
 *
 * @author usuario
 */
public class NotificacionEmailTest {
    
//Prueba exitosa
    // Identificador: testNotificarEmailExitosa
    // Propósito: Verificar la notificación correcta mediante correo electrónico.
    // Precondiciones: Cliente y correo deben estar inicializados correctamente.
    // Entradas: "Mensaje de prueba", cliente con nombre "Juan Pérez".
    // Salidas Esperadas: Se espera que la notificación sea enviada correctamente al correo indicado.

    @Test
    public void testNotificarEmailExitosa() {
        Cliente cliente = new Cliente(123456, "Juan Pérez", "juan@email.com", "password");
        NotificacionEmail notificacion = new NotificacionEmail("juan@email.com");

        notificacion.notificar("Mensaje de prueba", cliente);
        // La salida es un mensaje impreso, por lo que se verificará la impresión o un comportamiento similar
        // (en un caso real se podría usar un mock para verificar la salida)
        assertDoesNotThrow(() -> notificacion.notificar("Mensaje de prueba", cliente));
    }
    
/*Escenario de falla 1: Correo vacío*/
    // Identificador: testNotificarEmailConCorreoVacio
    // Propósito: Verificar que la notificación falle si el correo es vacío.
    // Precondiciones: Cliente y correo vacío.
    // Entradas: "Mensaje de prueba", cliente con nombre "Juan Pérez", correo vacío.
    // Salidas Esperadas: Se espera que lance una excepción debido a que el correo está vacío.

    @Test
    public void testNotificarEmailConCorreoVacio() {
        Cliente cliente = new Cliente(123456, "Juan Pérez", "juan@email.com", "password");
        NotificacionEmail notificacion = new NotificacionEmail("");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            notificacion.notificar("Mensaje de prueba", cliente);
        });

        assertEquals("El correo no puede estar vacío", exception.getMessage());
    }
    
//Escenario de falla 2: Cliente nulo
    // Identificador: testNotificarEmailConClienteNulo
    // Propósito: Verificar que la notificación falle si el cliente es nulo.
    // Precondiciones: Cliente nulo.
    // Entradas: "Mensaje de prueba", cliente nulo.
    // Salidas Esperadas: Se espera que lance una excepción debido a que el cliente es nulo.

    @Test
    public void testNotificarEmailConClienteNulo() {
        NotificacionEmail notificacion = new NotificacionEmail("juan@email.com");

        Exception exception = assertThrows(NullPointerException.class, () -> {
            notificacion.notificar("Mensaje de prueba", null);
        });

        assertEquals("El cliente no puede ser nulo", exception.getMessage());
    }
}
