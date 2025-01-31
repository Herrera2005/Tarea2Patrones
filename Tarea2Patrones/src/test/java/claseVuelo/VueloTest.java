/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package claseVuelo;

import clases.Cliente;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
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
class VueloTest {

    private Vuelo vuelo;
    private Cliente cliente;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @BeforeEach
    void setUp() throws Exception {
        Date horaSalida = sdf.parse("10-10-2025");
        Date horaLlegada = sdf.parse("11-10-2025");
        List<Asiento> asientos = new ArrayList<>();
        vuelo = new Vuelo(1, "AeroTest", horaSalida, horaLlegada, 10, Collections.singletonList(asientos));
        cliente = new Cliente(1,"Juan Perez", "12345@h","s");
    }

    /**
     * Prueba añadir un pasajero en un caso normal.
     */
    @Test
    void testAnadirPasajero() {
        vuelo.anadirPasajero(cliente);
        assertEquals(9, vuelo.getAsientosDisponibles());
    }

    /**
     * Prueba de fallo: intentar añadir un pasajero cuando no hay asientos disponibles.
     */
    @Test
    void testAnadirPasajeroSinAsientos() {
        for (int i = 0; i < 10; i++) {
            vuelo.anadirPasajero(new Cliente(1,"Juan Perez", "12345@h","s"));
        }
        assertThrows(IllegalStateException.class, () -> vuelo.anadirPasajero(cliente));
    }

    /**
     * Prueba verificar disponibilidad en un caso normal.
     */
    @Test
    void testVerificarDisponibilidad() {
        assertTrue(vuelo.verificarDisponibilidad());
    }

    /**
     * Prueba de fallo: verificar disponibilidad cuando no hay asientos disponibles.
     */
    @Test
    void testVerificarDisponibilidadSinAsientos() {
        for (int i = 0; i < 10; i++) {
            vuelo.anadirPasajero(new Cliente(1,"Juan Perez", "12345@h","s"));
        }
        assertFalse(vuelo.verificarDisponibilidad());
    }
}
