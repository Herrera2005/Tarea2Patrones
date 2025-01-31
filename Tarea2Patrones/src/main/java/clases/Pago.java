package clases;

import enums.EstadoPago;
import clases.MetodoPago; // Importar el nuevo enum

public class Pago {
    private double monto;
    private MetodoPago metodoPago; // Cambiar de String a MetodoPago
    private EstadoPago estadoPago;

    // Constructor actualizado
    public Pago(double monto, MetodoPago metodoPago, EstadoPago estadoPago) {
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.estadoPago = estadoPago;
    }

    // Getters
    public double getMonto() {
        return monto;
    }

    public MetodoPago getMetodoPago() { // Cambiar el tipo de retorno a MetodoPago
        return metodoPago;
    }

    public EstadoPago getEstadoPago() {
        return estadoPago;
    }

    // Métodos de la clase
    public void procesarPago() {
        // Lógica para procesar el pago
    }

    public boolean validarPago() {
        // Lógica para validar el pago
        return true;
    }

    @Override
    public String toString() {
        return monto + "," +
                metodoPago + "," +
                estadoPago;
    }
}