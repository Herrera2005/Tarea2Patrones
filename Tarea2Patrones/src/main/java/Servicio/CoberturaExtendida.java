package Servicio;

public class CoberturaExtendida extends Servicio {

    public CoberturaExtendida(int costo) {
        super(costo);
    }

    @Override
    public String descripcion() {
        return "Cobertura Extendida";
    }
}
