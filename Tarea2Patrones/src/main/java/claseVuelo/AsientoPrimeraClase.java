package claseVuelo;

public class AsientoPrimeraClase {
    private final Asiento asientoBase;

    public AsientoPrimeraClase(int idClase, double precio) {
        this.asientoBase = new AsientoConcreto(idClase, "PrimeraClase", precio);
    }

    public double getPrecio() {
        return asientoBase.getPrecio();
    }


}
