/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author herreranc
 */
public class Administracion extends Usuario{

    public Administracion(int idCedula, String nombre, String email, String contrasenia) {
        super(idCedula, nombre, email, contrasenia);
    }
    
    public void getionarVuelos(Vuelo vuelo){
    
    }
    
    public void gestionarVehiculos(Vehiculo vehiculo){
    
    }
    
    public void modificarPoliticas(){
    
    }
    
    @Override
    public void actualizar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
