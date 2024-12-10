/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package claseVuelo;

import claseVuelo.Asiento;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author herreranc
 */
public class Vuelo {
    private int idVuelo;
    private String aerolinea;
    private Date horaSalida;
    private Date horaLlegada;
    private int asientosDisponibles;
    private List<Asiento> clases;

    public int getIdVuelo() {
        return idVuelo;
    }

    public String getAerolinea() {
        return aerolinea;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public Date getHoraLlegada() {
        return horaLlegada;
    }

    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }

    public List<Asiento> getClases() {
        return clases;
    }

    public Vuelo(int idVuelo, String aerolinea, Date horaSalida, Date horaLlegada, int asientosDisponibles, List<Asiento> clases) {
        this.idVuelo = idVuelo;
        this.aerolinea = aerolinea;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.asientosDisponibles = asientosDisponibles;
        this.clases = clases;
    }
    
    public void verificarDisponibilidad(){
    
    }
    
    public void bloquearAsientos(){
    
    }
    
    public void confirmarVuelo(){
    
    }
    
    public static List<Vuelo> cargarVuelosDesdeArchivo(String archivo) {
        List<Vuelo> vuelos = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",", 6);

                int idVuelo = Integer.parseInt(partes[0].trim());
                String aerolinea = partes[1].trim();
                Date horaSalida = sdf.parse(partes[2].trim());
                Date horaLlegada = sdf.parse(partes[3].trim());
                int asientosDisponibles = Integer.parseInt(partes[4].trim());

                String clasesTexto = partes[5].trim().replace("{", "").replace("}", "");
                List<Asiento> clases = crearListaAsientos(clasesTexto.split(";"));

                vuelos.add(new Vuelo(idVuelo, aerolinea, horaSalida, horaLlegada, asientosDisponibles, clases));
            }
        } catch (IOException | ParseException e) {
            System.err.println("Error al cargar vuelos: " + e.getMessage());
        }
        return vuelos;
    }
    
    private static List<Asiento> crearListaAsientos(String[] tipos) {
        List<Asiento> asientos = new ArrayList<>();
        for (String tipo : tipos) {
            String[] datos = tipo.split(":");
            String nombreClase = datos[0].trim();
            double precio = Double.parseDouble(datos[1].trim());

            switch (nombreClase) {
                case "Economico" -> asientos.add(new AsientoEconomico(asientos.size() + 1, precio));
                case "Ejecutivo" -> asientos.add(new AsientoEjecutivo(asientos.size() + 1, precio));
                case "PrimeraClase" -> asientos.add(new AsientoPrimeraClase(asientos.size() + 1, precio));
            }
        }
        return asientos;
    }

    @Override
    public String toString() {
        return idVuelo + ", " + aerolinea + ", " + horaSalida + ", " + horaLlegada + ", " + asientosDisponibles + ", " + clases;
    }
}
