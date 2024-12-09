/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package claseVuelo;

import claseVuelo.Asiento;
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
}
