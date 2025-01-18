package Servicio;

public class SeguroViaje extends Servicio {

    public SeguroViaje(int costo) {
        super(costo);
    }

    @Override
    public String descripcion() {
        return "Seguro de Viaje";
    }
}
