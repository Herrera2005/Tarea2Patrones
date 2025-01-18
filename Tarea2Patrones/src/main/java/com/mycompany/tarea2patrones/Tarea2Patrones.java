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
        Scanner scanner = new Scanner(System.in);
         Servicio servicio = new Servicio();
         servicio.Iniciar(CLIENTE_TXT, ADMIN_TXT, SOPORTE_TXT, RESERVAS_TXT);
    }

    
}
