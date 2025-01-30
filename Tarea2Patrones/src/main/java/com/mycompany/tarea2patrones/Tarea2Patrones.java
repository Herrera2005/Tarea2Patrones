/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tarea2patrones;

import Observer.GestorReservas;
import Observer.ObservadorCambioReserva;
import Observer.ObservadorIncumplimiento;
import claseVehiculo.Vehiculo;
import claseVehiculo.VehiculoEconomico;
import claseVuelo.Vuelo;
import clases.Cliente;
import clases.Pago;
import clases.Reserva;
import clases.ReservaBuilder;
import clases.SistemaReservas;
import clases.SistemaReservasVehiculo;
import clases.SistemaReservasVuelo;
import enums.EstadoPago;
import enums.EstadoReserva;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import DataLoader.ClientLoader;

/**
 *
 * @author herreranc
 */
public class Tarea2Patrones {
    private static final String CLIENTE_TXT = "clientes.txt";
    private static final String ADMIN_TXT = "admin.txt";
    private static final String SOPORTE_TXT = "soporte.txt";
    private static final String RESERVAS_TXT = "reservas.txt";
    

    public static void main(String[] args) throws IOException {
        ClientLoader clientes = new ClientLoader(); // Assuming this loads the client data
        SistemaReservasVehiculo vehiculos = new SistemaReservasVehiculo();
        SistemaReservasVuelo vuelos = new SistemaReservasVuelo();
        SistemaReservas reservas = new SistemaReservas(vehiculos.getVehiculos(), vuelos.getVuelos(), clientes.getClientes()); // Pass the List<Cliente> to the constructor
        
        reservas.mostrarReservas();
        
        // Assuming clientes.getClientes() returns a List<Cliente>
        Servicio s = new Servicio(clientes.getClientes(), new Scanner(System.in)); // No need to cast
        s.iniciar(reservas.getReservas(), vuelos.getVuelos(), vehiculos.getVehiculos());
    }
}