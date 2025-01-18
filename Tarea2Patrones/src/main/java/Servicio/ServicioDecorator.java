package Servicio;

public abstract class ServicioDecorator extends Servicio {
    protected Servicio servicio;

    public ServicioDecorator(Servicio servicio) {
        super(servicio.getCosto());
        this.servicio = servicio;
    }

    @Override
    public abstract String descripcion();

    @Override
    public int getCosto() {
        return servicio.getCosto();
    }
}
