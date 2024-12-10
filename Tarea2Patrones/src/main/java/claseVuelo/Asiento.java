/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package claseVuelo;

/**
 *
 * @author usuario
 */
public abstract class Asiento {
    private int idClase;
    private String tipoClase;
    private double precio;

    public Asiento(int idClase, String tipoClase, double precio) {
        this.idClase = idClase;
        this.tipoClase = tipoClase;
        this.precio = precio;
    }
    
    public void modificarClase(){
    
    }
    
    @Override
    public String toString() {
        return tipoClase + " (" + precio + ")";
    }
    
    public abstract void verificarDisponibilidad();
    
    public abstract void bloquearAsiento();
    
    public abstract void desbloquear();
    
}
