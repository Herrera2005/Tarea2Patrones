/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package claseIncidente;

/**
 *
 * @author usuario
 */
public class Incidente {
    private int idIncidente;
    private String descripcion;
    private EstadoIncidente estado;

    public Incidente(int idIncidente, String descripcion, EstadoIncidente estado) {
        this.idIncidente = idIncidente;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    // Getters y setters
    public int getIdIncidente() {
        return idIncidente;
    }

    public void setIdIncidente(int idIncidente) {
        this.idIncidente = idIncidente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoIncidente getEstado() {
        return estado;
    }

    public void setEstado(EstadoIncidente estado) {
        this.estado = estado;
    }
}

