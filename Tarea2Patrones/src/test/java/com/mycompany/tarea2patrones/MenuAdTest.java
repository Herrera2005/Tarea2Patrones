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
import enums.EstadoReserva;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
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
class MenuAdTest {
    private MenuAd menuAd;
    private List<Reserva> reservas;
    private List<Vuelo> vuelos;
    private List<Vehiculo> vehiculos;
    private MockInputProvider inputProvider;

    @BeforeEach
    void setUp() {
        reservas = new ArrayList<>();
        vuelos = new ArrayList<>();
        vehiculos = new ArrayList<>();
        inputProvider = new MockInputProvider();
        menuAd = new MenuAd(inputProvider.getScanner());
    }

    @Test
    void testGestionarReservas_CancelarReserva_Existente() {
        Reserva reserva = new Reserva(1, EstadoReserva.CONFIRMADO, new Date(), null, null, null, new Cliente(1, "Juan Perez", "12345@h", "s"));
        reservas.add(reserva);

        inputProvider.setInputs("1", "1"); // Opción 1: Cancelar, ID 1
        menuAd.menuAdministracion(reservas, vuelos, vehiculos);

        assertEquals(EstadoReserva.CANCELADO, reserva.getEstadoReserva());
    }

    @Test
    void testGestionarReservas_CancelarReserva_NoExistente() {
        inputProvider.setInputs("1", "99"); // Opción 1: Cancelar, ID 99 no existe
        menuAd.menuAdministracion(reservas, vuelos, vehiculos);

        assertTrue(reservas.isEmpty());
    }

    @Test
    void testGestionarVuelos_CancelarVuelo_Existente() {
        Vuelo vuelo = new Vuelo(1, "AerolineaX", new Date(), new Date(), 100, new ArrayList<>());
        vuelos.add(vuelo);
        Cliente cliente = new Cliente(1, "Juan Perez", "12345@h", "s");
        Reserva reserva = new Reserva(1, EstadoReserva.CONFIRMADO, new Date(), null, vuelo, null, cliente);
        cliente.getReservas().add(reserva);
        vuelo.anadirPasajero(cliente);
        reservas.add(reserva);

        inputProvider.setInputs("2", "1"); // Opción 2: Gestionar vuelos, ID 1
        menuAd.menuAdministracion(reservas, vuelos, vehiculos);

        assertEquals(EstadoReserva.CANCELADO, reserva.getEstadoReserva());
    }

    @Test
    void testGestionarVuelos_CancelarVuelo_NoExistente() {
        inputProvider.setInputs("2", "99"); // Opción 2: Gestionar vuelos, ID 99 no existe
        menuAd.menuAdministracion(reservas, vuelos, vehiculos);

        assertTrue(vuelos.isEmpty());
    }

    @Test
    void testGestionarVehiculos_CancelarVehiculo_Existente() {
        Vehiculo vehiculo = new VehiculoEconomico(1, "SUV", "ProveedorX", true);
        vehiculos.add(vehiculo);
        Cliente cliente = new Cliente(1, "Juan Perez", "12345@h", "s");
        Reserva reserva = new Reserva(2, EstadoReserva.CONFIRMADO, new Date(), vehiculo, null, null, cliente);
        cliente.getReservas().add(reserva);
        vehiculo.reservarVehiculo(reserva);
        reservas.add(reserva);

        inputProvider.setInputs("3", "1"); // Opción 3: Gestionar vehículos, ID 1
        menuAd.menuAdministracion(reservas, vuelos, vehiculos);

        assertNull(reserva.getVehiculo(), "El vehículo debería haber sido removido de la reserva.");
        assertTrue(vehiculo.verificarDisponibilidad(), "El vehículo debería estar disponible nuevamente.");
    }

    @Test
    void testGestionarVehiculos_CancelarVehiculo_NoExistente() {
        inputProvider.setInputs("3", "99"); // Opción 3: Gestionar vehículos, ID 99 no existe
        menuAd.menuAdministracion(reservas, vuelos, vehiculos);

        assertTrue(vehiculos.isEmpty());
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