package claseVuelo;

public class AsientoConcreto extends Asiento {
    public AsientoConcreto(int idClase, String tipo, double precio) {
        super(idClase, tipo, precio);
    }

    @Override
    public void verificarDisponibilidad() {
        System.out.println("Verificando disponibilidad del asiento...");
    }

    @Override
    public void bloquearAsiento() {
        disponible = false;
        System.out.println("Asiento bloqueado.");
    }

    @Override
    public void desbloquear() {
        disponible = true;
        System.out.println("Asiento desbloqueado.");
    }
}
