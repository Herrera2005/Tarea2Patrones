/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Observer;

import clases.Reserva;
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
class GestorReservasTest {

    private GestorReservas gestor;
    private Reserva reserva;
    private boolean notificado;

    @BeforeEach
    void setUp() {
        gestor = new GestorReservas();
        reserva = new Reserva();
        notificado = false;
    }

    /**
     * ID: TC-GR-001
     * Propósito: Verificar que se puede agregar un observador correctamente.
     */
    @Test
    void testAgregarObservador() {
        System.out.println("Test-GestorReservasTest-001");
        Observador observador = reserva -> notificado = true;

        gestor.addObserver(observador);
        gestor.notifyObservers(reserva);

        assertTrue(notificado, "El observador debería haber sido notificado.");
    }

    /**
     * ID: TC-GR-002
     * Propósito: Verificar que se puede eliminar un observador correctamente.
     */
    @Test
    void testEliminarObservador() {
        System.out.println("Test-GestorReservasTest-002");
        Observador observador = reserva -> notificado = true;

        gestor.addObserver(observador);
        gestor.removeObserver(observador);
        gestor.notifyObservers(reserva);

        assertFalse(notificado, "El observador no debería haber sido notificado.");
    }

    /**
     * ID: TC-GR-003
     * Propósito: Verificar el comportamiento cuando se intenta eliminar un observador que no está en la lista.
     */
    @Test
    void testEliminarObservadorNoExistente() {
        System.out.println("Test-GestorReservasTest-003");
        Observador observador = reserva -> notificado = true;

        assertDoesNotThrow(() -> gestor.removeObserver(observador), 
            "Eliminar un observador inexistente no debería lanzar excepción.");
    }

    /**
     * ID: TC-GR-004
     * Propósito: Verificar que `notifyObservers` llama a `actualizar` en todos los observadores.
     */
    @Test
    void testNotificarTodosLosObservadores() {
        System.out.println("Test-GestorReservasTest-004");
        boolean[] notificados = {false, false, false};

        Observador observador1 = reserva -> notificados[0] = true;
        Observador observador2 = reserva -> notificados[1] = true;
        Observador observador3 = reserva -> notificados[2] = true;

        gestor.addObserver(observador1);
        gestor.addObserver(observador2);
        gestor.addObserver(observador3);

        gestor.notifyObservers(reserva);

        assertTrue(notificados[0], "El observador 1 debería haber sido notificado.");
        assertTrue(notificados[1], "El observador 2 debería haber sido notificado.");
        assertTrue(notificados[2], "El observador 3 debería haber sido notificado.");
    }

    /**
     * ID: TC-GR-005
     * Propósito: Manejar el caso en que se notifica sin observadores registrados.
     */
    @Test
    void testNotificarSinObservadores() {
        System.out.println("Test-GestorReservasTest-005");
        assertDoesNotThrow(() -> gestor.notifyObservers(reserva), 
            "No debería lanzarse una excepción si no hay observadores.");
    }

    /**
     * ID: TC-GR-006
     * Propósito: Manejar el caso en que se intenta agregar un observador nulo.
     */
    @Test
    void testAgregarObservadorNulo() {
        System.out.println("Test-GestorReservasTest-006");
        assertThrows(NullPointerException.class, () -> gestor.addObserver(null),
            "Agregar un observador nulo debería lanzar NullPointerException.");
    }
}