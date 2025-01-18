package Servicio;

public class CoberturaIncendio extends ServicioDecorator {

    public CoberturaIncendio(Servicio servicio) {
        super(servicio);
    }

    @Override
    public String descripcion() {
        return servicio.descripcion() + " + Cobertura contra Incendio";
    }

    @Override
    public int getCosto() {
        return servicio.getCosto() + 150;
    }
}
