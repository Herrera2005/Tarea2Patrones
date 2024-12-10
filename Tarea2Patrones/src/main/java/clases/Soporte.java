/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import claseIncidente.Incidente;
import claseIncidente.ManejadorIncidente;
import enums.EstadoIncidente;

/**
 *
 * @author usuario
 */
public class Soporte implements ManejadorIncidente {
    private ManejadorIncidente nextHandler;
    
    @Override
    public void setNextHandler(ManejadorIncidente nextHandler) {
        this.nextHandler = nextHandler;
    }
    
    @Override
    public void handleIncidente(Incidente incidente) {
        if (incidente.getEstado() == EstadoIncidente.Derivado) {
            System.out.println("Soporte manejando el incidente: " + incidente.getDescripcion());
            incidente.setEstado(EstadoIncidente.EnAnalisis);
            System.out.println("Incidente actualizado a estado EnAnalisis.");
        } else if (nextHandler != null) {
            nextHandler.handleIncidente(incidente);
        } else {
            System.out.println("Incidente no pudo ser manejado por ningún handler.");
        }
    }
    
    public boolean iniciarSesion() {
        System.out.println("Soporte iniciando sesión...");
        return true; 
    }
 }