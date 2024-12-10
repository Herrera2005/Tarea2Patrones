/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package claseVehiculo;

/**
 *
 * @author usuario
 */
public class VehiculoEjecutivo extends Vehiculo{

    public VehiculoEjecutivo(int idVehiculo, String tipoVehiculo, String proveedor, boolean disponibilidad) {
        super(idVehiculo, tipoVehiculo, proveedor, disponibilidad);
    }
    
    @Override
    public void confirmarVehiculo() {
        System.out.println("Vehículo ejecutivo confirmado.");
    }
    
}
