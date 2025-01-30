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

    @BeforeEach
    void setUp() {
        Cliente cliente1 = new Cliente(1, "Juan Perez", "12345@h", "s");
        Cliente cliente2 = new Cliente(2, "Maria Lopez", "67890", "x");
        clientes = Arrays.asList(cliente1, cliente2);
        reservas = Arrays.asList(new Reserva());
        vuelos = Arrays.asList(new Vuelo(1, "AeroTest", new Date(), new Date(), 10, new ArrayList<>()));
        vehiculos = Arrays.asList(new VehiculoEconomico(1, "Económico", "ProveedorX", true));
        servicio = new Servicio(clientes, new Scanner(System.in));
    }

    @Test
    void testIniciarSesionAdministracion() {
        servicio.iniciarSesionAdministracion(reservas, vuelos, vehiculos);
        assertNotNull(reservas);
        assertNotNull(vuelos);
        assertNotNull(vehiculos);
    }

    @Test
    void testIniciarSesionCliente() {
        Scanner scanner = new Scanner("1\n");
        servicio.iniciarSesionCliente(scanner, vuelos, vehiculos);
        assertEquals("Juan Perez", clientes.get(0).getNombre());
    }

    @Test
    void testIniciarSesionClienteOpcionInvalida() {
        Scanner scanner = new Scanner("3\n");
        servicio.iniciarSesionCliente(scanner, vuelos, vehiculos);
        // Se espera que no se inicie sesión, verificar salida en consola o una bandera
    }

    @Test
    void testIniciarSesionClienteInputNoNumerico() {
        Scanner scanner = new Scanner("abc\n");
        assertThrows(Exception.class, () -> servicio.iniciarSesionCliente(scanner, vuelos, vehiculos));
    }
}
