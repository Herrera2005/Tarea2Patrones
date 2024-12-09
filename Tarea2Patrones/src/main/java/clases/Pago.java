/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import enums.EstadoPago;

/**
 *
 * @author herreranc
 */
public class Pago {
    private double monto;
    private String metodoPago;
    private EstadoPago estadoPago;

    public Pago(double monto, String metodoPago, EstadoPago estadoPago) {
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.estadoPago = estadoPago;
    }
    
    public void procesarPago(){
    
    }
    
    public boolean validarPago(){
        return true;
    }
}
