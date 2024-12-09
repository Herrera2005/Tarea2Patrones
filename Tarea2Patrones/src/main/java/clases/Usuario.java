/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author herreranc
 */
public abstract class Usuario implements ObservadorReserva{
    private int idCedula;
    private String nombre;
    private String email;
    private String contrasenia;

    public Usuario(int idCedula, String nombre, String email, String contrasenia) {
        this.idCedula = idCedula;
        this.nombre = nombre;
        this.email = email;
        this.contrasenia = contrasenia;
    }
    
    public boolean iniciarSecion(){
        return true;
    }

    @Override
    public abstract void actualizar();
    
    
}
