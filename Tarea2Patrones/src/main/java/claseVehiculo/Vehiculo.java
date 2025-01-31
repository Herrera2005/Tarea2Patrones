package claseVehiculo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import clases.Reserva;

/**
 * Clase base para todos los vehículos.
 */
public abstract class Vehiculo {
    private int idVehiculo;
    private TipoVehiculoEnum tipoVehiculo;
    private ProveedorEnum proveedor;
    private boolean disponibilidad;
    private Reserva reserva;

    public Vehiculo(int idVehiculo, TipoVehiculoEnum tipoVehiculo, ProveedorEnum proveedor, boolean disponibilidad) {
        this.idVehiculo = idVehiculo;
        this.tipoVehiculo = tipoVehiculo;
        this.proveedor = proveedor;
        this.disponibilidad = disponibilidad;
    }

    public void reservarVehiculo(Reserva reserva) {
        if (disponibilidad) {
            disponibilidad = false;
            this.reserva = reserva;
        } else {
            System.out.println("Este vehículo ya se encuentra reservado");
        }
    }

    public void cancelarVehiculo(String mensaje) {
        reserva.quitarVehiculo(mensaje);
        reserva = null;
        disponibilidad = true;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public TipoVehiculoEnum getTipoVehiculo() {
        return tipoVehiculo;
    }

    public ProveedorEnum getProveedor() {
        return proveedor;
    }

    public boolean verificarDisponibilidad() {
        return disponibilidad;
    }

    public abstract void confirmarVehiculo();


    @Override
    public String toString() {
        return "🚗 Vehículo ID: " + getIdVehiculo() + "\n" +
                "   🔹 Tipo: " + getTipoVehiculo() + "\n" +
                "   🔹 Proveedor: " + getProveedor() + "\n" +
                "   🔹 Disponibilidad: " + (verificarDisponibilidad() ? "✅ Disponible" : "❌ No disponible");
    }
}
