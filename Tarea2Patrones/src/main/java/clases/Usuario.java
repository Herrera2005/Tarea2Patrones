/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author herreranc
 */
public class Usuario {
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

    public int getIdCedula() {
        return idCedula;
    }

    public void setIdCedula(int idCedula) {
        this.idCedula = idCedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    
}
