/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.tarea2patrones;

import claseVehiculo.Vehiculo;
import claseVehiculo.VehiculoEconomico;
import claseVuelo.Vuelo;
import clases.Cliente;
import clases.Reserva;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para la clase Servicio.
 *
 * Se prueban los métodos en escenarios normales y en al menos dos escenarios de fallo.
 */
class ServicioTest {
    private Servicio servicio;
    private List<Cliente> clientes;
    private List<Reserva> reservas;
    private List<Vuelo> vuelos;
    private List<Vehiculo> vehiculos;

    private MockInputProvider inputProvider;

    @BeforeEach
    void setUp() {
        Cliente cliente1 = new Cliente(1, "Juan Perez", "12345@h", "s");
        Cliente cliente2 = new Cliente(2, "Maria Lopez", "67890", "x");
        clientes = Arrays.asList(cliente1, cliente2);
        reservas = new ArrayList<>();
        vuelos = Arrays.asList(new Vuelo(1, "AeroTest", new Date(), new Date(), 10, new ArrayList<>()));
        vehiculos = Arrays.asList(new VehiculoEconomico(1, "Económico", "ProveedorX", true));

        inputProvider = new MockInputProvider();
        servicio = new Servicio(clientes, inputProvider.getScanner());
    }

    @Test
    void testIniciarSesionAdministracion() {
        System.out.println("Test-ServicioTest-001");
        inputProvider.setInputs("admin", "1234");
        System.out.println("Administrador - Inicio de Sesion");
        servicio.iniciarSesionAdministracion(reservas, vuelos, vehiculos);

        assertNotNull(reservas);
        assertNotNull(vuelos);
        assertNotNull(vehiculos);
    }

    @Test
    void testIniciarSesionCliente() {
        System.out.println("Test-ServicioTest-002");
        // Simulate user input for the test
        Scanner mockScanner = new Scanner("1"); // Simulating the user entering "1" for "Ver reservas"

        inputProvider.setInputs("1"); // This seems to be used elsewhere in your test setup; you might not need this
        System.out.println("Cliente - Inicio de Sesion");

        // Pass the mockScanner to the menuCliente method
        servicio.iniciarSesionCliente(mockScanner, vuelos, vehiculos); // Assuming this uses the menuCliente method internally

        // Assuming clientes.get(0).getNombre() should return the correct name after the login
        assertEquals("Juan Perez", clientes.get(0).getNombre());
    }

    @Test
    void testIniciarSesionClienteOpcionInvalida() {
        System.out.println("Test-ServicioTest-003");
        inputProvider.setInputs("3");
        servicio.iniciarSesionCliente(inputProvider.getScanner(), vuelos, vehiculos);

        assertFalse(clientes.stream().anyMatch(c -> c.getId() == 3));
    }

    @Test
    void testIniciarSesionClienteInputNoNumerico() {
        System.out.println("Test-ServicioTest-004");
        inputProvider.setInputs("abc");
        assertThrows(InputMismatchException.class, () -> servicio.iniciarSesionCliente(inputProvider.getScanner(), vuelos, vehiculos));
    }

    /**
     * MockInputProvider permite definir entradas simuladas para reemplazar Scanner.
     */
    private static class MockInputProvider {
        private StringBuilder inputBuffer = new StringBuilder();

        void setInputs(String... inputs) {
            inputBuffer.setLength(0); // Limpiar el buffer
            for (String input : inputs) {
                inputBuffer.append(input).append("\n");
            }
        }

        Scanner getScanner() {
            InputStream inputStream = new ByteArrayInputStream(inputBuffer.toString().getBytes());
            return new Scanner(inputStream);
        }
    }
}
