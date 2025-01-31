/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Notificaciones;

import clases.Cliente;
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
class NotificacionEmailTest {

    private NotificacionEmail notificacion;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        notificacion = new NotificacionEmail("test@example.com");
        cliente = new Cliente(1, "Juan Perez", "12345@h", "s");
    }

    /**
     * ID: TC-NE-001
     * Propósito: Verificar que la notificación se envía correctamente con datos válidos.
     * Precondiciones: El email y el cliente deben ser válidos.
     * Entradas: "Mensaje de prueba", cliente con nombre "Juan Perez".
     * Salida esperada: Mensaje impreso con los datos correctos.
     */
    @Test
    void testNotificarConDatosValidos() {
        System.out.println("Test-NotificacionEmailTest-001");
        assertDoesNotThrow(() -> notificacion.notificar("Mensaje de prueba", cliente));
    }

    /**
     * ID: TC-NE-002
     * Propósito: Verificar el comportamiento cuando el mensaje es vacío.
     * Precondiciones: El email y el cliente deben ser válidos.
     * Entradas: "", cliente con nombre "Juan Perez".
     * Salida esperada: Mensaje impreso con un cuerpo vacío.
     */
    @Test
    void testNotificarConMensajeVacio() {
        System.out.println("Test-NotificacionEmailTest-002");
        assertDoesNotThrow(() -> notificacion.notificar("", cliente));
    }

    /**
     * ID: TC-NE-003
     * Propósito: Manejar el caso en que el cliente es null.
     * Precondiciones: El email debe ser válido.
     * Entradas: "Mensaje de prueba", cliente null.
     * Salida esperada: Excepción NullPointerException.
     */
    @Test
    void testNotificarConClienteNulo() {
        System.out.println("Test-NotificacionEmailTest-003");
        assertThrows(NullPointerException.class, () -> notificacion.notificar("Mensaje de prueba", null));
    }

    /**
     * ID: TC-NE-004
     * Propósito: Verificar el comportamiento cuando el correo es inválido.
     * Precondiciones: El email debe tener un formato incorrecto.
     * Entradas: "Mensaje de prueba", cliente con nombre "Juan Perez".
     * Salida esperada: La clase actual no valida correos, pero se podría modificar para lanzar una excepción.
     */
    @Test
    void testNotificarConCorreoInvalido() {
        System.out.println("Test-NotificacionEmailTest-004");
        notificacion.setCorreo("correo-invalido");
        assertDoesNotThrow(() -> notificacion.notificar("Mensaje de prueba", cliente));
        // Se recomienda agregar validación en la implementación de NotificacionEmail
    }

    /**
     * ID: TC-NE-005
     * Propósito: Verificar que el método getCorreo devuelve el correo correcto.
     * Precondiciones: El email ha sido correctamente configurado en el constructor.
     * Entradas: Llamar a getCorreo().
     * Salida esperada: "test@example.com".
     */
    @Test
    void testGetCorreo() {
        System.out.println("Test-NotificacionEmailTest-005");
        assertEquals("test@example.com", notificacion.getCorreo());
    }

    /**
     * ID: TC-NE-006
     * Propósito: Verificar que el método setCorreo cambia correctamente el correo.
     * Precondiciones: El email puede ser modificado después de la creación.
     * Entradas: "nuevo@example.com".
     * Salida esperada: El método getCorreo() devuelve "nuevo@example.com".
     */
    @Test
    void testSetCorreo() {
        System.out.println("Test-NotificacionEmailTest-006");
        notificacion.setCorreo("nuevo@example.com");
        assertEquals("nuevo@example.com", notificacion.getCorreo());
    }
    
    /*Escenario de falla 1: Correo vacío*/
    // Id: TC-NE-007
    // Propósito: Verificar que la notificación falle si el correo es vacío.
    // Precondiciones: Cliente y correo vacío.
    // Entradas: "Mensaje de prueba", cliente con nombre "Juan Pérez", correo vacío.
    // Salidas Esperadas: Se espera que lance una excepción debido a que el correo está vacío.

    @Test
    public void testNotificarEmailConCorreoVacio() {
        System.out.println("Test-NotificacionEmailTest-007");
        Cliente cliente = new Cliente(123456, "Juan Pérez", "juan@email.com", "password");
        NotificacionEmail notificacion = new NotificacionEmail("");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            notificacion.notificar("Mensaje de prueba", cliente);
        });

        assertEquals("El correo no puede estar vacío", exception.getMessage());
    }
    
//Escenario de falla 2: Cliente nulo
    // Id: TC-NE-008
    // Propósito: Verificar que la notificación falle si el cliente es nulo.
    // Precondiciones: Cliente nulo.
    // Entradas: "Mensaje de prueba", cliente nulo.
    // Salidas Esperadas: Se espera que lance una excepción debido a que el cliente es nulo.

    @Test
    public void testNotificarEmailConClienteNulo() {
    System.out.println("Test-NotificacionEmailTest-008");
        NotificacionEmail notificacion = new NotificacionEmail("juan@email.com");

        Exception exception = assertThrows(NullPointerException.class, () -> {
            notificacion.notificar("Mensaje de prueba", null);
        });

        assertEquals("El cliente no puede ser nulo", exception.getMessage());
    }
}