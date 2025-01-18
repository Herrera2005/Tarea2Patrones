package Servicio;

public class CoberturaRobo extends ServicioDecorator {

    public CoberturaRobo(Servicio servicio) {
        super(servicio);
    }

    @Override
    public String descripcion() {
        return servicio.descripcion() + " + Cobertura contra Robo";
    }

    @Override
    public int getCosto() {
        return servicio.getCosto() + 200; // Agrega un costo adicional
    }
}
