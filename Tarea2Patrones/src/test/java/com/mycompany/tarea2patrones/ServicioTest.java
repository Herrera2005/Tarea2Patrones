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
import java.util.*;

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
        // Simula la entrada del usuario para administrador correcto
        inputProvider.setInputs("admin", "1234");
        System.out.println("Administrador - Inicio de Sesión");

        servicio.iniciarSesionAdministracion(reservas, vuelos, vehiculos);

        assertNotNull(reservas);
        assertNotNull(vuelos);
        assertNotNull(vehiculos);
    }

    @Test
    void testIniciarSesionCliente() {
        // Simula la selección del cliente "Juan Perez"
        inputProvider.setInputs("1", "4");
        System.out.println("Cliente - Inicio de Sesión");

        servicio.iniciarSesionCliente(inputProvider.getScanner(), vuelos, vehiculos);

        assertEquals("Juan Perez", clientes.get(0).getNombre());
    }

    @Test
    void testIniciarSesionClienteOpcionInvalida() {
        // Simula una opción inválida
        inputProvider.setInputs("3"); // No hay cliente con ID 3

        servicio.iniciarSesionCliente(inputProvider.getScanner(), vuelos, vehiculos);
        assertFalse(clientes.stream().anyMatch(c -> c.getId() == 3));
    }

    @Test
    void testIniciarSesionClienteInputNoNumerico() {
        // Simula entrada de texto no numérico
        inputProvider.setInputs("abc");

        assertThrows(Exception.class, () -> servicio.iniciarSesionCliente(inputProvider.getScanner(), vuelos, vehiculos));
    }

    /**
     * MockInputProvider permite definir entradas simuladas para reemplazar Scanner.
     */
    private static class MockInputProvider {
        private final Queue<String> inputs = new LinkedList<>();

        void setInputs(String... inputs) {
            this.inputs.clear();
            this.inputs.addAll(Arrays.asList(inputs));
        }

        Scanner getScanner() {
            String simulatedInput = String.join("\n", inputs) + "\n";
            return new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
        }
    }
}