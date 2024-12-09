/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package claseVehiculo;

/**
 *
 * @author herreranc
 */
public abstract class Vehiculo {
    private int idVehiculo;
    private String TipoVehiculo;
    private String Proveedor;
    private boolean disponibilidad;

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public String getTipoVehiculo() {
        return TipoVehiculo;
    }

    public String getProveedor() {
        return Proveedor;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public Vehiculo(int idVehiculo, String TipoVehiculo, String Proveedor, boolean disponibilidad) {
        this.idVehiculo = idVehiculo;
        this.TipoVehiculo = TipoVehiculo;
        this.Proveedor = Proveedor;
        this.disponibilidad = disponibilidad;
    }
    
    public boolean verificarDisponibilidad(){
        return true;
    }
    
    public abstract void confirmarVehiculo();
    
    
}
