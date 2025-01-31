package Notificaciones;

import clases.Cliente;

public interface Notificacion {
    
    public void notificar(String mensaje, Cliente cliente);
    public String getMensaje();
}
