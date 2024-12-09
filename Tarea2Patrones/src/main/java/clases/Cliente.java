/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Notificaciones.Notificacion;

/**
 *
 * @author herreranc
 */
public class Cliente extends Usuario{
    
    private List<Reserva> reservas;
    private List<Notificacion> notificaiones;


    public Cliente(int idCedula, String nombre, String email, String contrasenia) {
        super(idCedula, nombre, email, contrasenia);
        reservas = new ArrayList<>();
    }

    public List<Reserva> getReservas() {
        return reservas;
    }
    
    public void registrarse(){
    
    }
    
    public Reserva realizarReserva(){
        return null;
    }
    
    public void cancelarReserva(Reserva reserva){
    }

    public List<Notificacion> getNotificaciones(){
        return notificaiones;
    }

    
    
    public void cargarReservas(File archivoCliente, Map<Integer, Reserva> todasLasReservas) {
        this.reservas = new ArrayList<>(); // Inicializar la lista de reservas
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoCliente))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith("Reservas:")) {
                    // Extraer los identificadores de reserva
                    String ids = linea.substring(linea.indexOf("{") + 1, linea.indexOf("}"));
                    String[] idArray = ids.split(",");
                    for (String id : idArray) {
                        int idReserva = Integer.parseInt(id.trim());
                        if (todasLasReservas.containsKey(idReserva)) {
                            this.reservas.add(todasLasReservas.get(idReserva));
                        }
                    }
                    break; // No es necesario continuar leyendo
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer las reservas del cliente: " + e.getMessage());
        }
    }
}
