package claseVuelo;

public abstract class Asiento {
    protected int idClase;
    protected String tipo;
    protected double precio;
    protected boolean disponible = true;

    public Asiento(int idClase, String tipo, double precio) {
        this.idClase = idClase;
        this.tipo = tipo;
        this.precio = precio;
    }

    public abstract void verificarDisponibilidad();
    public abstract void bloquearAsiento();
    public abstract void desbloquear();

    public double getPrecio() {
        return precio;
    }
}
