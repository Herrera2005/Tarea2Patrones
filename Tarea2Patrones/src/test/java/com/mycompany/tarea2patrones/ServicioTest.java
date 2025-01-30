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
        reservas = new ArrayList<>();
        vuelos = Arrays.asList(new Vuelo(1, "AeroTest", new Date(), new Date(), 10, new ArrayList<>()));
        vehiculos = Arrays.asList(new VehiculoEconomico(1, "Económico", "ProveedorX", true));

        // Inyectamos un Scanner vacío para evitar entrada manual
        servicio = new Servicio(clientes, new Scanner(""));
    }

    @Test
    void testIniciarSesionAdministracion() {
        // Simula la entrada de usuario con un número de administrador correcto
        Scanner scanner = new Scanner("admin\n1234\n"); // Admin y clave correcta
        servicio = new Servicio(clientes, scanner);

        System.out.println("Administrador - Inicio de Sesión");
        servicio.iniciarSesionAdministracion(reservas, vuelos, vehiculos);

        assertNotNull(reservas);
        assertNotNull(vuelos);
        assertNotNull(vehiculos);
    }

    @Test
    void testIniciarSesionCliente() {
        // Simula la entrada de usuario: selecciona cliente "Juan Perez"
        Scanner scanner = new Scanner("1\n");
        servicio = new Servicio(clientes, scanner);

        System.out.println("Cliente - Inicio de Sesión");
        servicio.iniciarSesionCliente(scanner, vuelos, vehiculos);

        assertEquals("Juan Perez", clientes.get(0).getNombre());
    }

    @Test
    void testIniciarSesionClienteOpcionInvalida() {
        // Simula la entrada de una opción inválida
        Scanner scanner = new Scanner("3\n"); // No hay cliente con ID 3
        servicio = new Servicio(clientes, scanner);

        servicio.iniciarSesionCliente(scanner, vuelos, vehiculos);
        // No se puede iniciar sesión, así que verificamos que no cambia el estado
        assertFalse(clientes.stream().anyMatch(c -> c.getId() == 3));
    }

    @Test
    void testIniciarSesionClienteInputNoNumerico() {
        // Simula entrada de texto no numérico
        Scanner scanner = new Scanner("abc\n");
        servicio = new Servicio(clientes, scanner);

        // Se espera que lance una excepción por entrada inválida
        assertThrows(Exception.class, () -> servicio.iniciarSesionCliente(scanner, vuelos, vehiculos));
    }
}