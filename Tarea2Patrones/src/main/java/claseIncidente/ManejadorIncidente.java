/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package claseIncidente;

/**
 *
 * @author usuario
 */
public interface ManejadorIncidente {
    void setNextHandler(ManejadorIncidente nextHandler);
    void handleIncidente(Incidente incidente);
}
