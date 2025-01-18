package Servicio;

public abstract class Servicio {
    private int costo;
    
    public Servicio(int costo) {
        this.costo = costo;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public abstract String descripcion();
}
