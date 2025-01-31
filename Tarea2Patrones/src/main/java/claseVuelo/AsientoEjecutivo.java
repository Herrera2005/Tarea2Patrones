package claseVuelo;

/**
 * Clase que representa un asiento ejecutivo sin métodos innecesarios.
 */
public class AsientoEjecutivo extends Asiento {

    public AsientoEjecutivo(int idClase, double precio) {
        super(idClase, "Ejecutivo", precio);
    }

    @Override
    public void verificarDisponibilidad() {

    }

    @Override
    public void bloquearAsiento() {

    }

    @Override
    public void desbloquear() {

    }

    // Se eliminan los métodos verificarDisponibilidad(), bloquearAsiento() y desbloquear()
    // porque no se usan y solo lanzaban excepciones sin implementación.
}
