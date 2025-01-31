/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesTest;

import Notificaciones.Notificacion;
import Notificaciones.NotificacionEmail;
import claseVehiculo.Vehiculo;
import claseVehiculo.VehiculoEconomico;
import claseVuelo.Asiento;
import claseVuelo.Vuelo;
import clases.Cliente;
import clases.Reserva;
import enums.EstadoReserva;
import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author usuario
 */
public class ClienteTest {
//Propósito: Verificar que el constructor inicializa correctamente las reservas y las notificaciones.
    @Test
    public void testClienteConstructor() {
        System.out.println("Test-ClienteTest-001");
        Cliente cliente = new Cliente(12345, "Carlos", "carlos@mail.com", "password123");
        
        assertNotNull(cliente);
        assertEquals(12345, cliente.getIdCedula());
        assertEquals("Carlos", cliente.getNombre());
        assertEquals("carlos@mail.com", cliente.getEmail());
        assertEquals("password123", cliente.getContrasenia());
        assertNotNull(cliente.getReservas());
        assertTrue(cliente.getReservas().isEmpty());  // Inicialmente sin reservas
    }
    
/*Propósito: Verificar que el cliente pueda realizar una reserva correctamente.

Entradas:

Vehículos: Lista con un vehículo disponible.
Vuelos: Lista con un vuelo disponible.
El cliente introduce un monto válido.

Salida esperada: El cliente recibe una confirmación con la reserva y su ID.*/
    @Test
    public void testRealizarReservaPorConsola() {
        System.out.println("Test-ClienteTest-002");
        Cliente cliente = new Cliente(12345, "Carlos", "carlos@mail.com", "password123");
        Vehiculo vehiculo = new VehiculoEconomico(1, "Economico", "Proveedor A", true);
        List<Asiento> asientos = new ArrayList<>();
        Vuelo vuelo = new Vuelo(1, "Aerolínea A", new Date(), new Date(), 10, Collections.singletonList(asientos));
        
        List<Vehiculo> vehiculos = List.of(vehiculo);
        List<Vuelo> vuelos = List.of(vuelo);

        // Simulación de ingreso por consola
        // Nota: Esto podría necesitar un mock de Scanner para simular la entrada de usuario
        cliente.realizarReservaPorConsola(vehiculos, vuelos);
        System.out.println("Reserva realizada exitosamente.");

        // Verificar que la reserva se haya agregado
        assertFalse(cliente.getReservas().isEmpty());
        assertEquals(2, cliente.getReservas().size());
        assertEquals(EstadoReserva.PENDIENTE, cliente.getReservas().get(0).getEstadoReserva());
        assertEquals(vehiculo, cliente.getReservas().get(0).getVehiculo());
        assertEquals(vuelo, cliente.getReservas().get(0).getVuelo());
    }
    
/*Propósito: Verificar que el cliente pueda cargar las reservas desde un archivo correctamente.

Entradas: Un archivo con reservas preexistentes y un mapa de reservas.

Salida esperada: El cliente carga correctamente las reservas y las asocia.*/
    @Test
    public void testCargarReservas() throws IOException {
        System.out.println("Test-ClienteTest-003");
        // Crear un archivo de ejemplo con reservas
        File archivoCliente = new File("reservas_cliente.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoCliente))) {
            writer.write("Reservas: {1, 2, 3}");
        }

        // Crear un mapa con reservas
        Map<Integer, Reserva> todasLasReservas = new HashMap<>();
        todasLasReservas.put(1, new Reserva(1, EstadoReserva.PENDIENTE, new Date(), null, null, null, null));
        todasLasReservas.put(2, new Reserva(2, EstadoReserva.CONFIRMADO, new Date(), null, null, null, null));
        todasLasReservas.put(3, new Reserva(3, EstadoReserva.CANCELADO, new Date(), null, null, null, null));

        // Crear cliente y cargar reservas
        Cliente cliente = new Cliente(12345, "Carlos", "carlos@mail.com", "password123");
        cliente.cargarReservas(archivoCliente, todasLasReservas);

        // Verificar que las reservas se hayan cargado correctamente
        assertEquals(3, cliente.getReservas().size());
        assertTrue(cliente.getReservas().stream().anyMatch(r -> r.getIdReserva() == 1));
        assertTrue(cliente.getReservas().stream().anyMatch(r -> r.getIdReserva() == 2));
        assertTrue(cliente.getReservas().stream().anyMatch(r -> r.getIdReserva() == 3));
    }
    
    /*Propósito: Verificar que el cliente pueda eliminar una reserva correctamente.*/
    @Test
    public void testEliminarReserva() {
        System.out.println("Test-ClienteTest-004");
        Cliente cliente = new Cliente(12345, "Carlos", "carlos@mail.com", "password123");
        Reserva reserva = new Reserva(1, EstadoReserva.PENDIENTE, new Date(), null, null, null, cliente);

        cliente.agregarReserva(reserva);  // Agregar la reserva
        assertEquals(1, cliente.getReservas().size());

        // Eliminar la reserva
        cliente.eliminarReserva(1);

        // Verificar que la reserva ha sido eliminada
        assertTrue(cliente.getReservas().isEmpty());
    }
    
/*Propósito: Verificar que el cliente pueda agregar notificaciones correctamente.*/
    @Test
    public void testAddNotificacion() {
        System.out.println("Test-ClienteTest-005");
        Cliente cliente = new Cliente(12345, "Carlos", "carlos@mail.com", "password123");
        Notificacion notificacion = new NotificacionEmail("Tu reserva ha sido confirmada");

        // Agregar notificación
        cliente.addNotificacion(notificacion);

        // Verificar que la notificación se ha agregado
        assertFalse(cliente.getNotificaciones().isEmpty());
        assertEquals("Tu reserva ha sido confirmada", cliente.getNotificaciones().get(0).getMensaje());
    }

/*Propósito: Verificar que el cliente pueda cancelar una reserva correctamente.*/
    @Test
    public void testCancelarReserva() {
        System.out.println("Test-ClienteTest-006");
        Cliente cliente = new Cliente(12345, "Carlos", "carlos@mail.com", "password123");
        Reserva reserva = new Reserva(1, EstadoReserva.PENDIENTE, new Date(), null, null, null, cliente);
        cliente.agregarReserva(reserva);  // Agregar la reserva

        // Simular la cancelación de la reserva
        cliente.eliminarReserva(1);

        // Verificar que la reserva ha sido eliminada
        assertTrue(cliente.getReservas().isEmpty());
    }
    
/*Propósito: Verificar que el cliente reciba un mensaje de error si selecciona un vehículo no válido.*/
    @Test
    public void testRealizarReservaPorConsola_VehiculoInvalido() {
        System.out.println("Test-ClienteTest-007");
        Cliente cliente = new Cliente(12345, "Carlos", "carlos@mail.com", "password123");
        Vehiculo vehiculo = new VehiculoEconomico(1, "Economico", "Proveedor A", false);
        List<Asiento> asientos = new ArrayList<>();
        Vuelo vuelo = new Vuelo(1, "Aerolínea A", new Date(), new Date(), 10, Collections.singletonList(asientos));

        List<Vehiculo> vehiculos = List.of(vehiculo);
        List<Vuelo> vuelos = List.of(vuelo);

        // Simular que el cliente introduce un ID de vehículo inválido
        cliente.realizarReservaPorConsola(vehiculos, vuelos);
        System.out.println("Reserva realizada exitosamente.");

        // Verificar que no se haya agregado una reserva
        assertTrue(cliente.getReservas().isEmpty());
    }
}
